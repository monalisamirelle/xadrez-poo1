package br.edu.ifes.poo1.cln.cdp;

public class Bispo extends Peca {

	/**
	 * Instancia um bispo.
	 */
	public Bispo(Jogador jogador) {
		super(3, TipoPeca.BISPO, jogador);
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
			Tabuleiro tabuleiro) {
		if (super.podeSeMover(origem, destino, tabuleiro)
				&& tabuleiro.podeRealizarMovimentacao(origem, destino))
			if (this.tamanhoMovimento(origem.getLinha(), destino.getLinha()) == this
					.tamanhoMovimento(origem.getColuna(), destino.getColuna()))
				return true;
		return false;
	}

}