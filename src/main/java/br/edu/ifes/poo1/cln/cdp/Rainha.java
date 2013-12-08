package br.edu.ifes.poo1.cln.cdp;

public class Rainha extends Peca {

	/**
	 * Instancia uma rainha.
	 */
	public Rainha(Jogador jogador) {
		super(9, jogador); // valor da dama é 9.
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