package br.edu.ifes.poo1.ciu.cih;

import java.util.ArrayList;

/**
 * Classe para um menu de seleção do ambiente a ser usado pelo jogo.
 */
public class MenuCorJogador extends Menu {
	public MenuCorJogador() {
		super("Cor", new ArrayList<ItemMenu>() {

			/** Serial gerado automaticamente. */
			private static final long serialVersionUID = 1379733000527534784L;

			{
				add(new ItemMenu("Branco", "Selecione peças brancas"));
				add(new ItemMenu("Preto", "Selecione peças pretas"));
			}
		});
	}
}
