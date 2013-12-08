package br.edu.ifes.poo1.cln.cdp;

public class Torre extends Peca {

	/**
	 * Instancia uma torre.
	 */
	public Torre(Jogador jogador) {
		super(5, jogador); // valor da torre é 5.
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