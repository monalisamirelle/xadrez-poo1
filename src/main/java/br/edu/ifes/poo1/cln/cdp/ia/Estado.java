package br.edu.ifes.poo1.cln.cdp.ia;

import br.edu.ifes.poo1.cln.cdp.Jogada;
import br.edu.ifes.poo1.cln.cdp.TabuleiroXadrez;

public class Estado {

	private Jogada jogada;
	private TabuleiroXadrez tabuleiro;
	
	public Estado(Jogada jogada, TabuleiroXadrez tabuleiro){
		this.jogada = jogada;
		this.tabuleiro = tabuleiro;
	}

	public Jogada getJogada() {
		return jogada;
	}

	public void setJogada(Jogada jogada) {
		this.jogada = jogada;
	}

	public TabuleiroXadrez getTabuleiro() {
		return tabuleiro;
	}

	public void setTabuleiro(TabuleiroXadrez tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

}
