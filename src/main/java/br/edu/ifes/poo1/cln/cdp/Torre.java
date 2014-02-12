package br.edu.ifes.poo1.cln.cdp;

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
			Tabuleiro tabuleiro) throws CasaOcupadaException {
		if (super.podeSeMover(origem, destino, tabuleiro)
				&& tabuleiro.podeRealizarMovimentacao(origem, destino))
			if ((this.deslocamentoPeca(origem.getLinha(), destino.getLinha()) == 0)
					&& (this.deslocamentoPeca(origem.getColuna(),
							destino.getColuna()) > 0)
					|| (this.deslocamentoPeca(origem.getLinha(),
							destino.getLinha()) > 0)
					&& (this.deslocamentoPeca(origem.getColuna(),
							destino.getColuna()) == 0))
				return true;
		return false;
	}
}