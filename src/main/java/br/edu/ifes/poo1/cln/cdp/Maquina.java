package br.edu.ifes.poo1.cln.cdp;

public abstract class Maquina extends Jogador{
	

	public Maquina(String nome, CorJogador cor) {
		super(nome, cor);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Define qual será a jogada realizada pela máquina
	 * @return
	 * @throws CasaOcupadaException 
	 */
	public abstract Jogada escolherJogada() throws CasaOcupadaException;


}
