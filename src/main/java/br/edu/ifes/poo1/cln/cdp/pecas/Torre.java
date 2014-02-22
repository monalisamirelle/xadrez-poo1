package br.edu.ifes.poo1.cln.cdp.pecas;

import br.edu.ifes.poo1.cln.cdp.Posicao;
import br.edu.ifes.poo1.cln.cdp.TabuleiroXadrez;
import br.edu.ifes.poo1.cln.cdp.tipos.TipoCorJogador;
import br.edu.ifes.poo1.cln.cdp.tipos.TipoPeca;

public class Torre extends Peca {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia uma torre.
	 */
	public Torre(TipoCorJogador corJogador) {
		super(TipoPeca.TORRE, corJogador);
	}

	@Override
	public boolean podeSeMover(Posicao origem, Posicao destino,
			TabuleiroXadrez tabuleiro) {
		if (super.podeSeMover(origem, destino, tabuleiro)
				&& tabuleiro.podeRealizarMovimentacao(origem, destino))
			if ((this.medeDeslocamentoPeca(origem.getLinha(),
					destino.getLinha()) == 0)
					&& (this.medeDeslocamentoPeca(origem.getColuna(),
							destino.getColuna()) > 0)
					|| (this.medeDeslocamentoPeca(origem.getLinha(),
							destino.getLinha()) > 0)
					&& (this.medeDeslocamentoPeca(origem.getColuna(),
							destino.getColuna()) == 0))
				return true;
		return false;
	}
}