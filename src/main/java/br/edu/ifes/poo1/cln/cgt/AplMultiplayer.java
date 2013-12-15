package br.edu.ifes.poo1.cln.cgt;

import br.edu.ifes.poo1.cln.cdp.CasaOcupadaException;
import br.edu.ifes.poo1.cln.cdp.CorJogador;
import br.edu.ifes.poo1.cln.cdp.Jogada;
import br.edu.ifes.poo1.cln.cdp.JogadaInvalidaException;
import br.edu.ifes.poo1.cln.cdp.Jogador;
import br.edu.ifes.poo1.cln.cdp.Palhaco;
import br.edu.ifes.poo1.cln.cdp.Posicao;
import br.edu.ifes.poo1.cln.cdp.Tabuleiro;

/**
 * Aplicação para o controle do modo multiplayer.
 */
public class AplMultiplayer {
	/** Jogador que controla as peças brancas. */
	private Jogador brancas;

	/** Jogador que controla as peças pretas. */
	private Jogador pretas;

	/** Tabuleiro do jogo. */
	private Tabuleiro tabuleiro;

	/** Indica de quem é a vez de realizar a próxima jogada */
	private Jogador turno;

	/** Indica se o jogo já acabou (true). Ou se está em andamento (false). */
	private boolean acabouJogo = false;

	/**
	 * Inicia um jogo multiplayer.
	 * 
	 * @param nomeBrancas
	 *            Nome do jogador que controla as peças brancas.
	 * @param nomePretas
	 *            Nome do jogador que controla as peças pretas.
	 */
	public AplMultiplayer(String nomeBrancas, String nomePretas) {
		// Inicia o tabuleiro com as peças necessárias.
		this.tabuleiro = new Tabuleiro();

		// Inicia os jogadores.
		this.brancas = new Jogador(nomeBrancas, CorJogador.BRANCO,
				this.tabuleiro);
		this.pretas = new Jogador(nomePretas, CorJogador.PRETO, this.tabuleiro);

		// Coloca as brancas para iniciarem.
		this.turno = brancas;
		
		// Posiciona as peças nos devidos lugares.
		posicionarPecas();
	}

	/**
	 * Posiciona as peças no tabuleiro para o início da partida.
	 */
	// FIXME: Refazer isso aqui tudo de forma que não use as peças palhaço. Mas
	// crie um tabuleiro padrão de xadrez.
	public void posicionarPecas() {
		// Posiciona as peças brancas.
		for (int c = 1; c <= 8; c++) { // Coluna
			try {
				tabuleiro.colocarPeca(new Posicao(c, 1), new Palhaco(brancas));
			} catch (CasaOcupadaException e) {
				// Não deveria haver casa sendo sobrescrita com peças na etapa
				// de construção do tabuleiro. Se isso acontecer, o código deve
				// ser verificado.
				System.out.println("Tela azul!!!");
			}
		}

		// Posiciona as peças pretas.
		for (int c = 1; c <= 8; c++) { // Coluna
			try {
				tabuleiro.colocarPeca(new Posicao(c, 8), new Palhaco(pretas));
			} catch (CasaOcupadaException e) {
				// Não deveria haver casa sendo sobrescrita com peças na etapa
				// de construção do tabuleiro. Se isso acontecer, o código deve
				// ser verificado.
				System.out.println("Tela azul!!!");
			}
		}
	}

	/**
	 * Processa a jogada recebida, executando-a no modelo.
	 * 
	 * @param jogada
	 *            Entrada do jogador que codifica a jogada que deverá ser
	 *            realizada.
	 * @throws JogadaInvalidaException
	 */
	public void executarjogada(Jogada jogada) throws JogadaInvalidaException {
		// Pega o jogador do turno atual.
		Jogador atualJogador = this.turno;
		
		// Solicita-o que faça o movimento
		atualJogador.movimentarPeca(jogada);
		
		// Troca para o próximo jogador.
		if (turno == brancas)
			turno = pretas;
		else
			turno = brancas;
	}

	/**
	 * Retorna o vencedor da partida, ou 'null' caso a partida não tenha
	 * terminado ainda.
	 * 
	 * @return O vencedor da partida.
	 */
	public Jogador getVencedor() {
		return this.brancas;
		// TODO: Implementar.
	}

	/**
	 * Indica de quem é a vez de realizar a próxima jogada.
	 * 
	 * @return Jogador que deve realizar a próxima jogada.
	 */
	public Jogador getTurno() {
		return this.turno;
	}

	/**
	 * Retorna se o jogo já acabou (true). Ou se ainda está em andamento
	 * (false).
	 * 
	 * @return Se o jogo já acabou.
	 */
	public boolean getAcabouJogo() {
		return acabouJogo;
	}

	public Jogador getBrancas() {
		return brancas;
	}

	public Jogador getPretas() {
		return pretas;
	}

	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
}
