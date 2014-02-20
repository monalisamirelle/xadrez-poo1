package br.edu.ifes.poo1.cln.cdp.pecas;

import br.edu.ifes.poo1.cln.cdp.CasaOcupadaException;
import br.edu.ifes.poo1.cln.cdp.Posicao;
import br.edu.ifes.poo1.cln.cdp.TabuleiroXadrez;
import br.edu.ifes.poo1.cln.cdp.tipos.TipoCorJogador;
import br.edu.ifes.poo1.cln.cdp.tipos.TipoPeca;

public class Bispo extends Peca {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia um bispo.
	 */
	public Bispo(TipoCorJogador corJogador) {
		super(TipoPeca.BISPO, corJogador);
	}
	
	@Override
	protected boolean podeSeMover(Posicao origem, Posicao destino,
			TabuleiroXadrez tabuleiro) throws CasaOcupadaException {
		if (super.podeSeMover(origem, destino, tabuleiro)
				&& tabuleiro.podeRealizarMovimentacao(origem, destino))
			if (this.medeDeslocamentoPeca(origem.getLinha(), destino.getLinha()) == this
					.medeDeslocamentoPeca(origem.getColuna(), destino.getColuna()))
				return true;
		return false;
	}

}