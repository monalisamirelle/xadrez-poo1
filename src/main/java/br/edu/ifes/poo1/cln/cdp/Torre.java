package br.edu.ifes.poo1.cln.cdp;

public class Torre extends Peca {

	/**
	 * Instancia uma torre.
	 */
	public Torre(Jogador jogador) {
		super(5, TipoPeca.TORRE, jogador);
	}
	
	@Override
	public boolean podeAndar(Posicao origem, Posicao destino) {
//		if (super.podeAndar(origem, destino)
//				&& Tabuleiro.podeRealizarMovimentacao(origem, destino))
//			if ((this.tamanhoMovimento(origem.getLinha(),
//					destino.getLinha()) == 0)
//					& (this.tamanhoMovimento(origem.getColuna(),
//							destino.getColuna()) > 0)
//					|| (this.tamanhoMovimento(origem.getLinha(),
//							destino.getLinha()) > 0)
//					& (this.tamanhoMovimento(origem.getColuna(),
//							destino.getColuna()) == 0))
//				return true;
		return false;
		// TODO Auto-generated method stub
	}

	@Override
	public boolean podeAtacar(Posicao origem, Posicao destino) {
//		if (super.podeAtacar(origem, destino)
//				&& Tabuleiro.podeRealizarMovimentacao(origem, destino))
//			if ((this.tamanhoMovimento(origem.getLinha(),
//					destino.getLinha()) == 0)
//					& (this.tamanhoMovimento(origem.getColuna(),
//							destino.getColuna()) > 0)
//					|| (this.tamanhoMovimento(origem.getLinha(),
//							destino.getLinha()) > 0)
//					& (this.tamanhoMovimento(origem.getColuna(),
//							destino.getColuna()) == 0))
//				return true;
		return false;
	}
}