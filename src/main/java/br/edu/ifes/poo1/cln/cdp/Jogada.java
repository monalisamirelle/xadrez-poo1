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
	 * Inicia uma jogada, mas somente do tipo roque. Qualquer outro tipo de
	 * jogada indicado será recusado.
	 * 
	 * @param tipoRoque
	 *            Tipo da jogada que será feita. Deve ser estritamente um Roque
	 *            Menor ou um Roque Maior.
	 * @throws JogadaInvalidaException
	 */
	public Jogada(TipoJogada tipoRoque) throws JogadaInvalidaException {
		switch (tipoRoque) {
		case ANDAR:
		case ATACAR:
			throw new JogadaInvalidaException(
					"Para realizar uma jogada que não seja um Roque, você deve especificar a origem e o destino do movimento.");

		default:
			this.tipoJogada = tipoRoque;
			this.ehPromocao = false;
			break;
		}
	}

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
	 * Inicia uma jogada En passant que não inclua promoção
	 * 
	 * @param origem
	 * @param tipo
	 */
	public Jogada(Posicao origem, TipoJogada tipo) {
		this.origem = origem;
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

	/**
	 * Método de apoio ao programador que descreve uma jogada
	 */
	public void digaJogada() {
		System.out.println("Saia da coluna " + this.getOrigem().getColuna()
				+ " e linha " + this.getOrigem().getLinha()
				+ " e vá para a coluna " + this.getDestino().getColuna()
				+ " e linha " + this.getDestino().getLinha());
	}
}
