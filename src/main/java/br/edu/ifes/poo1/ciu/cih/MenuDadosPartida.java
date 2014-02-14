package br.edu.ifes.poo1.ciu.cih;

import java.util.ArrayList;

public class MenuDadosPartida extends Menu {
	public MenuDadosPartida() {
		super("Menu de dados de partidas: ", new ArrayList<ItemMenu>() {

			/** Serial gerado automaticamente. */
			private static final long serialVersionUID = 1379733000527534784L;

			{
				add(new ItemMenu("CARREGAR",
						"Carregar uma partida"));
				add(new ItemMenu("APAGAR",
						"Apagar dados de partida"));
				add(new ItemMenu("VOLTAR", "Voltar ao menu principal"));
			}
		});
	}
}
