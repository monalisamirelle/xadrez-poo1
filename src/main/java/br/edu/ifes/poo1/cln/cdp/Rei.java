package br.edu.ifes.poo1.cln.cdp;

public class Rei extends Peca {

	/**
	 * Instancia um rei.
	 */
	public Rei(Jogador jogador) {
		super(1, TipoPeca.REI, jogador); // REI NÃO TEM VALOR!.
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean podeAndar(Posicao origem, Posicao destino) {
		if (super.podeAndar(origem, destino)
				&& Tabuleiro.podeRealizarMovimentacao(origem, destino))
			if ((this.tamanhoMovimento(origem.getLinha(),
					destino.getLinha()) == 1)
					|| (this.tamanhoMovimento(origem.getColuna(),
							destino.getColuna()) == 1))
				return true;
		return false;
		// TODO Auto-generated method stub
	}

	@Override
	/**
	 * MUITO MAIS COMPLEXO !
	 */
	public boolean podeAtacar(Posicao origem, Posicao destino) {
		if (super.podeAndar(origem, destino)
				&& Tabuleiro.podeRealizarMovimentacao(origem, destino))
			if ((this.tamanhoMovimento(origem.getLinha(),
					destino.getLinha()) == 1)
					|| (this.tamanhoMovimento(origem.getColuna(),
							destino.getColuna()) == 1))
				return true;
		return false;
	}

}