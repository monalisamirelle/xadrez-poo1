package br.edu.ifes.poo1.ciu.cih;

import java.util.ArrayList;

/**
 * Classe para um menu com as atividades principais do jogo, como jogar,
 * visualizar dados e sair do jogo.
 */
public class MenuInicioPartida extends Menu {
	public MenuInicioPartida() {
		super("Menu Partida", new ArrayList<ItemMenu>() {

			/** Serial gerado automaticamente. */
			private static final long serialVersionUID = -2202065529317389240L;

			{
				add(new ItemMenu("SINGLEPLAYER",
						"Iniciar uma nova partida contra o computador"));
				add(new ItemMenu("MULTIPLAYER",
						"Iniciar uma nova partida contra outro jogador"));
				add(new ItemMenu("RETORNAR", "Retornar ao menu principal"));
			}

		});
	}
}
