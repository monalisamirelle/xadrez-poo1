package br.edu.ifes.poo1.cln.cdp.tipos;

/**
 * Usado para designar o tipo de cada peça. Esta ENUM é usada para que a Camada
 * de Interface com o Usuário (CIU) conheça o tipo de cada peça, para que possa
 * exibi-las corretamente.
 */
public enum TipoPeca {
	TORRE(5), CAVALO(3), BISPO(3), REI(0), RAINHA(9), PEAO(1);

	/** Pontuação a qual a peça equivale. */
	private int valor;

	/**
	 * Cria o tipo, iniciando a referência ao seu valor
	 * 
	 * @param valor
	 *            Pontuação que peça vale.
	 */
	private TipoPeca(int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}

}
