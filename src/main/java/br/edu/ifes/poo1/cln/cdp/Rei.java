package br.edu.ifes.poo1.cln.cdp;

public class Rei extends Peca {

	/**
	 * Instancia um rei.
	 */
	public Rei(Jogador jogador) {
		super(1, jogador); // REI NÃO TEM VALOR!.
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