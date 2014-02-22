package br.edu.ifes.poo1.cln.cdp;

import br.edu.ifes.poo1.cln.cdp.tipos.TipoCorJogador;
import br.edu.ifes.poo1.cln.cdp.tipos.TipoJogador;

/**
 * Representação de um jogador. Há no máximo 2 jogadores para o jogo de xadrez.
 */
public class Pessoa extends Jogador {
	public final int TOTAL_RECOMENDACOES = 3;
	private int recomendacoes;

	private static final long serialVersionUID = 1L;

	public Pessoa(String nome, TipoCorJogador cor) {
		super(nome, cor, TipoJogador.PESSOA);
		this.recomendacoes = 0;
	}

	public int getRecomendacoes() {
		return recomendacoes;
	}

	public void incrementarRecomendacoes() {
		this.recomendacoes = this.recomendacoes + 1;
	}

	public boolean verificarRecomendacoesRealizadas() {
		if (recomendacoes < TOTAL_RECOMENDACOES)
			return true;
		return false;
	}

}
