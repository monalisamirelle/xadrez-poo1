package br.edu.ifes.poo1.cln.cdp;

public class Estado {

	private Jogada jogada;
	private Tabuleiro tabuleiro;
	
	public Estado(Jogada jogada, Tabuleiro tabuleiro){
		this.jogada = jogada;
		this.tabuleiro = tabuleiro;
	}

	public Jogada getJogada() {
		return jogada;
	}

	public void setJogada(Jogada jogada) {
		this.jogada = jogada;
	}

	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	public void setTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

}
