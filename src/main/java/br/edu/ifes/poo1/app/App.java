package br.edu.ifes.poo1.app;

import br.edu.ifes.poo1.ciu.cci.Controlador;


/**
 * Aplicação principal, para a execução do jogo na linha de comando.
 */
public class App {

	public static void main(String[] args) {
		Controlador ctrl = new Controlador();
		ctrl.iniciarJogo();
		System.out.println("Terminando a aplicação...");
	}

}
