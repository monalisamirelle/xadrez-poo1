package br.edu.ifes.poo1.cln.cgt;

import br.edu.ifes.poo1.cln.cdp.Jogador;
import br.edu.ifes.poo1.cln.cdp.Tabuleiro;

public class AplMultiplayer {
	private Jogador brancas;
	private Jogador pretas;
	private Tabuleiro tabuleiro;

	/** Indica de quem é a vez de realizar a próxima jogada */
	// FIXME: Dá para colocar um nome bem melhor. Só não consegui pensar nele
	// ainda.
	private Jogador vez;

	/** Indica se o jogo já acabou (true). Ou se está em andamento (false). */
	private boolean acabouOJogo;

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

	/**
	 * Processa a jogada recebida, executando-a no modelo.
	 * 
	 * @param jogada
	 *            Entrada do jogador que codifica a jogada que deverá ser
	 *            realizada.
	 */
	public void executarjogada(String jogada) {
		// TODO: Implementar.
	}

	/**
	 * Retorna o vencedor da partida, ou 'null' caso a partida não tenha
	 * terminado ainda.
	 * 
	 * @return O vencedor da partida.
	 */
	public Jogador getVencedor() {
		// TODO: Implementar.
	}

	/**
	 * Indica de quem é a vez de realizar a próxima jogada.
	 * 
	 * @return Jogador que deve realizar a próxima jogada.
	 */
	// FIXME: Adotar um nome melhor.
	public Jogador vez() {
		return this.vez;
	}

	/**
	 * Retorna se o jogo já acabou (true). Ou se ainda está em andamento
	 * (false).
	 * 
	 * @return Se o jogo já acabou.
	 */
	public boolean acabouOJogo() {
		return acabouOJogo;
	}
}
