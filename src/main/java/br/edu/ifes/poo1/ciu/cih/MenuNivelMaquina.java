package br.edu.ifes.poo1.ciu.cih;

import java.util.ArrayList;

public class MenuNivelMaquina extends Menu {
	public MenuNivelMaquina() {
		super("Nível de inimigos: ", new ArrayList<ItemMenu>() {

			/** Serial gerado automaticamente. */
			private static final long serialVersionUID = 1379733000527534784L;

			{
				add(new ItemMenu("CERBERO",
						"Cérbero: Do que adiantam 3 cabeças se nenhuma é capaz de pensar?"));
				add(new ItemMenu("DIONISIO",
						"Dionísio: Esse oponente está embriagado demais para saber o que está fazendo"));
				add(new ItemMenu("ARES",
						"Ares: Age sempre de maneira agressiva não medindo a consequência de seus atos"));
				add(new ItemMenu("ZEUS",
						"Zeus: Oh, Pai, eterno e inefável. deus infalível, Criador do universo"));
				add(new ItemMenu("PROMETEU",
						"Prometeu: Aquele que sabe antes..."));
			}
		});
	}
}
