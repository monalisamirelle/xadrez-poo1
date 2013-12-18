package br.edu.ifes.poo1.ciu.cih;

/**
 * Possui dados de um item de um menu.
 */
public class ItemMenu {
	private String nome;
	private String descricao;
	
	public ItemMenu(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
}
