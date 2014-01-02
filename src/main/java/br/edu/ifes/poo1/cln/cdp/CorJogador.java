package br.edu.ifes.poo1.cln.cdp;

public enum CorJogador {
	BRANCO, PRETO;

	/**
	 * Retorna a cor oposta a cor indicada.
	 * 
	 * @param cor
	 *            Cor indicada.
	 * @return Cor oposta a indicada.
	 */
	public static CorJogador getCorOposta(CorJogador cor) {
		if (cor == CorJogador.BRANCO)
			return CorJogador.PRETO;
		else
			return CorJogador.BRANCO;
	}
}
