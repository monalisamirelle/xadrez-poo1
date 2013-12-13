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
		if (super.podeAndar(casaDesejada)
				&& tabuleiro.podeRealizarMovimentacao(this.casa, casaDesejada))
			if ((this.tamanhoMovimento(casa.getPosicao().getLinha(),
					casaDesejada.getPosicao().getLinha()) == 0)
					& (this.tamanhoMovimento(casa.getPosicao().getColuna(),
							casaDesejada.getPosicao().getColuna()) > 0)
					|| (this.tamanhoMovimento(casa.getPosicao().getLinha(),
							casaDesejada.getPosicao().getLinha()) > 0)
					& (this.tamanhoMovimento(casa.getPosicao().getColuna(),
							casaDesejada.getPosicao().getColuna()) == 0))
				return true;
		return false;
		// TODO Auto-generated method stub
	}

	@Override
	public boolean podeAtacar(Casa casaDesejada) {
		if (super.podeAtacar(casaDesejada)
				&& tabuleiro.podeRealizarMovimentacao(this.casa, casaDesejada))
			if ((this.tamanhoMovimento(casa.getPosicao().getLinha(),
					casaDesejada.getPosicao().getLinha()) == 0)
					& (this.tamanhoMovimento(casa.getPosicao().getColuna(),
							casaDesejada.getPosicao().getColuna()) > 0)
					|| (this.tamanhoMovimento(casa.getPosicao().getLinha(),
							casaDesejada.getPosicao().getLinha()) > 0)
					& (this.tamanhoMovimento(casa.getPosicao().getColuna(),
							casaDesejada.getPosicao().getColuna()) == 0))
				return true;
		return false;
	}

}