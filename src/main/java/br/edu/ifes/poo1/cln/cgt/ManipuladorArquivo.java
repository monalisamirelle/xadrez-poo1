package br.edu.ifes.poo1.cln.cgt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifes.poo1.cgd.Arquivo;
import br.edu.ifes.poo1.cln.cdp.DadosPartida;

public class ManipuladorArquivo {

	Arquivo arquivo = new Arquivo();

	/**
	 * 
	 * Método que manda ao arquivo gravar o jogo atual
	 * 
	 * @param dadosJogo
	 */
	public void gravarPartida(AplJogo dadosJogo) {
		List<DadosPartida> listaPartidas;
		try {
			listaPartidas = arquivo.leiaJogos();
		} catch (ClassNotFoundException | IOException e) {
			listaPartidas = new ArrayList<DadosPartida>();
		}
		DadosPartida dadosPartidaAtual = new DadosPartida(dadosJogo);
		listaPartidas.add(dadosPartidaAtual);
		gravarListaPartidas(listaPartidas);
	}

	/**
	 * 
	 * Método que manda ao arquivo gravar a lista de partidas
	 * 
	 * @param listaPartidas
	 */
	public void gravarListaPartidas(List<DadosPartida> listaPartidas) {
		arquivo.escrevaPartidas(listaPartidas);
	}

	/**
	 * Método que lê todos dados dos jogos
	 * 
	 * @return
	 */
	public List<DadosPartida> lerListaPartidas() {
		List<DadosPartida> listaPartidas = new ArrayList<DadosPartida>();
		try {
			listaPartidas = arquivo.leiaJogos();
		} catch (ClassNotFoundException | IOException e) {
			listaPartidas = new ArrayList<DadosPartida>();
		}
		return listaPartidas;
	}

	/**
	 * Método que retorna um jogo com base no índice informado pelo usuário
	 * (jogo já se encontra em memória)
	 * 
	 * @param indice
	 * @return
	 */
	public AplJogo carregarPartida(int indice, List<DadosPartida> dadosPartidas) {
		return dadosPartidas.get(indice).getJogo();
	}

	/**
	 * Método que busca apagar uma partida da memória (só será efetivamente
	 * apagado do arquivo ao persistir os dados)
	 * 
	 * @return
	 */
	public List<DadosPartida> apagarPartida(int indiceApagar,
			List<DadosPartida> listaPartidas) {
		listaPartidas.remove(indiceApagar);
		return listaPartidas;
	}

}
