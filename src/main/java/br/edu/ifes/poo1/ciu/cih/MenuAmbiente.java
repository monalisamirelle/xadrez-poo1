package br.edu.ifes.poo1.ciu.cih;

import java.util.ArrayList;

/**
 * Classe para um menu de seleção do ambiente a ser usado pelo jogo.
 */
public class MenuAmbiente extends Menu {
	public MenuAmbiente() {
		super("Menu de seleção de ambiente", new ArrayList<ItemMenu>() {

			/** Serial gerado automaticamente. */
			private static final long serialVersionUID = 1379733000527534784L;

			{
				add(new ItemMenu("Prompt",
						"Usar interface voltada para o prompt de comando do windows."));
				add(new ItemMenu("Terminal",
						"Usar interface voltada para o terminal dos sistemas derivados do unix."));
			}
		});
	}
}
