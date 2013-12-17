package br.edu.ifes.poo1.ciu.cih;

/**
 * Cor para ser aplicado a escrita no terminal. A sequência que gera a
 * cor varia de 30 a 37.
 */
public enum ForegroundColor {
	PRETO      ("30"),
	VERMELHO   ("31"),
	VERDE      ("32"),
	AMARELO    ("33"),
	AZUL_ESCURO("34"),
	ROSA       ("35"),
	AZUL_CLARO ("36"),
	BRANCO     ("37");
	
	/** String com o número que codifica a cor. */
	private String sequencia;
	
	private ForegroundColor(String sequencia) {
		this.sequencia = sequencia;
	}

	public String getSequencia() {
		return sequencia;
	}
}
