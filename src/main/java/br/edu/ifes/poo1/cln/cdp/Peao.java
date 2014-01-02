package br.edu.ifes.poo1.cln.cdp;

/**
 * Representa um peão do jogo de xadrez.
 */
public class Peao extends Peca {

	/**
	 * Instancia um peão.
	 */
	public Peao(Jogador jogador) {
		super(1, TipoPeca.PEAO, jogador);
	}

	/**
	 * Criar um clone de uma torre para IAElaborada
	 * @param peca
	 */
	public Peao(Peca peca) {
		super(peca);
	}
	
	@Override
	public boolean podeAndar(Posicao origem, Posicao destino,
			Tabuleiro tabuleiro) {
		int avanca;
		if (this.getJogador().getCor() == CorJogador.BRANCO)
			avanca = 1;
		else
			avanca = -1;
		if (super.podeAndar(origem, destino, tabuleiro)
				&& tabuleiro.podeRealizarMovimentacao(origem, destino))
			// Se o peão quer avançar, não quer se movimentar na coluna
			if (origem.getColuna() - destino.getColuna() == 0) {
				// Se o peão já se movimentou alguma vez
				if (this.getJaMoveu() == true) {
					if ((destino.getLinha() - origem.getLinha()) == avanca)
						return true;
				} else {
					if (destino.getLinha() - origem.getLinha() == avanca
							|| destino.getLinha() - origem.getLinha() == 2 * avanca)
						return true;
				}
			}
		return false;
	}

	@Override
	public boolean podeAtacar(Posicao origem, Posicao destino,
			Tabuleiro tabuleiro) {
		int avanca;
		if ((this.getJogador().getCor().toString()).equals("BRANCO"))
			avanca = 1;
		else
			avanca = -1;
		if (super.podeAtacar(origem, destino, tabuleiro)
				&& tabuleiro.podeRealizarMovimentacao(origem, destino))
			// Se quer avançar na coluna 1
			if(this.tamanhoMovimento(origem.getColuna(),
							destino.getColuna()) == 1)
				if(destino.getLinha() - origem.getLinha() == avanca)
					return true;
		return false;
	}
}