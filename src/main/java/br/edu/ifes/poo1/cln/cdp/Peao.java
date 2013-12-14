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
	public boolean podeAndar(Posicao posicao) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean podeAtacar(Posicao posicao) {
		// TODO Auto-generated method stub
		return true;
	}

}
