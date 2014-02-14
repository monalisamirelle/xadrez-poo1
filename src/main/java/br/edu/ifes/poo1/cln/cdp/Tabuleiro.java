package br.edu.ifes.poo1.cln.cdp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Um tabuleiro é composto por 64 casas, estas podem estar ocupadas por uma peça
 * ou vazias.
 */
/**
 * @author T
 * 
 */
public class Tabuleiro implements TamanhoTabuleiro, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Casas do tabuleiro. Com colunas e linhas variando de 0 a 7. Mas deve
	 * haver um controle interno para que quando as outras classes forem se
	 * comunicar com o tabuleiro, enxerguem as linhas e colunas como de 1 a 8. O
	 * primeiro índice da matriz é a coluna e em seguida a linha, igual no
	 * xadrez.
	 */
	private Peca[][] pecas;

	/**
	 * Inicia um tabuleiro vazio, sem peça alguma.
	 */
	public Tabuleiro() {
		pecas = new Peca[8][8];
	}

	/**
	 * Inicia um tabuleiro padrão de xadrez, com as peças já posicionadas e com
	 * suas referências para os jogadores corretos.
	 * 
	 * @param brancas
	 *            .getCor() Jogador que controla as peças brancas.
	 * @param pretas
	 *            .getCor() Jogador que controla as peças pretas.
	 * @throws CasaOcupadaException
	 */
	public Tabuleiro(Jogador brancas, Jogador pretas) {
		// Inicia o tabuleiro sem nenhuma peça.
		this();

		// Posiciona as peças.
		try {
			// Posiciona as peças brancas, exceto os peões.
			this.colocarPeca(new Posicao(1, 1), new Torre(brancas.getCor()));
			this.colocarPeca(new Posicao(2, 1), new Cavalo(brancas.getCor()));
			this.colocarPeca(new Posicao(3, 1), new Bispo(brancas.getCor()));
			this.colocarPeca(new Posicao(4, 1), new Rainha(brancas.getCor()));
			this.colocarPeca(new Posicao(5, 1), new Rei(brancas.getCor()));
			this.colocarPeca(new Posicao(6, 1), new Bispo(brancas.getCor()));
			this.colocarPeca(new Posicao(7, 1), new Cavalo(brancas.getCor()));
			this.colocarPeca(new Posicao(8, 1), new Torre(brancas.getCor()));

			// Posiciona os peões brancos.
			for (int coluna = COLUNAINFERIOR; coluna <= COLUNASUPERIOR; coluna++) {
				this.colocarPeca(new Posicao(coluna, 2),
						new Peao(brancas.getCor()));
			}

			// Posiciona as peças brancas, exceto os peões.
			this.colocarPeca(new Posicao(1, 8), new Torre(pretas.getCor()));
			this.colocarPeca(new Posicao(2, 8), new Cavalo(pretas.getCor()));
			this.colocarPeca(new Posicao(3, 8), new Bispo(pretas.getCor()));
			this.colocarPeca(new Posicao(4, 8), new Rainha(pretas.getCor()));
			this.colocarPeca(new Posicao(5, 8), new Rei(pretas.getCor()));
			this.colocarPeca(new Posicao(6, 8), new Bispo(pretas.getCor()));
			this.colocarPeca(new Posicao(7, 8), new Cavalo(pretas.getCor()));
			this.colocarPeca(new Posicao(8, 8), new Torre(pretas.getCor()));

			// Posiciona os peões pretos.
			for (int coluna = COLUNAINFERIOR; coluna <= COLUNASUPERIOR; coluna++) {
				this.colocarPeca(new Posicao(coluna, 7),
						new Peao(pretas.getCor()));
			}
		} catch (CasaOcupadaException e) {
			// Se a excessão for lançada, o construtor está tentando posicionar
			// peças onde já há alguma e deve ser refeito.
			e.printStackTrace();
		}
	}

	/**
	 * Espia a peça que está na posição indicada. Se não houver peça na posição
	 * indicada, será retornado 'null'.
	 * 
	 * @param posicao
	 *            Posição do tabuleiro aonde deseja-se espiar.
	 * @return A peça que ocupa aquele posição. Ou 'null', se não houver peça
	 *         ali.
	 */
	public Peca espiarPeca(Posicao posicao) {
		return pecas[posicao.getColuna() - 1][posicao.getLinha() - 1];
	}

	/**
	 * Retira a peça da posição indicada e tal peça é retornada. Se não houver
	 * qualquer peça no local indicado, nada será feito e será retornado 'null'.
	 * 
	 * @param posicao
	 *            Posição da qual deseja-se retirar a peça.
	 * @return A peça que "outrora" ocupava a posição indicada. Ou 'null' caso
	 *         não haja peça na posição indicada.
	 */
	public Peca retirarPeca(Posicao posicao) {
		Peca peca = pecas[posicao.getColuna() - 1][posicao.getLinha() - 1];
		pecas[posicao.getColuna() - 1][posicao.getLinha() - 1] = null;
		return peca;
	}

	/**
	 * Posiciona uma peça no tabuleiro. Se o lugar já estiver ocupado, lança uma
	 * excessão.
	 * 
	 * @param posicao
	 *            Local onde a peça será colocada.
	 * @param peca
	 *            Peça que será posicionada no tabuleiro.
	 * @throws CasaOcupadaException
	 */
	public void colocarPeca(Posicao posicao, Peca peca)
			throws CasaOcupadaException {
		// Não é possível posicionar a peça se a casa estiver ocupada.
		if (pecas[posicao.getColuna() - 1][posicao.getLinha() - 1] != null)
			throw new CasaOcupadaException();

		// Coloca a peça na posição indicada.
		pecas[posicao.getColuna() - 1][posicao.getLinha() - 1] = peca;
	}

	/**
	 * Indica se uma determinada posição do tabuleiro está vazia ou não.
	 * 
	 * @param posicao
	 *            Posição no tabuleiro.
	 * @return Se está vazio.
	 */
	public boolean estaVazio(Posicao posicao) {
		// Está vazio quando não houver peça ali.
		return espiarPeca(posicao) == null;
	}

	/**
	 * Indica se uma determinada posição do tabuleiro está ocupada por um aliado
	 * ou não.
	 * 
	 * @param posicao
	 *            Posição no tabuleiro.
	 * @return Se está vazio.
	 */
	public boolean estaAliado(TipoCorJogador corJogador, Posicao destino) {
		if (this.estaVazio(new Posicao(destino.getColuna(), destino.getLinha())) == false)
			if (this.espiarPeca(
					new Posicao(destino.getColuna(), destino.getLinha()))
					.getCorJogador() == corJogador)
				return true;
		return false;
	}

	/**
	 * Indica se uma determinada posição do tabuleiro está ocupada por um
	 * inimigo ou não.
	 * 
	 * @param posicao
	 *            Posição no tabuleiro.
	 * @return Se está vazio.
	 */
	public boolean estaInimigo(TipoCorJogador corJogador, Posicao destino) {
		if (this.estaVazio(new Posicao(destino.getColuna(), destino.getLinha())) == false)
			if (this.espiarPeca(
					new Posicao(destino.getColuna(), destino.getLinha()))
					.getCorJogador() != corJogador)
				return true;
		return false;
	}

	/**
	 * Verifica se a posição indicada está fora dos limites do tabuleiro.
	 * 
	 * @param posicao
	 *            Posição a ser verificada.
	 * @return Se a posição está fora do tabuleiro (true), ou dentro (false).
	 */
	public static boolean estaForaDoTabuleiro(Posicao posicao) {
		if (posicao.getLinha() >= LINHAINFERIOR
				& posicao.getLinha() <= LINHASUPERIOR
				& posicao.getColuna() >= COLUNAINFERIOR
				& posicao.getColuna() <= COLUNASUPERIOR)
			return false;
		else
			return true;
	}

	/**
	 * Método que captura o sentido de uma peça em uma determinada direção
	 * (vertical/horizontal)
	 * 
	 * @param direcaoOrigem
	 * @param direcaoDestino
	 * @return
	 */
	public int sentidoPeca(int direcaoOrigem, int direcaoDestino) {
		return (int) Math.signum(direcaoDestino - direcaoOrigem);
	}

	/**
	 * Verifica se a peça pode se movimentar até o local desejado
	 * 
	 * @param origem
	 *            Onde a peça se encontra
	 * @param destino
	 *            Onde a peça deseja chegar
	 * @return se a peça pode realizar movimento ou não
	 */
	public boolean podeRealizarMovimentacao(Posicao origem, Posicao destino) {
		int linha = origem.getLinha();
		int coluna = origem.getColuna();
		int sentidoHorizontal = sentidoPeca(origem.getLinha(),
				destino.getLinha());
		int sentidoVertical = sentidoPeca(origem.getColuna(),
				destino.getColuna());
		while ((linha != destino.getLinha() || coluna != destino.getColuna())) {
			// Se não tivermos chegado na posição
			linha = linha + sentidoHorizontal;
			coluna = coluna + sentidoVertical;
			if (estaForaDoTabuleiro(new Posicao(coluna, linha)) == true) {
				return false;
			}
			if (!(linha == destino.getLinha() && coluna == destino.getColuna())) {
				// Se a posição no tabuleiro não for nula, informe que o
				// movimento é proibido
				if (!this.estaVazio(new Posicao(coluna, linha))) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Verifica se o roque é válido, dado suas posições
	 * 
	 * @param posicaoRei
	 *            Posição que o rei se encontra
	 * @param posicaoTorre
	 *            Posição que a torre se encontra
	 * @param posicaoDesejadaRei
	 *            Posição que o o rei deseja ocupar
	 * @param posicaoDesejadaTorre
	 *            Posição que a torre deseja ocupar
	 * @return A verificação se as peças já se moveram e se as casas desejadas
	 *         estão vazias
	 */
	private boolean verificaRoque(Posicao posicaoRei, Posicao posicaoTorre,
			Posicao posicaoDesejadaRei, Posicao posicaoDesejadaTorre) {
		// Se o rei se encontra onde realmente deveria e a torre se encontra
		// onde realmente deveria
		if (this.espiarPeca(posicaoRei) != null
				&& this.espiarPeca(posicaoTorre) != null)
			// Se o rei e a torre ainda não se moveram
			if (this.espiarPeca(posicaoRei).getJaMoveu() == false
					&& this.espiarPeca(posicaoTorre).getJaMoveu() == false)
				// Se as casas que serão ocupadas pelo rei e a torre estão
				// vazias
				if (this.estaVazio(posicaoDesejadaRei) == true
						&& this.estaVazio(posicaoDesejadaTorre) == true)
					return true;

		return false;
	}

	/**
	 * Verifica se pode ser realizado o roque menor
	 * 
	 * @param jogador
	 *            Jogador que quer realizar o roque menor
	 * @return Se o roque foi possivel ou não
	 */
	public boolean ehRoqueMenor(TipoCorJogador corJogador) {
		// Se for um roque menor do jogador de peças brancas
		if (corJogador == TipoCorJogador.BRANCO)
			return verificaRoque(new Posicao(5, 1), new Posicao(8, 1),
					new Posicao(7, 1), new Posicao(6, 1));
		// Se for um roque menor do jogador de peças pretas
		else {
			return verificaRoque(new Posicao(5, 8), new Posicao(8, 8),
					new Posicao(7, 8), new Posicao(6, 8));
		}
	}

	/**
	 * Verifica se pode ser realizado o roque maior
	 * 
	 * @param jogador
	 *            Jogador que quer realizar o roque maior
	 * @return se o roque foi possivel ou não
	 */
	public boolean ehRoqueMaior(TipoCorJogador corJogador) {
		// Se for um roque maior do jogador de peças brancas
		if (corJogador == TipoCorJogador.BRANCO)
			return verificaRoque(new Posicao(5, 1), new Posicao(1, 1),
					new Posicao(3, 1), new Posicao(4, 1));
		// Se for um roque maior do jogador de peças pretas
		else
			return verificaRoque(new Posicao(5, 8), new Posicao(1, 8),
					new Posicao(3, 8), new Posicao(4, 8));
	}

	/**
	 * Verifica se o enPassant esquerda é válido
	 * 
	 * @param cor
	 *            Cor do rei
	 * @return Posições ao redor do rei.
	 */
	public boolean ehEnPassantEsquerda(Posicao posicaoPeca) {
		Posicao esquerda = new Posicao(posicaoPeca.getColuna() - 1,
				posicaoPeca.getLinha());
		return ehEnPassant(posicaoPeca, esquerda);
	}

	/**
	 * Verifica se o enPassant direita é válido
	 * 
	 * @param cor
	 *            Cor do rei
	 * @return Posições ao redor do rei.
	 */
	public boolean ehEnPassantDireita(Posicao posicaoPeca) {
		Posicao direita = new Posicao(posicaoPeca.getColuna() + 1,
				posicaoPeca.getLinha());
		return ehEnPassant(posicaoPeca, direita);
	}

	/**
	 * Verifica se o enPassant válido
	 * 
	 * @param cor
	 *            Cor do rei
	 * @return Posições ao redor do rei.
	 */
	public boolean ehEnPassant(Posicao posicaoPeca, Posicao lado) {
		if (this.espiarPeca(posicaoPeca).getTipoPeca() == TipoPeca.PEAO) {
			// Se a posição não se encontra fora do tabuleiro
			if (!estaForaDoTabuleiro(lado))
				// Se a posicao tem uma peça inimiga
				if (this.estaInimigo(this.espiarPeca(posicaoPeca)
						.getCorJogador(), lado))
					// Se a peça inimiga é um peão
					if (this.espiarPeca(lado).getTipoPeca() == TipoPeca.PEAO) {
						Peao peaoInimigo = (Peao) this.espiarPeca(lado);
						// Se o peão em questão pode sofre um en passant
						if (peaoInimigo.isPodeEnPassant() == true)
							return true;
					}
		}
		return false;
	}

	/**
	 * Reseta o atributo "podeEnPassant"
	 * 
	 * @param corJogador
	 */
	public void resetaPodeEnPassant(TipoCorJogador corJogador) {
		for (int linha = LINHAINFERIOR; linha <= LINHASUPERIOR; linha++)
			for (int coluna = COLUNAINFERIOR; coluna <= COLUNASUPERIOR; coluna++)
				if (this.estaAliado(corJogador, new Posicao(coluna, linha)))
					if (this.espiarPeca(new Posicao(coluna, linha))
							.getTipoPeca() == TipoPeca.PEAO) {
						Peao peao = (Peao) this.espiarPeca(new Posicao(coluna,
								linha));
						peao.setPodeEnPassant(false);
					}
	}

	/**
	 * Função que verifica se uma promoção é válida
	 * 
	 * @param peca
	 * @return
	 * @throws CasaOcupadaException
	 */
	public boolean ehPromocao(Posicao posicaoPeca) throws CasaOcupadaException {
		// Se existe alguma peça na casa
		if (!this.estaVazio(posicaoPeca)) {
			// Se a peça é um peão
			if (this.espiarPeca(posicaoPeca).getTipoPeca() == TipoPeca.PEAO) {
				Peao peao = (Peao) this.espiarPeca(posicaoPeca);
				// Se a peça é branca
				if (this.espiarPeca(posicaoPeca).getCorJogador() == TipoCorJogador.BRANCO) {
					// Se a peça se encontra na linha 7
					if (posicaoPeca.getLinha() == 7)
						if (peao.podeAndar(posicaoPeca,
								new Posicao(posicaoPeca.getColuna(), 8), this)
								|| peao.podeAtacar(posicaoPeca, new Posicao(
										posicaoPeca.getColuna() - 1, 8), this)
								|| peao.podeAtacar(posicaoPeca, new Posicao(
										posicaoPeca.getColuna() + 1, 8), this))

							return true;
					// Se a peça é preta
				} else {
					// Se a peça se encontra na linha 2
					if (posicaoPeca.getLinha() == 2)
						if (peao.podeAndar(posicaoPeca,
								new Posicao(posicaoPeca.getColuna(), 1), this)
								|| peao.podeAtacar(posicaoPeca, new Posicao(
										posicaoPeca.getColuna() - 1, 1), this)
								|| peao.podeAtacar(posicaoPeca, new Posicao(
										posicaoPeca.getColuna() + 1, 1), this))
							return true;
				}
			}
		}

		return false;
	}

	/**
	 * Indica se o rei está realizando uma jogada que cuminará em sua captura na
	 * próxima jogada inimiga
	 * 
	 * @param posicaoAtual
	 * @param posicaoDesejada
	 * @param corPecaInimiga
	 * @return
	 * @throws CasaOcupadaException
	 */
	public boolean jogadaSuicida(Posicao posicaoAtual, Posicao posicaoDesejada,
			TipoCorJogador corPecaInimiga) throws CasaOcupadaException {

		TipoCorJogador corJogador = TipoCorJogador.getCorOposta(corPecaInimiga);

		// Vemos a peça que pode ser ameaçada
		Peca pecaAmeacada = this.espiarPeca(posicaoAtual);
		if (this.estaInimigo(corJogador, posicaoDesejada)
				|| this.estaVazio(posicaoDesejada)) {

			// Vemos se existe uma peça na casa desejada atualmente
			Peca pecaPossivel = this.espiarPeca(posicaoDesejada);

			// Modificamos o tambuleiro para verificar se está ameaçado
			this.retirarPeca(posicaoAtual);
			if (this.estaInimigo(corJogador, posicaoDesejada))
				this.retirarPeca(posicaoDesejada);
			this.colocarPeca(posicaoDesejada, pecaAmeacada);

			// Verificamos se está ameaçado
			boolean estaAmeacado = estaAmeacadoPor(posicaoDesejada,
					corPecaInimiga);

			// Retornamos o tabuleiro ao estado anterior
			this.retirarPeca(posicaoDesejada);
			this.colocarPeca(posicaoAtual, pecaAmeacada);
			if (pecaPossivel != null)
				this.colocarPeca(posicaoDesejada, pecaPossivel);

			// Informamos se o rei ficará ameaçado ao realizar o movimento
			return estaAmeacado;
		}
		return true;
	}

	/**
	 * Indica se alguma peça da cor indicada ameaça a posição indicada
	 * 
	 * @param posicaoObjetivo
	 *            Posição a ser verificada.
	 * @param corPecaInimiga
	 *            Cor do jogador que pode estar ameaçando a posição.
	 * @return Se a o jogador da cor indicada está ameçando a posição com alguma
	 *         peça.
	 * @throws CasaOcupadaException
	 */
	public boolean estaAmeacadoPor(Posicao posicaoDesejada,
			TipoCorJogador corPecaInimiga) throws CasaOcupadaException {
		// Verifica se há alguma peça da cor indicada que ameaça a posição
		// indicada.
		for (int coluna = COLUNAINFERIOR; coluna <= COLUNASUPERIOR; coluna++) {
			for (int linha = LINHAINFERIOR; linha <= LINHASUPERIOR; linha++) {
				Posicao origem = new Posicao(coluna, linha);

				// Pula as casas vazias.
				if (estaVazio(origem))
					continue;

				// Pega a peça na casa varrida.
				Peca peca = espiarPeca(origem);

				// Pula as peças que não forem da cor indicada.
				if (peca.getCorJogador() != corPecaInimiga)
					continue;

				// Verifica se a peça pode atacar a posição indicada.
				if (peca.podeAtacar(origem, posicaoDesejada, this))
					return true;
			}
		}

		// Caso não haja peça que possa ameaçar a posição, retorna falso.
		return false;
	}

	/**
	 * Encontra a posição no tabuleiro em que o rei da cor indicada está. Se o
	 * rei não for encontrado (o que é impossível numa partida de xadrez), o
	 * método retorna 'null'.
	 * 
	 * @param cor
	 *            Cor do rei a procurar.
	 * @return Onde está o rei.
	 */
	public Posicao encontrarRei(TipoCorJogador cor) {
		// Varre o tabuleiro procurando o rei da cor indicada.
		for (int coluna = COLUNAINFERIOR; coluna <= COLUNASUPERIOR; coluna++) {
			for (int linha = LINHAINFERIOR; linha <= LINHASUPERIOR; linha++) {
				// Forma a posição que estamos a verificar.
				Posicao posicao = new Posicao(coluna, linha);

				// Pula se não houver peça ali.
				if (estaVazio(posicao))
					continue;

				// Pega a peça que está ali.
				Peca peca = espiarPeca(posicao);

				// Verifica se é o rei da cor indicada. E retorna-o.
				if (peca.getTipoPeca() == TipoPeca.REI
						&& peca.getCorJogador() == cor) {
					return posicao;
				}
			}
		}

		// Se o rei não for encontrado (o que não acontece em uma partida de
		// xadrez), retorna 'null'.
		return null;
	}

	/**
	 * Verifica se o rei da cor indicada está em Xeque.
	 * 
	 * @param cor
	 *            Cor do rei.
	 * @return Se a o rei está em Xeque.
	 * @throws CasaOcupadaException
	 */
	public boolean verificarXeque(TipoCorJogador cor)
			throws CasaOcupadaException {
		// Encontra a posição do rei.
		Posicao posicaoRei = encontrarRei(cor);
		// Retorna se a posição do rei está ameaçadado.
		return estaAmeacadoPor(posicaoRei, TipoCorJogador.getCorOposta(cor));
	}

	/**
	 * Verifica se houve Xeque Mate na cor indicada.
	 * 
	 * @param cor
	 *            Cor do rei que está em Xeque Mate.
	 * @return Se a cor indicada sofreu Xeque Mate.
	 * @throws CasaOcupadaException
	 * @throws JogadaInvalidaException
	 */
	public boolean verificarXequeMate(TipoCorJogador cor)
			throws CasaOcupadaException, JogadaInvalidaException {
		List<Estado> estadosPossiveis = this.proximosEstadosPossiveis(cor);
		if (!estadosPossiveis.isEmpty())
			return false;
		return true;
	}

	/**
	 * Método que armazena, dado um tabuleiro, os próximos estados possíveis
	 * para o tabuleiro
	 * 
	 * @param corJogador
	 * @return
	 * @throws CasaOcupadaException
	 * @throws JogadaInvalidaException
	 */
	public List<Estado> proximosEstadosPossiveis(TipoCorJogador corJogador) throws CasaOcupadaException {

		// Primeiramente, cria uma cópia do tabuleiro para não atrapalhar o
		// tabuleiro atual
		Tabuleiro copiaTabuleiro = this.tabuleiroClonado();

		// Em seguida, reseta o estado de en passant do jogador
		copiaTabuleiro.resetaPodeEnPassant(corJogador);

		List<Jogada> possiveisJogadas = geraJogadasPossiveis(corJogador);

		List<Estado> proximosEstados = new ArrayList<Estado>();
		Peca peca = null;

		// Para cada jogada realizada pela peça, gere um novo
		// tabuleiro e or armazene na lista de tabuleiros
		for (Jogada jogada : possiveisJogadas) {
			Tabuleiro tabuleiroNovo = copiaTabuleiro.tabuleiroClonado();

			// Verifica o tipo de jogada e gera o tabuleiro correto
			switch (jogada.getTipoJogada()) {
			case ANDAR:
				peca = this.espiarPeca(jogada.getOrigem());
				tabuleiroNovo.retirarPeca(jogada.getOrigem());
				// Se for Promoção
				if (jogada.ehPromocao()) {
					Peca rainha = new Rainha(corJogador);
					rainha.setJaMoveu();
					tabuleiroNovo.colocarPeca(jogada.getDestino(), rainha);
				}
				// Se a peça pode ser vítima de en passant
				else if (peca.getTipoPeca() == TipoPeca.PEAO
						&& peca.deslocamentoPeca(jogada.getOrigem().getLinha(),
								jogada.getDestino().getLinha()) == 2) {
					Peao peaoVitima = (Peao) peca.clone();
					peaoVitima.setPodeEnPassant(true);
					peaoVitima.setJaMoveu();
					tabuleiroNovo.colocarPeca(jogada.getDestino(), peaoVitima);
				} else {
					// Controle Roque
					if (peca.getJaMoveu() == true)
						tabuleiroNovo.colocarPeca(jogada.getDestino(), peca);
					else {
						Peca novaPeca = peca.clone();
						novaPeca.setJaMoveu();
						tabuleiroNovo
								.colocarPeca(jogada.getDestino(), novaPeca);
					}
				}
				break;
			case ATACAR:
				peca = this.espiarPeca(jogada.getOrigem());
				tabuleiroNovo.retirarPeca(jogada.getOrigem());
				tabuleiroNovo.retirarPeca(jogada.getDestino());
				// Se for Promoção
				if (jogada.ehPromocao()) {
					Rainha rainha = new Rainha(corJogador);
					rainha.setJaMoveu();
					tabuleiroNovo.colocarPeca(jogada.getDestino(), rainha);
				} else {
					// Controle roque
					if (peca.getJaMoveu() == true)
						tabuleiroNovo.colocarPeca(jogada.getDestino(), peca);
					else {
						Peca novaPeca = peca.clone();
						novaPeca.setJaMoveu();
						tabuleiroNovo
								.colocarPeca(jogada.getDestino(), novaPeca);
					}
				}
				break;
			case ROQUE_MENOR:
				if (corJogador == TipoCorJogador.BRANCO) {
					Peca novoRei = this.espiarPeca(new Posicao(5, 1)).clone();
					novoRei.setJaMoveu();
					Peca novaTorre = this.espiarPeca(new Posicao(8, 1)).clone();
					novaTorre.setJaMoveu();
					tabuleiroNovo.retirarPeca(new Posicao(5, 1));
					tabuleiroNovo.retirarPeca(new Posicao(8, 1));
					tabuleiroNovo.colocarPeca(new Posicao(7, 1), novoRei);
					tabuleiroNovo.colocarPeca(new Posicao(6, 1), novaTorre);
				} else {
					Peca novoRei = this.espiarPeca(new Posicao(5, 8)).clone();
					novoRei.setJaMoveu();
					Peca novaTorre = this.espiarPeca(new Posicao(8, 8)).clone();
					novaTorre.setJaMoveu();
					tabuleiroNovo.retirarPeca(new Posicao(5, 8));
					tabuleiroNovo.retirarPeca(new Posicao(8, 8));
					tabuleiroNovo.colocarPeca(new Posicao(7, 8), novoRei);
					tabuleiroNovo.colocarPeca(new Posicao(6, 8), novaTorre);
				}
				break;
			case ROQUE_MAIOR:
				if (corJogador == TipoCorJogador.BRANCO) {
					Peca novoRei = this.espiarPeca(new Posicao(5, 1)).clone();
					novoRei.setJaMoveu();
					Peca novaTorre = this.espiarPeca(new Posicao(1, 1)).clone();
					novaTorre.setJaMoveu();
					tabuleiroNovo.retirarPeca(new Posicao(5, 1));
					tabuleiroNovo.retirarPeca(new Posicao(1, 1));
					tabuleiroNovo.colocarPeca(new Posicao(3, 1), novoRei);
					tabuleiroNovo.colocarPeca(new Posicao(4, 1), novaTorre);
				} else {
					Peca novoRei = this.espiarPeca(new Posicao(5, 8)).clone();
					novoRei.setJaMoveu();
					Peca novaTorre = this.espiarPeca(new Posicao(1, 8)).clone();
					novaTorre.setJaMoveu();
					tabuleiroNovo.retirarPeca(new Posicao(5, 8));
					tabuleiroNovo.retirarPeca(new Posicao(1, 8));
					tabuleiroNovo.colocarPeca(new Posicao(3, 8), novoRei);
					tabuleiroNovo.colocarPeca(new Posicao(4, 8), novaTorre);
				}
				break;
			case EN_PASSANT_ESQUERDA:
				peca = this.espiarPeca(jogada.getOrigem());
				// Retirar a peça de sua posição
				tabuleiroNovo.retirarPeca(jogada.getOrigem());
				// Retirar peça inimiga à esquerda
				tabuleiroNovo.retirarPeca(new Posicao(jogada.getOrigem()
						.getColuna() - 1, jogada.getOrigem().getLinha()));
				// Se o en passant for favorável as peças brancas
				if (corJogador == TipoCorJogador.BRANCO)
					tabuleiroNovo.colocarPeca(
							new Posicao(jogada.getOrigem().getColuna() - 1,
									jogada.getOrigem().getLinha() + 1), peca);
				// Se en passant for favorável as peças pretas
				else
					tabuleiroNovo.colocarPeca(
							new Posicao(jogada.getOrigem().getColuna() - 1,
									jogada.getOrigem().getLinha() - 1), peca);
				break;
			case EN_PASSANT_DIREITA:
				peca = this.espiarPeca(jogada.getOrigem());
				// Retirar a peça de sua posição
				tabuleiroNovo.retirarPeca(jogada.getOrigem());
				// Retirar peça inimiga à direita
				tabuleiroNovo.retirarPeca(new Posicao(jogada.getOrigem()
						.getColuna() + 1, jogada.getOrigem().getLinha()));
				// Se o en passant for favorável as peças brancas
				if (corJogador == TipoCorJogador.BRANCO)
					tabuleiroNovo.colocarPeca(
							new Posicao(jogada.getOrigem().getColuna() + 1,
									jogada.getOrigem().getLinha() + 1), peca);
				// Se en passant for favorável as peças pretas
				else
					tabuleiroNovo.colocarPeca(
							new Posicao(jogada.getOrigem().getColuna() + 1,
									jogada.getOrigem().getLinha() - 1), peca);
				break;
			}
			// Se a jogada a ser realizada não leva um jogador a um estado de
			// xeque
			if (!tabuleiroNovo.verificarXeque(corJogador))
				proximosEstados.add(new Estado(jogada, tabuleiroNovo));
		}
		return proximosEstados;
	}

	/**
	 * Método que gera todas as possiveis jogadas de serem realizadas por um
	 * jogador no tabuleiro. Nesse momento, ainda não foi verificado se a jogada
	 * deixa o jogador em xeque ou xeque-mate.
	 * 
	 * @param corJogador
	 *            cor do jogador que se deseja ver as jogadas possíveis
	 * @return
	 * @throws JogadaInvalidaException
	 * @throws CasaOcupadaException
	 */
	public List<Jogada> geraJogadasPossiveis(TipoCorJogador corJogador) throws CasaOcupadaException {
		// Contém todas as jogadas que podem ser realizadas por um jogador
		List<Jogada> possiveisJogadas = new ArrayList<Jogada>();
		// Contém todas as jogadas que podem ser realizadas por uma peça
		List<Jogada> jogadasPeca = new ArrayList<Jogada>();
		// Jogadas relacionadas a andar e atacar
		for (int coluna = COLUNAINFERIOR; coluna <= COLUNASUPERIOR; coluna++)
			for (int linha = LINHAINFERIOR; linha <= LINHASUPERIOR; linha++)
				// Se a peça encontrada for do jogador
				if (this.estaAliado(corJogador, new Posicao(coluna, linha))) {
					Peca peca = this.espiarPeca(new Posicao(coluna, linha));
					// Encontro todas as jogadas que podem ser realizadas por
					// uma peça
					jogadasPeca = peca.jogadasPeca(new Posicao(coluna, linha),
							this);
					// Para todas as jogadas que podem ser realizadas por uma
					// peça
					for (Jogada jogada : jogadasPeca)
						// Insira a jogada nas possíveis jogadas
						possiveisJogadas.add(jogada);

				}
		// TODO testar
		// Analise de roque menor e roque maior (verifica se é possível)
		if (this.ehRoqueMenor(corJogador) == true)
			try {
				possiveisJogadas.add(new Jogada(TipoJogada.ROQUE_MENOR));
			} catch (JogadaInvalidaException e) {
				System.out.println(e);
			}
		if (this.ehRoqueMaior(corJogador) == true)
			try {
				possiveisJogadas.add(new Jogada(TipoJogada.ROQUE_MAIOR));
			} catch (JogadaInvalidaException e) {
				System.out.println(e);
			}
		return possiveisJogadas;
	}

	/**
	 * Verifica o valor do tabuleiro com base nas peças que um jogador possui e
	 * nas peças que o jogador inimigo possui (trabalhar com Max min)
	 * 
	 * @return valor daquele tabuleiro
	 */
	public int valorTabuleiro(TipoCorJogador corJogador, int xequeMate) {
		int valor = 0;
		// Percorrendo o tabuleiro
		for (int coluna = COLUNAINFERIOR; coluna <= COLUNASUPERIOR; coluna++) {
			for (int linha = LINHAINFERIOR; linha <= LINHASUPERIOR; linha++) {
				Posicao posicao = new Posicao(coluna, linha);
				// Se houver uma peça na posição
				if (!this.estaVazio(posicao)) {
					// Se for uma peça aliada
					if (this.estaAliado(corJogador, posicao))
						valor = valor + this.espiarPeca(posicao).getValor();
					// Se for uma peça inimiga
					else
						valor = valor - this.espiarPeca(posicao).getValor();
				}
			}
		}
		// Se o jogador em questão realizou xeque-mate
		if (xequeMate == 1)
			valor = valor + 100;
		// Se o jogador em questão recebeu xeque-mate
		if (xequeMate == -1)
			valor = valor - 100;
		return valor;
	}

	/**
	 * Método que copia as peças para um novo tabuleiro
	 * 
	 * @return uma cópia da peça
	 */
	public Tabuleiro tabuleiroClonado() {
		Tabuleiro novoTabuleiro = new Tabuleiro();
		for (int coluna = COLUNAINFERIOR; coluna <= COLUNASUPERIOR; coluna++)
			for (int linha = LINHAINFERIOR; linha <= LINHASUPERIOR; linha++)
				if (this.espiarPeca(new Posicao(coluna, linha)) != null) {
					try {
						novoTabuleiro.colocarPeca(
								new Posicao(coluna, linha),
								(Peca) this.espiarPeca(
										new Posicao(coluna, linha)).clone());
					} catch (CasaOcupadaException e) {
						System.out.println("Já existe uma peça (impossível)");
					}
				}
		return novoTabuleiro;
	}

	/**
	 * Método que recomenda uma jogada ao jogador
	 * 
	 * @param corJogador
	 *            Cor do jogador que deseja uma recomendação
	 * @return recomendação (jogada)
	 * @throws JogadaInvalidaException
	 * @throws CasaOcupadaException
	 */
	public Jogada recomendaJogada(TipoCorJogador corJogador) {
		// Criamos uma lista de estados possíveis
		List<Estado> estadosPossiveis = null;
		// TODO testar
		try {
			estadosPossiveis = this
					.proximosEstadosPossiveis(corJogador);
		} catch (CasaOcupadaException e) {
			System.out.println(e);
		}
		Random random = new Random();
		if (!estadosPossiveis.isEmpty()) {
			return estadosPossiveis
					.get(random.nextInt(estadosPossiveis.size())).getJogada();
		}
		// Não há recomendação
		return null;
	}

	/**
	 * Método que captura todos os dados de um tabuleiro e colocam em uma lista
	 * de strings
	 * 
	 * @return
	 */
	public List<String> estadoTabuleiro() {
		List<String> dadoPartida = new ArrayList<String>();
		for (int coluna = COLUNAINFERIOR; coluna <= COLUNASUPERIOR; coluna++)
			for (int linha = LINHAINFERIOR; linha <= LINHASUPERIOR; linha++) {
				if (this.espiarPeca(new Posicao(coluna, linha)) != null) {
					String texto = (coluna
							+ " "
							+ linha
							+ " "
							+ this.espiarPeca(new Posicao(coluna, linha))
									.getTipoPeca()
							+ " "
							+ this.espiarPeca(new Posicao(coluna, linha))
									.getCorJogador() + " " + this.espiarPeca(
							new Posicao(coluna, linha)).getJaMoveu());
					if (this.espiarPeca(new Posicao(coluna, linha))
							.getTipoPeca() == TipoPeca.PEAO) {
						Peao peao = (Peao) this.espiarPeca(new Posicao(coluna,
								linha));
						texto = (texto + " " + peao.isPodeEnPassant());
					}
					dadoPartida.add(texto);
				}
			}
		return dadoPartida;
	}

	/**
	 * Método de apoio ao programador que descreve o que há no tabuleiro
	 */
	public String toString() {
		String dadosTabuleiro = "";
		for (int coluna = COLUNAINFERIOR; coluna <= COLUNASUPERIOR; coluna++)
			for (int linha = LINHAINFERIOR; linha <= LINHASUPERIOR; linha++) {
				if (this.espiarPeca(new Posicao(coluna, linha)) != null) {
					dadosTabuleiro = dadosTabuleiro
							+ this.espiarPeca(new Posicao(coluna, linha))
									.getTipoPeca()
							+ " "
							+ this.espiarPeca(new Posicao(coluna, linha))
									.getCorJogador()
							+ " na coluna "
							+ coluna
							+ " e linha "
							+ linha
							+ " e já moveu: "
							+ this.espiarPeca(new Posicao(coluna, linha))
									.getJaMoveu();
					if (this.espiarPeca(new Posicao(coluna, linha))
							.getTipoPeca() == TipoPeca.PEAO) {
						Peao peao = (Peao) this.espiarPeca(new Posicao(coluna,
								linha));
						dadosTabuleiro = dadosTabuleiro + " "
								+ "pode enPassant: " + peao.isPodeEnPassant();
					}
					dadosTabuleiro = dadosTabuleiro + "\n";
				}
			}
		return dadosTabuleiro;
	}
}