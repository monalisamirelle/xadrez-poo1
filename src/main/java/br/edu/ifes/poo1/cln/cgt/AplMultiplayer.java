package br.edu.ifes.poo1.cln.cgt;

import br.edu.ifes.poo1.cln.cdp.Jogador;
import br.edu.ifes.poo1.cln.cdp.Tabuleiro;

public class AplMultiplayer {
	private Jogador brancas;
	private Jogador pretas;
	private Tabuleiro tabuleiro;

	/**
	 * Inicia um jogo multiplayer.
	 * 
	 * @param nomeBrancas
	 *            Nome do jogador que controla as peças brancas.
	 * @param nomePretas
	 *            Nome do jogador que controla as peças pretas.
	 */
	public AplMultiplayer(String nomeBrancas, String nomePretas) {
		// Inicia os jogadores.
		this.brancas = new Jogador(nomeBrancas);
		this.pretas = new Jogador(nomePretas);
		
		// Inicia o tabuleiro com as peças necessárias.
		this.tabuleiro = new Tabuleiro(this.brancas, this.pretas);
	}
}
