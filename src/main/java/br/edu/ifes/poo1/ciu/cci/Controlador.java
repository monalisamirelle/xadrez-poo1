package br.edu.ifes.poo1.ciu.cci;

import br.edu.ifes.poo1.ciu.cih.Cli;
import br.edu.ifes.poo1.ciu.cih.EntradaMenuInvalidaException;
import br.edu.ifes.poo1.ciu.cih.ItemMenuPrincipal;
import br.edu.ifes.poo1.ciu.cih.Terminal;
import br.edu.ifes.poo1.cln.cdp.Jogador;
import br.edu.ifes.poo1.cln.cgt.AplMultiplayer;

/**
 * Controla a entrada e a saída da interface de linha de comando.
 */
public class Controlador {
	private Cli cli = new Terminal();

	/**
	 * Inicia o jogo.
	 */
	public void iniciarJogo() {
		// Este é o item do menu que o jogador escolheu (escolherá).
		ItemMenuPrincipal itemEscolhido;

		// Inicia o menu deixando a escolha para o usuário do que fazer.
		itemEscolhido = menuPrincipal();

		// Só termina o laço quando o jogador selecionar a opção de sair no menu
		// principal.
		while (itemEscolhido != ItemMenuPrincipal.SAIR) {
			// Busca qual foi a escolha do jogador.
			switch (itemEscolhido) {

			// Se o jogador tiver escolhido jogar o singleplayer.
			case SINGLEPLAYER:
				System.out
						.println("O singleplayer não foi implementado ainda. :S");
				// TODO: Implementar o singleplayer.
				break;

			// Se o jogador tiver escolhido o multiplayer.
			case MULTIPLAYER:
				// Pega o nome dos jogadores.
				String nomeBrancas = cli.lerNomeJogadorBranco();
				String nomePretas = cli.lerNomeJogadorPreto();

				// Constrói a aplicação do jogo.
				AplMultiplayer aplmulti = new AplMultiplayer(nomeBrancas,
						nomePretas);

				// Enquando não acabar o jogo, continuamos executando as jogadas
				// dos jogadores e exibindo o estado do tabuleiro.
				String jogada;
				while (!aplmulti.acabouOJogo()) {
					// Atualiza o que é visual para os jogadores.
					cli.atualizar(aplmulti.getTabuleiro(),
							aplmulti.getBrancas(), aplmulti.getPretas());

					// Pede e executa uma nova jogada.
					jogada = cli.lerJogada();
					aplmulti.executarjogada(jogada);
				}

				// Após o fim do jogo, pegamos o vencedor, atualizamos o
				// tabuleiro mais uma vez e comprimentamos o ganhador.
				Jogador vencedor = aplmulti.getVencedor();
				cli.atualizar(aplmulti.getTabuleiro(), aplmulti.getBrancas(),
						aplmulti.getPretas());
				cli.parabenizarVencedor(vencedor);
				break;

			// Se o jogador tiver escolhido ver os dados das partidas.
			case DADOS:
				System.out
						.println("A visualização dos dados não foi implementado ainda. :S");
				// TODO: Implementar a visualização dos dados.
				break;

			default:
				System.out.println("Esta opção não foi implementada ainda");
				break;
			}

			// Re-exibe o menu para que o jogador selecione o que deseja.
			itemEscolhido = menuPrincipal();
		}

		// Se a execução chegou aqui, é porque o jogador optou por sair.
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
			} catch (EntradaMenuInvalidaException e) {
				// Se o jogador escolher alguma entrada inválida, avise-o.
				System.out.println("Entrada de menu inválida.");
				continue; // E repita o laço.
			}
		} while (true);
	}
}
