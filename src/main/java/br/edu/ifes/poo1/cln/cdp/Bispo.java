package br.edu.ifes.poo1.cln.cdp;

public class Bispo extends Peca {

	/**
	 * Instancia um bispo.
	 */
	public Bispo(Jogador jogador) {
		super(3, jogador); // valor do bispo é 3.
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean podeAndar(Casa casaDesejada) {

		// Ficaria a parte provar que a peça não se moveu, talvez criar um
		// método para isso venha a ser interessante, mas.. onde colocar?
		if (this.valorMovimento(casa.getPosicao().getLinha(), casaDesejada
				.getPosicao().getLinha()) == this.valorMovimento(casa
				.getPosicao().getLinha(), casaDesejada.getPosicao().getLinha()))
			return true;
		else
			return false;
	}

	@Override
	public boolean podeAtacar(Casa casa) {
		// TODO Auto-generated method stub
		return true;
	}

}