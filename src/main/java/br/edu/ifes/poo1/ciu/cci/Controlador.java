package br.edu.ifes.poo1.ciu.cci;

import java.util.List;

import br.edu.ifes.poo1.ciu.cih.Cli;
import br.edu.ifes.poo1.ciu.cih.EntradaSaida;
import br.edu.ifes.poo1.ciu.cih.Interpretador;
import br.edu.ifes.poo1.ciu.cih.ItemMenu;
import br.edu.ifes.poo1.ciu.cih.Menu;
import br.edu.ifes.poo1.ciu.cih.MenuAmbiente;
import br.edu.ifes.poo1.ciu.cih.MenuCorJogador;
import br.edu.ifes.poo1.ciu.cih.MenuDadosPartidas;
import br.edu.ifes.poo1.ciu.cih.MenuInicioPartida;
import br.edu.ifes.poo1.ciu.cih.MenuNivelMaquina;
import br.edu.ifes.poo1.ciu.cih.MenuPrincipal;
import br.edu.ifes.poo1.ciu.cih.MenuRetornarPartida;
import br.edu.ifes.poo1.ciu.cih.Prompt;
import br.edu.ifes.poo1.ciu.cih.Terminal;
import br.edu.ifes.poo1.cln.cdp.CasaOcupadaException;
import br.edu.ifes.poo1.cln.cdp.DadosPartida;
import br.edu.ifes.poo1.cln.cdp.IAElaborada;
import br.edu.ifes.poo1.cln.cdp.IARandomica;
import br.edu.ifes.poo1.cln.cdp.Jogada;
import br.edu.ifes.poo1.cln.cdp.JogadaInvalidaException;
import br.edu.ifes.poo1.cln.cdp.Jogador;
import br.edu.ifes.poo1.cln.cdp.Maquina;
import br.edu.ifes.poo1.cln.cdp.Pessoa;
import br.edu.ifes.poo1.cln.cdp.TipoCorJogador;
import br.edu.ifes.poo1.cln.cdp.TipoJogador;
import br.edu.ifes.poo1.cln.cdp.TipoSituacaoPartida;
import br.edu.ifes.poo1.cln.cgt.AplJogo;
import br.edu.ifes.poo1.cln.cgt.ManipuladorArquivo;

/**
 * Faz o intermédio entre a camada do modelo de dados e a parte da visão. Para
 * que as entradas do jogador sejam interpretadas e executadas devidamente no
 * modelo. Também atualiza as informações que estão disponíveis na tela do
 * jogador.
 */
// FIXME BUGA SE INSERE ALGO DIFERENTE DE NÚMERO NOS MENUS
public class Controlador {
	private Cli cli;
	private ManipuladorArquivo manipuladorArquivo = new ManipuladorArquivo();

	/**
	 * Pede ao usuário para selecionar a interface a ser usada.
	 */
	public Controlador() {
		this(new MenuAmbiente().insistirPorEntradaValida(new EntradaSaida()));
	}

	/**
	 * Inicia o controlador indicando que ambiente deverá ser usado para a
	 * comunicação com o usuário.
	 * 
	 * @param ambiente
	 *            Ambiente a ser usado.
	 */
	public Controlador(ItemMenu ambiente) {
		switch (ambiente.getNome()) {
		case "PROMPT":
			cli = new Prompt();
			break;
		case "TERMINAL":
			cli = new Terminal();
			break;

		default:
			cli = new Prompt();
			break;
		}
	}

	/**
	 * Inicia o jogo. Serão exibidos os menus necessários e faz todo o controle
	 * necessário do jogo.
	 * 
	 */
	public void iniciar() {
		// Este é o item do menu que o jogador escolheu (escolherá).
		Menu menuPrincipal = new MenuPrincipal();

		// Inicia o menu deixando a escolha para o usuário do que fazer.
		ItemMenu itemEscolhido = menuPrincipal.insistirPorEntradaValida(cli
				.getIo());

		// Só termina o laço quando o jogador selecionar a opção de sair no menu
		// principal.
		while (!itemEscolhido.getNome().contentEquals("SAIR")) {
			// Busca qual foi a escolha do jogador.
			switch (itemEscolhido.getNome()) {
			case "PARTIDA":
				iniciaPartida();
				break;
			case "RETORNO":
				retornaPartida();
				break;
			// Se o jogador tiver escolhido ver os dados das partidas.
			case "DADOS":
				controlarExibicaoPartidas();
				break;
			default:
				System.out.println("Esta opção não foi implementada ainda");
				break;
			}

			// Re-exibe o menu para que o jogador selecione o que deseja.
			itemEscolhido = menuPrincipal.insistirPorEntradaValida(cli.getIo());
		}

		// Se a execução chegou aqui, é porque o jogador optou por sair de todo
		// o jogo.
	}

	/**
	 * Método responsável por iniciar uma partida singleplayer ou multiplayer
	 */
	private void iniciaPartida() {
		// Este é o item do menu que o jogador escolheu (escolherá).
		Menu menuInicioPartida = new MenuInicioPartida();

		// Inicia o menu deixando a escolha para o usuário do que fazer.
		ItemMenu itemEscolhido = menuInicioPartida.insistirPorEntradaValida(cli
				.getIo());
		switch (itemEscolhido.getNome()) {
		// Se o jogador tiver escolhido jogar o singleplayer.
		case "SINGLEPLAYER":
			AplJogo apl = prepararSingleplayer();
			controlarPartida(apl);
			break;
		// Se o jogador tiver escolhido o multiplayer.
		case "MULTIPLAYER":
			apl = prepararMultiplayer();
			controlarPartida(apl);
			break;
		}
	}

	/**
	 * Método que prepara uma pessoa para ser jogada no Singleplayer
	 * 
	 * @return
	 */
	private Pessoa prepararPessoaSingleplayer() {
		Menu menuCorJogador = new MenuCorJogador();
		ItemMenu corEscolhida = menuCorJogador
				.insistirPorEntradaValida(new EntradaSaida());
		// Escolhe o nome e retorna uma pessoa
		switch (corEscolhida.getNome()) {
		case "BRANCO":
			return new Pessoa(cli.lerNomeJogadorBranco(), TipoCorJogador.BRANCO);
		case "PRETO":
			return new Pessoa(cli.lerNomeJogadorPreto(), TipoCorJogador.PRETO);
		}
		return null;
	}

	/**
	 * Método que prepara uma máquina para ser jogada no singleplayer
	 * 
	 * @param corMaquina
	 * @return
	 */
	private Maquina prepararMaquinaSingleplayer(TipoCorJogador corMaquina) {
		// Nível do inimigo
		Menu menuNivelMaquina = new MenuNivelMaquina();
		ItemMenu nivelEscolhido = menuNivelMaquina
				.insistirPorEntradaValida(new EntradaSaida());

		// Criar máquina
		Maquina maquina = null;
		switch (nivelEscolhido.getNome()) {
		case "CERBERO":
			maquina = new IAElaborada("Cerbero", corMaquina, 10, 15, false);
			break;
		case "DIONISIO":
			maquina = new IARandomica("Dionisio", corMaquina);
			break;
		case "ARES":
			maquina = new IAElaborada("Ares", corMaquina, 1, 45, true);
			break;
		case "ZEUS":
			maquina = new IAElaborada("Zeus", corMaquina, 10, 15, true);
			break;
		case "PROMETEU":
			maquina = new IAElaborada("Prometeu", corMaquina, 10, 45, true);
			break;
		}
		return maquina;
	}

	/**
	 * Prepara e captura todos os dados de uma partida Singleplayer
	 * 
	 * @return
	 */
	private AplJogo prepararSingleplayer() {

		// Cria a pessoa
		Pessoa pessoa = prepararPessoaSingleplayer();

		// Cria a máquina
		Maquina maquina = prepararMaquinaSingleplayer(TipoCorJogador
				.getCorOposta(pessoa.getCor()));

		// Contrói a aplicação do jogo
		return new AplJogo(pessoa, maquina);
	}

	/**
	 * Prepara e captura todos os dados de uma partida Multiplayer
	 * 
	 * @return
	 */
	private AplJogo prepararMultiplayer() {
		// Pega o nome dos jogadores.
		String nomeBrancas = cli.lerNomeJogadorBranco();
		String nomePretas = cli.lerNomeJogadorPreto();

		// Constrói a aplicação do jogo.
		return new AplJogo(new Pessoa(nomeBrancas, TipoCorJogador.BRANCO),
				new Pessoa(nomePretas, TipoCorJogador.PRETO));
	}

	/**
	 * Método responsável por iniciar uma partida singleplayer ou multiplayer
	 */
	// TODO apagar entra aqui?
	private void retornaPartida() {

		// Cria uma lista somente com as partidas que não foram concluídas
		List<DadosPartida> listaPartidasPausadas = manipuladorArquivo
				.criarListaPartidasPausadas();

		// Exibe as partidas atuais
		exibirPartidasAndamento(listaPartidasPausadas);

		// Este é o item do menu que o jogador escolheu (escolherá).
		Menu menuRetornarPartida = new MenuRetornarPartida();

		// Inicia o menu deixando a escolha para o usuário do que fazer.
		ItemMenu itemEscolhido = menuRetornarPartida
				.insistirPorEntradaValida(cli.getIo());

		switch (itemEscolhido.getNome()) {
		// Se o jogador tiver escolhido jogar o singleplayer.
		case "REINICIAR":
			// Tente carregar uma partida
			AplJogo apl = buscarCarregarPartida(listaPartidasPausadas);
			// Tente iniciar uma partida
			try {
				controlarPartida(apl);
			} catch (Exception e) {
				cli.exibirAlerta("Nenhuma partida foi carregada");
			}
			break;
		}
	}

	// TODO (precisa da situação da partida se todas se encontram pausadas aqui?)
	private void exibirPartidasAndamento(List<DadosPartida> listaPartidas) {
		// Exibe os campos da lista de jogos
		cli.imprimirLinha("Lista de jogos:\n");
		cli.imprimirLinha("Índice" + "..." + "Data Início" + "..." + "Data Fim"
				+ "..." + "Jogador Branco" + "..." + "Jogador Preto" + "..."
				+ "Situação da Partida\n");
		// Exibe a lista de jogos
		int indice = 0;
		for (DadosPartida partida : listaPartidas)
			if (partida.getJogo().getMotivoDeFinalizacao() == TipoSituacaoPartida.PAUSA) {
				cli.exibirDadosPartidas(indice, partida);
				indice++;
			}
		System.out.println("");
	}

	/**
	 * Controla uma partida durante seu andamento
	 * 
	 * @param apl
	 */
	// FIXME Oferecer ao jogador brancas e pretas INVERTENDO O TABULEIRO
	// prejudica na lógica do peão, en passant e, provavelmente, promoção. Seria
	// mesmo, considerando a falta de tempo, interessante inverter o tabuleiro
	// ou apenas deixar como está para o jogador jogar? (não inverte e ele pode
	// andar com pretas seguindo a lógica já criada)
	private void controlarPartida(AplJogo apl) {
		// Enquando não acabar o jogo, continuamos executando as jogadas
		// dos jogadores e exibindo o estado do tabuleiro.
		String acaoJogador;
		String aviso = "";
		while (!apl.isSairPartida()) {
			// Atualiza o que é visual para os jogadores. Exibe o aviso se for
			// necessário.
			if (aviso == "")
				cli.atualizar(apl.getTabuleiro(), apl.getJogadorBrancas(),
						apl.getJogadorPretas());
			else
				cli.atualizar(apl.getTabuleiro(), apl.getJogadorBrancas(),
						apl.getJogadorPretas(), aviso);

			// Retira a mensagem de aviso.
			aviso = "";

			// Máquina executa uma jogada
			Jogada jogada = null;
			if (apl.getJogadorTurnoAtual().getTipoJogador() == TipoJogador.PESSOA) {
				// Pede o movimento do jogador.
				acaoJogador = cli.lerAcaoJogador(apl.getJogadorTurnoAtual());
				acaoJogador = acaoJogador.toUpperCase();
				// TODO conferir
				switch (acaoJogador) {
				case "PONTOS":
					cli.imprimirPontuacoes(apl.getJogadorBrancas(),
							apl.getJogadorPretas());
					cli.imprimirLinha("");
					break;
				case "DESISTIR":
					apl.finalizarPartida(apl.getJogadorTurnoAtual(),
							TipoSituacaoPartida.DESISTENCIA);
					encerrarPartida(apl);
					manipuladorArquivo.gravarPartida(apl);
					break;
				case "EMPATE":
					if (requisitarEmpate(apl)) {
						apl.finalizarPartida(TipoSituacaoPartida.EMPATE);
						encerrarPartida(apl);
						manipuladorArquivo.gravarPartida(apl);
					}
					break;
				case "SALVAR":
					manipuladorArquivo.gravarPartida(apl);
					break;
				case "SAIR":
					apl.finalizarPartida(TipoSituacaoPartida.PAUSA);
					encerrarPartida(apl);
					manipuladorArquivo.gravarPartida(apl);
					break;
				default:
					// Executa o movimento do jogador.
					try {
						jogada = Interpretador.interpretarJogada(acaoJogador);
					} catch (JogadaInvalidaException e) {
						cli.exibirAlerta("Comando desconhecido");
					}
					break;
				}
			} else {
				Maquina maquina = (Maquina) apl.getJogadorTurnoAtual();
				try {
					jogada = maquina.escolherJogada(apl.getTabuleiro());
					// Forma de escapar caso máquina não retorne nenhuma jogada
					if (jogada == null) {
						Maquina maquinaApoio = new IARandomica("",
								maquina.getCor());
						jogada = maquinaApoio
								.escolherJogada(apl.getTabuleiro());
						System.out.println("RETORNOU NULL");
					}
				} catch (JogadaInvalidaException e) {
					cli.exibirAlerta("Máquina apresentou problemas ao tentar realizar jogada");
				} catch (CasaOcupadaException e) {
					cli.imprimirLinha("Casa se encontra ocupada");
				} catch (InterruptedException e) {
					cli.imprimirLinha("Jogada interrompida");
				}
			}

			if (jogada != null) {
				try {
					apl.executarJogadaTurno(jogada);
					apl.trocarTurno();
					apl.getTabuleiro().resetaPodeEnPassant(
							apl.getJogadorTurnoAtual().getCor());
				} catch (JogadaInvalidaException e) {
					cli.exibirAlerta("Comando desconhecido");
				} catch (CasaOcupadaException e) {
					cli.imprimirLinha("Casa se encontra ocupada");
				}
			}
		}

		// Encerra a partida.
		encerrarPartida(apl);
	}

	/**
	 * Realiza uma requisição de empate a outro jogador. O atributo
	 * "númeroEscolha" foi colocado propositalmente para fazer com que qualquer
	 * coisa não seja considerada como false
	 * 
	 * @param solicitador
	 * @return
	 */
	private boolean requisitarEmpate(AplJogo apl) {
		// Se for uma partida multiplayer
		if (apl.getJogadorBrancas().getTipoJogador() == TipoJogador.PESSOA
				&& apl.getJogadorPretas().getTipoJogador() == TipoJogador.PESSOA) {
			int numeroEscolha = -1;
			do {
				cli.imprimirLinha(apl.getJogadorTurnoAtual().getNome()
						+ " está requisitando empate. Deseja aceitar?");
				cli.imprimirLinha("0. Não");
				cli.imprimirLinha("1. Sim");
				String resposta = cli.pedir("Resposta: ");
				try {
					numeroEscolha = Integer.valueOf(resposta);
				} catch (Exception e) {
					cli.exibirAlerta("Erro, escolha um número!");
				}
				if (numeroEscolha != 0 && numeroEscolha != 1)
					cli.exibirAlerta("Opção inválida!");
			} while (numeroEscolha != 0 && numeroEscolha != 1);
			if (numeroEscolha == 0)
				return false;
			return true;
			// Se algum jogador for uma máquina, aceita empate
		} else
			return true;
	}

	/**
	 * Termina a partida exibindo um cumprimento aos jogadores.
	 * 
	 * @param apljogo
	 *            Apl que em que a partida encerrou.
	 */
	private void encerrarPartida(AplJogo apljogo) {
		// Após o fim do jogo, pegamos o vencedor, atualizamos o
		// tabuleiro mais uma vez e comprimentamos o ganhador.
		cli.atualizar(apljogo.getTabuleiro(), apljogo.getJogadorBrancas(),
				apljogo.getJogadorPretas());
		TipoSituacaoPartida motivoFim = apljogo.getMotivoDeFinalizacao();
		// Vê o fim da partida para fazer o encerramento de forma adequada.
		switch (motivoFim) {
		// Se houve desistência, ou vitória, houve um ganhador.
		case VITORIA:
		case DESISTENCIA:
			Jogador vencedor = apljogo.getVencedor();
			cli.fechamentoDaPartida("Vitória para o jogador: "
					+ vencedor.getNome());
			break;
		// A partida terminou em um empate.
		case EMPATE:
			cli.fechamentoDaPartida("A partida terminou em um empate.");
			break;
		// A partida foi pausada
		case PAUSA:
			cli.fechamentoDaPartida("Jogo foi pausado.");
			break;
		default:
			break;
		}
	}

	/**
	 * Método que busca carregar uma partida informada por um usuário
	 * 
	 * @param maiorIndice
	 * @return
	 */
	private AplJogo buscarCarregarPartida(List<DadosPartida> listaPartidas) {
		int indice = -1;
		do {
			// Tenta capturar uma partida
			try {
				indice = Integer.parseInt(cli.pedeIndicePartidaCarregar());
				// Lança uma exception se não conseguir
			} catch (Exception e) {
				cli.exibirAlerta("Digite um número referente ao índice!");
			}
			// Se for um índice válido
			if (indice >= 0 & indice < listaPartidas.size())
				return manipuladorArquivo
						.carregarPartida(indice, listaPartidas);
			// Se for para retornar
			else if (indice == -1)
				return null;
			// Se for um índice inesperado
			else
				cli.exibirAlerta("O índice digitado não existe!");
		} while (indice < 0 || indice >= listaPartidas.size());
		cli.imprimirLinha("");
		return null;
	}

	/**
	 * Método que busca apagar uma partida informada pelo usuário (é
	 * inicialmente apagada em memória e posteriormente em arquivo)
	 * 
	 * @param maiorIndice
	 */
	private List<DadosPartida> buscarApagarPartida(
			List<DadosPartida> listaPartidas) {
		int indice = -2;
		do {
			// Tenta capturar uma partida
			try {
				indice = Integer.parseInt(cli.pedeIndicePartidaCarregar());
				// Lança uma exception se não conseguir
			} catch (Exception e) {
				cli.exibirAlerta("Digite um número referente ao índice!");
			}
			// Se for um índice válido
			if (indice >= 0 & indice < listaPartidas.size())
				return manipuladorArquivo.apagarPartida(indice, listaPartidas);
			// Se for para retornar
			else if (indice == -1)
				cli.imprimirLinha("Retornando ao menu de partidas");
			// Se for um índice inesperado
			else
				cli.exibirAlerta("O índice digitado não existe!");
		} while (indice < -1 || indice >= listaPartidas.size());
		cli.imprimirLinha("");
		return listaPartidas;
	}

	/**
	 * Método responsável por controlar a exibição dos dados de partidas
	 */
	// TODO está para ser depreciado
	private void controlarExibicaoPartidas() {

		List<DadosPartida> listaPartidas = manipuladorArquivo
				.lerListaPartidas();

		ItemMenu opcao = new ItemMenu("", "");
		boolean iniciouPartida = false;
		while (!listaPartidas.isEmpty() & opcao.getNome() != "VOLTAR"
				& iniciouPartida == false) {
			// TODO deixar menu certinho em formatação
			cli.imprimirLinha("Lista de jogos:\n");
			cli.imprimirLinha("Índice" + "..." + "Data" + "..."
					+ "Jogador Branco" + "..." + "Jogador Preto" + "..."
					+ "Situação da Partida\n");

			for (int indice = 0; indice < listaPartidas.size(); indice++)
				cli.exibirDadosPartidas(indice, listaPartidas.get(indice));

			System.out.println("");
			Menu menuDadosPartidas = new MenuDadosPartidas();
			opcao = menuDadosPartidas
					.insistirPorEntradaValida(new EntradaSaida());
			// Escolher uma opção
			switch (opcao.getNome()) {
			// Se escolher para carregar
			case "CARREGAR":
				// Tente carregar uma partida
				AplJogo apl = buscarCarregarPartida(listaPartidas);
				// Tente iniciar uma partida
				try {
					controlarPartida(apl);
					iniciouPartida = true;
				} catch (Exception e) {
					cli.exibirAlerta("Nenhuma partida foi carregada");
				}
				break;
			// Se escolher para apagar uma partida
			case "APAGAR":
				// Apague a partida em memória
				listaPartidas = buscarApagarPartida(listaPartidas);
				break;
			// Se escolher por voltar
			case "VOLTAR":
				// Salve as alterações realizadas (apagar partida, etc)
				manipuladorArquivo.gravarListaPartidas(listaPartidas);
				break;
			}
		}
	}
}
