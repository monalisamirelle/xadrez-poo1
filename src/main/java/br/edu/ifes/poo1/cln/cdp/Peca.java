package br.edu.ifes.poo1.cln.cdp;

/**
 * Representa uma peça qualquer do tabuleiro. Cada peça específica, como cavalo,
 * peão, rei, herdam desta classe e implementão as características específicas
 * do movimento.
 */
public abstract class Peca {
	/** Pontuação a qual a peça se equivale. */
	private int valor;

	/** Jogador que controla a peça. */
	private Jogador jogador;
	
	/** Tipo da peça */
	private TipoPeca tipoPeca;

	/** Posição onde a peça se encontra no tabuleiro. */
	// FIXME: Quem atualiza essa referência da peça? Ela mesma não tem como
	// saber, já que são outros que estão movendo ela. Talvez um bom lugar para
	// atualizar isso seria nos métodos de tabuleiro. Mas apesar de parecer o
	// melhor lugar para fazer tal coisa (uma vez que todo movimento das peças
	// passa pelo tabuleiro), isso parece ferir o encapsulamento da peça.
	protected Posicao posicao;

	/**
	 * Instancia um peça com o devido valor e o jogador que a controla.
	 * 
	 * @param valor
	 *            Pontuação a qual a peça se equivale.
	 * @param jogador
	 *            Jogador que detém a peça.
	 */
	public Peca(int valor, TipoPeca tipoPeca, Jogador jogador) {
		this.valor = valor;
		this.tipoPeca = tipoPeca;
		this.jogador = jogador;
	}

	/**
	 * Indica se esta peça pode se mover para a casa desejada. Este método será
	 * sobrescrito por cada uma das implementações de 'Peca'. Assim cada peça
	 * dirá exatamente se pode, ou não, se mover para a casa indicada.
	 * 
	 * @param casa
	 *            Casa para onde deseja-se andar a peça.
	 * @return Se é possível andar com a peça até a casa desejada.
	 */
	public abstract boolean podeAndar(Posicao posicao);

	/**
	 * Indica se esta peça pode atacar a casa desejada. Este método será
	 * sobrescrito por cada uma das implementações de 'Peca'. Assim cada peça
	 * dirá exatamente se pode ou não atacar a casa.
	 * 
	 * @param casa
	 *            Casa a qual deseja-se atacar.
	 * @return Se é possível atacar a casa desejada.
	 */
	public abstract boolean podeAtacar(Posicao posicao);

	public int getValor() {
		return valor;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public TipoPeca getTipoPeca() {
		return tipoPeca;
	}
}
