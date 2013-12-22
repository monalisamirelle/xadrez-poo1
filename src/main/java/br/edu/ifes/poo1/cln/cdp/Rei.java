package br.edu.ifes.poo1.cln.cdp;

public class Rei extends Peca {

	/**
	 * Instancia um rei.
	 */
	public Rei(Jogador jogador) {
		// O rei nunca poderá ser capturado, então o seu valor não é relevante.
		super(8000, TipoPeca.REI, jogador);
	}

	@Override
	// FIXME: MUITO MAIS COMPLEXO !
	public boolean podeSeMover(Posicao origem, Posicao destino,
			Tabuleiro tabuleiro) {
		if (super.podeSeMover(origem, destino, tabuleiro)
				&& tabuleiro.podeRealizarMovimentacao(origem, destino))
			if ((this.tamanhoMovimento(origem.getLinha(), destino.getLinha()) == 1)
					&& (this.tamanhoMovimento(origem.getColuna(),
							destino.getColuna()) == 1))
				return true;
		return false;
	}
}