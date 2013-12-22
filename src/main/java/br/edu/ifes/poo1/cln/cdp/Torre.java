package br.edu.ifes.poo1.cln.cdp;

public class Torre extends Peca {

	/**
	 * Instancia uma torre.
	 */
	public Torre(Jogador jogador) {
		super(5, TipoPeca.TORRE, jogador);
	}

	@Override
	public boolean podeSeMover(Posicao origem, Posicao destino,
			Tabuleiro tabuleiro) {
		if (super.podeSeMover(origem, destino, tabuleiro)
				&& tabuleiro.podeRealizarMovimentacao(origem, destino))
			if ((this.tamanhoMovimento(origem.getLinha(), destino.getLinha()) == 0)
					&& (this.tamanhoMovimento(origem.getColuna(),
							destino.getColuna()) > 0)
					|| (this.tamanhoMovimento(origem.getLinha(),
							destino.getLinha()) > 0)
					&& (this.tamanhoMovimento(origem.getColuna(),
							destino.getColuna()) == 0))
				return true;
		return false;
	}
}