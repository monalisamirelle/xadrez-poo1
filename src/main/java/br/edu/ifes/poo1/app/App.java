package br.edu.ifes.poo1.app;

import br.edu.ifes.poo1.ciu.cci.Controlador;

/**
 * Aplicação principal, para a execução do jogo na linha de comando.
 */
public class App {

	public static void main(String[] args) {

		// Inicia o jogo.
		Controlador ctrl = new Controlador();
		ctrl.iniciar();
	}
}
