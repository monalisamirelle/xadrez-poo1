package br.edu.ifes.poo1.cln.cdp;

public class PosicaoEscolhidaMaquina {
	private Posicao posicao;
	private int indice;

	PosicaoEscolhidaMaquina(Posicao posicao, int indice) {
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
