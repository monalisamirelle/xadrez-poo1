package br.edu.ifes.poo1.cln.cdp;

public class Rainha extends Peca {

	/**
	 * Instancia uma rainha.
	 */
	public Rainha(Jogador jogador) {
		super(9, TipoPeca.RAINHA, jogador);
	}
	
	/**
	 * Criar um clone de uma torre para IAElaborada
	 * @param peca
	 */
	public Rainha(Peca peca) {
		super(peca);
	}

	@Override
	protected boolean podeSeMover(Posicao origem, Posicao destino,
			Tabuleiro tabuleiro) {
		if (super.podeSeMover(origem, destino, tabuleiro)
				&& tabuleiro.podeRealizarMovimentacao(origem, destino)) {
			if ((this.tamanhoMovimento(origem.getLinha(), destino.getLinha()) > 0)
					|| (this.tamanhoMovimento(origem.getColuna(),
							destino.getColuna()) > 0)) {
				return true;
			}
		}
		return false;
	}
}