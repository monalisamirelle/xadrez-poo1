package br.edu.ifes.poo1.cln.cdp;

/**
 * 
 * @author lucas
 * 
 */
public class Peao extends Peca {

	/**
	 * Instancia um peão.
	 */
	public Peao(Jogador jogador) {
		super(1, jogador); // valor do peão é 1.
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean podeAndar(Casa casaDesejada) {
		// Se o peão quer avançar, não quer se movimentar na coluna
		if ((casa.getPosicao().getLinha()
				- casaDesejada.getPosicao().getLinha() > 0)
				& (casa.getPosicao().getColuna()
						- casaDesejada.getPosicao().getColuna() == 0)) {
			// Se o peão já se movimentou alguma vez
			// O else estava me trollando, podemos mudar isso depois
			if (jaAndou == true) {
				if ((casa.getPosicao().getLinha() - casaDesejada.getPosicao()
						.getLinha()) == 1)
					return true;
			} else {
				if ((casa.getPosicao().getLinha() - casaDesejada.getPosicao()
						.getLinha()) < 3)
					return true;
			}
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean podeAtacar(Casa casa) {
		// TODO Auto-generated method stub
		return true;
	}

}
