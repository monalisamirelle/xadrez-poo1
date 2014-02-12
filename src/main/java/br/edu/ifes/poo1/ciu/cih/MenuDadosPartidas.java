package br.edu.ifes.poo1.ciu.cih;

import java.util.ArrayList;

public class MenuDadosPartidas extends Menu {
	public MenuDadosPartidas() {
		super("Menu de Dados de Partidas: ", new ArrayList<ItemMenu>() {

			/** Serial gerado automaticamente. */
			private static final long serialVersionUID = 1379733000527534784L;

			{
				add(new ItemMenu("CARREGAR",
						"Carrega: Reinicia uma partida"));
				add(new ItemMenu("APAGAR",
						"Apaga: Apagar dados de partida"));
				add(new ItemMenu("VOLTAR", "Volta ao menu principal"));
			}
		});
	}
}
