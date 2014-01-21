package br.edu.ifes.poo1.cln.cdp;

public class Torre extends Peca {

	/**
	 * Instancia uma torre.
	 */
	public Torre(CorJogador corJogador) {
		super(5, TipoPeca.TORRE, corJogador);
	}
	
	/**
	 * Criar um clone de uma torre para IAElaborada
	 * @param peca
	 */
	public Torre(Peca peca) {
		super(peca);
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