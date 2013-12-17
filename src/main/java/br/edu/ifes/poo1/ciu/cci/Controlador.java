package br.edu.ifes.poo1.ciu.cci;

import br.edu.ifes.poo1.ciu.cih.Cli;
import br.edu.ifes.poo1.ciu.cih.EntradaMenuInvalidaException;
import br.edu.ifes.poo1.ciu.cih.ItemMenuPrincipal;
import br.edu.ifes.poo1.ciu.cih.Terminal;
import br.edu.ifes.poo1.cln.cdp.Jogada;
import br.edu.ifes.poo1.cln.cdp.JogadaInvalidaException;
import br.edu.ifes.poo1.cln.cdp.Jogador;
import br.edu.ifes.poo1.cln.cdp.Posicao;
import br.edu.ifes.poo1.cln.cdp.TipoPeca;
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
						jogadaInterpretada = interpretarJogada(jogadaCrua);
						aplmulti.executarjogada(jogadaInterpretada);
					} catch (JogadaInvalidaException e) {
						// Avisa o usuário do erro e continua o jogo.
						cli.exibirAlerta(e.getMessage());
						continue;
					}
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

	/**
	 * Decodifica a entrada do jogador para o formato adequado.
	 * 
	 * @param jogada
	 *            Entrada do jogador.
	 * @return Uma jogada já interpretada.
	 * @throws JogadaInvalidaException
	 *             Lançada se a entrada do usuário não puder ser convertida para
	 *             uma jogada válida.
	 */
	private static Jogada interpretarJogada(String jogada)
			throws JogadaInvalidaException {
		// Inicia os dados necessários para criação da jogada.
		String strOrigem;
		String strDestino;
		boolean ehAtaque;
		boolean ehPromocao;
		char promocaoChar = 0; // Será sobrescrito depois, se necessário.

		// Dicerne o tipo da jogada.
		switch (jogada.length()) {
		case 4: // Será uma jogada simples sem ataque, como: 1253
			// Preenche os dados necessários.
			strOrigem = jogada.substring(0, 2);
			strDestino = jogada.substring(2, 4);
			ehAtaque = false;
			ehPromocao = false;
			break;

		case 5: // Será uma jogada simples do tipo ataque, como: 24x53
			// Verifica o 'x' no meio.
			if (jogada.charAt(2) != 'x')
				throw new JogadaInvalidaException(
						"O comando dado não pode ser interpretado.");

			// Preenche os dados necessários.
			strOrigem = jogada.substring(0, 2);
			strDestino = jogada.substring(3, 5);
			ehAtaque = true;
			ehPromocao = false;
			break;

		case 6: // Jogada sem ataque, mas com promoção de peão, como: 4568=D
			// Verifica o '=' da promoção.
			if (jogada.charAt(4) != '=')
				throw new JogadaInvalidaException(
						"O comando dado não pode ser interpretado.");

			// Preenche os dados necessários.
			strOrigem = jogada.substring(0, 2);
			strDestino = jogada.substring(2, 4);
			ehAtaque = false;
			ehPromocao = true;
			promocaoChar = jogada.charAt(5);
			break;
		case 7: // Jogada com ataque e promoção de peão, como: 57x48=T
			// Verifica o 'x' do ataque e o '=' da promoção.
			if (jogada.charAt(2) != 'x' || jogada.charAt(4) != '=')
				throw new JogadaInvalidaException(
						"O comando dado não pode ser interpretado.");

			// Preenche os dados necessários.
			strOrigem = jogada.substring(0, 2);
			strDestino = jogada.substring(3, 5);
			ehAtaque = true;
			ehPromocao = true;
			promocaoChar = jogada.charAt(6);
			break;
		default: // Qualquer outro tipo de jogada será inválida.
			throw new JogadaInvalidaException(
					"O comando dado não pode ser interpretado.");
		}

		// Pega as colunas e as linhas.
		String origemColuna = strOrigem.substring(0, 1);
		String origemLinha = strOrigem.substring(1, 2);
		String destinoColuna = strDestino.substring(0, 1);
		String destinoLinha = strDestino.substring(1, 2);

		// Convertendo as colunas e linhas para inteiro.
		int intOrigemColuna, intOrigemLinha, intDestinoColuna, intDestinoLinha;
		try {
			intOrigemColuna = Integer.parseInt(origemColuna);
			intOrigemLinha = Integer.parseInt(origemLinha);
			intDestinoColuna = Integer.parseInt(destinoColuna);
			intDestinoLinha = Integer.parseInt(destinoLinha);
		} catch (NumberFormatException e) {
			throw new JogadaInvalidaException(
					"O comando dado não pode ser interpretado. Use algo como: 1213");
		}

		// Verifica se estão dentro dos limites do tabuleiro.
		if (intOrigemColuna < 1 || intOrigemColuna > 8 || intOrigemLinha < 1
				|| intOrigemLinha > 8 || intDestinoColuna < 1
				|| intDestinoColuna > 8 || intDestinoLinha < 1
				|| intDestinoLinha > 8)
			throw new JogadaInvalidaException(
					"Você está indicando uma casa fora dos limites do tabuleiro.");

		// Retorna a jogada interpretada.
		if (ehPromocao) {
			// Filtra o tipo de peça para o qual deseja-se promover o peão.
			TipoPeca promocao;
			switch (promocaoChar) {
			case 'D':
				promocao = TipoPeca.RAINHA;
				break;
			case 'T':
				promocao = TipoPeca.TORRE;
				break;
			case 'B':
				promocao = TipoPeca.BISPO;
				break;
			case 'C':
				promocao = TipoPeca.CAVALO;
				break;

			default:
				// Qualquer outra peça solicitada é inválida.
				throw new JogadaInvalidaException(
						"As promoções apenas podem acontecer para: rainha (D), torre(T), bispo (B) e cavalo(C).");
			}

			// Retorna uma jogada com promoção.
			return new Jogada(new Posicao(intOrigemColuna, intOrigemLinha),
					new Posicao(intDestinoColuna, intDestinoLinha), ehAtaque,
					promocao);
		} else
			// Retorna uma jogada simples, sem promoção.
			return new Jogada(new Posicao(intOrigemColuna, intOrigemLinha),
					new Posicao(intDestinoColuna, intDestinoLinha), ehAtaque);
	}
}
