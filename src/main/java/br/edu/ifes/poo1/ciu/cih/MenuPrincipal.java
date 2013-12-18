package br.edu.ifes.poo1.ciu.cih;

import java.util.ArrayList;

public class MenuPrincipal extends Menu {
	public MenuPrincipal() {
		super("Menu Principal", new ArrayList<ItemMenu>() {
			/** Serial gerado automaticamente. */
			private static final long serialVersionUID = -2202065529317389240L;

			{
				add(new ItemMenu("Singleplayer",
						"Iniciar uma nova partida contra o computador"));
				add(new ItemMenu("Multiplayer",
						"Iniciar uma nova partida contra outro jogador"));
				add(new ItemMenu("Dados", "Dados das partidas anteriores"));
				add(new ItemMenu("Sair", "Sair"));
			}
		});

		// TODO: Entender melhor o que foi feito aqui. Referência:
		// http://stackoverflow.com/questions/1005073/initialization-of-an-arraylist-in-one-line
	}
}
