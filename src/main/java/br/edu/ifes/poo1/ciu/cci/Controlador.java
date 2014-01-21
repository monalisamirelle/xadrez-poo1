package br.edu.ifes.poo1.ciu.cci;

import br.edu.ifes.poo1.ciu.cih.Cli;
import br.edu.ifes.poo1.ciu.cih.EntradaSaida;
import br.edu.ifes.poo1.ciu.cih.Interpretador;
import br.edu.ifes.poo1.ciu.cih.ItemMenu;
import br.edu.ifes.poo1.ciu.cih.Menu;
import br.edu.ifes.poo1.ciu.cih.MenuAmbiente;
import br.edu.ifes.poo1.ciu.cih.MenuPrincipal;
import br.edu.ifes.poo1.ciu.cih.Prompt;
import br.edu.ifes.poo1.ciu.cih.Terminal;
import br.edu.ifes.poo1.cln.cdp.CasaOcupadaException;
import br.edu.ifes.poo1.cln.cdp.CorJogador;
import br.edu.ifes.poo1.cln.cdp.Jogada;
import br.edu.ifes.poo1.cln.cdp.JogadaInvalidaException;
import br.edu.ifes.poo1.cln.cdp.Jogador;
import br.edu.ifes.poo1.cln.cdp.MotivoFimDaPartida;
import br.edu.ifes.poo1.cln.cdp.Pessoa;
import br.edu.ifes.poo1.cln.cgt.AplJogo;
import br.edu.ifes.poo1.cln.cgt.AplMultiplayer;
import br.edu.ifes.poo1.cln.cgt.AplSingleplayer;

/**
 * Faz o intermédio entre a camada do modelo de dados e a parte da visão. Para
 * que as entradas do jogador sejam interpretadas e executadas devidamente no
 * modelo. Também atualiza as informações que estão disponíveis na tela do
 * jogador.
 */
public class Controlador {
	private Cli cli;

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
	 * @throws CasaOcupadaException 
	 * @throws CloneNotSupportedException 
	 */
	public void iniciar() throws CasaOcupadaException, CloneNotSupportedException {
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
				controlarSinglePlayer();
				break;

			// Se o jogador tiver escolhido o multiplayer.
			case "MULTIPLAYER":
				controlarMultiplayer();
				break;

			// Se o jogador tiver escolhido ver os dados das partidas.
			case "DADOS":
				System.out
						.println("A visualização dos dados não foi implementado ainda. :S");
				// TODO: Implementar a visualização dos dados.
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

	private void controlarSinglePlayer() throws CasaOcupadaException, CloneNotSupportedException {
		
		// TODO Agora não deverá ter distinção pois a máquina pode ser tanto branca quanto preta
		// Fazer algo do tipo:
		// se a pessoa optar por ser branco...
		// Pessoa pessoa = new Pessoa(cli.lerNomeJogadorBranco(),CorJogador.BRANCO);
		// senão
		// Pessoa pessoa = new Pessoa(cli.lerNomeJogadorPreto(),CorJogador.PRETO);
		
		// NÃO DELETE ISSO! (ESSA EXPLICAÇÃO PODERÁ SER USADA NO RELATÓRIO)
		// Será oferecido a pessoa 6 opções de IA (em grau de inteligência):
		// 1 IAElaborada 10;45;false      -> Pensa muito pra ser burra
		// 2 IAElaborada 10;15;false      -> Pensa pra ser burra
		// 3 IARandomica                  -> Ao acaso
		// 4 IAElaborada 1;45;true		-> Joga de maneira agressiva (só analisando o próximo movimento)        
		// 5 IAElaborada 10;15;true		-> Realiza movimentos inteligentes
		// 6 IAElaborada 10;45;true		-> Realiza os melhores movimentos possíveis
		
		// Os valores em questão dizem respeito a o que deve ir na classe construtora da "maquina"

		// Então... se por exemplo a pessoa escolher 6, você deverá criar:
		// Maquina maquina = new IAElaborada("nomeMaquina",CorJogador.getCorOposta(pessoa.getCor()),10,45,true);
		// Se a pessoa escolher 2:
		// Maquina maquina = new IAElaborada("nomeMaquina",CorJogador.getCorOposta(pessoa.getCor()),10,15,false);
		// Se escolher 3:
		// Maquina maquina = new IARandomica("nomeMaquina",CorJogador.getCorOposta(pessoa.getCor());
		// ;)
		
		// Contrói a aplicação do jogo
		AplJogo apl = new AplSingleplayer(pessoa,maquina);

		// Enquando não acabar o jogo, continuamos executando as jogadas
		// do jogador e exibindo o estado do tabuleiro.
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

			// Pede o movimento do humano.
			jogadaCrua = cli.lerJogada(apl.getJogadorTurnoAtual());

			// Executa o movimento do humano. E a apl executa o movimento da IA
			// logo em seguida.
			Jogada jogadaInterpretada;
			try {
				jogadaInterpretada = Interpretador
						.interpretarJogada(jogadaCrua);
				apl.executarjogada(jogadaInterpretada);
			} catch (JogadaInvalidaException e) {
				// Prepara um aviso para ser exibido na tela quando ela
				// atualizar.
				aviso = e.getMessage();

				// E continua o ritmo do jogo.
				continue;
			}
		}

		// Encerra a partida.
		encerrarPartida(apl);
	}

	/**
	 * Controla tudo o que é necessário para uma partida multiplayer. Ao final
	 * do método, a partida terá encerrado.
	 * @throws CasaOcupadaException 
	 * @throws CloneNotSupportedException 
	 */
	private void controlarMultiplayer() throws CasaOcupadaException, CloneNotSupportedException {
		// Pega o nome dos jogadores.
		String nomeBrancas = cli.lerNomeJogadorBranco();
		String nomePretas = cli.lerNomeJogadorPreto();

		// Constrói a aplicação do jogo.
		AplMultiplayer aplmulti = new AplMultiplayer(nomeBrancas, nomePretas);

		// Enquando não acabar o jogo, continuamos executando as jogadas
		// dos jogadores e exibindo o estado do tabuleiro.
		String jogadaCrua;
		String aviso = "";
		while (!aplmulti.getAcabouJogo()) {
			// Atualiza o que é visual para os jogadores. Exibe o aviso se for
			// necessário.
			if (aviso == "")
				cli.atualizar(aplmulti.getTabuleiro(), aplmulti.getBrancas(),
						aplmulti.getPretas());
			else
				cli.atualizar(aplmulti.getTabuleiro(), aplmulti.getBrancas(),
						aplmulti.getPretas(), aviso);

			// Retira a mensagem de aviso.
			aviso = "";

			// Pede o movimento do jogador.
			jogadaCrua = cli.lerJogada(aplmulti.getJogadorTurnoAtual());

			// Executa o movimento do jogador.
			Jogada jogadaInterpretada;
			try {
				jogadaInterpretada = Interpretador
						.interpretarJogada(jogadaCrua);
				aplmulti.executarjogada(jogadaInterpretada);
			} catch (JogadaInvalidaException e) {
				// Prepara um aviso para ser exibido na tela quando ela
				// atualizar.
				aviso = e.getMessage();

				// E continua o ritmo do jogo.
				continue;
			}
		}

		// Encerra a partida.
		encerrarPartida(aplmulti);
	}

	/**
	 * Términa a partida exibindo um cumprimento aos jogadores.
	 * 
	 * @param apljogo
	 *            Apl que em que a partida encerrou.
	 */
	private void encerrarPartida(AplJogo apljogo) {
		// Após o fim do jogo, pegamos o vencedor, atualizamos o
		// tabuleiro mais uma vez e comprimentamos o ganhador.
		cli.atualizar(apljogo.getTabuleiro(), apljogo.getBrancas(),
				apljogo.getPretas());
		MotivoFimDaPartida motivoFim = apljogo.getMotivoDeFinalizacao();

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
		}
	}
}
