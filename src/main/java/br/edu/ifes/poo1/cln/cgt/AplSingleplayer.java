package br.edu.ifes.poo1.cln.cgt;

import br.edu.ifes.poo1.cln.cdp.CorJogador;
import br.edu.ifes.poo1.cln.cdp.Jogada;
import br.edu.ifes.poo1.cln.cdp.JogadaInvalidaException;
import br.edu.ifes.poo1.cln.cdp.Jogador;
import br.edu.ifes.poo1.cln.cdp.Maquina;
import br.edu.ifes.poo1.cln.cdp.Pessoa;
import br.edu.ifes.poo1.cln.cdp.Tabuleiro;

public class AplSingleplayer extends AplJogo {

	/**
	 * Inicia uma nova partida singleplayer.
	 * 
	 * @param nomeJogador
	 *            Nome do jogador humano.
	 */
	public AplSingleplayer(Jogador jogadorBranco, Jogador jogadorPreto) {
		super(new Jogador(jogadorBranco.getNome(), CorJogador.BRANCO), new Jogador(
				jogadorPreto.getNome(), CorJogador.PRETO));
	}

	/**
	 * Continua um jogo singleplayer
	 * 
	 * @param jogadorBranco
	 * @param jogadorPreto
	 * @param tabuleiro
	 * @param branco
	 */
	public AplSingleplayer(Jogador jogadorBranco, Jogador jogadorPreto,
			Tabuleiro tabuleiro, CorJogador turno) {
		super(jogadorBranco, jogadorPreto, tabuleiro, turno);
	}

	@Override
	public void executarjogada(Jogada jogada) throws JogadaInvalidaException {
		// Solicita o humano que faça a jogada proposta.
		brancas.executarJogada(jogada);

		// Vê se o jogador conseguiu dar um Xeque Mate no oponente. E finaliza a
		// partida, caso tenha conseguido.
		if (tabuleiro.verificarXequeMate(super.getOutraCor(turno))) {
			finalizarPartida(getJogadorTurnoAtual(), false);
			return;
		}

		// Troca o turno.
		super.trocarTurno();

		// Pede a jogada da máquina.
		// TODO agora a máquina pode ser preta ou branca (mude isso)
		Jogada jogadaMaquina = ((Maquina) pretas).escolherJogada();

		// Executa a jogada da máquina.
		pretas.executarJogada(jogadaMaquina);

		// Vê se o jogador conseguiu dar um Xeque Mate no oponente. E finaliza a
		// partida, caso tenha conseguido.
		if (tabuleiro.verificarXequeMate(super.getOutraCor(turno))) {
			finalizarPartida(getJogadorTurnoAtual(), false);
			return;
		}

		// Troca novamente o turno.
		super.trocarTurno();
	}
}
