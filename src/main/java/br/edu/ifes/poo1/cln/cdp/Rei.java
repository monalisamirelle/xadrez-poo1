package br.edu.ifes.poo1.cln.cdp;

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
			Tabuleiro tabuleiro) throws CasaOcupadaException {
		// Verifica se o rei não vai entrar em Xeque com a jogada.
		if (tabuleiro.jogadaSuicida(origem, destino,
				TipoCorJogador.getCorOposta(this.getCorJogador())))
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