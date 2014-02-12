package br.edu.ifes.poo1.cln.cdp;


/**
 * Representação de um jogador. Há no máximo 2 jogadores para o jogo de xadrez.
 */
public class Pessoa extends Jogador{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Pessoa(String nome, TipoCorJogador cor){
		super(nome, cor, TipoJogador.PESSOA);
	}

}
