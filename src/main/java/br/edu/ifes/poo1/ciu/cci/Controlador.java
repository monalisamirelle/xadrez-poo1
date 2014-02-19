package br.edu.ifes.poo1.ciu.cci;

import java.util.Collections;
import java.util.List;

import br.edu.ifes.poo1.ciu.cih.Cli;
import br.edu.ifes.poo1.ciu.cih.EntradaSaida;
import br.edu.ifes.poo1.ciu.cih.Interpretador;
import br.edu.ifes.poo1.ciu.cih.Prompt;
import br.edu.ifes.poo1.ciu.cih.Terminal;
import br.edu.ifes.poo1.ciu.cih.menus.ItemMenu;
import br.edu.ifes.poo1.ciu.cih.menus.Menu;
import br.edu.ifes.poo1.ciu.cih.menus.MenuAmbiente;
import br.edu.ifes.poo1.ciu.cih.menus.MenuCorJogador;
import br.edu.ifes.poo1.ciu.cih.menus.MenuDadosPartida;
import br.edu.ifes.poo1.ciu.cih.menus.MenuInicioPartida;
import br.edu.ifes.poo1.ciu.cih.menus.MenuNivelMaquina;
import br.edu.ifes.poo1.ciu.cih.menus.MenuPrincipal;
import br.edu.ifes.poo1.ciu.cih.menus.MenuRetornarPartida;
import br.edu.ifes.poo1.cln.cdp.CasaOcupadaException;
import br.edu.ifes.poo1.cln.cdp.DadosPartida;
import br.edu.ifes.poo1.cln.cdp.DadosPessoa;
import br.edu.ifes.poo1.cln.cdp.Jogada;
import br.edu.ifes.poo1.cln.cdp.JogadaInvalidaException;
import br.edu.ifes.poo1.cln.cdp.Jogador;
import br.edu.ifes.poo1.cln.cdp.Pessoa;
import br.edu.ifes.poo1.cln.cdp.TipoCorJogador;
import br.edu.ifes.poo1.cln.cdp.TipoJogador;
import br.edu.ifes.poo1.cln.cdp.TipoSituacaoPartida;
import br.edu.ifes.poo1.cln.cdp.ia.IAElaborada;
import br.edu.ifes.poo1.cln.cdp.ia.IARandomica;
import br.edu.ifes.poo1.cln.cdp.ia.Maquina;
import br.edu.ifes.poo1.cln.cgt.AplJogo;
import br.edu.ifes.poo1.cln.cgt.ManipuladorArquivo;

/**
 * Faz o intermédio entre a camada do modelo de dados e a parte da visão. Para
 * que as entradas do jogador sejam interpretadas e executadas devidamente no
 * modelo. Também atualiza as informações que estão disponíveis na tela do
 * jogador.
 */
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
				informaDadosPartida();
				break;
			default:
				cli.imprimir("Esta opção não foi implementada ainda");
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
			return new Pessoa(nomeValido(TipoCorJogador.BRANCO),
					TipoCorJogador.BRANCO);
		case "PRETO":
			return new Pessoa(nomeValido(TipoCorJogador.PRETO),
					TipoCorJogador.PRETO);
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
		Jogador pessoa = prepararPessoaSingleplayer();

		// Cria a máquina
		Jogador maquina = prepararMaquinaSingleplayer(TipoCorJogador
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
		// Pega o nome do jogador branco.
		String nomeBrancas = nomeValido(TipoCorJogador.BRANCO);
		// Pega o nome do jogador preto.
		String nomePretas = nomeValido(TipoCorJogador.PRETO, nomeBrancas);

		Jogador jogadorBranco = new Pessoa(nomeBrancas, TipoCorJogador.BRANCO);
		Jogador jogadorPreto = new Pessoa(nomePretas, TipoCorJogador.PRETO);
		// Constrói a aplicação do jogo.
		return new AplJogo(jogadorBranco, jogadorPreto);
	}

	/**
	 * Método que força o jogador a escolher um nome diferente de uma
	 * inteligência
	 * 
	 * @return
	 */
	private String nomeValido(TipoCorJogador cor) {
		boolean nomeValido = false;
		String nome = null;
		do {
			if (cor == TipoCorJogador.BRANCO)
				nome = cli.lerNomeJogadorBranco();
			else
				nome = cli.lerNomeJogadorPreto();
			String nomeCase = nome.toUpperCase();
			if (nomeCase.equals("CÉRBERO") || nomeCase.equals("DIONÍSIO")
					|| nomeCase.equals("ARES") || nomeCase.equals("ZEUS")
					|| nomeCase.equals("PROMETEU"))
				cli.exibirAlerta("Nome inválido, pertence a uma máquina");
			else
				nomeValido = true;
		} while (!nomeValido);
		return nome;
	}

	/**
	 * Método que força ao jogador escolher um nome diferente do adversário
	 * 
	 * @param cor
	 * @param nomeAdversario
	 * @return
	 */
	private String nomeValido(TipoCorJogador cor, String nomeAdversario) {
		boolean nomeValido = false;
		String nome = null;
		do {
			nome = nomeValido(cor);
			String nomeCase = nome.toUpperCase();
			if (nomeCase.equals(nomeAdversario.toUpperCase()))
				cli.exibirAlerta("Nome inválido, pertence ao adversário");
			else
				nomeValido = true;
		} while (!nomeValido);
		return nome;
	}

	/**
	 * Controla uma partida durante seu andamento
	 * 
	 * @param apl
	 */
	// FIXME Ao jogar SinglePlayer com peças pretas o tabuleiro está invertendo.
	// NÃO INVERTA!!!
	private void controlarPartida(AplJogo apl) {
		// Enquando não acabar o jogo, continuamos executando as jogadas
		// dos jogadores e exibindo o estado do tabuleiro.
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
			Jogada jogada = null;
			// Pessoa executa uma jogada
			if (apl.getJogadorTurnoAtual().getTipoJogador() == TipoJogador.PESSOA) {
				jogada = acaoRealizadaPessoa(apl);
				// Máquina executa uma jogada
			} else {
				jogada = acaoRealizadaMaquina(apl);
			}

			if (jogada != null) {
				try {
					apl.executarJogadaTurno(jogada);
					apl.trocarTurno();
					apl.getTabuleiro().resetaPodeEnPassant(
							apl.getJogadorTurnoAtual().getCor());
				} catch (JogadaInvalidaException e) {
					cli.exibirAlerta("Jogada inválida.");
				} catch (CasaOcupadaException e) {
					cli.imprimirLinha("Casa se encontra ocupada.");
				}
			}
		}

		// Encerra a partida.
		encerrarPartida(apl);
	}

	/**
	 * Método que captura a ação realizada por um jogador
	 * 
	 * @param apl
	 * @return
	 */
	private Jogada acaoRealizadaPessoa(AplJogo apl) {
		Pessoa pessoa = (Pessoa) apl.getJogadorTurnoAtual();
		String acaoJogador;
		// Pede o movimento do jogador.
		acaoJogador = cli.lerAcaoJogador(apl.getJogadorTurnoAtual());
		acaoJogador = acaoJogador.toUpperCase();
		Jogada jogada = null;
		switch (acaoJogador) {
		case "PONTOS":
			cli.imprimirPontuacoes(apl.getJogadorBrancas(),
					apl.getJogadorPretas());
			cli.imprimirLinha("");
			break;
		case "RECOMENDAR":
			boolean podeRecomendar = pessoa.verificarRecomendacoesRealizadas();
			if (podeRecomendar) {
				Jogada recomendacao = apl.getTabuleiro().recomendaJogada(
						apl.getJogadorTurnoAtual().getCor());
				pessoa.setRecomendacoes();
				cli.imprimirRecomendacao(pessoa.getRecomendacoes(), apl
						.getTabuleiro().espiarPeca(recomendacao.getOrigem()),
						recomendacao);
			} else
				cli.exibirAlerta("Você já realizou suas "
						+ pessoa.getTOTALRECOMENDACOES() + " recomendações.");
			break;
		case "DESISTIR":
			apl.finalizarPartida(apl.getOponente(),
					TipoSituacaoPartida.DESISTENCIA);
			break;
		case "EMPATE":
			if (requisitarEmpate(apl)) {
				apl.finalizarPartida(TipoSituacaoPartida.EMPATE);
			}
			break;
		case "SAIR":
			apl.finalizarPartida(TipoSituacaoPartida.PAUSA);
			break;
		case "SALVAR":
			manipuladorArquivo.gravarPartida(apl);
			break;
		case "AJUDA":
			cli.exibirComandos();
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
		return jogada;
	}

	/**
	 * Método que captura a ação realizada por uma máquina
	 * 
	 * @param apl
	 * @return
	 */
	private Jogada acaoRealizadaMaquina(AplJogo apl) {
		Maquina maquina = (Maquina) apl.getJogadorTurnoAtual();
		Jogada jogada = null;
		try {
			jogada = maquina.escolherJogada(apl.getTabuleiro());
		} catch (CasaOcupadaException e) {
			// TODO Auto-generated catch block
			cli.exibirAlerta("Erro, casa ocupada");
		}
		// Se a máquina não encontrar jogadas para realizar
		if (jogada == null) {
			// Desiste da partida
			apl.finalizarPartida(apl.getOponente(),
					TipoSituacaoPartida.DESISTENCIA);
		}
		return jogada;
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
					cli.exibirAlerta("Erro, escolha um número.");
				}
				if (numeroEscolha != 0 && numeroEscolha != 1)
					cli.exibirAlerta("Opção inválida.");
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
		TipoSituacaoPartida motivoFim = apljogo.getSituacaoPartida();
		// Vê o fim da partida para fazer o encerramento de forma adequada.
		switch (motivoFim) {
		// Se houve desistência, ou vitória, houve um ganhador.
		case VITORIA:
		case DESISTENCIA:
			String nomeVencedor = apljogo.getNomeVencedor();
			cli.fechamentoDaPartida("Vitória para o jogador: " + nomeVencedor);
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
		manipuladorArquivo.gravarPartida(apljogo);
	}

	/**
	 * Método responsável por iniciar uma partida singleplayer ou multiplayer
	 */
	// TODO apagar entra aqui? se entrar, deveremos salvar estado ao "voltar"
	// (ver método a ser depreciado no final da classe)
	private void retornaPartida() {
		// Captura de um arquivo as partidas nao finalizadas
		List<DadosPartida> listaPartidasNaoFinalizadas = manipuladorArquivo
				.criarListaPartidasNaoFinalizadas();

		// Exibe as partidas atuais
		exibirPartidasNaoFinalizadas(listaPartidasNaoFinalizadas);

		// Este é o item do menu que o jogador escolheu (escolherá).
		Menu menuRetornarPartida = new MenuRetornarPartida();

		boolean retornarMenu = false;
		do {
			// Inicia o menu deixando a escolha para o usuário do que fazer.
			ItemMenu itemEscolhido = menuRetornarPartida
					.insistirPorEntradaValida(cli.getIo());

			switch (itemEscolhido.getNome()) {
			// Se o jogador tiver escolhido jogar o singleplayer.
			case "REINICIAR":
				// Tente carregar uma partida
				AplJogo apl = buscarCarregarPartida(listaPartidasNaoFinalizadas);
				apl.setSairPartida(false);
				// Tente iniciar uma partida
				try {
					controlarPartida(apl);
					retornarMenu = true;
				} catch (Exception e) {
					cli.exibirAlerta("Nenhuma partida foi carregada.");
				}
				break;
			case "RETORNAR":
				retornarMenu = true;
				break;
			}
		} while (!retornarMenu);
	}

	/**
	 * Método que exibe a as partidas pausadas
	 * 
	 * @param listaPartidas
	 */
	private void exibirPartidasNaoFinalizadas(
			List<DadosPartida> listaPartidasNaoFinalizadas) {
		// Exibe os campos da lista de jogos
		cli.imprimirLinha("Lista de jogos:\n");
		cli.imprimirLinha("Índice" + "..." + "Data Início" + "..." + "Data Fim"
				+ "..." + "Jogador Branco" + "..." + "Jogador Preto");

		// Exibe a lista de jogos
		for (int indice = 0; indice < listaPartidasNaoFinalizadas.size(); indice++)
			cli.exibirDadosPartidasAndamento(indice,
					listaPartidasNaoFinalizadas.get(indice));
		cli.imprimirLinha("");
	}

	/**
	 * Método responsável por informar dados das partidas
	 */
	private void informaDadosPartida() {
		// Captura de um arquivo as partidas concluídas
		List<DadosPartida> listaPartidasConcluidas = manipuladorArquivo
				.criarListaPartidasConcluidas();

		// Este é o item do menu que o jogador escolheu (escolherá).
		Menu menuDados = new MenuDadosPartida();

		boolean retornarMenu = false;
		do {
			// Inicia o menu deixando a escolha para o usuário do que fazer.
			ItemMenu itemEscolhido = menuDados.insistirPorEntradaValida(cli
					.getIo());
			switch (itemEscolhido.getNome()) {
			// Se escolher visualizar as partidas.
			case "PARTIDAS":
				// Ordena a lista de partidas conforme o nome do vencedor
				Collections.sort(listaPartidasConcluidas);
				exibirPartidasConcluidas(listaPartidasConcluidas);
				break;
			// Se escolher visualizar os jogadores.
			case "JOGADORES":
				DadosPessoa dp = new DadosPessoa();
				List<DadosPessoa> dadosPessoas = dp
						.geraListaDadosPessoa(listaPartidasConcluidas);
				Collections.sort(dadosPessoas);
				exibirJogadores(dadosPessoas);
				break;
			case "RETORNAR":
				retornarMenu = true;
				break;
			}
		} while (!retornarMenu);
	}

	/**
	 * Método responsável por enviar ao cih os dados de partidas concluídas
	 * 
	 * @param listaPartidasConcluidas
	 */
	private void exibirPartidasConcluidas(
			List<DadosPartida> listaPartidasConcluidas) {
		// Exibe os campos da lista de jogos
		cli.imprimirLinha("Lista de jogos:\n");
		cli.imprimirLinha("Índice" + "..." + "Data Início" + "..." + "Data Fim"
				+ "..." + "Vencedor");
		// Exibe a lista de jogos
		for (int indice = 0; indice < listaPartidasConcluidas.size(); indice++)
			cli.exibirDadosPartidasConcluidas(indice,
					listaPartidasConcluidas.get(indice));
		cli.imprimirLinha("");
	}

	/**
	 * Método responsável por enviar ao cih os dados de jogadores
	 * 
	 * @param dadosPessoas
	 */
	private void exibirJogadores(List<DadosPessoa> dadosPessoas) {
		// Exibe os campos da lista de jogos
		cli.imprimirLinha("Lista de jogadores:\n");
		cli.imprimirLinha("Índice" + "..." + "Nome" + "..." + "Vitórias"
				+ "..." + "Derrotas");

		// Exibe a lista de jogos
		for (int indice = 0; indice < dadosPessoas.size(); indice++)
			cli.exibirDadosJogadores(indice, dadosPessoas.get(indice));
		cli.imprimirLinha("");
	}

	/**
	 * Método que busca carregar uma partida informada por um usuário
	 * 
	 * @param maiorIndice
	 * @return
	 */
	private AplJogo buscarCarregarPartida(List<DadosPartida> listaPartidas) {
		int indice = -2;
		do {
			// Tenta capturar uma partida
			do {
				try {
					indice = Integer.parseInt(cli.pedeIndicePartidaCarregar());
					// Lança uma exception se não conseguir
				} catch (Exception e) {
					cli.exibirAlerta("Digite um número referente ao índice.");
				}
			} while (indice == -2);
			// Se for um índice válido
			if (indice >= 0 & indice < listaPartidas.size())
				return manipuladorArquivo
						.carregarPartida(indice, listaPartidas);
			// Se for para retornar
			else if (indice == -1)
				return null;
			// Se for um índice inesperado
			else
				cli.exibirAlerta("O índice digitado não existe.");
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
				cli.exibirAlerta("Digite um número referente ao índice.");
			}
			// Se for um índice válido
			if (indice >= 0 & indice < listaPartidas.size())
				return manipuladorArquivo.apagarPartida(indice, listaPartidas);
			// Se for para retornar
			else if (indice == -1)
				cli.imprimirLinha("Retornando ao menu de partidas");
			// Se for um índice inesperado
			else
				cli.exibirAlerta("O índice digitado não existe.");
		} while (indice < -1 || indice >= listaPartidas.size());
		cli.imprimirLinha("");
		return listaPartidas;
	}
}
