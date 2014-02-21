package br.edu.ifes.poo1.cln.cgt;

import java.io.Serializable;
import java.util.GregorianCalendar;

import br.edu.ifes.poo1.cln.cdp.CasaOcupadaException;
import br.edu.ifes.poo1.cln.cdp.Jogada;
import br.edu.ifes.poo1.cln.cdp.JogadaInvalidaException;
import br.edu.ifes.poo1.cln.cdp.Jogador;
import br.edu.ifes.poo1.cln.cdp.TabuleiroXadrez;
import br.edu.ifes.poo1.cln.cdp.pecas.Rainha;
import br.edu.ifes.poo1.cln.cdp.tipos.TipoCorJogador;
import br.edu.ifes.poo1.cln.cdp.tipos.TipoJogador;
import br.edu.ifes.poo1.cln.cdp.tipos.TipoSituacaoPartida;

// TODO muitos métodos construtores, precisam ser melhor
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
	private TabuleiroXadrez tabuleiro;

	/** Indica de quem é a vez de realizar a próxima jogada */
	private TipoCorJogador turno;

	/** Indica se o jogo já acabou (true). Ou se está em andamento (false). */
	private boolean acabouJogo = false;

	/**
	 * Captura se a partida, acontecendo em determinado momento, é interrompida
	 * (seja porque acabou o jogo ou não)
	 */
	private boolean sairPartida = false;

	/** Só é instanciado ao término da partida. */
	private String nomeVencedor;

	/** Captura a data de criação de uma partida */
	private GregorianCalendar dataCriacao;

	/** Indica o motivo pelo qual a partida terminou. */
	private TipoSituacaoPartida situacaoPartida;

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
		this.tabuleiro = new TabuleiroXadrez(brancas, pretas);

		// Inicia a situação da partida como andamento
		this.situacaoPartida = TipoSituacaoPartida.ANDAMENTO;

		// Informa os jogadores sobre qual o tabuleiro que está em uso na
		// partida.
		brancas.setTabuleiro(tabuleiro);
		pretas.setTabuleiro(tabuleiro);

	}

	/**
	 * Método construtor destinado a jogos que foram salvos mas se encontravam
	 * em andamento ou pausados
	 * 
	 * @param brancas
	 * @param pretas
	 * @param tabuleiro
	 * @param turno
	 */
	public AplJogo(Jogador brancas, Jogador pretas, TabuleiroXadrez tabuleiro,
			TipoCorJogador turno) {
		// Pega os jogadores.
		this.brancas = brancas;
		this.pretas = pretas;

		// Modifica a situação da partida
		this.situacaoPartida = TipoSituacaoPartida.ANDAMENTO;

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
	public AplJogo(Jogador brancas, Jogador pretas, TabuleiroXadrez tabuleiro,
			TipoSituacaoPartida motivo, String nomeVencedor) {
		// Pega os jogadores.
		this.brancas = brancas;
		this.pretas = pretas;

		// Motivo fim da partida
		this.situacaoPartida = motivo;

		// Turno em que a partida parou
		this.turno = null;

		// Partida terminou
		this.acabouJogo = true;

		// Inicia o tabuleiro, com as peças já posicionadas.
		this.tabuleiro = tabuleiro;

		// Informa o vencedor
		if (nomeVencedor.equals(brancas.getNome()))
			this.nomeVencedor = brancas.getNome();
		else if (nomeVencedor.equals(pretas.getNome()))
			this.nomeVencedor = pretas.getNome();
		else
			this.nomeVencedor = brancas.getNome() + "/" + pretas.getNome();

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
	// TODO verificar necessidade
	public AplJogo(Jogador brancas, Jogador pretas, TabuleiroXadrez tabuleiro,
			TipoSituacaoPartida motivo) {
		// Pega os jogadores.
		this.brancas = brancas;
		this.pretas = pretas;

		// Motivo fim da partida
		this.situacaoPartida = motivo;

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

		// Se o jogador for uma máquina, realize a modificação da promoção
		if (this.getJogadorTurnoAtual().getTipoJogador() == TipoJogador.IAELABORADA
				|| this.getJogadorTurnoAtual().getTipoJogador() == TipoJogador.IARANDOMICA)
			modificarMaquinaPromocao(jogada);

		// Reseta as propriedades que controlam o en passant.
		tabuleiro.resetaPodeEnPassant(TipoCorJogador.getCorOposta(turno));

		// Vê se o jogador conseguiu dar um Xeque Mate no oponente. E finaliza a
		// partida, caso tenha conseguido.
		if (tabuleiro.verificarXequeMate(this.getOponente().getCor())) {
			finalizarPartida(getJogadorTurnoAtual(),
					TipoSituacaoPartida.VITORIA);
			return;
		}
	}

	/**
	 * Método que modifica a peça da máquina na promoção
	 * 
	 * @param jogada
	 * @throws CasaOcupadaException
	 */
	private void modificarMaquinaPromocao(Jogada jogada)
			throws CasaOcupadaException {
		if (jogada.ehPromocao()) {
			tabuleiro.retirarPeca(jogada.getDestino());
			tabuleiro.colocarPeca(jogada.getDestino(), new Rainha(this
					.getJogadorTurnoAtual().getCor()));
		}
	}

	/**
	 * Retorna o vencedor da partida, ou 'null' caso a partida não tenha
	 * terminado ainda.
	 * 
	 * @return O vencedor da partida.
	 */
	public String getNomeVencedor() {
		return this.nomeVencedor;
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

	public TabuleiroXadrez getTabuleiro() {
		return tabuleiro;
	}

	/**
	 * Troca o jogador que está a jogar o turno atual.
	 */
	public void trocarTurno() {
		turno = TipoCorJogador.getCorOposta(turno);
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
	 * @param vencedor
	 *            ganhador da partida.
	 */
	public void finalizarPartida(Jogador vencedor, TipoSituacaoPartida situacao) {
		this.nomeVencedor = vencedor.getNome();
		this.acabouJogo = true;
		switch (situacao) {
		case VITORIA:
			this.situacaoPartida = TipoSituacaoPartida.VITORIA;
			break;
		case DESISTENCIA:
			this.situacaoPartida = TipoSituacaoPartida.DESISTENCIA;
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
		this.nomeVencedor = brancas.getNome() + "/" + pretas.getNome();
		switch (situacao) {
		case PAUSA:
			this.situacaoPartida = TipoSituacaoPartida.PAUSA;
			break;
		case EMPATE:
			this.acabouJogo = true;
			this.situacaoPartida = TipoSituacaoPartida.EMPATE;
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
	public TipoSituacaoPartida getSituacaoPartida() {
		return this.situacaoPartida;
	}

	/**
	 * Método que retornar o oponente do jogador atual
	 */
	public Jogador getOponente() {
		if (this.getTurno() == TipoCorJogador.BRANCO)
			return pretas;
		else
			return brancas;
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

	public void setSairPartida(boolean sairPartida) {
		this.sairPartida = sairPartida;
	}

}
