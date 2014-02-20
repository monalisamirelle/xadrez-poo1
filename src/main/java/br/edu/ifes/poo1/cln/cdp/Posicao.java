package br.edu.ifes.poo1.cln.cdp;

import java.io.Serializable;

/**
 * Indica uma casa do tabuleiro.
 */
public class Posicao implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	@Override
	public boolean equals(Object obj) {
		// Faz casting do objeto para posição.
		Posicao outraPosicao = (Posicao) obj;

		// Se o casting falhou, ou o obj já era null, as posições não são
		// iguais.
		if (outraPosicao == null)
			return false;

		// As posições são iguais somente se suas linhas e colunas forem iguais.
		if (this.getLinha() == outraPosicao.getLinha()
				&& this.getColuna() == outraPosicao.getColuna())
			return true;
		else
			return false;
	}
}
