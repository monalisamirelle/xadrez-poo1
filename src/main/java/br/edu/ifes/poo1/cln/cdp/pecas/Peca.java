package br.edu.ifes.poo1.cln.cdp.pecas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import br.edu.ifes.poo1.cln.cdp.CasaOcupadaException;
import br.edu.ifes.poo1.cln.cdp.Jogada;
import br.edu.ifes.poo1.cln.cdp.Posicao;
import br.edu.ifes.poo1.cln.cdp.TabuleiroXadrez;
import br.edu.ifes.poo1.cln.cdp.tipos.TipoCorJogador;
import br.edu.ifes.poo1.cln.cdp.tipos.TipoJogada;
import br.edu.ifes.poo1.cln.cdp.tipos.TipoPeca;

/**
 * Representa uma peça qualquer do tabuleiro. Cada peça específica, como cavalo,
 * peão, rei, herdam desta classe e implementão as características específicas
 * do movimento.
 */
public abstract class Peca implements Comparable<Peca>, Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Jogador que controla a peça. */
	private TipoCorJogador corJogador;

	/** Tipo da peça */
	private TipoPeca tipoPeca;

	/** Indica se peça já se moveu em algum momento. */
	private boolean jaMoveu;

	/**
	 * Instancia um peça com o jogador que a controla.
	 * 
	 * @param valor
	 *            Pontuação a qual a peça se equivale.
	 * @param jogador
	 *            Jogador que detém a peça.
	 */
	public Peca(TipoPeca tipoPeca, TipoCorJogador corJogador) {
		this.corJogador = corJogador;
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
	 *            Posição para onde a peça deve ser movida.
	 * @return Se é possível andar com a peça até a casa desejada.
	 */
	public boolean podeAndar(Posicao origem, Posicao destino,
			TabuleiroXadrez tabuleiro) {
		// Puramente verifica se a peça pode se mover para o local indicado. No
		// caso do peão, este método será sobrescrito, pois anda de forma
		// diferente a que ataca.
		if (podeSeMover(origem, destino, tabuleiro))
			return tabuleiro.estaVazio(destino);
		else
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
	 *            Local a ser atacado pela peça.
	 * @return Se é possível usar esta peça para atacar a casa indicada.
	 */
	public boolean podeAtacar(Posicao origem, Posicao destino,
			TabuleiroXadrez tabuleiro) {
		// Puramente verifica se a peça pode se mover para o local indicado. No
		// caso do peão, este método será sobrescrito, pois anda de forma
		// diferente a que ataca.
		if (podeSeMover(origem, destino, tabuleiro))
			return tabuleiro.estaInimigo(this.getCorJogador(), destino);
		else
			return false;
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
			TabuleiroXadrez tabuleiro) {
		// As posições devem ser internas ao tabuleiro.
		if (TabuleiroXadrez.estaForaDoTabuleiro(origem)
				|| TabuleiroXadrez.estaForaDoTabuleiro(destino))
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
	public int medeDeslocamentoPeca(int posicaoOcupada, int posicaoDesejada) {
		return (Math.abs(posicaoOcupada - posicaoDesejada));
	}

	/**
	 * Gera uma lista com todas as jogadas que uma peça pode realizar
	 * 
	 * @param posicaoOrigem
	 * @param tabuleiro
	 * @return
	 * @throws CasaOcupadaException
	 */
	public List<Jogada> jogadasPeca(Posicao posicaoOrigem,
			TabuleiroXadrez tabuleiro) throws CasaOcupadaException {
		List<Jogada> listaJogadas = new ArrayList<Jogada>();
		// Caminhando pelo tabuleiro
		for (int coluna = TabuleiroXadrez.COLUNAINFERIOR; coluna <= TabuleiroXadrez.COLUNASUPERIOR; coluna++)
			for (int linha = TabuleiroXadrez.LINHAINFERIOR; linha <= TabuleiroXadrez.LINHASUPERIOR; linha++) {
				// Se a peça puder se movimentar para uma posição
				if (this.podeAndar(posicaoOrigem, new Posicao(coluna, linha),
						tabuleiro) == true
						&& tabuleiro.estaVazio(new Posicao(coluna, linha)))
					if (tabuleiro.ehPromocao(posicaoOrigem))
						listaJogadas.add(new Jogada(posicaoOrigem, new Posicao(
								coluna, linha), TipoJogada.ANDAR,
								TipoPeca.RAINHA));
					else
						listaJogadas.add(new Jogada(posicaoOrigem, new Posicao(
								coluna, linha), TipoJogada.ANDAR));
				// Se a peça puder atacar uma posição
				if (this.podeAtacar(posicaoOrigem, new Posicao(coluna, linha),
						tabuleiro) == true
						&& tabuleiro.estaInimigo(this.getCorJogador(),
								new Posicao(coluna, linha)))
					if (tabuleiro.ehPromocao(posicaoOrigem))
						listaJogadas.add(new Jogada(posicaoOrigem, new Posicao(
								coluna, linha), TipoJogada.ATACAR,
								TipoPeca.RAINHA));
					else
						listaJogadas.add(new Jogada(posicaoOrigem, new Posicao(
								coluna, linha), TipoJogada.ATACAR));
			}
		// Se puder En Passant a esquerda
		if (tabuleiro.ehEnPassantEsquerda(posicaoOrigem))
			listaJogadas.add(new Jogada(posicaoOrigem,
					TipoJogada.EN_PASSANT_ESQUERDA));
		// Se puder En Passant a direita
		if (tabuleiro.ehEnPassantDireita(posicaoOrigem))
			listaJogadas.add(new Jogada(posicaoOrigem,
					TipoJogada.EN_PASSANT_DIREITA));
		return listaJogadas;
	}

	/**
	 * Clona uma peça
	 */
	public Peca clone() {
		Peca novaPeca = null;
		try {
			novaPeca = (Peca) super.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println("Peça não foi clonada");
		}
		return novaPeca;
	}

	public static Peca criaPeca(String peca, TipoCorJogador corJogador) {
		if (peca.equals("BISPO"))
			return new Bispo(corJogador);
		else if (peca.equals("CAVALO"))
			return new Cavalo(corJogador);
		else if (peca.equals("PEAO"))
			return new Peao(corJogador);
		else if (peca.equals("REI"))
			return new Rei(corJogador);
		else if (peca.equals("RAINHA"))
			return new Rainha(corJogador);
		else if (peca.equals("TORRE"))
			return new Torre(corJogador);
		else
			return null;
	}

	/**
	 * Captura o valor de uma peça
	 * 
	 * @return
	 */
	public int getValor() {
		return tipoPeca.getValor();
	}

	public TipoCorJogador getCorJogador() {
		return corJogador;
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

	public int compareTo(Peca outra) {
		if (this.getValor() > outra.getValor()) {
			return 1;
		} else if (this.getValor() == outra.getValor()) {
			return 0;
		} else {
			return -1;
		}

	}

}
