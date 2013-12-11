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
	public boolean podeAndar(Casa casaDesejada) {
		if ((this.valorMovimento(casa.getPosicao().getLinha(), casaDesejada
				.getPosicao().getLinha()) == 0)
				& (this.valorMovimento(casa.getPosicao().getColuna(),
						casaDesejada.getPosicao().getColuna()) > 0)
				|| (this.valorMovimento(casa.getPosicao().getLinha(),
						casaDesejada.getPosicao().getLinha()) > 0)
				& (this.valorMovimento(casa.getPosicao().getColuna(),
						casaDesejada.getPosicao().getColuna()) == 0))
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