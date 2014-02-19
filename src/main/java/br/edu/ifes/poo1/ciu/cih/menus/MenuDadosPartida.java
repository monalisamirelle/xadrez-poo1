package br.edu.ifes.poo1.ciu.cih.menus;

import java.util.ArrayList;

public class MenuDadosPartida extends Menu {
	public MenuDadosPartida() {
		super("Dados das Partidas: ", new ArrayList<ItemMenu>() {

			/** Serial gerado automaticamente. */
			private static final long serialVersionUID = 1379733000527534784L;

			{
				add(new ItemMenu("PARTIDAS",
						"Partidas e vencedores"));
				add(new ItemMenu("JOGADORES", "Jogadores (Vitórias X Derrotas)"));
				add(new ItemMenu("APAGAR", "Apaga todos os dados do histórico"));
				add(new ItemMenu("RETORNAR", "Retornar ao menu principal"));
			}
		});
	}
}
