package br.edu.ifes.poo1.cgd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Arquivo<T> {
	private final String ARQUIVOPARTIDAS = "partidas.dat";

	/**
	 * Método que escreve um determinado jogo no arquivo
	 * 
	 * @param jogo
	 * @throws IOException
	 */
	public void escrevaPartidas(List<T> listaPartidas) throws IOException {
		ObjectOutputStream oo = null;
		oo = new ObjectOutputStream(new FileOutputStream(ARQUIVOPARTIDAS));
		oo.writeObject(listaPartidas);
		oo.close();
	}

	/**
	 * Método que lê determinados jogos de certoa arquivo
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public List<T> leiaJogos() throws ClassNotFoundException, IOException {
		ObjectInputStream oi = null;
		List<T> listaPartidas;
		try {
			oi = new ObjectInputStream(new FileInputStream(ARQUIVOPARTIDAS));
			listaPartidas = (ArrayList<T>) oi.readObject();
		} catch (IOException e) {
			criaArquivo(ARQUIVOPARTIDAS);
			listaPartidas = new ArrayList<T>();
		}
		if (oi != null)
			oi.close();
		return listaPartidas;
	}

	/**
	 * Método responsável por criar um arquivo
	 * 
	 * @param nome
	 * @throws IOException
	 */
	private void criaArquivo(String nome) throws IOException {
		File file = new File(nome);
		file.createNewFile();
	}

}
