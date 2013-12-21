package br.edu.ifes.poo1.cln.cgt;

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

	/**
	 * Processa a jogada recebida, executando-a no modelo.
	 * 
	 * @param jogada
	 *            Entrada do jogador que codifica a jogada que deverá ser
	 *            realizada.
	 * @throws JogadaInvalidaException
	 */
	@Override
	public void executarjogada(Jogada jogada) throws JogadaInvalidaException {
		// Pega o jogador do turno atual.
		Pessoa atualJogador = (Pessoa) getJogadorTurnoAtual();

		// Solicita-o que faça o movimento
		atualJogador.executarJogada(jogada);

		// TODO: Fazer verificação do Xeque e o Xeque Mate.

		super.trocarTurno();
	}
}
