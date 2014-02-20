package br.edu.ifes.poo1.cln.cdp.ia;

import java.io.Serializable;

import br.edu.ifes.poo1.cln.cdp.Jogada;
import br.edu.ifes.poo1.cln.cdp.TabuleiroXadrez;

public class Estado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Jogada jogada;
	private TabuleiroXadrez tabuleiro;

	public Estado(Jogada jogada, TabuleiroXadrez tabuleiro) {
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
