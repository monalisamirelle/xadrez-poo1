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
	public boolean podeAndar(Casa casaDesejada) {
		if ((this.valorMovimento(casa.getPosicao().getLinha(), casaDesejada
				.getPosicao().getLinha()) > 0)
				|| (this.valorMovimento(casa.getPosicao().getColuna(),
						casaDesejada.getPosicao().getColuna()) > 0))
			return true;
		else
			return false;
		// TODO Auto-generated method stub
	}

	@Override
	public boolean podeAtacar(Casa casa) {
		// TODO Auto-generated method stub
		return true;
	}

}