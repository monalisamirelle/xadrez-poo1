package br.edu.ifes.poo1.cgd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifes.poo1.cln.cdp.DadosPartida;

public class Arquivo {
	private final String ARQUIVOPARTIDAS = "partidas.dat";

	/**
	 * Método que escreve um determinado jogo no arquivo
	 * 
	 * @param jogo
	 */
	public void escrevaJogo(List<DadosPartida> listaDados) {
		ObjectOutputStream oo = null;
		try {
			oo = new ObjectOutputStream(new FileOutputStream(ARQUIVOPARTIDAS));
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado\n");
		} catch (IOException e) {
			System.out.println("Entrada e saida com problema\n");
		}
		try {
			oo.writeObject(listaDados);
		} catch (IOException e) {
			System.out.println("Incapaz de escrever no arquivo\n");
		}
		try {
			oo.close();
		} catch (IOException e) {
			System.out.println("Erro, não foi possível fechar o arquivo\n");
		}
	}

	/**
	 * Método que lê determinados jogos de certoa arquivo
	 * 
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public List<DadosPartida> leiaJogos() throws ClassNotFoundException,
			IOException {
		ObjectInputStream oi = null;
		List<DadosPartida> listaDados;
		try {
			oi = new ObjectInputStream(new FileInputStream(ARQUIVOPARTIDAS));
			listaDados = (ArrayList<DadosPartida>) oi.readObject();
		} catch (IOException e) {
			System.out.println("Não há partidas salvas\n");
			criaArquivo(ARQUIVOPARTIDAS);
			listaDados = new ArrayList<DadosPartida>();
		}
		if (oi != null)
			oi.close();
		return listaDados;
	}

	private void criaArquivo(String nome) {
		File file = new File(nome);
		try {
			file.createNewFile();
		} catch (IOException e) {
			System.out
					.println("Erro, arquivo não existe e não pôde ser criado");
		}
	}

}
