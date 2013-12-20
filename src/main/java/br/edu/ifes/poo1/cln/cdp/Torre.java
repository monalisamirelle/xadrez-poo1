package br.edu.ifes.poo1.cln.cdp;

public class Torre extends Peca {

	/**
	 * Instancia uma torre.
	 */
	public Torre(Pessoa jogador) {
		super(5, TipoPeca.TORRE, jogador);
	}

	@Override
	public boolean podeAndar(Posicao origem, Posicao destino,
			Tabuleiro tabuleiro) {
		if (super.podeAndar(origem, destino, tabuleiro)
				&& tabuleiro.podeRealizarMovimentacao(origem, destino))
			if ((this.tamanhoMovimento(origem.getLinha(), destino.getLinha()) == 0)
					& (this.tamanhoMovimento(origem.getColuna(),
							destino.getColuna()) > 0)
					|| (this.tamanhoMovimento(origem.getLinha(),
							destino.getLinha()) > 0)
					& (this.tamanhoMovimento(origem.getColuna(),
							destino.getColuna()) == 0))
				return true;
		return false;
	}

	@Override
	public boolean podeAtacar(Posicao origem, Posicao destino,
			Tabuleiro tabuleiro) {
		if (super.podeAtacar(origem, destino, tabuleiro)
				&& tabuleiro.podeRealizarMovimentacao(origem, destino))
			if ((this.tamanhoMovimento(origem.getLinha(), destino.getLinha()) == 0)
					& (this.tamanhoMovimento(origem.getColuna(),
							destino.getColuna()) > 0)
					|| (this.tamanhoMovimento(origem.getLinha(),
							destino.getLinha()) > 0)
					& (this.tamanhoMovimento(origem.getColuna(),
							destino.getColuna()) == 0))
				return true;
		return false;
	}
}