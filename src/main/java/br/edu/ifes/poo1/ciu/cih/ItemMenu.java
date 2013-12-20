package br.edu.ifes.poo1.ciu.cih;

/**
 * Possui dados de um item de um menu.
 */
public class ItemMenu {
	private String nome;
	private String descricao;

	/**
	 * Constrói um item de menu, com seu nome e sua descrição.
	 * 
	 * @param nome
	 *            Nome do item.
	 * @param descricao
	 *            Descrição do item.
	 */
	public ItemMenu(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	/** Retorna o nome do item, em maíusculas. */
	public String getNome() {
		return nome.toUpperCase();
	}

	public String getDescricao() {
		return descricao;
	}

}
