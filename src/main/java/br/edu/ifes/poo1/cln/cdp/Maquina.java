package br.edu.ifes.poo1.cln.cdp;

public abstract class Maquina extends Jogador {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Maquina(String nome, TipoCorJogador cor, TipoJogador tipoJogador) {
		super(nome, cor, tipoJogador);
	}

	/**
	 * Define qual será a jogada realizada pela máquina
	 * 
	 * @return
	 * @throws CasaOcupadaException 
	 */
	public abstract Jogada escolherJogada(Tabuleiro tabuleiroAtual) throws CasaOcupadaException;

}
