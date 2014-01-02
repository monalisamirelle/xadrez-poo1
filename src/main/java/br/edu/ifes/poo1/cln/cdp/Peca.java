package br.edu.ifes.poo1.cln.cdp;

import java.util.ArrayList;

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
	public Peca(int valor, TipoPeca tipoPeca, Jogador jogador) {
		this.valor = valor;
		this.jogador = jogador;
		this.tipoPeca = tipoPeca;
		this.jaMoveu = false;
	}

	/**
	 * Teste
	 * 
	 * @param valor
	 * @param tipoPeca
	 * @param jogador
	 * @param jaMoveu
	 */
	public Peca(Peca peca) {
		this.valor = peca.valor;
		this.jogador = peca.jogador;
		this.tipoPeca = peca.tipoPeca;
		this.jaMoveu = true;
	}

	/**
	 * Indica se esta peça pode se mover para a casa indicada. Este método será
	 * sobrescrito por cada uma das implementações de 'Peca'. Assim cada peça
	 * dirá exatamente se pode, ou não, se mover para a casa indicada.
	 * 
	 * @param origem
	 *            Posição atual da peça.
	 * @param destino
	 *            Posição para onde a peça deve ser movida.
	 * @return Se é possível andar com a peça até a casa desejada.
	 */
	public boolean podeAndar(Posicao origem, Posicao destino,
			Tabuleiro tabuleiro) {
		// Puramente verifica se a peça pode se mover para o local indicado. No
		// caso do peão, este método será sobrescrito, pois anda de forma
		// diferente a que ataca.
		return podeSeMover(origem, destino, tabuleiro);
	}

	/**
	 * Indica se esta peça pode atacar a casa desejada. Este método será
	 * sobrescrito por cada uma das implementações de 'Peca'. Assim cada peça
	 * dirá exatamente se pode ou não atacar a casa.
	 * 
	 * @param origem
	 *            Posição atual da peça.
	 * @param destino
	 *            Local a ser atacado pela peça.
	 * @return Se é possível usar esta peça para atacar a casa indicada.
	 */
	public boolean podeAtacar(Posicao origem, Posicao destino,
			Tabuleiro tabuleiro) {
		// Puramente verifica se a peça pode se mover para o local indicado. No
		// caso do peão, este método será sobrescrito, pois anda de forma
		// diferente a que ataca.
		return podeSeMover(origem, destino, tabuleiro);
	}

	/**
	 * Indica se está peça alcança o destino saindo de sua origem, ao longo do
	 * tabuleiro.
	 * 
	 * @param origem
	 *            Posição da qual a peça está saindo.
	 * @param destino
	 *            Posicao em que a peça deve chegar.
	 * @param tabuleiro
	 *            Tabuleiro em que será feita movimentação.
	 * @return Se a peça pode se mover.
	 */
	protected boolean podeSeMover(Posicao origem, Posicao destino,
			Tabuleiro tabuleiro) {
		// As posições devem ser internas ao tabuleiro.
		if (Tabuleiro.estaForaDoTabuleiro(origem)
				|| Tabuleiro.estaForaDoTabuleiro(destino))
			return false;

		// Não é um movimento se as posições forem as mesmas.
		if (origem.equals(destino))
			return false;
		else
			return true;
	}

	/**
	 * Vê o valor absoluto de um movimento horizontal ou vertical (utiliza o
	 * valor desejado menos o valor atual)
	 * 
	 * @param posicaoOcupada
	 * @param posicaoDesejada
	 * @return
	 */
	protected int tamanhoMovimento(int posicaoOcupada, int posicaoDesejada) {
		return (Math.abs(posicaoOcupada - posicaoDesejada));
	}

	/**
	 * Gera uma lista com todas as jogadas que uma peça pode realizar
	 * 
	 * @param posicaoOrigem
	 * @param tabuleiro
	 * @return
	 */
	public ArrayList<Jogada> jogadasPeca(Posicao posicaoOrigem,
			Tabuleiro tabuleiro) {
		ArrayList<Jogada> listaJogadas = new ArrayList<Jogada>();
		// Caminhando pelo tabuleiro
		for (int coluna = 1; coluna <= 8; coluna++)
			for (int linha = 1; linha <= 8; linha++) {
				// Se a peça puder se movimentar para uma posição
				if (this.podeAndar(posicaoOrigem, new Posicao(coluna, linha),
						tabuleiro) == true
						&& tabuleiro.estaVazio(new Posicao(coluna, linha)))
					listaJogadas.add(new Jogada(posicaoOrigem, new Posicao(
							coluna, linha), TipoJogada.ANDAR));
				// Se a peça puder atacar uma posição
				if (this.podeAtacar(posicaoOrigem, new Posicao(coluna, linha),
						tabuleiro) == true
						&& tabuleiro.estaInimigo(this.getJogador().getCor(),
								new Posicao(coluna, linha)))
					listaJogadas.add(new Jogada(posicaoOrigem, new Posicao(
							coluna, linha), TipoJogada.ATACAR));

			}
		// TODO Promoção
		/**
		 * if(this.getTipoPeca()==tipoPeca.PEAO)
		 * if(this.getJogador().getCor()==CorJogador.BRANCO)
		 * if(posicaoOrigem.getLinha())
		 */
		return listaJogadas;
	}

	/**
	 * Cria uma nova instancia de uma peça, conforme o seu tipo (clona)
	 * 
	 * @return
	 */
	public Peca novaInstancia() {
		switch (this.getTipoPeca()) {
		case BISPO:
			return new Bispo(this);
		case CAVALO:
			return new Cavalo(this);
		case PEAO:
			return new Peao(this);
		case RAINHA:
			return new Rainha(this);
		case REI:
			return new Rei(this);
		case TORRE:
			return new Torre(this);
		default:
			return null;
		}
	}

	public int getValor() {
		return valor;
	}

	public Jogador getJogador() {
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
