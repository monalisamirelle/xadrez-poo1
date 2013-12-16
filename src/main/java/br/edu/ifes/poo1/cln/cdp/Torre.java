package br.edu.ifes.poo1.cln.cdp;

public class Torre extends Peca {

	/**
	 * Instancia uma torre.
	 */
	public Torre(Jogador jogador) {
		super(5, TipoPeca.TORRE, jogador); // valor da torre é 5.
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean podeAndar(Posicao origem, Posicao destino) {
		if (super.podeAndar(origem, destino)
				&& tabuleiro.podeRealizarMovimentacao(origem, destino))
			if ((this.tamanhoMovimento(origem.getLinha(),
					destino.getLinha()) == 0)
					& (this.tamanhoMovimento(origem.getColuna(),
							destino.getColuna()) > 0)
					|| (this.tamanhoMovimento(origem.getLinha(),
							destino.getLinha()) > 0)
					& (this.tamanhoMovimento(origem.getColuna(),
							destino.getColuna()) == 0))
				return true;
		return false;
		// TODO Auto-generated method stub
	}

	@Override
	public boolean podeAtacar(Posicao origem, Posicao destino) {
		if (super.podeAtacar(origem, destino)
				&& tabuleiro.podeRealizarMovimentacao(origem, destino))
			if ((this.tamanhoMovimento(origem.getLinha(),
					destino.getLinha()) == 0)
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