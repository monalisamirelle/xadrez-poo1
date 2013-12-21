package br.edu.ifes.poo1.cln.cdp;

public class Cavalo extends Peca {
	/**
	 * Instancia um cavalo.
	 */
	public Cavalo(Jogador jogador) {
		super(3, TipoPeca.CAVALO, jogador);
	}

	@Override
	public boolean podeAndar(Posicao origem, Posicao destino,
			Tabuleiro tabuleiro) {
		if (super.podeAndar(origem, destino, tabuleiro))
			if (this.tamanhoMovimento(origem.getLinha(), destino.getLinha()) == 1
					& this.tamanhoMovimento(origem.getColuna(),
							destino.getColuna()) == 2
					| this.tamanhoMovimento(origem.getLinha(),
							destino.getLinha()) == 2
					& this.tamanhoMovimento(origem.getColuna(),
							destino.getColuna()) == 1)
				return true;
		return false;
	}

	@Override
	public boolean podeAtacar(Posicao origem, Posicao destino,
			Tabuleiro tabuleiro) {
		if (super.podeAtacar(origem, destino, tabuleiro))
			if (this.tamanhoMovimento(origem.getLinha(), destino.getLinha()) == 1
					& this.tamanhoMovimento(origem.getColuna(),
							destino.getColuna()) == 2
					| this.tamanhoMovimento(origem.getLinha(),
							destino.getLinha()) == 2
					& this.tamanhoMovimento(origem.getColuna(),
							destino.getColuna()) == 1)
				return true;
		return false;
	}

}
