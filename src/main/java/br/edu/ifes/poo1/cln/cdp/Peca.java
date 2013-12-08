package br.edu.ifes.poo1.cln.cdp;

/**
 * Representa uma peça qualquer do tabuleiro. Cada peça específica, como cavalo,
 * peão, rei, herdam desta classe e implementão as características específicas
 * do movimento.
 * 
 * @author lucas
 * 
 */
public abstract class Peca {
	/**
	 * Pontuação a qual a peça se equivale.
	 */
	private int valor;
	private Jogador jogador;

	public Peca(int valor, Jogador jogador) {
		this.valor = valor;
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
	public abstract boolean podeAndar(Casa casa);

	/**
	 * Indica se esta peça pode atacar a casa desejada. Este método será
	 * sobrescrito por cada uma das implementações de 'Peca'. Assim cada peça
	 * dirá exatamente se pode ou não atacar a casa.
	 * 
	 * @param casa
	 *            Casa a qual deseja-se atacar.
	 * @return Se é possível atacar a casa desejada.
	 */
	public abstract boolean podeAtacar(Casa casa);

	public int getValor() {
		return valor;
	}

}
