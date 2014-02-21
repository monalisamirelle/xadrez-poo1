package br.edu.ifes.poo1.cln.cgt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifes.poo1.cgd.ManipuladorArquivo;
import br.edu.ifes.poo1.cln.cdp.DadosPartida;

public class ManipuladorPartidas {

	private final String NOME_ARQUIVO_PARTIDAS = "partidas.dat";

	ManipuladorArquivo<ArrayList<DadosPartida>> arquivo = new ManipuladorArquivo<ArrayList<DadosPartida>>(
			NOME_ARQUIVO_PARTIDAS);

	/**
	 * Grava uma partida, juntamente com as outras partidas que já foram
	 * gravadas.
	 * 
	 * @param jogo
	 *            Objeto com os dados do jogo a ser gravado.
	 * @throws IOException
	 *             Lançada caso haja uma falha ao gravar os dados.
	 */
	public void gravarPartida(AplJogo jogo) throws IOException {
		// Lê os dados das partidas que já estavam gravados.
		ArrayList<DadosPartida> partidas = LerListaPartidas();

		// Cria os novos dados para o jogo que será gravado.
		DadosPartida dadosPartidaAtual = new DadosPartida(jogo);

		// Junta a partida criada com as outras.
		partidas.add(dadosPartidaAtual);

		// Grava todas as partidas em um arquivo.
		arquivo.escrever(partidas);
	}

	/**
	 * Retorna um jogo com base no índice informado (jogo já se encontra em
	 * memória)
	 * 
	 * @param indice
	 *            Indice da partida que será retornada
	 * @param listaPartidas
	 *            Lista com as partidas.
	 * @return
	 */
	public AplJogo carregarPartida(int indice, List<DadosPartida> dadosPartidas) {
		return dadosPartidas.get(indice).getJogo();
	}

	/**
	 * Apaga uma partida da memória (só será efetivamente apagado do arquivo ao
	 * persistir os dados).
	 * 
	 * @param indice
	 *            Indice da partida que deve ser apagada.
	 * @param listaPartidas
	 *            Lista com as partidas da qual uma delas será apagada.
	 */
	public void apagarPartida(int indice, List<DadosPartida> listaPartidas) {
		listaPartidas.remove(indice);
	}

	/**
	 * Método que lê todos dados dos jogos que foram persistidos.
	 * 
	 * @return Dados das partidas.
	 */
	public ArrayList<DadosPartida> LerListaPartidas() {
		ArrayList<DadosPartida> listaPartidas = null;
		try {
			listaPartidas = arquivo.carregar();
		} catch (ClassNotFoundException | IOException e) {
			listaPartidas = new ArrayList<DadosPartida>();
		}
		return listaPartidas;
	}

	/**
	 * Retorna apenas as partidas que já foram concluídas.
	 * 
	 * @return Partidas concluídas.
	 */
	public List<DadosPartida> criarListaPartidasNaoFinalizadas() {
		// Lê os dados de todas as partidas que estão persistidas.
		List<DadosPartida> listaPartidas = LerListaPartidas();

		// Filtra as partidas, pegando apenas aquelas que ainda não terminaram.
		List<DadosPartida> listaPartidasPausadas = new ArrayList<DadosPartida>();
		for (DadosPartida partida : listaPartidas) {
			switch (partida.getJogo().getSituacaoPartida()) {
			case PAUSA:
			case ANDAMENTO:
				listaPartidasPausadas.add(partida);
				break;

			default:
				break;
			}
		}

		return listaPartidasPausadas;
	}

	/**
	 * Retorna apenas as partidas que já foram concluídas.
	 * 
	 * @return Partidas concluídas.
	 */
	public List<DadosPartida> criarListaPartidasConcluidas() {
		// Lê os dados de todas as partidas que estão persistidas.
		List<DadosPartida> listaPartidas = LerListaPartidas();

		// Filtra as partidas, pegando apenas aquelas que já terminaram.
		List<DadosPartida> listaPartidasConcluidas = new ArrayList<DadosPartida>();
		for (DadosPartida partida : listaPartidas) {
			switch (partida.getJogo().getSituacaoPartida()) {
			case VITORIA:
			case EMPATE:
			case DESISTENCIA:
				listaPartidasConcluidas.add(partida);
				break;

			default:
				break;
			}
		}

		return listaPartidasConcluidas;
	}

	/**
	 * Apaga todas os dados de todas as partidas que já estão persistidas. Sejam
	 * elas finalizadas ou ainda em andamento.
	 */
	public void apagarTodasPartidas() {
		arquivo.apagarArquivo();
	}
}
