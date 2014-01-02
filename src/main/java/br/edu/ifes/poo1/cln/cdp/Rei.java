package br.edu.ifes.poo1.cln.cdp;

public class Rei extends Peca {

	/**
	 * Instancia um rei.
	 */
	public Rei(Jogador jogador) {
		// O rei nunca poderá ser capturado, então o seu valor não é relevante.
		super(8000, TipoPeca.REI, jogador);
	}

	/**
	 * Criar um clone de uma torre para IAElaborada
	 * @param peca
	 */
	public Rei(Peca peca) {
		super(peca);
	}
	
	@Override
	public boolean podeSeMover(Posicao origem, Posicao destino,
			Tabuleiro tabuleiro) {
		// Verifica se o rei não vai entrar em Xeque com a jogada.
		if (tabuleiro.estaAmeacadoPor(destino,
				CorJogador.getCorOposta(this.getJogador().getCor())))
			return false;

		// Verifica o movimento natural do rei.
		if (super.podeSeMover(origem, destino, tabuleiro)
				&& tabuleiro.podeRealizarMovimentacao(origem, destino))
			if ((this.tamanhoMovimento(origem.getLinha(), destino.getLinha()) > 1)
					|| (this.tamanhoMovimento(origem.getColuna(),
							destino.getColuna()) > 1))
				return false;
			else
				return true;
		return false;
	}
}