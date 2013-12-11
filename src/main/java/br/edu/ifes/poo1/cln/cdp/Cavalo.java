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
	public boolean podeAndar(Casa casaDesejada) {
		if (this.valorMovimento(casa.getPosicao().getLinha(), casaDesejada
				.getPosicao().getLinha()) == 1
				& this.valorMovimento(casa.getPosicao().getColuna(),
						casaDesejada.getPosicao().getColuna()) == 2
				| this.valorMovimento(casa.getPosicao().getLinha(),
						casaDesejada.getPosicao().getLinha()) == 2
				& this.valorMovimento(casa.getPosicao().getColuna(),
						casaDesejada.getPosicao().getColuna()) == 1)
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
