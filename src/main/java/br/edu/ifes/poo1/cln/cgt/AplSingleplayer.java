package br.edu.ifes.poo1.cln.cgt;

import br.edu.ifes.poo1.cln.cdp.CorJogador;
import br.edu.ifes.poo1.cln.cdp.Jogada;
import br.edu.ifes.poo1.cln.cdp.JogadaInvalidaException;
import br.edu.ifes.poo1.cln.cdp.Maquina;
import br.edu.ifes.poo1.cln.cdp.Pessoa;

public class AplSingleplayer extends AplJogo {

	/**
	 * Inicia uma nova partida singleplayer.
	 * 
	 * @param nomeJogador
	 *            Nome do jogador humano.
	 */
	public AplSingleplayer(String nomeJogador, String nomeMaquina) {
		super(new Pessoa(nomeJogador, CorJogador.BRANCO), new Maquina(
				nomeMaquina, CorJogador.PRETO));
	}

	@Override
	public void executarjogada(Jogada jogada) throws JogadaInvalidaException,
			FimDeJogoException {
		// Solicita o humano que faça a jogada proposta.
		brancas.executarJogada(jogada);

		// Vê se o jogador conseguiu dar um Xeque Mate no oponente. E finaliza a
		// partida, caso tenha conseguido.
		if (tabuleiro.verificarXequeMate(super.getOutraCor(turno))) {
			finalizarPartida(getJogadorTurnoAtual());
			throw new FimDeJogoException(getJogadorTurnoAtual().getNome()
					+ "venceu a partida.");
		}

		// Troca o turno.
		super.trocarTurno();

		// Pede a jogada da máquina.
		Jogada jogadaMaquina = ((Maquina) pretas).escolherJogada();

		// Executa a jogada da máquina.
		pretas.executarJogada(jogadaMaquina);

		// Vê se o jogador conseguiu dar um Xeque Mate no oponente. E finaliza a
		// partida, caso tenha conseguido.
		if (tabuleiro.verificarXequeMate(super.getOutraCor(turno))) {
			finalizarPartida(getJogadorTurnoAtual());
			throw new FimDeJogoException(getJogadorTurnoAtual().getNome()
					+ "venceu a partida.");
		}

		// Troca novamente o turno.
		super.trocarTurno();
	}
}
