package br.edu.ifes.poo1.cln.cdp;

public class PosicaoEscolhida {
	private Posicao posicao;
	private int indice;

	PosicaoEscolhida(Posicao posicao, int indice) {
		this.posicao = posicao;
		this.indice = indice;
	}

	public Posicao getPosicao() {
		return posicao;
	}

	public int getIndice() {
		return indice;
	}
}
