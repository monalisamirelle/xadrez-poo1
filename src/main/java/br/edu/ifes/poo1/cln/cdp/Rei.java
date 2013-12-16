package br.edu.ifes.poo1.cln.cdp;

public class Rei extends Peca {

	/**
	 * Instancia um rei.
	 */
	public Rei(Jogador jogador) {
		super(8000, TipoPeca.REI, jogador);
	}

	@Override
	public boolean podeAndar(Posicao origem, Posicao destino) {
//		if (super.podeAndar(posicaoDesejada)
//				&& Tabuleiro.podeRealizarMovimentacao(this.posicao,
//						posicaoDesejada))
//			if ((this.tamanhoMovimento(posicao.getLinha(),
//					posicaoDesejada.getLinha()) == 1)
//					|| (this.tamanhoMovimento(posicao.getColuna(),
//							posicaoDesejada.getColuna()) == 1))
//				return true;
//		return false;
//		// TODO Auto-generated method stub
		return true;
	}

	@Override
	/**
	 * MUITO MAIS COMPLEXO !
	 */
	public boolean podeAtacar(Posicao origem, Posicao destino) {
//		if (super.podeAndar(posicaoDesejada)
//				&& Tabuleiro.podeRealizarMovimentacao(this.posicao,
//						posicaoDesejada))
//			if ((this.tamanhoMovimento(posicao.getLinha(),
//					posicaoDesejada.getLinha()) == 1)
//					|| (this.tamanhoMovimento(posicao.getColuna(),
//							posicaoDesejada.getColuna()) == 1))
//				return true;
//		return false;
		return true;
	}
}