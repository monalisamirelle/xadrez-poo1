package br.edu.ifes.poo1.cln.cdp;

public class Rei extends Peca {

	/**
	 * Instancia um rei.
	 */
	public Rei(Jogador jogador) {
		// O rei nunca poderá ser capturado, então o seu valor não é relevante.
		super(8000, TipoPeca.REI, jogador);
	}

	@Override
	public boolean podeAndar(Posicao origem, Posicao destino) {
//		if (super.podeAndar(origem, destino)
//				&& tabuleiro.podeRealizarMovimentacao(origem, destino))
//			if ((this.tamanhoMovimento(origem.getLinha(),
//					destino.getLinha()) == 1)
//					|| (this.tamanhoMovimento(origem.getColuna(),
//							destino.getColuna()) == 1))
//				return true;
		return false;
		// TODO Auto-generated method stub
	}

	@Override
	// FIXME: MUITO MAIS COMPLEXO !
	public boolean podeAtacar(Posicao origem, Posicao destino) {
//		if (super.podeAndar(origem, destino)
//				&& tabuleiro.podeRealizarMovimentacao(origem, destino))
//			if ((this.tamanhoMovimento(origem.getLinha(),
//					destino.getLinha()) == 1)
//					|| (this.tamanhoMovimento(origem.getColuna(),
//							destino.getColuna()) == 1))
//				return true;
		return false;
	}
}