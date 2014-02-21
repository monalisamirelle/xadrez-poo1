package br.edu.ifes.poo1.cln.cdp.pecas;

import br.edu.ifes.poo1.cln.cdp.Posicao;
import br.edu.ifes.poo1.cln.cdp.TabuleiroXadrez;
import br.edu.ifes.poo1.cln.cdp.tipos.TipoCorJogador;
import br.edu.ifes.poo1.cln.cdp.tipos.TipoPeca;

public class Rei extends Peca {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia um rei.
	 */
	public Rei(TipoCorJogador corJogador) {
		// O rei nunca poderá ser capturado, então o seu valor não é relevante.
		super(TipoPeca.REI, corJogador);
	}

	@Override
	public boolean podeSeMover(Posicao origem, Posicao destino,
			TabuleiroXadrez tabuleiro) {
		
		// Verifica o movimento natural do rei.
		if (super.podeSeMover(origem, destino, tabuleiro)
				&& tabuleiro.podeRealizarMovimentacao(origem, destino))
			if ((this.medeDeslocamentoPeca(origem.getLinha(),
					destino.getLinha()) > 1)
					|| (this.medeDeslocamentoPeca(origem.getColuna(),
							destino.getColuna()) > 1))
				return false;
			else
				return true;
		return false;
	}
}