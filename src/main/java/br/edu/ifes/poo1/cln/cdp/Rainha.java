package br.edu.ifes.poo1.cln.cdp;

public class Rainha extends Peca {

	/**
	 * Instancia uma rainha.
	 */
	public Rainha(CorJogador corJogador) {
		super(9, TipoPeca.RAINHA, corJogador);
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
			Tabuleiro tabuleiro) throws CasaOcupadaException {
		if (super.podeSeMover(origem, destino, tabuleiro)
				&& tabuleiro.podeRealizarMovimentacao(origem, destino)) {
			if ((this.deslocamentoPeca(origem.getLinha(), destino.getLinha()) > 0)
					|| (this.deslocamentoPeca(origem.getColuna(),
							destino.getColuna()) > 0)) {
				return true;
			}
		}
		return false;
	}
}