package br.edu.ifes.poo1.ciu.cih;

/**
 * Enumera os possíveis ambientes para a execução da aplicação.
 */
public enum Ambiente {
	PROMPT(1, "Prompt"), TERMINAL(2, "Terminal");

	private int ordem;
	private String nome;

	private Ambiente(int ordem, String nome) {
		this.ordem = ordem;
		this.nome = nome;
	}

	@Override
	public String toString() {
		return this.nome;
	}

	public int getOrdem() {
		return this.ordem;
	}
	
	public static int size() {
		return Ambiente.values().length;
	}
}
