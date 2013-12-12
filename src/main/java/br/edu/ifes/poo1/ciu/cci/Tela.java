package br.edu.ifes.poo1.ciu.cci;

import br.edu.ifes.poo1.ciu.cih.Cli;
import br.edu.ifes.poo1.ciu.cih.EntradaMenuInvalida;
import br.edu.ifes.poo1.ciu.cih.ItemMenuPrincipal;
import br.edu.ifes.poo1.ciu.cih.Terminal;

/**
 * Controla a entrada e a saída da interface de linha de comando.
 * 
 * @author lucas
 * 
 */
public class Tela {
	private Cli cli = new Terminal();
	
	public void iniciarJogo() {
		menuPrincipal();
	}

	/**
	 * Atualiza os elementos da tela.
	 */
	private void atualizar() {
		// TODO: Implementar.
	}

	/**
	 * Pede a jogada do usuário.
	 */
	private void pedirJogada() {
		// TODO: Implementar.
	}

	/**
	 * Pede o nome do jogado.
	 */
	private void pedirNome() {
		// TODO: Implementar.
	}

	/**
	 * Pede para exibir o menu principal.
	 */
	private void menuPrincipal() {
		// Teste do Menu Principal
		try {
			ItemMenuPrincipal itemEscolhido = cli.exibirMenuPrincipal();
			System.out.println(itemEscolhido.getDescricao());
		} catch (EntradaMenuInvalida e) {
			System.out.println("Entrada de menu inválida.");
		}
		
	}
}
