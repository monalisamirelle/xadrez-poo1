package br.edu.ifes.poo1.app;

import br.edu.ifes.poo1.ciu.cci.Controlador;
import br.edu.ifes.poo1.cln.cdp.CasaOcupadaException;

/**
 * Aplicação principal, para a execução do jogo na linha de comando.
 */
public class App {

	public static void main(String[] args) throws CasaOcupadaException, CloneNotSupportedException {
		// Inicia o jogo.
		Controlador ctrl = new Controlador();
		ctrl.iniciar();
	}

}
