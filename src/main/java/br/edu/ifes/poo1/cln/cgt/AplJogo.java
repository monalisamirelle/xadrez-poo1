package br.edu.ifes.poo1.cln.cgt;

import java.io.Serializable;
import java.util.GregorianCalendar;

import br.edu.ifes.poo1.cln.cdp.CasaOcupadaException;
import br.edu.ifes.poo1.cln.cdp.Jogada;
import br.edu.ifes.poo1.cln.cdp.JogadaInvalidaException;
import br.edu.ifes.poo1.cln.cdp.Jogador;
import br.edu.ifes.poo1.cln.cdp.Tabuleiro;
import br.edu.ifes.poo1.cln.cdp.TipoCorJogador;
import br.edu.ifes.poo1.cln.cdp.TipoSituacaoPartida;

public class AplJogo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Jogador que controla as peças brancas. */
	private Jogador brancas;

	/** Jogador que controla as peças pretas. */
	private Jogador pretas;

	/** Tabuleiro do jogo. */
	private Tabuleiro tabuleiro;

	/** Indica de quem é a vez de realizar a próxima jogada */
	private TipoCorJogador turno;

	/** Indica se o jogo já acabou (true). Ou se está em andamento (false). */
	private boolean acabouJogo = false;

	/** Só é instanciado ao término da partida. */
	private Jogador vencedor;

	/** Captura a data de criação de uma partida */
	private GregorianCalendar dataCriacao;

	/** Captura se a partida, acontecendo em determinado momento, finalizou */
	private boolean sairPartida = false;

	/** Indica o motivo pelo qual a partida terminou. */
	private TipoSituacaoPartida motivoDeFinalizacao;

	/**
	 * Método construtor destinado a partidas que estão iniciando
	 * 
	 * @param brancas
	 * @param pretas
	 */
	public AplJogo(Jogador brancas, Jogador pretas) {

		// Captura a data de criação do jogo
		this.dataCriacao = new GregorianCalendar();

		// Pega os jogadores.
		this.brancas = brancas;
		this.pretas = pretas;

		// As brancas iniciam.
		this.turno = TipoCorJogador.BRANCO;

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
			TipoSituacaoPartida motivo, TipoCorJogador turno) {
		// Pega os jogadores.
		this.brancas = brancas;
		this.pretas = pretas;

		// Motivo fim da partida
		this.motivoDeFinalizacao = motivo;

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
	 * Método construtor para uma partida finalizada
	 * 
	 * @param brancas
	 * @param pretas
	 * @param tabuleiro
	 * @param nome
	 */
	public AplJogo(Jogador brancas, Jogador pretas, Tabuleiro tabuleiro,
			TipoSituacaoPartida motivo, String nomeVencedor) {
		// Pega os jogadores.
		this.brancas = brancas;
		this.pretas = pretas;

		// Motivo fim da partida
		this.motivoDeFinalizacao = motivo;

		// Turno em que a partida parou
		this.turno = null;

		// Partida terminou
		this.acabouJogo = true;

		// Inicia o tabuleiro, com as peças já posicionadas.
		this.tabuleiro = tabuleiro;

		// Informa o vencedor
		if (nomeVencedor.equals(brancas.getNome()))
			this.vencedor = brancas;
		else if (nomeVencedor.equals(pretas.getNome()))
			this.vencedor = pretas;
		else
			this.vencedor = null;

		// Informa os jogadores sobre qual o tabuleiro que está em uso na
		// partida.
		brancas.setTabuleiro(tabuleiro);
		pretas.setTabuleiro(tabuleiro);
	}

	/**
	 * Método construtor para uma partida finalizada por empate
	 * 
	 * @param brancas
	 * @param pretas
	 * @param tabuleiro
	 * @param nome
	 */
	public AplJogo(Jogador brancas, Jogador pretas, Tabuleiro tabuleiro,
			TipoSituacaoPartida motivo) {
		// Pega os jogadores.
		this.brancas = brancas;
		this.pretas = pretas;

		// Motivo fim da partida
		this.motivoDeFinalizacao = motivo;

		// Turno em que a partida parou
		this.turno = null;

		// Partida terminou
		this.acabouJogo = true;

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
	 * @throws FimDeJogoException
	 */
	public void executarJogadaTurno(Jogada jogada) throws CasaOcupadaException,
			JogadaInvalidaException {
		// Executa a jogada
		this.getJogadorTurnoAtual().executarJogada(jogada);

		// Vê se o jogador conseguiu dar um Xeque Mate no oponente. E finaliza a
		// partida, caso tenha conseguido.
		if (tabuleiro.verificarXequeMate(this.getOutraCor(turno))) {
			finalizarPartida(getJogadorTurnoAtual(),
					TipoSituacaoPartida.VITORIA);
			return;
		}
	}

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
	public TipoCorJogador getTurno() {
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

	public Jogador getJogadorBrancas() {
		return brancas;
	}

	public Jogador getJogadorPretas() {
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
	public TipoCorJogador getOutraCor(TipoCorJogador cor) {
		if (cor == TipoCorJogador.BRANCO)
			return TipoCorJogador.PRETO;
		else
			return TipoCorJogador.BRANCO;
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
		if (turno == TipoCorJogador.BRANCO)
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
	public void finalizarPartida(Jogador ganhador, TipoSituacaoPartida situacao) {
		this.vencedor = ganhador;
		this.acabouJogo = true;
		switch (situacao) {
		case VITORIA:
			this.motivoDeFinalizacao = TipoSituacaoPartida.VITORIA;
			break;
		case DESISTENCIA:
			this.motivoDeFinalizacao = TipoSituacaoPartida.DESISTENCIA;
			break;
		default:
			break;
		}
		this.sairPartida = true;
	}

	/**
	 * Faz o término da partida, tendo havido um empate ou uma pausa.
	 */
	public void finalizarPartida(TipoSituacaoPartida situacao) {
		switch (situacao) {
		case PAUSA:
			this.motivoDeFinalizacao = TipoSituacaoPartida.PAUSA;
			break;
		case EMPATE:
			this.acabouJogo = true;
			this.motivoDeFinalizacao = TipoSituacaoPartida.EMPATE;
			break;
		default:
			break;
		}
		this.sairPartida = true;
	}

	/**
	 * Indica o motivo pelo qual a partida acabou.
	 * 
	 * @return Motivo de finalização.
	 */
	public TipoSituacaoPartida getMotivoDeFinalizacao() {
		return this.motivoDeFinalizacao;
	}

	/**
	 * Retorna a data de criação de uma partida em formato de string
	 * 
	 * @return
	 */
	public GregorianCalendar getDataCriacao() {
		return this.dataCriacao;
	}

	public boolean isSairPartida() {
		return sairPartida;
	}

	public void setSairPartida() {
		this.sairPartida = true;
	}

}
