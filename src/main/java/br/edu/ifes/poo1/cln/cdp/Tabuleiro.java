package br.edu.ifes.poo1.cln.cdp;

import java.util.ArrayList;
import java.util.List;

/**
 * Um tabuleiro é composto por 64 casas, estas podem estar ocupadas por uma peça
 * ou vazias.
 */
public class Tabuleiro implements Cloneable {

	public final int LINHAINFERIOR = 1;
	public final int LINHASUPERIOR = 8;
	public final int COLUNAINFERIOR = 1;
	public final int COLUNASUPERIOR = 8;

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
	 * Método construtor de um clone de tabuleiro
	 * 
	 * @param clone
	 */
	public Tabuleiro(Peca[][] clone) {
		this.pecas = clone;
	}

	/**
	 * Inicia um tabuleiro padrão de xadrez, com as peças já posicionadas e com
	 * suas referências para os jogadores corretos.
	 * 
	 * @param brancas
	 *            Jogador que controla as peças brancas.
	 * @param pretas
	 *            Jogador que controla as peças pretas.
	 * @throws CasaOcupadaException
	 */
	public Tabuleiro(Jogador brancas, Jogador pretas) {
		// Inicia o tabuleiro sem nenhuma peça.
		this();

		// Posiciona as peças.
		try {
			// Posiciona as peças brancas, exceto os peões.
			this.colocarPeca(new Posicao(1, 1), new Torre(brancas));
			this.colocarPeca(new Posicao(2, 1), new Cavalo(brancas));
			this.colocarPeca(new Posicao(3, 1), new Bispo(brancas));
			this.colocarPeca(new Posicao(4, 1), new Rainha(brancas));
			this.colocarPeca(new Posicao(5, 1), new Rei(brancas));
			this.colocarPeca(new Posicao(6, 1), new Bispo(brancas));
			this.colocarPeca(new Posicao(7, 1), new Cavalo(brancas));
			this.colocarPeca(new Posicao(8, 1), new Torre(brancas));

			// Posiciona os peões brancos.
			for (int coluna = 1; coluna <= 8; coluna++) {
				this.colocarPeca(new Posicao(coluna, 2), new Peao(brancas));
			}

			// Posiciona as peças brancas, exceto os peões.
			this.colocarPeca(new Posicao(1, 8), new Torre(pretas));
			this.colocarPeca(new Posicao(2, 8), new Cavalo(pretas));
			this.colocarPeca(new Posicao(3, 8), new Bispo(pretas));
			this.colocarPeca(new Posicao(4, 8), new Rainha(pretas));
			this.colocarPeca(new Posicao(5, 8), new Rei(pretas));
			this.colocarPeca(new Posicao(6, 8), new Bispo(pretas));
			this.colocarPeca(new Posicao(7, 8), new Cavalo(pretas));
			this.colocarPeca(new Posicao(8, 8), new Torre(pretas));

			// Posiciona os peões pretos.
			for (int coluna = 1; coluna <= 8; coluna++) {
				this.colocarPeca(new Posicao(coluna, 7), new Peao(pretas));
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
	public boolean estaAliado(CorJogador corJogador, Posicao destino) {
		if (this.estaVazio(new Posicao(destino.getColuna(), destino.getLinha())) == false)
			if (this.espiarPeca(
					new Posicao(destino.getColuna(), destino.getLinha()))
					.getJogador().getCor() == corJogador)
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
	public boolean estaInimigo(CorJogador corJogador, Posicao destino) {
		if (this.estaVazio(new Posicao(destino.getColuna(), destino.getLinha())) == false)
			if (this.espiarPeca(
					new Posicao(destino.getColuna(), destino.getLinha()))
					.getJogador().getCor() != corJogador)
				return true;
		return false;
	}

	/**
	 * Indica se alguma peça da cor indicada ameaça a posição indicada
	 * 
	 * @param posicao
	 *            Posição a ser verificada.
	 * @param cor
	 *            Cor do jogador que pode estar ameaçando a posição.
	 * @return Se a o jogador da cor indicada está ameçando a posição com alguma
	 *         peça.
	 */
	public boolean estaAmeacadoPor(Posicao posicao, CorJogador cor) {
		// Verifica se há alguma peça da cor indicada que ameaça a posição
		// indicada.
		for (int coluna = 1; coluna <= 8; coluna++) {
			for (int linha = 1; linha <= 8; linha++) {
				Posicao origem = new Posicao(coluna, linha);

				// Pula as casas vazias.
				if (estaVazio(origem))
					continue;

				// Pega a peça na casa varrida.
				Peca peca = espiarPeca(origem);

				// Se for um rei, pula.
				if (peca.getTipoPeca() == TipoPeca.REI)
					continue;

				// Pula as peças que não forem da cor indicada.
				if (peca.getJogador().getCor() != cor)
					continue;

				// Verifica se a pessa pode atacar a posição indicada.
				if (peca.podeAtacar(origem, posicao, this))
					return true;
			}
		}

		// Caso não haja peça que possa ameaçar a posição, retorna falso.
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
		if (posicao.getLinha() > 0 & posicao.getLinha() <= 8
				& posicao.getColuna() > 0 & posicao.getColuna() <= 8)
			return false;
		else
			return true;
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
		int movimentoHorizontal = (int) Math.signum(destino.getLinha()
				- origem.getLinha());
		int movimentoVertical = (int) Math.signum(destino.getColuna()
				- origem.getColuna());
		while ((linha != destino.getLinha() || coluna != destino.getColuna())) {
			// Se não tivermos chegado na posição
			linha = linha + movimentoHorizontal;
			coluna = coluna + movimentoVertical;
			if (estaForaDoTabuleiro(new Posicao(coluna, linha)) == true) {
				return false;
			}
			if (!(linha == destino.getLinha() && coluna == destino.getColuna())) {
				// Se a posição no tabuleiro não for nula, informe que o
				// movimento é proibido
				if (pecas[coluna - 1][linha - 1] != null) {
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
	public boolean ehRoqueMenor(CorJogador corJogador) {
		// Se for um roque menor do jogador de peças brancas
		if (corJogador == CorJogador.BRANCO)
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
	public boolean ehRoqueMaior(CorJogador corJogador) {
		// Se for um roque maior do jogador de peças brancas
		if (corJogador == CorJogador.BRANCO)
			return verificaRoque(new Posicao(5, 1), new Posicao(1, 1),
					new Posicao(3, 1), new Posicao(4, 1));
		// Se for um roque maior do jogador de peças pretas
		else
			return verificaRoque(new Posicao(5, 8), new Posicao(1, 8),
					new Posicao(3, 8), new Posicao(4, 8));
	}

	/**
	 * Verifica se o enPassant é válido
	 * 
	 * @param cor
	 *            Cor do rei
	 * @return Posições ao redor do rei.
	 */
	// TODO testar
	private boolean ehEnPassant(Posicao posicaoPeca) {
		if (this.espiarPeca(posicaoPeca).getTipoPeca() == TipoPeca.PEAO) {
			Posicao esquerda = new Posicao(posicaoPeca.getColuna() - 1,
					posicaoPeca.getLinha());
			// Se a posição não se encontra fora do tabuleiro
			if (!estaForaDoTabuleiro(esquerda))
				// Se a posicao tem uma peça inimiga
				if (this.estaInimigo(this.espiarPeca(posicaoPeca).getJogador()
						.getCor(), esquerda))
					// Se a peça inimiga é um peão
					if (this.espiarPeca(esquerda).getTipoPeca() == TipoPeca.PEAO) {
						Peao peaoInimigo = (Peao) this.espiarPeca(esquerda);
						// Se o peão em questão pode sofre um en passant
						if (peaoInimigo.isPodeEnPassant() == true)
							return true;
					}
			Posicao direita = new Posicao(posicaoPeca.getColuna() - 1,
					posicaoPeca.getLinha());
			// Se a posição não se encontra fora do tabuleiro
			if (!estaForaDoTabuleiro(direita))
				// Se a posicao tem uma peça inimiga
				if (this.estaInimigo(this.espiarPeca(posicaoPeca).getJogador()
						.getCor(), direita))
					// Se a peça inimiga é um peão
					if (this.espiarPeca(direita).getTipoPeca() == TipoPeca.PEAO) {
						Peao peaoInimigo = (Peao) this.espiarPeca(direita);
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
	// TODO testar
	public void resetaPodeEnPassant(CorJogador corJogador) {
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
	 * Verifica se o rei da cor indicada está em Xeque.
	 * 
	 * @param cor
	 *            Cor do rei.
	 * @return Se a o rei está em Xeque.
	 */
	public boolean verificarXeque(CorJogador cor) {
		// Encontra a posição do rei.
		Posicao posicaoRei = encontrarRei(cor);
		// Retorna se a posição do rei está ameaçada.
		if (cor == CorJogador.BRANCO)
			return estaAmeacadoPor(posicaoRei, CorJogador.PRETO);
		else
			return estaAmeacadoPor(posicaoRei, CorJogador.BRANCO);
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
	public Posicao encontrarRei(CorJogador cor) {
		// Varre o tabuleiro procurando o rei da cor indicada.
		for (int coluna = 1; coluna <= 8; coluna++) {
			for (int linha = 1; linha <= 8; linha++) {
				// Forma a posição que estamos a verificar.
				Posicao posicao = new Posicao(coluna, linha);

				// Pula se não houver peça ali.
				if (estaVazio(posicao))
					continue;

				// Pega a peça que está ali.
				Peca peca = espiarPeca(posicao);

				// Verifica se é o rei da cor indicada. E retorna-o.
				if (peca.getTipoPeca() == TipoPeca.REI
						&& peca.getJogador().getCor() == cor) {
					return posicao;
				}
			}
		}

		// Se o rei não for encontrado (o que não acontece em uma partida de
		// xadrez), retorna 'null'.
		return null;
	}

	/**
	 * Retorna uma lista de posições válidas que estão ao redor do rei da cor
	 * indicada, mesmo vazias ou não.
	 * 
	 * @param cor
	 *            Cor do rei
	 * @return Posições ao redor do rei.
	 */
	private List<Posicao> posicoesAoRedorDoRei(CorJogador cor) {
		// Encontra a posição do rei.
		Posicao posicaoRei = encontrarRei(cor);

		// Inicia uma lista de posições ao redor.
		List<Posicao> posicoesAoRedor = new ArrayList<Posicao>();

		// Adiciona todas as posições acima e abaixo do rei.
		for (int coluna = posicaoRei.getColuna() - 1; coluna < posicaoRei
				.getColuna() + 1; coluna++) {
			// Acima do rei.
			posicoesAoRedor.add(new Posicao(coluna, posicaoRei.getLinha() + 1));

			// Abaixo do rei.
			posicoesAoRedor.add(new Posicao(coluna, posicaoRei.getLinha() - 1));
		}

		// Adiciona a posição a esquerda do rei.
		posicoesAoRedor.add(new Posicao(posicaoRei.getColuna() - 1, posicaoRei
				.getLinha()));

		// Adiciona a posição a direita do rei.
		posicoesAoRedor.add(new Posicao(posicaoRei.getColuna() + 1, posicaoRei
				.getLinha()));

		List<Posicao> posicoesAoRedorValidas = new ArrayList<Posicao>();
		// Filtra as posições, verificando quais estão dentro dos limites do
		// tabuleiro.
		for (Posicao posicao : posicoesAoRedor) {
			// Se a posição está dentro do tabuleiro ela é adcionada como
			// válida.
			if (!estaForaDoTabuleiro(posicao))
				posicoesAoRedorValidas.add(posicao);
		}

		// Retorna a lista pronta.
		return posicoesAoRedorValidas;
	}

	/**
	 * Verifica se houve Xeque Mate na cor indicada.
	 * 
	 * @param cor
	 *            Cor do rei que está em Xeque Mate.
	 * @return Se a cor indicada sofreu Xeque Mate.
	 */
	public boolean verificarXequeMate(CorJogador cor) {
		// Encontra o rei.
		Posicao posicaoRei = encontrarRei(cor);
		Peca rei = espiarPeca(posicaoRei);

		// Pega as posições que estão ao redor do rei.
		List<Posicao> posicoes = posicoesAoRedorDoRei(cor);

		// Verifica se há alguma posição para onde o rei pode se mover.
		boolean podeSeMover = false;
		for (Posicao posicao : posicoes) {
			// Verifica se ele pode se mover ou atacar, para sair da posição
			// atual.
			if (rei.podeAndar(posicaoRei, posicao, this)
					|| rei.podeAtacar(posicaoRei, posicao, this))
				podeSeMover = true;
		}

		// É Xeque Mate se o rei está em Xeque e não há para onde ele se mover.
		// FIXME: Na verdade, para que o Xeque Mate ocorra, nenhuma peça pode
		// conseguir ajudar o rei. Mas isto está fora do escopo do trabalho.
		return verificarXeque(cor) && !podeSeMover;
	}

	/**
	 * Método que armazena, dado um tabuleiro, os próximos estados possíveis
	 * para o tabuleiro
	 * 
	 * @param corJogador
	 * @return
	 * @throws CasaOcupadaException
	 * @throws CloneNotSupportedException
	 * @throws JogadaInvalidaException
	 */
	public List<Tabuleiro> proximosEstadosPossiveis(CorJogador corJogador)
			throws CasaOcupadaException, CloneNotSupportedException,
			JogadaInvalidaException {
		List<Tabuleiro> proximosEstados = new ArrayList<Tabuleiro>();
		List<Jogada> possiveisJogadas = geraJogadasPossiveis(corJogador);

		Peca peca = null;
		Peca rei = null;
		Peca torre = null;

		// Para cada jogada realizada pela peça, gere um novo
		// tabuleiro e or armazene na lista de tabuleiros
		for (Jogada jogada : possiveisJogadas) {
			Peca[][] pecasAtuais = this.copiaPecas();
			Tabuleiro tabuleiroNovo = new Tabuleiro(pecasAtuais);

			// System.out.println("\nTabuleiro antigo");
			// this.digaTabuleiro();

			// Verifica o tipo de jogada e gera o tabuleiro correto
			switch (jogada.getTipoJogada()) {
			case ANDAR:
				peca = this.espiarPeca(jogada.getOrigem());
				tabuleiroNovo.retirarPeca(jogada.getOrigem());
				if (peca.getJaMoveu() == true)
					tabuleiroNovo.colocarPeca(jogada.getDestino(), peca);
				else
					tabuleiroNovo.colocarPeca(jogada.getDestino(),
							peca.novaInstancia());
				break;
			case ATACAR:
				peca = this.espiarPeca(jogada.getOrigem());
				tabuleiroNovo.retirarPeca(jogada.getOrigem());
				tabuleiroNovo.retirarPeca(jogada.getDestino());
				if (peca.getJaMoveu() == true)
					tabuleiroNovo.colocarPeca(jogada.getDestino(), peca);
				else
					tabuleiroNovo.colocarPeca(jogada.getDestino(),
							peca.novaInstancia());
				break;
			case ROQUE_MENOR:
				if (corJogador == CorJogador.BRANCO) {
					rei = this.espiarPeca(new Posicao(5, 1));
					torre = this.espiarPeca(new Posicao(8, 1));
					tabuleiroNovo.retirarPeca(new Posicao(5, 1));
					tabuleiroNovo.retirarPeca(new Posicao(8, 1));
					tabuleiroNovo.colocarPeca(new Posicao(7, 1),
							rei.novaInstancia());
					tabuleiroNovo.colocarPeca(new Posicao(6, 1),
							torre.novaInstancia());
				} else {
					rei = this.espiarPeca(new Posicao(5, 8));
					torre = this.espiarPeca(new Posicao(8, 8));
					tabuleiroNovo.retirarPeca(new Posicao(5, 8));
					tabuleiroNovo.retirarPeca(new Posicao(8, 8));
					tabuleiroNovo.colocarPeca(new Posicao(7, 8),
							rei.novaInstancia());
					tabuleiroNovo.colocarPeca(new Posicao(6, 8),
							torre.novaInstancia());
				}
				break;
			case ROQUE_MAIOR:
				if (corJogador == CorJogador.BRANCO) {
					rei = this.espiarPeca(new Posicao(5, 1));
					torre = this.espiarPeca(new Posicao(1, 1));
					tabuleiroNovo.retirarPeca(new Posicao(5, 1));
					tabuleiroNovo.retirarPeca(new Posicao(1, 1));
					tabuleiroNovo.colocarPeca(new Posicao(3, 1),
							rei.novaInstancia());
					tabuleiroNovo.colocarPeca(new Posicao(4, 1),
							torre.novaInstancia());
				} else {
					rei = this.espiarPeca(new Posicao(5, 8));
					torre = this.espiarPeca(new Posicao(1, 8));
					tabuleiroNovo.retirarPeca(new Posicao(5, 8));
					tabuleiroNovo.retirarPeca(new Posicao(1, 8));
					tabuleiroNovo.colocarPeca(new Posicao(3, 8),
							rei.novaInstancia());
					tabuleiroNovo.colocarPeca(new Posicao(4, 8),
							torre.novaInstancia());
				}
				break;
			}
			// System.out.println(jogada.getDestino().getColuna()+" "+jogada.getDestino().getLinha());

			// System.out.println("\nTabuleiro novo");
			// tabuleiroNovo.digaTabuleiro();

			// Armazena o tabuleiro na lista de próximos estados
			proximosEstados.add(tabuleiroNovo);
		}
		return proximosEstados;
	}

	/**
	 * Método que gera todas as possiveis jogadas de serem realizadas por um
	 * jogador no tabuleiro
	 * 
	 * @param jogador
	 * @return
	 * @throws JogadaInvalidaException
	 * @throws CasaOcupadaException
	 */
	public List<Jogada> geraJogadasPossiveis(CorJogador corJogador)
			throws JogadaInvalidaException {
		// Contém todas as jogadas que podem ser realizadas por um jogador
		List<Jogada> possiveisJogadas = new ArrayList<Jogada>();
		// Contém todas as jogadas que podem ser realizadas por uma peça
		List<Jogada> jogadasPeca = new ArrayList<Jogada>();
		// Jogadas relacionadas a andar e atacar
		for (int coluna = 1; coluna <= 8; coluna++)
			for (int linha = 1; linha <= 8; linha++)
				// Se a peça encontrada for do jogador
				if (this.estaAliado(corJogador, new Posicao(coluna, linha)) == true) {
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
		// Analise de roque menor e roque maior (verifica se é possível)
		if (this.ehRoqueMenor(corJogador) == true)
			possiveisJogadas.add(new Jogada(TipoJogada.ROQUE_MENOR));
		if (this.ehRoqueMaior(corJogador) == true)
			possiveisJogadas.add(new Jogada(TipoJogada.ROQUE_MAIOR));
		// System.out.println(possiveisJogadas.size());
		return possiveisJogadas;
	}

	/**
	 * Verifica o valor do tabuleiro com base nas peças que um jogador possui e
	 * nas peças que o jogador inimigo possui (trabalhar com Max min)
	 * 
	 * @return
	 */
	public int valorTabuleiro() {
		int valor = 0;
		// Percorrendo o tabuleiro
		for (int coluna = 1; coluna <= 8; coluna++) {
			for (int linha = 1; linha <= 8; linha++) {
				Posicao posicao = new Posicao(coluna, linha);
				// Se houver uma peça na posição
				if (this.estaVazio(posicao) == false) {
					// Se for uma peça inimiga
					if (this.estaInimigo(CorJogador.BRANCO, posicao) == true)
						valor = valor + this.espiarPeca(posicao).getValor();
					// Se for uma peça diferente
					else
						valor = valor - this.espiarPeca(posicao).getValor();
				}
			}
		}
		return valor;
	}

	/**
	 * Método que copia as peças para um novo tabuleiro
	 * 
	 * @return
	 */
	private Peca[][] copiaPecas() {
		Peca[][] copiaPeca = new Peca[8][8];
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				copiaPeca[i][j] = this.pecas[i][j];
		return copiaPeca;
	}

	/**
	 * Método de apoio que descreve o que há no tabuleiro
	 */
	public void digaTabuleiro() {
		for (int coluna = 1; coluna <= 8; coluna++)
			for (int linha = 1; linha <= 8; linha++)
				if (this.espiarPeca(new Posicao(coluna, linha)) != null)
					System.out.println(this.espiarPeca(
							new Posicao(coluna, linha)).getTipoPeca()
							+ " "
							+ this.espiarPeca(new Posicao(coluna, linha))
									.getJogador().getCor()
							+ " na coluna "
							+ coluna
							+ " e linha "
							+ linha
							+ " e já moveu: "
							+ this.espiarPeca(new Posicao(coluna, linha))
									.getJaMoveu());
	}
}