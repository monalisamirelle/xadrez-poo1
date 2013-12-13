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
	public boolean podeAndar(Casa casaDesejada) {
		if (super.podeAndar(casaDesejada)
				&& tabuleiro.podeRealizarMovimentacao(this.casa, casaDesejada))
			if ((this.tamanhoMovimento(casa.getPosicao().getLinha(),
					casaDesejada.getPosicao().getLinha()) == 1)
					|| (this.tamanhoMovimento(casa.getPosicao().getColuna(),
							casaDesejada.getPosicao().getColuna()) == 1))
				return true;
		return false;
		// TODO Auto-generated method stub
	}

	@Override
	/**
	 * MUITO MAIS COMPLEXO !
	 */
	public boolean podeAtacar(Casa casaDesejada) {
		if (super.podeAndar(casaDesejada)
				&& tabuleiro.podeRealizarMovimentacao(this.casa, casaDesejada))
			if ((this.tamanhoMovimento(casa.getPosicao().getLinha(),
					casaDesejada.getPosicao().getLinha()) == 1)
					|| (this.tamanhoMovimento(casa.getPosicao().getColuna(),
							casaDesejada.getPosicao().getColuna()) == 1))
				return true;
		return false;
	}

}