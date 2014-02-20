package br.edu.ifes.poo1.cln.cdp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifes.poo1.cln.cdp.pecas.Peao;
import br.edu.ifes.poo1.cln.cdp.pecas.Peca;

public abstract class Jogador implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Nome do jogador. */
	protected String nome;

	/** Cor do jogador. */
	protected TipoCorJogador cor;

	/** Peças que o jogador já capturou. */
	List<Peca> pecasCapturadas = new ArrayList<Peca>();

	/** Tabuleiro no qual está jogando. */
	protected TabuleiroXadrez tabuleiro;

	/** Tipo de jogador */
	protected TipoJogador tipoJogador;

	/**
	 * Constrói um jogador.
	 * 
	 * @param nome
	 *            Nome do jogador.
	 */
	public Jogador(String nome, TipoCorJogador cor, TipoJogador tipoJogador) {
		this.nome = nome;
		this.cor = cor;
		this.tipoJogador = tipoJogador;
	}

	/**
	 * Faz a movimentação de uma peça e também as verificações necessárias.
	 * 
	 * @param jogada
	 *            Jogada que deve ser aplicada.
	 * @throws JogadaInvalidaException
	 * @throws CasaOcupadaException
	 */
	public void executarJogada(Jogada jogada) throws JogadaInvalidaException,
			CasaOcupadaException {
		switch (jogada.getTipoJogada()) {
		// Se for um roque menor, o executa.
		case ROQUE_MENOR:
			// Verifica se pode fazer o Roque Menor.
			if (tabuleiro.ehRoqueMenor(this.getCor()))
				// ... e o faz.
				aplicarRoqueMenor();
			else
				throw new JogadaInvalidaException(
						"Não é possível realizar o roque menor.");
			return;

		case ROQUE_MAIOR:
			// Verifica se pode fazer o Roque Maior.
			if (tabuleiro.ehRoqueMaior(this.getCor()))
				// ... e o faz.
				aplicarRoqueMaior();
			else
				throw new JogadaInvalidaException(
						"Não é possível realizar o roque maior.");
			return;
		default:
			break;
		}

		// Para as outras jogadas, é necessário saber o que há na origem e no
		// destino.
		Peca pecaOrigem = tabuleiro.espiarPeca(jogada.getOrigem());
		Peca pecaDestino = tabuleiro.espiarPeca(jogada.getDestino());

		// Verifica se há uma peça na origem.
		if (pecaOrigem == null)
			throw new JogadaInvalidaException(
					"Não há uma peça na origem do movimento.");

		// E verifica se a peça em origem é do jogador
		if (pecaOrigem.getCorJogador() != this.getCor())
			throw new JogadaInvalidaException(
					"A peça que você está tentando mover não é sua.");

		// Se a jogada for uma promoção de peão, na origem do movimento deve
		// estar um peão e o destino deve ser na linha do lado oposto ao de
		// início das peças do jogador.
		// Faz as verificações relevantes a promoção dos peões.
		if (jogada.ehPromocao()) {
			// Sendo uma promoção, na origem deve estar um peão.
			if (pecaOrigem.getTipoPeca() != TipoPeca.PEAO) {
				throw new JogadaInvalidaException(
						"Só pode haver promoção, se a peça de origem for um peão.");
			}

			// E este peão deve estar se movimentando para a última linha do
			// tabuleiro.
			Posicao destino = jogada.getDestino();
			if ((cor == TipoCorJogador.BRANCO && destino.getLinha() != 8)
					|| (cor == TipoCorJogador.PRETO && destino.getLinha() != 1)) {
				throw new JogadaInvalidaException(
						"Para haver uma promoção, o peão deve estar se movimentando para a última linha do lado oposto do tabuleiro.");
			}
		}

		// Faz as verificações para o ataque
		if (jogada.getTipoJogada() == TipoJogada.ATACAR) {
			// Se a peça for um peão
			if (tabuleiro.ehEnPassantEsquerda(jogada.getOrigem())) {
				jogada.setTipoJogada(TipoJogada.EN_PASSANT_ESQUERDA);
				aplicarEnPassantEsquerda(jogada, this.getCor());
				return;
			} else if (tabuleiro.ehEnPassantDireita(jogada.getOrigem())) {
				jogada.setTipoJogada(TipoJogada.EN_PASSANT_DIREITA);
				aplicarEnPassantDireita(jogada, this.getCor());
				return;
			} else {

				// Se não houver peça no destino e for um ataque, a jogada é
				// inválida.
				if (pecaDestino == null)
					throw new JogadaInvalidaException(
							"Não há peça para ser atacada, na casa indicada.");

				// E a peça sendo atacada não pode pertencer ao jogador.
				if (pecaDestino.getCorJogador() == this.getCor())
					throw new JogadaInvalidaException(
							"A peça que você está tentando atacar é sua!");

				// Além disso verifica se a peça pode atacar ali.
				if (!pecaOrigem.podeAtacar(jogada.getOrigem(),
						jogada.getDestino(), tabuleiro))
					throw new JogadaInvalidaException(
							"A peça não consegue atacar a casa indicada.");

				// Remove a peça do destino e acrescenta a lista de peças
				// capturadas, já que se trata de um ataque.
				this.pecasCapturadas.add(tabuleiro.retirarPeca(jogada
						.getDestino()));
			}
		}

		// Faz as verificações para o andar
		if (jogada.getTipoJogada() == TipoJogada.ANDAR) {
			// O destino deve estar livre, se a jogada não for um ataque.
			if (pecaDestino != null)
				throw new JogadaInvalidaException(
						"A casa, para a qual você está tentando mover, está ocupada por outra peça.");

			// E a peça deve ser capaz de andar para ali.
			if (!pecaOrigem.podeAndar(jogada.getOrigem(), jogada.getDestino(),
					tabuleiro))
				throw new JogadaInvalidaException(
						"A peça não consegue andar até a casa indicada.");
		}

		// Move a peça para o destino.
		Peca pecaRetirada = tabuleiro.retirarPeca(jogada.getOrigem());
		pecaRetirada.setJaMoveu(); // Marca a peça como já movida.
		// Se peça for um peão
		if (pecaRetirada.getTipoPeca() == TipoPeca.PEAO) {
			Peao peao = (Peao) pecaRetirada;
			peao.verificaEnPassant(jogada);
		}

		try {
			tabuleiro.colocarPeca(jogada.getDestino(), pecaRetirada);
		} catch (CasaOcupadaException e) {
			// A excessão acima não deveria ter sido lançada em hipótese alguma,
			// pois as verificações com respeito ao destino já foram feitas. Se
			// ela for lançada, o código deve ser revisto.
			throw new JogadaInvalidaException(
					"Erro gravíssimo. Entre em contato com os administradores do sistema! :)");
		}
	}

	/**
	 * Aplica a jogada de Roque Maior.
	 * 
	 * @throws JogadaInvalidaException
	 */
	private void aplicarRoqueMaior() throws JogadaInvalidaException {
		// Pega a linha em que o roque deve ser feito.
		int linha = getMinhaLinhaDeRoque();

		// Executa o roque.
		Peca torre = tabuleiro.retirarPeca(new Posicao(1, linha));
		Peca rei = tabuleiro.retirarPeca(new Posicao(5, linha));
		try {
			tabuleiro.colocarPeca(new Posicao(3, linha), rei);
			tabuleiro.colocarPeca(new Posicao(4, linha), torre);
		} catch (CasaOcupadaException e) {
			throw new JogadaInvalidaException(
					"O caminho para fazer o Roque Maior não está livre.");
		}
	}

	/**
	 * Aplica a jogada de Roque Menor.
	 * 
	 * @throws JogadaInvalidaException
	 */
	private void aplicarRoqueMenor() throws JogadaInvalidaException {
		// Pega a linha em que o roque deve ser feito.
		int linha = getMinhaLinhaDeRoque();

		// Executa o roque.
		Peca torre = tabuleiro.retirarPeca(new Posicao(8, linha));
		Peca rei = tabuleiro.retirarPeca(new Posicao(5, linha));
		try {
			tabuleiro.colocarPeca(new Posicao(7, linha), rei);
			tabuleiro.colocarPeca(new Posicao(6, linha), torre);
		} catch (CasaOcupadaException e) {
			throw new JogadaInvalidaException(
					"O caminho para fazer o Roque Menor não está livre.");
		}
	}

	/**
	 * 
	 * @param jogada
	 * @param corJogador
	 * @param copiaTabuleiro
	 * @param tabuleiroNovo
	 * @return
	 * @throws CasaOcupadaException
	 */
	private void aplicarEnPassantEsquerda(Jogada jogada,
			TipoCorJogador corJogador) throws CasaOcupadaException {
		Peca peca = tabuleiro.espiarPeca(jogada.getOrigem());
		// Retirar a peça de sua posição
		tabuleiro.retirarPeca(jogada.getOrigem());
		// Retirar a peça inimiga à esquerda e acrescentar a lista de peças capturadas.
		Peca pecaCapturada = tabuleiro.retirarPeca(new Posicao(jogada.getOrigem().getColuna() - 1,
				jogada.getOrigem().getLinha()));
		this.pecasCapturadas.add(pecaCapturada);
		// Se o en passant for favorável as peças brancas
		if (corJogador == TipoCorJogador.BRANCO)
			tabuleiro.colocarPeca(new Posicao(
					jogada.getOrigem().getColuna() - 1, jogada.getOrigem()
							.getLinha() + 1), peca);
		// Se en passant for favorável as peças pretas
		else
			tabuleiro.colocarPeca(new Posicao(
					jogada.getOrigem().getColuna() - 1, jogada.getOrigem()
							.getLinha() - 1), peca);
	}

	/**
	 * 
	 * @param jogada
	 * @param corJogador
	 * @param copiaTabuleiro
	 * @param tabuleiro
	 * @return
	 * @throws CasaOcupadaException
	 */
	private void aplicarEnPassantDireita(Jogada jogada,
			TipoCorJogador corJogador) throws CasaOcupadaException {
		Peca peca = tabuleiro.espiarPeca(jogada.getOrigem());
		// Retirar a peça de sua posição
		tabuleiro.retirarPeca(jogada.getOrigem());
		// Retirar a peça inimiga à esquerda e acrescentar a lista de peças capturadas.
		Peca pecaCapturada = tabuleiro.retirarPeca(new Posicao(jogada.getOrigem().getColuna() + 1,
				jogada.getOrigem().getLinha()));
		this.pecasCapturadas.add(pecaCapturada);
		// Se o en passant for favorável as peças brancas
		if (corJogador == TipoCorJogador.BRANCO)
			tabuleiro.colocarPeca(new Posicao(
					jogada.getOrigem().getColuna() + 1, jogada.getOrigem()
							.getLinha() + 1), peca);
		// Se en passant for favorável as peças pretas
		else
			tabuleiro.colocarPeca(new Posicao(
					jogada.getOrigem().getColuna() + 1, jogada.getOrigem()
							.getLinha() - 1), peca);
	}

	/**
	 * Devolve a linha em que o jogador faz as jogadas de roque.
	 * 
	 * @return Linha onde é executada o roque do jogador.
	 */
	private int getMinhaLinhaDeRoque() {
		if (cor == TipoCorJogador.BRANCO)
			return 1;
		else
			return 8;
	}

	/**
	 * Acrescenta uma peça, a lista de peças capturadas pelo jogador.
	 * 
	 * @param peca
	 *            Peça capturada.
	 */
	public void acrescentarPecaCapturada(Peca peca) {
		pecasCapturadas.add(peca);
	}

	public String getNome() {
		return nome;
	}

	public List<Peca> getPecasCapturadas() {
		return pecasCapturadas;
	}

	public TipoCorJogador getCor() {
		return cor;
	}

	/** O tabuleiro só deve ser alterado, antes do início da partida. */
	public void setTabuleiro(TabuleiroXadrez tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	public TipoJogador getTipoJogador() {
		return this.tipoJogador;
	}
}
