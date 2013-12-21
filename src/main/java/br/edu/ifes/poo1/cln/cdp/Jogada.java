package br.edu.ifes.poo1.cln.cdp;

/**
 * Contém as informações para uma jogada a ser realizada.
 */
public class Jogada {
	/** Origem do movimento. */
	private Posicao origem;

	/** Destino do movimento. */
	private Posicao destino;

	/** Indica o tipo de jogada que deve ser realizada. */
	private TipoJogada tipoJogada;

	/** Se será necessária uma promoção de um peão (true), ou não (false). */
	private boolean ehPromocao;

	/** Para qual peça o peão deve ser promovido. */
	private TipoPeca tipoPromocao;

	/**
	 * Inicia uma jogada que não inclua a promoção de um peão.
	 * 
	 * @param origem
	 *            Posição da peça que será movida.
	 * @param destino
	 *            Destino do movimento.
	 * @param tipo
	 *            Indica o tipo de jogada que deve ser realizado.
	 */
	public Jogada(Posicao origem, Posicao destino, TipoJogada tipo) {
		this.origem = origem;
		this.destino = destino;
		this.tipoJogada = tipo;
		this.ehPromocao = false;
	}

	/**
	 * Inicia uma jogada que incluirá a promoção de um peão.
	 * 
	 * @param origem
	 *            Posição da peça que será movida.
	 * @param destino
	 *            Destino do movimento.
	 * @param tipo
	 *            Indica o tipo de jogada que deve ser realizado.
	 * @param promocao
	 *            O tipo de peça para o qual o peão será promovido.
	 */
	public Jogada(Posicao origem, Posicao destino, TipoJogada tipo,
			TipoPeca promocao) {
		this(origem, destino, tipo);

		this.ehPromocao = true; // Re-atribui o valor a variável.
		this.tipoPromocao = promocao;
	}

	public Posicao getOrigem() {
		return origem;
	}

	public Posicao getDestino() {
		return destino;
	}

	public TipoJogada getTipoJogada() {
		return tipoJogada;
	}

	public boolean ehPromocao() {
		return ehPromocao;
	}

	public TipoPeca getPromocao() {
		return tipoPromocao;
	}
}
