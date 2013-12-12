package br.edu.ifes.poo1.ciu.cih;

/**
 * Descreve cada um dos itens do menu principal.
 * 
 * @author lucas
 * 
 */
public enum ItemMenuPrincipal {
	NOVA_PARTIDA(1, "Iniciar nova partida"),
	DADOS(2, "Dados das partidas anteriores"),
	SAIR(0, "Sair");

	private int ordem;
	private String descricao;

	/**
	 * Inicia um item, com sua ordem, e uma breve descrição para ser exibida no
	 * menu principal.
	 * 
	 * @param ordem
	 *            Ordem na qual se apresentará no menu principal.
	 * @param descricao
	 *            Descrição a ser exibida para o usuário.
	 */
	private ItemMenuPrincipal(int ordem, String descricao) {
		this.descricao = descricao;
		this.ordem = ordem;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getOrdem() {
		return ordem;
	}
	
	public static int size() {
		return ItemMenuPrincipal.values().length;
	}
}
