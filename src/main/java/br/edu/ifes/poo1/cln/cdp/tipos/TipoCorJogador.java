package br.edu.ifes.poo1.cln.cdp.tipos;

public enum TipoCorJogador {
	BRANCO, PRETO;

	/**
	 * Retorna a cor oposta a cor indicada.
	 * 
	 * @param cor
	 *            Cor indicada.
	 * @return Cor oposta a indicada.
	 */
	public static TipoCorJogador getCorOposta(TipoCorJogador cor) {
		if (cor == TipoCorJogador.BRANCO)
			return TipoCorJogador.PRETO;
		else
			return TipoCorJogador.BRANCO;
	}
	
	public String toString(){
		if(this == TipoCorJogador.BRANCO)
			return "BRANCO";
		return "PRETO";
	}
	
	public static TipoCorJogador parseCorJogador(String corJogador){
		if(corJogador.equals("BRANCO"))
			return TipoCorJogador.BRANCO;
		return TipoCorJogador.PRETO;
	}
}
