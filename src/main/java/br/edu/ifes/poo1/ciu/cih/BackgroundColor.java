package br.edu.ifes.poo1.ciu.cih;

/**
 * Cor para ser aplicado ao fundo da escrita no terminal. A sequência que gera a
 * cor varia de 40 a 47.
 */
public enum BackgroundColor {
	ZERO("40"),
	UM("41"),
	DOIS("42"),
	TRES("43"),
	QUATRO("44"),
	CINCO("45"),
	SEIS("46"),
	SETE("47");

	/** Número que codifica a cor. */
	private String sequencia;

	private BackgroundColor(String sequencia) {
		this.sequencia = sequencia;
	}

	public String getSequencia() {
		return sequencia;
	}
}
