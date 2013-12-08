package br.edu.ifes.poo1.cln.cdp;

/**
 * Representação de uma casa do tabuleiro. Cada casa abriga tão somente uma
 * peça. Ao todo, em um tabuleiro de xadrez, elas são 64.
 * 
 * @author lucas
 * 
 */
public class Casa {

	private Posicao posicao;

	/**
	 * Peça que está ocupando a casa.
	 */
	private Peca peca;

	/**
	 * Constrói uma casa, baseando-se apenas na sua posição no tabuleiro.
	 * 
	 * @param coluna
	 *            Coluna em que se encontra a casa.
	 * @param linha
	 *            Linha em que se encontra a casa.
	 */
	public Casa(int coluna, int linha) {
		this.posicao = new Posicao(coluna, linha);
	}

	/**
	 * Constrói uma casa, baseando-se em sua posição no tabuleiro e a sua peça
	 * inicial.
	 * 
	 * @param coluna
	 *            Coluna em que a casa se encontra.
	 * @param linha
	 *            Linha em que a casa se encontra.
	 * @param peca
	 *            Peça que inicialmente ocupa a casa
	 */
	public Casa(int coluna, int linha, Peca peca) {
		// FIXME: usar o outro construtor aqui dentro também.
		this(coluna, linha);
		this.peca = peca;
	}

	/**
	 * Indica se a casa está ocupada ou não.
	 * 
	 * @return Um booleano que diz se a casa está ocupada.
	 */
	public boolean estaOcupada() {
		return this.peca != null;
	}

	/**
	 * Retorna a peça que está ocupando a casa. Se não houver nenhuma, retorna
	 * null.
	 * 
	 * @return A peça que está ocupando a casa.
	 */
	public Peca getPeca() {
		return peca;
	}

	public void setPeca(Peca peca) {
		this.peca = peca;
	}
}
