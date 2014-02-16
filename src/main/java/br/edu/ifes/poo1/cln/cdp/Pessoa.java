package br.edu.ifes.poo1.cln.cdp;

/**
 * Representação de um jogador. Há no máximo 2 jogadores para o jogo de xadrez.
 */
public class Pessoa extends Jogador {
	private final int TOTALRECOMENDACOES = 3;
	private int recomendacoes;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Pessoa(String nome, TipoCorJogador cor) {
		super(nome, cor, TipoJogador.PESSOA);
		this.recomendacoes = 0;
	}

	public int getRecomendacoes() {
		return recomendacoes;
	}

	public void setRecomendacoes() {
		this.recomendacoes = this.recomendacoes + 1;
	}

	public int getTOTALRECOMENDACOES() {
		return TOTALRECOMENDACOES;
	}
	
	public boolean verificarRecomendacoesRealizadas(){
		if(recomendacoes<TOTALRECOMENDACOES)
			return true;
		return false;
	}

}
