package br.edu.ifes.poo1.ciu.cci;

import br.edu.ifes.poo1.ciu.cih.Ambiente;
import br.edu.ifes.poo1.ciu.cih.Cli;
import br.edu.ifes.poo1.ciu.cih.EntradaInvalidaException;
import br.edu.ifes.poo1.ciu.cih.Interpretador;
import br.edu.ifes.poo1.ciu.cih.ItemMenuPrincipal;
import br.edu.ifes.poo1.ciu.cih.Prompt;
import br.edu.ifes.poo1.ciu.cih.Terminal;
import br.edu.ifes.poo1.cln.cdp.Jogada;
import br.edu.ifes.poo1.cln.cdp.JogadaInvalidaException;
import br.edu.ifes.poo1.cln.cdp.Pessoa;
import br.edu.ifes.poo1.cln.cgt.AplMultiplayer;

/**
 * Controla a entrada e a saída da interface de linha de comando.
 */
public class Controlador {
	private Cli cli = new Terminal();

	/**
	 * Pede ao usuário para selecionar a interface a ser usada.
	 */
	public Controlador() {
		instanciarAmbiente(selecionarAmbiente());
	}

	/**
	 * Inicia o controlador indicando que ambiente deverá ser usado para a
	 * comunicação com o usuário.
	 * 
	 * @param ambiente
	 *            Ambiente a ser usado.
	 */
	public Controlador(Ambiente ambiente) {
		instanciarAmbiente(ambiente);

	}

	/**
	 * Intancia o modo de comunicação com o usuário.
	 * 
	 * @param ambiente
	 *            Ambiente a ser usado para a comunicação.
	 */
	public void instanciarAmbiente(Ambiente ambiente) {
		switch (ambiente) {
		case PROMPT:
			cli = new Prompt();
			break;
		case TERMINAL:
			cli = new Terminal();
			break;

		default:
			cli = new Prompt();
			break;
		}
	}

	/**
	 * Inicia o jogo.
	 */
	public void iniciarJogo() {
		// Este é o item do menu que o jogador escolheu (escolherá).
		ItemMenuPrincipal itemEscolhido;

		// Inicia o menu deixando a escolha para o usuário do que fazer.
		itemEscolhido = selecionarMenuPrincipal();

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
				String jogadaCrua;
				while (!aplmulti.getAcabouJogo()) {
					// Atualiza o que é visual para os jogadores.
					cli.atualizar(aplmulti.getTabuleiro(),
							aplmulti.getBrancas(), aplmulti.getPretas());

					// Pede o movimento do jogador.
					jogadaCrua = cli.lerJogada(aplmulti.getTurno());

					// Executa o movimento do jogador.
					Jogada jogadaInterpretada;
					try {
						jogadaInterpretada = Interpretador
								.interpretarJogada(jogadaCrua);
						aplmulti.executarjogada(jogadaInterpretada);
					} catch (JogadaInvalidaException e) {
						// Avisa o usuário do erro e continua o jogo.
						cli.exibirAlerta(e.getMessage());
						continue;
					}
				}

				// Após o fim do jogo, pegamos o vencedor, atualizamos o
				// tabuleiro mais uma vez e comprimentamos o ganhador.
				Pessoa vencedor = aplmulti.getVencedor();
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
			itemEscolhido = selecionarMenuPrincipal();
		}

		// Se a execução chegou aqui, é porque o jogador optou por sair.
	}

	/**
	 * Exibe o menu principal e pega uma escolha válida do usuário.
	 * 
	 * @return Item do menu principal selecionado.
	 */
	private ItemMenuPrincipal selecionarMenuPrincipal() {
		do {
			try {
				// Tenta retornar o item escolhido pelo jogador
				return cli.selecionarItemMenuPrincipal();
			} catch (EntradaInvalidaException e) {
				// Se o jogador escolher alguma entrada inválida, avise-o...
				cli.exibirAlerta(e.getMessage());
				continue; // ... e repita o laço.
			}
		} while (true);
	}

	/**
	 * Pega uma escolha de ambiente válida do usuário.
	 * 
	 * @return Ambiente escolhido.
	 */
	private Ambiente selecionarAmbiente() {
		do {
			try {
				// Tenta retornar o item escolhido pelo jogador
				return cli.selecionarAmbiente();
			} catch (EntradaInvalidaException e) {
				// Se o jogador escolher alguma entrada inválida, avise-o...
				cli.exibirAlerta(e.getMessage());
				continue; // ... e repita o laço.
			}
		} while (true);
	}
}
