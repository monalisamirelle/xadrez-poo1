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
	private Pessoa jogador;

	/** Tipo da peça */
	private TipoPeca tipoPeca;

	/** Indica se peça já se moveu em algum momento. */
	private boolean jaMoveu;

	/**
	 * Instancia um peça com o devido valor e o jogador que a controla.
	 * 
	 * @param valor
	 *            Pontuação a qual a peça se equivale.
	 * @param jogador
	 *            Jogador que detém a peça.
	 */
	public Peca(int valor, TipoPeca tipoPeca, Pessoa jogador) {
		this.valor = valor;
		this.jogador = jogador;
		this.tipoPeca = tipoPeca;
		this.jaMoveu = false;
	}

	/**
	 * Indica se esta peça pode se mover para a casa indicada. Este método será
	 * sobrescrito por cada uma das implementações de 'Peca'. Assim cada peça
	 * dirá exatamente se pode, ou não, se mover para a casa indicada.
	 * 
	 * @param origem
	 *            Posição atual da peça.
	 * @param destino
	 *            Posição para onde deseja-se andar a peça.
	 * @return Se é possível andar com a peça até a casa desejada.
	 */
	// FIXME: Usar 'Posicao' ao invés de 'Casa'. E trabalhar de forma adequada
	// com os parâmetros recebidos.
	public boolean podeAndar(Posicao origem, Posicao destino,
			Tabuleiro tabuleiro) {
		if (tabuleiro.saiuPosicao(origem, destino)
				&& !tabuleiro.atravessouTabuleiro(destino)
				&& tabuleiro.estaVazio(destino))
			return true;
		return false;
	}

	/**
	 * Indica se esta peça pode atacar a casa desejada. Este método será
	 * sobrescrito por cada uma das implementações de 'Peca'. Assim cada peça
	 * dirá exatamente se pode ou não atacar a casa.
	 * 
	 * @param origem
	 *            Posição atual da peça.
	 * @param destino
	 *            Local o qual deseja-se atacar com a peça.
	 * @return Se é possível usar esta peça para atacar a casa indicada.
	 */
	public boolean podeAtacar(Posicao origem, Posicao destino,
			Tabuleiro tabuleiro) {
		if (tabuleiro.saiuPosicao(origem, destino)
				&& !tabuleiro.atravessouTabuleiro(destino)
				&& tabuleiro.estaInimigo(this.jogador, destino))
			return true;
		return false;
	}

	/**
	 * Vê o valor absoluto de um movimento (utiliza o valor desejado menos o
	 * valor atual)
	 * 
	 * @param posicaoOcupada
	 * @param posicaoDesejada
	 * @return
	 */
	public int tamanhoMovimento(int posicaoOcupada, int posicaoDesejada) {
		return (Math.abs(posicaoOcupada - posicaoDesejada));
	}

	public int getValor() {
		return valor;
	}

	public Pessoa getJogador() {
		return jogador;
	}

	public TipoPeca getTipoPeca() {
		return tipoPeca;
	}

	public boolean getJaMoveu() {
		return jaMoveu;
	}

	/** Marca a peça como já movimentada. */
	public void setJaMoveu() {
		this.jaMoveu = true;
	}
}
