package br.edu.ifes.poo1.cln.cdp;

public class Rei extends Peca {

	/**
	 * Instancia um rei.
	 */
	public Rei(CorJogador corJogador) {
		// O rei nunca poderá ser capturado, então o seu valor não é relevante.
		super(0, TipoPeca.REI, corJogador);
	}

	/**
	 * Criar um clone de uma torre para IAElaborada
	 * 
	 * @param peca
	 */
	public Rei(Peca peca) {
		super(peca);
	}

	@Override
	public boolean podeSeMover(Posicao origem, Posicao destino,
			Tabuleiro tabuleiro) throws CasaOcupadaException {
		// Verifica se o rei não vai entrar em Xeque com a jogada.
		if (tabuleiro.jogadaSuicida(origem, destino,
				CorJogador.getCorOposta(this.getCorJogador())))
			return false;

		// Verifica o movimento natural do rei.
		if (super.podeSeMover(origem, destino, tabuleiro)
				&& tabuleiro.podeRealizarMovimentacao(origem, destino))
			if ((this.deslocamentoPeca(origem.getLinha(), destino.getLinha()) > 1)
					|| (this.deslocamentoPeca(origem.getColuna(),
							destino.getColuna()) > 1))
				return false;
			else
				return true;
		return false;
	}
}