package br.edu.ifes.poo1.cln.cdp;

/**
 * Representa um peão do jogo de xadrez.
 */
public class Peao extends Peca {

	/**
	 * Instancia um peão.
	 */
	public Peao(Jogador jogador) {
		super(1, TipoPeca.PEAO, jogador); // valor do peão é 1.
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean podeAndar(Posicao origem, Posicao destino) {
//		if (super.podeAndar(posicao)
//				&& Tabuleiro.podeRealizarMovimentacao(this.posicao,
//						posicaoDesejada))
//			// Se o peão quer avançar, não quer se movimentar na coluna
//			if ((posicao.getLinha() - posicaoDesejada.getLinha() > 0)
//					& (posicao.getColuna() - posicaoDesejada.getColuna() == 0)) {
//				// Se o peão já se movimentou alguma vez
//				if (jaAndou == true) {
//					if ((posicao.getLinha() - posicaoDesejada.getLinha()) == 1)
//						return true;
//				} else {
//					if ((posicao.getLinha() - posicaoDesejada.getLinha()) < 3)
//						return true;
//				}
//			}
//		return false;
		return true;
	}

	@Override
	public boolean podeAtacar(Posicao origem, Posicao destino) {
//		if (super.podeAtacar(posicaoDesejada)
//				&& Tabuleiro.podeRealizarMovimentacao(this.posicao,
//						posicaoDesejada))
//			if ((posicao.getLinha() - posicaoDesejada.getLinha() == 1)
//					&& (this.tamanhoMovimento(posicao.getColuna(),
//							posicaoDesejada.getColuna()) == 1))
//				return true;
//		return false;
		return true;
	}
}
