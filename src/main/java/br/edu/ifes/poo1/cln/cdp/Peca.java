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
	protected Casa casa;
	// Preciso referenciar algo nessa classe, mas não preciso ter uma nova instância de tabuleiro. O que fazer?
	Tabuleiro tabuleiro = new Tabuleiro();

	/**
	 * Indica se a peça já andou ou não
	 */
	protected boolean jaAndou;

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
	public boolean podeAndar(Casa casaDesejada) {
		if (tabuleiro.relizaMovimento(this.casa, casaDesejada)
				&& tabuleiro.atravessouTabuleiro(casaDesejada)
				&& tabuleiro.ehCasaVazia(this.casa, casaDesejada))
			return true;
		return false;
	}

	/**
	 * Indica se esta peça pode atacar a casa desejada. Este método será
	 * sobrescrito por cada uma das implementações de 'Peca'. Assim cada peça
	 * dirá exatamente se pode ou não atacar a casa.
	 * 
	 * @param casa
	 *            Casa a qual deseja-se atacar.
	 * @return Se é possível atacar a casa desejada.
	 */
	 public boolean podeAtacar(Casa casaDesejada) {
		if (tabuleiro.relizaMovimento(this.casa, casaDesejada)
				&& tabuleiro.atravessouTabuleiro(casaDesejada)
				&& tabuleiro.ehCasaPecaInimiga(this.casa, casaDesejada))
			return true;
		return false;
	}
	
	 /**
	  * Captura o valor da peça
	  * @return
	  */
	public int getValor() {
		return valor;
	}
	
	/**
	 * Vê o valor absoluto de um movimento (utiliza o valor desejado menos o valor atual)
	 * @param posicaoOcupada
	 * @param posicaoDesejada
	 * @return
	 */
	public int tamanhoMovimento(int posicaoOcupada, int posicaoDesejada) {
		return (Math.abs(posicaoOcupada - posicaoDesejada));
	}

	public Jogador getJogador() {
		return jogador;
	}

}
