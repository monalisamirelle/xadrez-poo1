package br.edu.ifes.poo1.cln.cgt;

import br.edu.ifes.poo1.cln.cdp.CasaOcupadaException;
import br.edu.ifes.poo1.cln.cdp.CorJogador;
import br.edu.ifes.poo1.cln.cdp.Jogada;
import br.edu.ifes.poo1.cln.cdp.JogadaInvalidaException;
import br.edu.ifes.poo1.cln.cdp.Pessoa;

/**
 * Aplicação para o controle do modo multiplayer.
 */
public class AplMultiplayer extends AplJogo {

	/**
	 * Inicia um jogo multiplayer.
	 * 
	 * @param nomeBrancas
	 *            Nome do jogador que controla as peças brancas.
	 * @param nomePretas
	 *            Nome do jogador que controla as peças pretas.
	 */
	public AplMultiplayer(String nomeBrancas, String nomePretas) {
		super(new Pessoa(nomeBrancas, CorJogador.BRANCO), new Pessoa(
				nomePretas, CorJogador.PRETO));
	}

	@Override
	public void executarjogada(Jogada jogada) throws JogadaInvalidaException, CasaOcupadaException, CloneNotSupportedException {
		// Pega o jogador do turno atual.
		Pessoa atualJogador = (Pessoa) getJogadorTurnoAtual();

		// Solicita-o que faça o movimento
		atualJogador.executarJogada(jogada);

		// Vê se o jogador conseguiu dar um Xeque Mate no oponente. E finaliza a
		// partida, caso tenha conseguido.
		if (tabuleiro.verificarXequeMate(super.getOutraCor(turno))) {
			finalizarPartida(getJogadorTurnoAtual(), false);
			return;
		}

		super.trocarTurno();
	}
}
