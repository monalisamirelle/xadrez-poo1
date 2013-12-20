package br.edu.ifes.poo1.cln.cdp;

import java.util.ArrayList;
import java.util.List;

public class Jogador {
	/** Nome do jogador. */
	protected String nome;

	/** Cor do jogador. */
	protected CorJogador cor;

	/** Peças que o jogador já capturou. */
	List<Peca> pecasCapturadas = new ArrayList<Peca>();

	/** Tabuleiro no qual está jogando. */
	protected Tabuleiro tabuleiro;

	/**
	 * Constrói um jogador.
	 * 
	 * @param nome
	 *            Nome do jogador.
	 */
	public Jogador(CorJogador cor, Tabuleiro tabuleiro) {
		this.cor = cor;
		this.tabuleiro = tabuleiro;
	}

	/**
	 * Acrescenta uma peça, a lista de peças capturadas pelo jogador.
	 * 
	 * @param peca
	 *            Peça capturada.
	 */
	public void acrescentarPecaCapturada(Peca peca) {
		pecasCapturadas.add(peca);
	}

	public String getNome() {
		return nome;
	}

	public List<Peca> getPecasCapturadas() {
		return pecasCapturadas;
	}

	public CorJogador getCor() {
		return cor;
	}

}
