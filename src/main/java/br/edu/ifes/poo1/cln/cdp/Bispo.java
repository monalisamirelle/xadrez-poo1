package br.edu.ifes.poo1.cln.cdp;

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
			Tabuleiro tabuleiro) throws CasaOcupadaException {
		if (super.podeSeMover(origem, destino, tabuleiro)
				&& tabuleiro.podeRealizarMovimentacao(origem, destino))
			if (this.deslocamentoPeca(origem.getLinha(), destino.getLinha()) == this
					.deslocamentoPeca(origem.getColuna(), destino.getColuna()))
				return true;
		return false;
	}

}