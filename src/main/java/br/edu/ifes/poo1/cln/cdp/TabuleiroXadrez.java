package br.edu.ifes.poo1.cln.cdp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifes.poo1.cln.cdp.ia.Estado;
import br.edu.ifes.poo1.cln.cdp.ia.GeraEstado;
import br.edu.ifes.poo1.cln.cdp.pecas.Bispo;
import br.edu.ifes.poo1.cln.cdp.pecas.Cavalo;
import br.edu.ifes.poo1.cln.cdp.pecas.Peao;
import br.edu.ifes.poo1.cln.cdp.pecas.Peca;
import br.edu.ifes.poo1.cln.cdp.pecas.Rainha;
import br.edu.ifes.poo1.cln.cdp.pecas.Rei;
import br.edu.ifes.poo1.cln.cdp.pecas.Torre;
import br.edu.ifes.poo1.cln.cdp.tipos.TipoCorJogador;
import br.edu.ifes.poo1.cln.cdp.tipos.TipoJogada;
import br.edu.ifes.poo1.cln.cdp.tipos.TipoPeca;

/**
 * Um tabuleiro é composto por 64 casas, estas podem estar ocupadas por uma peça
 * ou vazias.
 */
public class TabuleiroXadrez implements Tabuleiro, Serializable {

	/** Cria um objeto da classe GeraEstado */
	private GeraEstado geraEstado = new GeraEstado();

	/* Constantes do tamanho do tabuleiro. */
	public static final int LINHAINFERIOR = 1;
	public static final int LINHASUPERIOR = 8;
	public static final int COLUNAINFERIOR = 1;
	public static final int COLUNASUPERIOR = 8;

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
	public TabuleiroXadrez() {
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
	public TabuleiroXadrez(Jogador brancas, Jogador pretas) {
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
		if (pecas[posicao.getColuna() - 1][posicao.getLinha() - 1] != null) {
			throw new CasaOcupadaException();
		} else
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
			// Se o lado que está analisando não se encontra fora do tabuleiro
			if (!estaForaDoTabuleiro(lado))
				// Se a posicao ao lado tem uma peça inimiga
				if (this.estaInimigo(this.espiarPeca(posicaoPeca)
						.getCorJogador(), lado))
					// Se a peça inimiga é um peão
					if (this.espiarPeca(lado).getTipoPeca() == TipoPeca.PEAO) {
						Peao peaoInimigo = (Peao) this.espiarPeca(lado);
						// Se o peão em questão pode sofrer um en passant
						if (peaoInimigo.isPodeEnPassant())
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
	 * Indica se o rei está realizando uma jogada que culminará em sua captura
	 * na próxima jogada inimiga
	 * 
	 * @param posicaoAtual
	 * @param posicaoDesejada
	 * @param corJogador
	 * @return
	 */
	public boolean jogadaSuicida(Jogada jogada, TipoCorJogador corJogador) {
		try {
			TipoCorJogador corAdversario = TipoCorJogador
					.getCorOposta(corJogador);

			TabuleiroXadrez novoTabuleiro = geraEstado.geraTabuleiroJogada(
					jogada, this, corJogador);
			System.out.println(novoTabuleiro.toString());

			Posicao posicaoRei = novoTabuleiro.encontrarRei(corJogador);

			// Verificamos se está ameaçado
			return novoTabuleiro.estaAmeacadoPor(posicaoRei, corAdversario);
		} catch (Exception e) {
			// TODO e isso?
			return true;
		}
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
	 * Indica se alguma peça da cor indicada ameaça a posição indicada
	 * 
	 * @param posicaoObjetivo
	 *            Posição a ser verificada.
	 * @param corAdversario
	 *            Cor do jogador que pode estar ameaçando a posição.
	 * @return Se a o jogador da cor indicada está ameçando a posição com alguma
	 *         peça.
	 */
	public boolean estaAmeacadoPor(Posicao posicaoRei,
			TipoCorJogador corAdversario) {

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
				if (peca.getCorJogador() != corAdversario)
					continue;

				// Verifica se a peça pode atacar a posição indicada.
				if (peca.podeAtacar(origem, posicaoRei, this))
					return true;
			}
		}
		// Caso não haja peça que possa ameaçar a posição, retorna falso.
		return false;
	}

	/**
	 * Verifica se o rei da cor indicada está em Xeque.
	 * 
	 * @param cor
	 *            Cor do rei.
	 * @return Se a o rei está em Xeque.
	 */
	public boolean verificarXeque(TipoCorJogador cor) {
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
		List<Estado> estadosPossiveis = geraEstado.proximosEstadosPossiveis(
				this, cor);
		if (!estadosPossiveis.isEmpty())
			return false;
		return true;
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
	public List<Jogada> geraJogadasPossiveis(TipoCorJogador corJogador)
			throws CasaOcupadaException, JogadaInvalidaException {
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
		// Analise de roque menor e roque maior (verifica se é possível)
		if (this.ehRoqueMenor(corJogador) == true)
			possiveisJogadas.add(new Jogada(TipoJogada.ROQUE_MENOR));
		if (this.ehRoqueMaior(corJogador) == true)
			possiveisJogadas.add(new Jogada(TipoJogada.ROQUE_MAIOR));
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
	 * @throws CasaOcupadaException
	 */
	public TabuleiroXadrez tabuleiroClonado() throws CasaOcupadaException {
		TabuleiroXadrez novoTabuleiro = new TabuleiroXadrez();
		for (int coluna = COLUNAINFERIOR; coluna <= COLUNASUPERIOR; coluna++)
			for (int linha = LINHAINFERIOR; linha <= LINHASUPERIOR; linha++)
				if (this.espiarPeca(new Posicao(coluna, linha)) != null) {
					novoTabuleiro.colocarPeca(new Posicao(coluna, linha),
							(Peca) this.espiarPeca(new Posicao(coluna, linha))
									.clone());
				}
		return novoTabuleiro;
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

	public GeraEstado getGeraEstado() {
		return geraEstado;
	}
}