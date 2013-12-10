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
	public boolean podeAndar(Casa casaAtual, Casa casaDesejada) {
		// linhaAtual = casaAtual.getPosicao().getLinha();
		// colunaAtual = casaAtual.getPosicao().getColuna();
		// linhaDesejada = casaDesejada.getPosicao().getLinha();
		// colunaDesejada = casaDesejada.getPosicao().getColuna();
		if (Math.abs(casaAtual.getPosicao().getLinha()
				- casaDesejada.getPosicao().getLinha()) == Math.abs(casaAtual
				.getPosicao().getColuna()
				- casaDesejada.getPosicao().getColuna())) // TODO Auto-generated
															// method stub
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