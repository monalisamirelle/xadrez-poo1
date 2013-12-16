package br.edu.ifes.poo1.cln.cdp;

public class Cavalo extends Peca {
	/**
	 * Instancia um cavalo.
	 */
	public Cavalo(Jogador jogador) {
		super(3, TipoPeca.CAVALO, jogador);
	}

	@Override
	public boolean podeAndar(Posicao origem, Posicao destino) {
//		if (super.podeAndar(posicaoDesejada))
//			if (this.tamanhoMovimento(this.posicao.getLinha(),
//					posicaoDesejada.getLinha()) == 1
//					& this.tamanhoMovimento(this.posicao.getColuna(),
//							posicaoDesejada.getColuna()) == 2
//					| this.tamanhoMovimento(this.posicao.getLinha(),
//							posicaoDesejada.getLinha()) == 2
//					& this.tamanhoMovimento(this.posicao.getColuna(),
//							posicaoDesejada.getColuna()) == 1)
//				return true;
//		return false;
//		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean podeAtacar(Posicao origem, Posicao destino) {
//		if (super.podeAtacar(posicaoDesejada))
//			if (this.tamanhoMovimento(this.posicao.getLinha(),
//					posicaoDesejada.getLinha()) == 1
//					& this.tamanhoMovimento(this.posicao.getColuna(),
//							posicaoDesejada.getColuna()) == 2
//					| this.tamanhoMovimento(this.posicao.getLinha(),
//							posicaoDesejada.getLinha()) == 2
//					& this.tamanhoMovimento(this.posicao.getColuna(),
//							posicaoDesejada.getColuna()) == 1)
//				return true;
//		return false;
//		// TODO Auto-generated method stub
		return true;
	}

}
