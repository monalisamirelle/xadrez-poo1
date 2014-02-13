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
import br.edu.ifes.poo1.ciu.cih.MenuNivelMaquina;
import br.edu.ifes.poo1.ciu.cih.MenuPrincipal;
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
	 * @throws CasaOcupadaException
	 * @throws InterruptedException
	 * @throws JogadaInvalidaException
	 */
	public void iniciar() throws CasaOcupadaException, JogadaInvalidaException,
			InterruptedException {
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
	 * Controla uma partida durante seu andamento
	 * 
	 * @param apl
	 * @throws CasaOcupadaException
	 * @throws InterruptedException
	 * @throws JogadaInvalidaException
	 * @throws CloneNotSupportedException
	 */
	// TODO acho que aqui entra "pausar/desistir/etc"
	// FIXME Oferecer ao jogador brancas e pretas INVERTENDO O TABULEIRO
	// prejudica na lógica do peão, en passant e, provavelmente, promoção. Seria
	// mesmo, considerando a falta de tempo, interessante inverter o tabuleiro
	// ou apenas deixar como está para o jogador jogar? (não inverte e ele pode
	// andar com pretas seguindo a lógica já criada)
	private void controlarPartida(AplJogo apl) throws CasaOcupadaException,
			JogadaInvalidaException, InterruptedException {
		// Enquando não acabar o jogo, continuamos executando as jogadas
		// dos jogadores e exibindo o estado do tabuleiro.
		String jogadaCrua;
		String aviso = "";
		while (!apl.getAcabouJogo()) {
			// Atualiza o que é visual para os jogadores. Exibe o aviso se for
			// necessário.
			if (aviso == "")
				cli.atualizar(apl.getTabuleiro(), apl.getBrancas(),
						apl.getPretas());
			else
				cli.atualizar(apl.getTabuleiro(), apl.getBrancas(),
						apl.getPretas(), aviso);

			// Retira a mensagem de aviso.
			aviso = "";

			// Máquina executa uma jogada
			Jogada jogada;
			if (apl.getJogadorTurnoAtual().getTipoJogador() == TipoJogador.PESSOA) {
				// Pede o movimento do jogador.
				jogadaCrua = cli.lerJogada(apl.getJogadorTurnoAtual());
				// Executa o movimento do jogador.
				jogada = Interpretador.interpretarJogada(jogadaCrua);
			} else {
				Maquina maquina = (Maquina) apl.getJogadorTurnoAtual();
				jogada = maquina.escolherJogada(apl.getTabuleiro());
			}

			try {
				apl.executarJogadaTurno(jogada);
				apl.trocarTurno();
				apl.getTabuleiro().resetaPodeEnPassant(
						apl.getJogadorTurnoAtual().getCor());
			} catch (JogadaInvalidaException e) {
				// Prepara um aviso para ser exibido na tela quando ela
				// atualizar.
				aviso = e.getMessage();
			}

			// E continua o ritmo do jogo.
			continue;
		}

		// Encerra a partida.
		encerrarPartida(apl);
	}

	/**
	 * Términa a partida exibindo um cumprimento aos jogadores.
	 * 
	 * @param apljogo
	 *            Apl que em que a partida encerrou.
	 */
	// TODO ADEQUAR
	private void encerrarPartida(AplJogo apljogo) {
		// Após o fim do jogo, pegamos o vencedor, atualizamos o
		// tabuleiro mais uma vez e comprimentamos o ganhador.
		cli.atualizar(apljogo.getTabuleiro(), apljogo.getBrancas(),
				apljogo.getPretas());
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
		int indice = 0;
		do {
			try {
				indice = Integer.parseInt(cli.pedeIndicePartidaCarregar());
			} catch (Exception e) {
				cli.exibirAlerta("Digite um número referente ao índice!");
			}
			if (indice >= 0 & indice < listaPartidas.size())
				return manipuladorArquivo
						.carregarPartida(indice, listaPartidas);
			else
				cli.exibirAlerta("O índice digitado não existe!");
		} while (indice < -1 & indice >= listaPartidas.size());
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
		int indice = 0;
		do {
			try {
				indice = Integer.parseInt(cli.pedeIndicePartidaCarregar());
			} catch (Exception e) {
				cli.exibirAlerta("Digite um número referente ao índice!");
			}
			if (indice >= 0 & indice < listaPartidas.size())
				return manipuladorArquivo.apagarPartida(indice, listaPartidas);
			else
				cli.exibirAlerta("O índice digitado não existe!");
		} while (indice < -1 & indice >= listaPartidas.size());
		return null;
	}

	/**
	 * Método responsável por controlar a exibição dos dados de partidas
	 */
	private void controlarExibicaoPartidas() {

		List<DadosPartida> listaPartidas = manipuladorArquivo
				.lerListaPartidas();

		ItemMenu opcao = new ItemMenu("", "");
		while (!listaPartidas.isEmpty() & opcao.getNome() != "VOLTAR") {
			// TODO deixar menu certinho em formatação
			cli.imprimirLinha("Índice" + "..." + "Data" + "..."
					+ "Jogador Branco" + "..." + "Jogador Preto" + "..."
					+ "Situação da Partida\n");

			for (int indice = 0; indice < listaPartidas.size(); indice++)
				cli.exibirDadosPartidas(indice, listaPartidas.get(indice));

			System.out.println("");
			Menu menuDadosPartidas = new MenuDadosPartidas();
			opcao = menuDadosPartidas
					.insistirPorEntradaValida(new EntradaSaida());
			switch (opcao.getNome()) {
			case "CARREGAR":
				AplJogo apl = buscarCarregarPartida(listaPartidas);
				try {
					this.controlarPartida(apl);
				} catch (Exception e) {
					cli.exibirAlerta("Partida não pode ser carregada");
				}
				break;
			case "APAGAR":
				listaPartidas = buscarApagarPartida(listaPartidas);
				break;
			case "VOLTAR":
				manipuladorArquivo.gravarListaPartidas(listaPartidas);
				break;
			}
		}
	}
}
