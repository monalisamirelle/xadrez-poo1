package br.edu.ifes.poo1.cln.cdp;

public class Cavalo extends Peca {
	
	/**
	 * Instancia um cavalo.
	 */
	public Cavalo(CorJogador corJogador) {
		super(3, TipoPeca.CAVALO, corJogador);
	}
	
	/**
	 * Criar um clone de uma torre para IAElaborada
	 * @param peca
	 */
	public Cavalo(Peca peca) {
		super(peca);
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
