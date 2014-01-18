package br.edu.ifes.poo1.cln.cdp;

public abstract class Maquina extends Jogador {

	public Maquina(String nome, CorJogador cor) {
		super(nome, cor);
	}

	/**
	 * Define qual será a jogada realizada pela máquina
	 * 
	 * @return
	 * @throws CasaOcupadaException
	 * @throws JogadaInvalidaException
	 * @throws CloneNotSupportedException
	 */
	public abstract Jogada escolherJogada(Tabuleiro tabuleiroAtual)
			throws CasaOcupadaException, CloneNotSupportedException,
			JogadaInvalidaException, InterruptedException;
	
}
