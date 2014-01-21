package br.edu.ifes.poo1.cln.cgt;

import br.edu.ifes.poo1.cln.cdp.CasaOcupadaException;
import br.edu.ifes.poo1.cln.cdp.CorJogador;
import br.edu.ifes.poo1.cln.cdp.Jogada;
import br.edu.ifes.poo1.cln.cdp.JogadaInvalidaException;
import br.edu.ifes.poo1.cln.cdp.Jogador;
import br.edu.ifes.poo1.cln.cdp.MotivoFimDaPartida;
import br.edu.ifes.poo1.cln.cdp.Tabuleiro;

public abstract class AplJogo {
	/** Jogador que controla as peças brancas. */
	protected Jogador brancas;

	/** Jogador que controla as peças pretas. */
	protected Jogador pretas;

	/** Tabuleiro do jogo. */
	protected Tabuleiro tabuleiro;

	/** Indica de quem é a vez de realizar a próxima jogada */
	protected CorJogador turno;

	/** Indica se o jogo já acabou (true). Ou se está em andamento (false). */
	private boolean acabouJogo = false;

	/** Só é instanciado ao término da partida. */
	private Jogador vencedor;

	/** Indica o motivo pelo qual a partida terminou. */
	private MotivoFimDaPartida motivoDeFinalizacao;

	/**
	 * Método construtor destinado a partidas que estão iniciando
	 * 
	 * @param brancas
	 * @param pretas
	 */
	public AplJogo(Jogador brancas, Jogador pretas) {
		// Pega os jogadores.
		this.brancas = brancas;
		this.pretas = pretas;

		// As brancas iniciam.
		this.turno = CorJogador.BRANCO;

		// Só está começando. ;)
		this.acabouJogo = false;

		// Inicia o tabuleiro, com as peças já posicionadas.
		this.tabuleiro = new Tabuleiro(brancas, pretas);

		// Informa os jogadores sobre qual o tabuleiro que está em uso na
		// partida.
		brancas.setTabuleiro(tabuleiro);
		pretas.setTabuleiro(tabuleiro);
	}

	/**
	 * Método construtor destinado a jogos que foram salvos mas se encontravam
	 * em andamento
	 * 
	 * @param brancas
	 * @param pretas
	 * @param tabuleiro
	 * @param turno
	 */
	public AplJogo(Jogador brancas, Jogador pretas, Tabuleiro tabuleiro,
			CorJogador turno) {
		// Pega os jogadores.
		this.brancas = brancas;
		this.pretas = pretas;

		// Turno em que a partida parou
		this.turno = turno;

		// Só está continuando. ;)
		this.acabouJogo = false;

		// Inicia o tabuleiro, com as peças já posicionadas.
		this.tabuleiro = tabuleiro;

		// Informa os jogadores sobre qual o tabuleiro que está em uso na
		// partida.
		brancas.setTabuleiro(tabuleiro);
		pretas.setTabuleiro(tabuleiro);
	}

	/**
	 * Processa a jogada recebida, executando-a no modelo.
	 * 
	 * @param jogada
	 *            Entrada do jogador que codifica a jogada que deverá ser
	 *            realizada.
	 * @throws JogadaInvalidaException
	 * @throws CasaOcupadaException
	 * @throws CloneNotSupportedException
	 * @throws FimDeJogoException
	 */
	public abstract void executarjogada(Jogada jogada)
			throws JogadaInvalidaException, CasaOcupadaException,
			CloneNotSupportedException;

	/**
	 * Retorna o vencedor da partida, ou 'null' caso a partida não tenha
	 * terminado ainda.
	 * 
	 * @return O vencedor da partida.
	 */
	public Jogador getVencedor() {
		return this.vencedor;
	}

	/**
	 * Indica de quem é a vez de realizar a próxima jogada.
	 * 
	 * @return Jogador que deve realizar a próxima jogada.
	 */
	public CorJogador getTurno() {
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

	/**
	 * Retorna a cor oposta a cor indicada.
	 * 
	 * @param cor
	 *            Cor da qual deseja-se a oposta.
	 * @return Cor oposta a cor indicada.
	 */
	public CorJogador getOutraCor(CorJogador cor) {
		if (cor == CorJogador.BRANCO)
			return CorJogador.PRETO;
		else
			return CorJogador.BRANCO;
	}

	/**
	 * Troca o jogador que está a jogar o turno atual.
	 */
	public void trocarTurno() {
		turno = getOutraCor(turno);
	}

	/**
	 * Retorna a instância do jogador que deve jogar o turno atual.
	 * 
	 * @return Jogador que deve jogar agora.
	 */
	public Jogador getJogadorTurnoAtual() {
		if (turno == CorJogador.BRANCO)
			return brancas;
		else
			return pretas;
	}

	/**
	 * Termina uma partida em que tenha havido um jogador vitorioso.
	 * 
	 * @param ganhador
	 *            ganhador da partida.
	 */
	public void finalizarPartida(Jogador ganhador, boolean houveDesistencia) {
		this.vencedor = ganhador;
		this.acabouJogo = true;
		if (houveDesistencia)
			this.motivoDeFinalizacao = MotivoFimDaPartida.DESISTENCIA;
		else
			this.motivoDeFinalizacao = MotivoFimDaPartida.VITORIA;
		// TODO: Salvar o histórico da partida.
	}

	/**
	 * Faz o término da partida, tendo havido um empate ou uma pausa.
	 */
	public void finalizarPartida(boolean jogoPausado) {
		if(jogoPausado)
			this.motivoDeFinalizacao = MotivoFimDaPartida.PAUSA;
		else{
		this.acabouJogo = true;
		this.motivoDeFinalizacao = MotivoFimDaPartida.EMPATE;
		}
		// TODO: Salvar o histórico da partida.
	}

	/**
	 * Indica o motivo pelo qual a partida acabou.
	 * 
	 * @return Motivo de finalização.
	 */
	public MotivoFimDaPartida getMotivoDeFinalizacao() {
		return this.motivoDeFinalizacao;
	}
}
