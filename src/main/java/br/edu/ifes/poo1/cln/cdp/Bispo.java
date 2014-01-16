package br.edu.ifes.poo1.cln.cdp;

public class Bispo extends Peca {

	/**
	 * Instancia um bispo.
	 */
	public Bispo(CorJogador corJogador) {
		super(3, TipoPeca.BISPO, corJogador);
	}
	
	/**
	 * Criar um clone de uma torre para IAElaborada
	 * @param peca
	 */
	public Bispo(Peca peca) {
		super(peca);
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