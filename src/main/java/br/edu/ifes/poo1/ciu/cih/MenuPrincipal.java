package br.edu.ifes.poo1.ciu.cih;

import java.util.ArrayList;

/**
 * Classe para um menu com as atividades principais do jogo, como jogar,
 * visualizar dados e sair do jogo.
 */
public class MenuPrincipal extends Menu {
	public MenuPrincipal() {
		super("Menu Principal", new ArrayList<ItemMenu>() {

			/** Serial gerado automaticamente. */
			private static final long serialVersionUID = -2202065529317389240L;

			{
				add(new ItemMenu("PARTIDA",
						"Iniciar uma nova partida"));
				add(new ItemMenu("RETORNO",
						"Retornar uma partida"));
				add(new ItemMenu("DADOS", "Dados das partidas"));
				add(new ItemMenu("SAIR", "Sair"));
			}
		});
	}
}
