package br.edu.ifes.poo1.cln.cdp;

/**
 * Indica uma casa do tabuleiro.
 */
public class Posicao {
	private int coluna;
	private int linha;

	/**
	 * Instancia uma posição com base em sua coluna e posição do tabuleiro.
	 * 
	 * @param coluna
	 *            Coluna do tabuleiro.
	 * @param linha
	 *            Linha do tabuleiro.
	 */
	public Posicao(int coluna, int linha) {
		this.coluna = coluna;
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public int getLinha() {
		return linha;
	}

}
