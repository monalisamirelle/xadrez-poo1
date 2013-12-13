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

	/**
	 * Inicia o jogo.
	 */
	public void iniciarJogo() {
		ItemMenuPrincipal itemMenu;

		itemMenu = menuPrincipal();
		while (itemMenu != ItemMenuPrincipal.SAIR) {
			switch (itemMenu) {
			case SINGLEPLAYER:
				System.out.println("O singleplayer não foi implementado ainda. :S");
				// TODO: Implementar o singleplayer.
				break;
				
			case MULTIPLAYER:
				cli.lerNomeJogadorBranco();
				cli.lerNomeJogadorPreto();
				// TODO: Implementar o multiplayer.
				break;
				
			case DADOS:
				System.out.println("O visualização dos dados não foi implementado ainda. :S");
				// TODO: Implementar a visualização dos dados.
				break;
				
			default:
				break;
			}
			itemMenu = menuPrincipal();
		}
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
	 * Exibe o menu principal e pega a escolha do usuário.
	 */
	// FIXME: Deve ter um jeito melhor de fazer isso do que usando um laço
	// infinito.
	private ItemMenuPrincipal menuPrincipal() {
		do {
			// Tenta retornar uma entrada de menu selecionada pelo jogador.
			try {
				// Retorna o item escolhido pelo jogador
				return cli.exibirMenuPrincipal();
			} catch (EntradaMenuInvalida e) {
				// Se o jogador escolher alguma entrada inválida, avise-o.
				System.out.println("Entrada de menu inválida.");
				continue; // E repita o laço.
			}
		} while (true);
	}
}
