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
		if (super.podeAndar(casaDesejada)
				&& tabuleiro.podeRealizarMovimentacao(this.casa, casaDesejada))
			if (this.tamanhoMovimento(casa.getPosicao().getLinha(),
					casaDesejada.getPosicao().getLinha()) == this
					.tamanhoMovimento(casa.getPosicao().getLinha(),
							casaDesejada.getPosicao().getLinha()))
				return true;
		return false;
	}

	@Override
	public boolean podeAtacar(Casa casaDesejada) {
		if (super.podeAtacar(casaDesejada)
				&& tabuleiro.podeRealizarMovimentacao(this.casa, casaDesejada))
			if (this.tamanhoMovimento(casa.getPosicao().getLinha(),
					casaDesejada.getPosicao().getLinha()) == this
					.tamanhoMovimento(casa.getPosicao().getLinha(),
							casaDesejada.getPosicao().getLinha()))
				return true;
		return false;
		// TODO Auto-generated method stub
	}

}