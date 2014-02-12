package br.edu.ifes.poo1.cln.cgt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifes.poo1.cgd.Arquivo;
import br.edu.ifes.poo1.cln.cdp.DadosPartida;

public class ManipuladorArquivo {

	Arquivo arquivo = new Arquivo();

	/**
	 * Método que manda ao arquivo gravar o jogo atual
	 */
	public void gravarDadosJogo(AplJogo dadosJogo) {
		List<DadosPartida> dadosPartida;
		try {
			dadosPartida = arquivo.leiaJogos();
		} catch (ClassNotFoundException | IOException e) {
			dadosPartida = new ArrayList<DadosPartida>();
		}
		DadosPartida dadosPartidaAtual = new DadosPartida(dadosJogo);
		dadosPartida.add(dadosPartidaAtual);
		arquivo.escrevaJogo(dadosPartida);
	}

	/**
	 * Método que lê todos dados dos jogos
	 * 
	 * @return
	 */
	public List<DadosPartida> lerDadosJogo() {
		List<DadosPartida> dadosPartida;
		try {
			dadosPartida = arquivo.leiaJogos();
		} catch (ClassNotFoundException | IOException e) {
			dadosPartida = new ArrayList<DadosPartida>();
		}
		return dadosPartida;
	}

	/**
	 * Método que retorna um jogo com base no índice informado pelo usuário
	 * 
	 * @param indice
	 * @return
	 */
	public AplJogo carregarPartida(int indice) {
		List<DadosPartida> dadosPartida = lerDadosJogo();
		return dadosPartida.get(indice).getJogo();
	}

}
