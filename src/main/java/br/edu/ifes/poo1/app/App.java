package br.edu.ifes.poo1.app;

import br.edu.ifes.poo1.ciu.cci.Controlador;

/**
 * Aplicação principal, para a execução do jogo na linha de comando.
 */
public class App {

	public static void main(String[] args) {
		System.out.print("\u001b[34;43m"); // muda a cor. 30 - 37; 40 - 47;
		System.out.println("hello world! Testando a cor no terminal.");
		Controlador ctrl = new Controlador();
		ctrl.iniciarJogo();
		System.out.println("Terminando a aplicação...");
	}

}
