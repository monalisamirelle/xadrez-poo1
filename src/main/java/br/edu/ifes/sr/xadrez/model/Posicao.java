package br.edu.ifes.sr.xadrez.model;

/**
 * Representa uma posição no tabuleiro, composta por linha e coluna.
 * 
 * @author lucas
 * 
 */
public class Posicao {
	private int coluna;
	private int linha;

	public Posicao(int coluna, int linha) {
		this.coluna = coluna;
		this.linha = linha;
	}

	public void alterarPosicao(int coluna, int linha) {
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
