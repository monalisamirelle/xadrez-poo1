package br.edu.ifes.poo1.cln.cdp;

public class Cavalo extends Peca {
	// Testando
	/**
	 * Instancia um cavalo.
	 */
	public Cavalo(Jogador jogador) {
		super(3, TipoPeca.CAVALO, jogador); // valor do cavalo é 3.
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean podeAndar(Posicao origem, Posicao destino) {
		if (super.podeAndar(origem, destino))
			if (this.tamanhoMovimento(origem.getLinha(),
					destino.getLinha()) == 1
					& this.tamanhoMovimento(origem.getColuna(),
							destino.getColuna()) == 2
					| this.tamanhoMovimento(origem.getLinha(),
							destino.getLinha()) == 2
					& this.tamanhoMovimento(origem.getColuna(),
							destino.getColuna()) == 1)
				return true;
		return false;
		// TODO Auto-generated method stub
	}

	@Override
	public boolean podeAtacar(Posicao origem, Posicao destino) {
		if (super.podeAtacar(origem, destino))
			if (this.tamanhoMovimento(origem.getLinha(),
					destino.getLinha()) == 1
					& this.tamanhoMovimento(origem.getColuna(),
							destino.getColuna()) == 2
					| this.tamanhoMovimento(origem.getLinha(),
							destino.getLinha()) == 2
					& this.tamanhoMovimento(origem.getColuna(),
							destino.getColuna()) == 1)
				return true;
		return false;
		// TODO Auto-generated method stub
	}

}
