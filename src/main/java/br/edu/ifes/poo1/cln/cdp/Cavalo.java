package br.edu.ifes.poo1.cln.cdp;

public class Cavalo extends Peca {
// Testando
	/**
	 * Instancia um cavalo.
	 */
	public Cavalo(Jogador jogador) {
		super(3, jogador); // valor do cavalo é 3.
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean podeAndar(Casa casa) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean podeAtacar(Casa casa) {
		// TODO Auto-generated method stub
		return true;
	}

}
