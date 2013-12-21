package br.edu.ifes.poo1.cln.cdp;

import java.util.List;

/**
 * Classe que representa uma partida. Contendo as informações de uma partida
 * como os jogadores, tabuleiro, quem foi o vencedor, etc. Também tem um
 * atributo estático que contém as partidas que já aconteceram.
 */
public class Partida {
	public static List<Partida> partidas;

	/** Jogador que controla as peças brancas. */
	private Jogador branco;

	/** Jogador que controla as peças pretas. */
	private Jogador preto;

	/** Tabuleiro que está sendo usado na partida */
	private Tabuleiro tabuleiro;

	/** Cor do jogador que deve jogar agora. */
	private Jogador turno;

	/** Indica se a partida já terminou. */
	private boolean jaTerminou;

	/** Somente é iniciado quando a partida termina. */
	private Jogador vencedor;

	/**
	 * Inicia uma partida.
	 * 
	 * @param branco
	 *            Jogador que controlará as brancas.
	 * @param preto
	 *            Jogador que controlará as pretas.
	 */
	public Partida(Jogador branco, Jogador preto) {
		super();
		this.branco = branco;
		this.preto = preto;

		// TODO: Criar um tabuleiro padrão de xadrez.
	}

	public Jogador getBranco() {
		return branco;
	}

	public Jogador getPreto() {
		return preto;
	}

	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	public Jogador getTurno() {
		return turno;
	}

	public boolean isJaTerminou() {
		return jaTerminou;
	}

	public Jogador getVencedor() {
		return vencedor;
	}
}
