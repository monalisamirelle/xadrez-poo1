package br.edu.ifes.poo1.cln.cdp;

public class Rainha extends Peca {

	/**
	 * Instancia uma rainha.
	 */
	public Rainha(Jogador jogador) {
		super(9, TipoPeca.RAINHA, jogador);
	}

	@Override
	public boolean podeAndar(Posicao origem, Posicao destino) {
//		if (super.podeAndar(posicaoDesejada)
//				&& Tabuleiro.podeRealizarMovimentacao(this.posicao,
//						posicaoDesejada))
//			if ((this.tamanhoMovimento(posicao.getLinha(),
//					posicaoDesejada.getLinha()) > 0)
//					|| (this.tamanhoMovimento(posicao.getColuna(),
//							posicaoDesejada.getColuna()) > 0))
//				return true;
//		return false;
		return true;
	}

	@Override
	public boolean podeAtacar(Posicao origem, Posicao destino) {
//		if (super.podeAtacar(posicaoDesejada)
//				&& Tabuleiro.podeRealizarMovimentacao(this.posicao,
//						posicaoDesejada))
//			if ((this.tamanhoMovimento(posicao.getLinha(),
//					posicaoDesejada.getLinha()) > 0)
//					|| (this.tamanhoMovimento(posicao.getColuna(),
//							posicaoDesejada.getColuna()) > 0))
//				return true;
//		return false;
		return true;
	}
}