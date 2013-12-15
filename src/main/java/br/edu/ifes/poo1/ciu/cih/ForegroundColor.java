package br.edu.ifes.poo1.ciu.cih;

/**
 * Cor para ser aplicado a escrita no terminal. A sequência que gera a
 * cor varia de 30 a 37.
 */
public enum ForegroundColor {
	ZERO("30"),
	UM("31"),
	DOIS("32"),
	TRES("33"),
	QUATRO("34"),
	CINCO("35"),
	SEIS("36"),
	SETE("37");
	
	/** Número que codifica a cor. */
	private String sequencia;
	
	private ForegroundColor(String sequencia) {
		this.sequencia = sequencia;
	}

	public String getSequencia() {
		return sequencia;
	}
}
