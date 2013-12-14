package br.edu.ifes.poo1.cln.cdp;

/**
 * Contém as informações para uma jogada a ser realizada.
 */
public class Jogada {
	private Posicao origem;
	private Posicao destino;
	private boolean ehAtaque;
	private boolean ehPromocao;
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
	
	public boolean isEhAtaque() {
		return ehAtaque;
	}
	
	public boolean isEhPromocao() {
		return ehPromocao;
	}
	
	public TipoPeca getPromocao() {
		return promocao;
	}
}
