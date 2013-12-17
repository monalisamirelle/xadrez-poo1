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
	public boolean podeAndar(Posicao origem, Posicao destino,
			Tabuleiro tabuleiro) {
		// FIXME: PEÃO BRANCO +1 PEÃO PRETO -1
		// if (super.podeAndar(origem, destino, tabuleiro)
		// && tabuleiro.podeRealizarMovimentacao(origem, destino))
		// Se o peão quer avançar, não quer se movimentar na coluna
		// if ((origem.getLinha() - destino.getLinha() > 0)
		// & (origem.getColuna() - destino.getColuna() == 0)) {
		// Se o peão já se movimentou alguma vez
		// if (this.getJaMoveu() == true) {
		// if ((origem.getLinha() - destino.getLinha()) == 1)
		// return true;
		// } else {
		// if ((origem.getLinha() - destino.getLinha()) < 3)
		// return true;
		// }
		// }
		// return false;
		return true;
	}

	@Override
	public boolean podeAtacar(Posicao origem, Posicao destino,
			Tabuleiro tabuleiro) {
		// if (super.podeAtacar(origem, destino, tabuleiro)
		// && tabuleiro.podeRealizarMovimentacao(origem, destino))
		// if ((origem.getLinha() - destino.getLinha() == 1)
		// && (this.tamanhoMovimento(origem.getColuna(),
		// destino.getColuna()) == 1))
		// return true;
		// return false;
		return true;
	}
}
