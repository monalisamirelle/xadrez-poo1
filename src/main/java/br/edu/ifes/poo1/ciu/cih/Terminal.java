package br.edu.ifes.poo1.ciu.cih;

import br.edu.ifes.poo1.cln.cdp.CorJogador;
import br.edu.ifes.poo1.cln.cdp.Jogador;
import br.edu.ifes.poo1.cln.cdp.Peca;
import br.edu.ifes.poo1.cln.cdp.Posicao;
import br.edu.ifes.poo1.cln.cdp.Tabuleiro;

/**
 * Interface para o Prompt do Windows.
 */
public class Terminal extends Cli {

	/** Cor das peças brancas */
	private final ForegroundColor corBrancas = ForegroundColor.AZUL_ESCURO;

	/** Cor das peças pretas */
	private final ForegroundColor corPretas = ForegroundColor.VERMELHO;

	/** Cor da casa do canto esquerdo das peças brancas */
	private final BackgroundColor corInferiorEsquerdo = BackgroundColor.PRETO;

	/** Cor da casa do canto direito das peças brancas */
	private final BackgroundColor corInferiorDireito = BackgroundColor.BRANCO;

	@Override
	public void atualizar(Tabuleiro tabuleiro, Jogador brancas, Jogador pretas) {
		// Limpa a tela, antes de qualquer coisa.
		limparTela();

		// Procede com a atualização dos objetos na tela.
		super.atualizar(tabuleiro, brancas, pretas);
	}

	@Override
	public void imprimirTabuleiro(Tabuleiro tabuleiro) {
		// Imprime o tabuleiro
		for (int linha = 8; linha >= 1; linha--) {
			for (int coluna = 1; coluna <= 8; coluna++) {
				Posicao posicao = new Posicao(coluna, linha);

				// Descobre a peça que está no tabuleiro.
				Peca peca = tabuleiro.espiarPeca(posicao);

				// Adequa as cores a serem usadas na casa e na peça.
				if (peca == null) {
					trocarCor(ForegroundColor.ROSA, getCorCasa(posicao));
					System.out.print("  ");
				} else {
					trocarCor(getCorPeca(peca), getCorCasa(posicao));
					System.out.print(PecaToString(peca));
				}
			}
			// Restaura as cores normais do terminal.
			resetarCor();
			System.out.println();
		}
	}

	/**
	 * Determina com qual cor a peça deve ser colorida.
	 * 
	 * @param peca
	 *            Peca a colorir.
	 * @return Cor com que a peça deve ser colorida.
	 */
	public ForegroundColor getCorPeca(Peca peca) {
		if (peca.getJogador().getCor() == CorJogador.BRANCO)
			return corBrancas;
		else
			return corPretas;
	}

	/**
	 * Informa a cor com que uma determinada casa deve ser colorida.
	 * 
	 * @param posicao
	 *            Posição no tabuleiro que será colorida.
	 * @return A cor com que a casa deve ser colorida.
	 */
	public BackgroundColor getCorCasa(Posicao posicao) {
		if ((posicao.getLinha() + posicao.getColuna()) % 2 == 0)
			return corInferiorEsquerdo;
		else
			return corInferiorDireito;
	}

	@Override
	public String PecaToString(Peca peca) {
		if (peca == null)
			return "  ";

		switch (peca.getTipoPeca()) {
		case PEAO:
			return "♟ ";
		case TORRE:
			return "♜ ";
		case BISPO:
			return "♝ ";
		case CAVALO:
			return "♞ ";
		case REI:
			return "♚ ";
		case RAINHA:
			return "♛ ";
		default:
			return "Erro!";
		}
	}

	/**
	 * Troca a cor com que será impresso no terminal.
	 * 
	 * @param fg
	 *            Cor que será aplicado no texto.
	 * @param bg
	 *            Cor que será aplicada ao fundo do texto.
	 */
	public static void trocarCor(ForegroundColor fg, BackgroundColor bg) {
		System.out.print("\u001b[" + fg.getSequencia() + ";"
				+ bg.getSequencia() + "m");
	}

	/**
	 * Troca a cor do que será impresso no terminal para a cor padrão do
	 * ambiente.
	 */
	public static void resetarCor() {
		System.out.print("\u001b[0m");
	}

	/**
	 * Limpar a tela e retorna o cursors
	 */
	public static void limparTela() {
		// Limpa a tela (ANSI_CLS)
		System.out.print("\u001b[2J");

		// Retorna o cursor (ANSI_HOME)
		System.out.print("\u001b[H");
	}

}
