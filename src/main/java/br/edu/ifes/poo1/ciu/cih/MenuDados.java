package br.edu.ifes.poo1.ciu.cih;

import java.util.ArrayList;

public class MenuDados extends Menu {
	public MenuDados() {
		super("Dados das Partidas: ", new ArrayList<ItemMenu>() {

			/** Serial gerado automaticamente. */
			private static final long serialVersionUID = 1379733000527534784L;

			{
				add(new ItemMenu("PARTIDAS",
						"Partidas e vencedores das partidas"));
				add(new ItemMenu("JOGADORES", "Jogadores e Vitórias X Derrotas"));
				add(new ItemMenu("RETORNAR", "Retornar ao menu principal"));
			}
		});
	}
}
