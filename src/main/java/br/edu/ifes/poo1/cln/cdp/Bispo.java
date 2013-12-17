package br.edu.ifes.poo1.cln.cdp;

public class Bispo extends Peca {

	/**
	 * Instancia um bispo.
	 */
	public Bispo(Jogador jogador) {
		super(3, TipoPeca.BISPO, jogador);
	}

	@Override
	public boolean podeAndar(Posicao origem, Posicao destino, Tabuleiro tabuleiro) {
		if (super.podeAndar(origem, destino, tabuleiro)
				&& tabuleiro.podeRealizarMovimentacao(origem, destino))
			if (this.tamanhoMovimento(origem.getLinha(),
					destino.getLinha()) == this
					.tamanhoMovimento(origem.getLinha(),
							destino.getLinha()))
				return true;
		return false;
	}

	@Override
	public boolean podeAtacar(Posicao origem, Posicao destino, Tabuleiro tabuleiro) {
		if (super.podeAtacar(origem, destino, tabuleiro)
				&& tabuleiro.podeRealizarMovimentacao(origem, destino))
			if (this.tamanhoMovimento(origem.getLinha(),
					destino.getLinha()) == this
					.tamanhoMovimento(origem.getLinha(),
							destino.getLinha()))
				return true;
		return false;
	}

}