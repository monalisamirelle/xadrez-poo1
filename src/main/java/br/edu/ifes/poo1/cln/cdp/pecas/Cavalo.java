package br.edu.ifes.poo1.cln.cdp.pecas;

import br.edu.ifes.poo1.cln.cdp.CasaOcupadaException;
import br.edu.ifes.poo1.cln.cdp.Posicao;
import br.edu.ifes.poo1.cln.cdp.TabuleiroXadrez;
import br.edu.ifes.poo1.cln.cdp.tipos.TipoCorJogador;
import br.edu.ifes.poo1.cln.cdp.tipos.TipoPeca;

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
			TabuleiroXadrez tabuleiro) throws CasaOcupadaException {
		if (super.podeSeMover(origem, destino, tabuleiro))
			if (this.medeDeslocamentoPeca(origem.getLinha(), destino.getLinha()) == 1
					& this.medeDeslocamentoPeca(origem.getColuna(),
							destino.getColuna()) == 2
					| this.medeDeslocamentoPeca(origem.getLinha(),
							destino.getLinha()) == 2
					& this.medeDeslocamentoPeca(origem.getColuna(),
							destino.getColuna()) == 1)
				return true;
		return false;
	}

}
