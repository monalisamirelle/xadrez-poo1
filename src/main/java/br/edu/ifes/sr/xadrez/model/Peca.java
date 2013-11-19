package br.edu.ifes.sr.xadrez.model;

import java.util.List;

public abstract class Peca {
	private int valor;
	private int numeroDeMovimentosRealizados = 0;
	private Posicao posicao;
	private Jogador jogador;

	public Peca(int valor, Jogador jogador) {
		this.valor = valor;
		this.jogador = jogador;
	}

	/**
	 * A peça anda para uma determinada posição no tabuleiro.
	 * 
	 * @param posicao
	 *            A posição para onde a peça deve andar.
	 * @throws JogadaInvalida
	 *             Lançada caso a peça não possa se mover para a posição
	 *             indicada.
	 */
	public void andar(Posicao posicao) throws JogadaInvalida {
		if (podeAndarPara(posicao)) {
			this.posicao = posicao;
			this.numeroDeMovimentosRealizados++;
		} else
			throw new JogadaInvalida(
					"Não é possível andar para a casa desejada");
	}

	/**
	 * A peça se move para a posição desejada e ataca a peça que estiver ali.
	 * 
	 * @param posicao
	 *            A posição a qual deseja-se atacar.
	 * @throws JogadaInvalida
	 *             Lançada quando não é possível que a peça ataque a casa
	 *             desejada.
	 */
	// TODO: Falta remover a peça derrotada.
	public void atacar(Posicao posicao) throws JogadaInvalida {
		if (podeAtacar(posicao)) {
			this.posicao = posicao;
			this.numeroDeMovimentosRealizados++;
		} else
			throw new JogadaInvalida("Não é possível atacar a casa desejada");
	}

	public boolean podeAndarPara(Posicao posicao) {

	}

	public boolean podeAtacar(Posicao posicao) {

	}
	
	public List<Posicao> podeAndarPara(Posicao posicao) {

	}

	public List<Posicao> podeAtacar(Posicao posicao) {

	}
}
