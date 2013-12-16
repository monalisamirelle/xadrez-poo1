package br.edu.ifes.poo1.cln.cdp;

public class Bispo extends Peca {

	/**
	 * Instancia um bispo.
	 */
	public Bispo(Jogador jogador) {
		super(3, TipoPeca.BISPO, jogador);
	}

	@Override
	public boolean podeAndar(Posicao origem, Posicao destino) {
//		if (super.podeAndar(origem, destino)
//				&& Tabuleiro.podeRealizarMovimentacao(origem, destino))
//			if (this.tamanhoMovimento(origem.getLinha(),
//					destino.getLinha()) == this
//					.tamanhoMovimento(origem.getLinha(),
//							destino.getLinha()))
//				return true;
		return false;
	}

	@Override
	public boolean podeAtacar(Posicao origem, Posicao destino) {
//		if (super.podeAtacar(origem, destino)
//				&& Tabuleiro.podeRealizarMovimentacao(origem, destino))
//			if (this.tamanhoMovimento(origem.getLinha(),
//					destino.getLinha()) == this
//					.tamanhoMovimento(origem.getLinha(),
//							destino.getLinha()))
//				return true;
		return false;
		// TODO Auto-generated method stub
	}

}