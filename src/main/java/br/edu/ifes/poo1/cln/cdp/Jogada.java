package br.edu.ifes.poo1.cln.cdp;

/**
 * Contém as informações para uma jogada a ser realizada.
 */
public class Jogada {
	/** Origem do movimento. */
	private Posicao origem;

	/** Destino do movimento. */
	private Posicao destino;

	/** Se a jogada é um ataque (true), ou se a peça deve andar apenas (false). */
	private boolean ehAtaque;

	/** Se será necessária uma promoção de um peão (true), ou não (false). */
	private boolean ehPromocao;

	/** Para qual peça o peão deve ser promovido. */
	private TipoPeca promocao;

	/**
	 * Inicia uma jogada que não inclua a promoção de um peão.
	 * 
	 * @param origem
	 *            Posição da peça que será movida.
	 * @param destino
	 *            Destino do movimento.
	 * @param ehAtaque
	 *            Indica se a jogada é um ataque (true) ou um movimento do tipo
	 *            andar (false).
	 */
	public Jogada(Posicao origem, Posicao destino, boolean ehAtaque) {
		this.origem = origem;
		this.destino = destino;
		this.ehAtaque = ehAtaque;
		this.ehPromocao = false;
	}

	/**
	 * Inicia uma jogada que inclua a promoção de um peão.
	 * 
	 * @param origem
	 *            Posição da peça que será movida.
	 * @param destino
	 *            Destino do movimento.
	 * @param ehAtaque
	 *            Indica se a jogada é um ataque (true) ou um movimento do tipo
	 *            andar (false).
	 * @param promocao
	 *            O tipo de peça para o qual o peão será promovido.
	 */
	public Jogada(Posicao origem, Posicao destino, boolean ehAtaque,
			TipoPeca promocao) {
		this(origem, destino, ehAtaque);

		this.ehPromocao = true; // Re-atribui o valor a variável.
		this.promocao = promocao;
	}

	public Posicao getOrigem() {
		return origem;
	}

	public Posicao getDestino() {
		return destino;
	}

	public boolean ehAtaque() {
		return ehAtaque;
	}

	public boolean ehPromocao() {
		return ehPromocao;
	}

	public TipoPeca getPromocao() {
		return promocao;
	}
}
