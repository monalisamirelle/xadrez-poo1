package br.edu.ifes.poo1.cln.cgt;

import br.edu.ifes.poo1.cln.cdp.Bispo;
import br.edu.ifes.poo1.cln.cdp.CasaOcupadaException;
import br.edu.ifes.poo1.cln.cdp.Cavalo;
import br.edu.ifes.poo1.cln.cdp.CorJogador;
import br.edu.ifes.poo1.cln.cdp.Jogada;
import br.edu.ifes.poo1.cln.cdp.JogadaInvalidaException;
import br.edu.ifes.poo1.cln.cdp.Jogador;
import br.edu.ifes.poo1.cln.cdp.Peao;
import br.edu.ifes.poo1.cln.cdp.Posicao;
import br.edu.ifes.poo1.cln.cdp.Rainha;
import br.edu.ifes.poo1.cln.cdp.Rei;
import br.edu.ifes.poo1.cln.cdp.Tabuleiro;
import br.edu.ifes.poo1.cln.cdp.Torre;

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
		try {
			posicionarPecas();
		} catch (CasaOcupadaException e) {
			// Essa excessão nunca deveria ser lançada. Mas se for, o código em
			// 'posicionarPecas()' está errado.
			e.printStackTrace();
		}
	}

	/**
	 * Posiciona as peças no tabuleiro para o início da partida.
	 * 
	 * @throws CasaOcupadaException
	 */
	public void posicionarPecas() throws CasaOcupadaException {
		// Posiciona as peças brancas, exceto os peões.
		tabuleiro.colocarPeca(new Posicao(1, 1), new Torre(brancas));
		tabuleiro.colocarPeca(new Posicao(2, 1), new Cavalo(brancas));
		tabuleiro.colocarPeca(new Posicao(3, 1), new Bispo(brancas));
		tabuleiro.colocarPeca(new Posicao(4, 1), new Rainha(brancas));
		tabuleiro.colocarPeca(new Posicao(5, 1), new Rei(brancas));
		tabuleiro.colocarPeca(new Posicao(6, 1), new Bispo(brancas));
		tabuleiro.colocarPeca(new Posicao(7, 1), new Cavalo(brancas));
		tabuleiro.colocarPeca(new Posicao(8, 1), new Torre(brancas));

		// Posiciona os peões brancos.
		for (int coluna = 1; coluna <= 8; coluna++) {
			tabuleiro.colocarPeca(new Posicao(coluna, 2), new Peao(brancas));
		}

		// Posiciona as peças brancas, exceto os peões.
		tabuleiro.colocarPeca(new Posicao(1, 8), new Torre(pretas));
		tabuleiro.colocarPeca(new Posicao(2, 8), new Cavalo(pretas));
		tabuleiro.colocarPeca(new Posicao(3, 8), new Bispo(pretas));
		tabuleiro.colocarPeca(new Posicao(4, 8), new Rei(pretas));
		tabuleiro.colocarPeca(new Posicao(5, 8), new Rainha(pretas));
		tabuleiro.colocarPeca(new Posicao(6, 8), new Bispo(pretas));
		tabuleiro.colocarPeca(new Posicao(7, 8), new Cavalo(pretas));
		tabuleiro.colocarPeca(new Posicao(8, 8), new Torre(pretas));

		// Posiciona os peões pretos.
		for (int coluna = 1; coluna <= 8; coluna++) {
			tabuleiro.colocarPeca(new Posicao(coluna, 7), new Peao(pretas));
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
