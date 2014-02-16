package br.edu.ifes.poo1.cln.cdp.pecas;

import br.edu.ifes.poo1.cln.cdp.CasaOcupadaException;
import br.edu.ifes.poo1.cln.cdp.Posicao;
import br.edu.ifes.poo1.cln.cdp.TabuleiroXadrez;
import br.edu.ifes.poo1.cln.cdp.TipoCorJogador;
import br.edu.ifes.poo1.cln.cdp.TipoPeca;

public class Rainha extends Peca {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia uma rainha.
	 */
	public Rainha(TipoCorJogador corJogador) {
		super(TipoPeca.RAINHA, corJogador);
	}

	@Override
	protected boolean podeSeMover(Posicao origem, Posicao destino,
			TabuleiroXadrez tabuleiro) throws CasaOcupadaException {
		if (super.podeSeMover(origem, destino, tabuleiro)
				&& tabuleiro.podeRealizarMovimentacao(origem, destino)) {
			if ((this.medeDeslocamentoPeca(origem.getLinha(), destino.getLinha()) > 0)
					|| (this.medeDeslocamentoPeca(origem.getColuna(),
							destino.getColuna()) > 0)) {
				return true;
			}
		}
		return false;
	}
}