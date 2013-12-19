package br.edu.ifes.poo1.ciu.cih;

/**
 * Cor para ser aplicado ao fundo da escrita no terminal. A sequência que gera a
 * cor varia de 40 a 47.
 */
public enum BackgroundColor {
	PRETO      ("40"),
	VERMELHO   ("41"),
	VERDE      ("42"),
	AMARELO    ("43"),
	AZUL_ESCURO("44"),
	ROSA       ("45"),
	AZUL_CLARO ("46"),
	BRANCO     ("47");

	/** String com o número que codifica a cor. */
	private String sequencia;

	private BackgroundColor(String sequencia) {
		this.sequencia = sequencia;
	}

	public String getSequencia() {
		return sequencia;
	}
}
