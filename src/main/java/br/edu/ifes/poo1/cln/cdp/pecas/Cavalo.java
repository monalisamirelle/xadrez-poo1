package br.edu.ifes.poo1.cln.cdp.pecas;

import br.edu.ifes.poo1.cln.cdp.CasaOcupadaException;
import br.edu.ifes.poo1.cln.cdp.Posicao;
import br.edu.ifes.poo1.cln.cdp.Tabuleiro;
import br.edu.ifes.poo1.cln.cdp.TipoCorJogador;
import br.edu.ifes.poo1.cln.cdp.TipoPeca;

public class Cavalo extends Peca {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia um cavalo.
	 */
	public Cavalo(TipoCorJogador corJogador) {
		super(TipoPeca.CAVALO, corJogador);
	}

	@Override
	protected boolean podeSeMover(Posicao origem, Posicao destino,
			Tabuleiro tabuleiro) throws CasaOcupadaException {
		if (super.podeSeMover(origem, destino, tabuleiro))
			if (this.deslocamentoPeca(origem.getLinha(), destino.getLinha()) == 1
					& this.deslocamentoPeca(origem.getColuna(),
							destino.getColuna()) == 2
					| this.deslocamentoPeca(origem.getLinha(),
							destino.getLinha()) == 2
					& this.deslocamentoPeca(origem.getColuna(),
							destino.getColuna()) == 1)
				return true;
		return false;
	}

}
