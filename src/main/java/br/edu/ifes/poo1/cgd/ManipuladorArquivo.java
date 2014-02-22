package br.edu.ifes.poo1.cgd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Controla a persistência dos objeto em arquivos.
 * 
 * @param <T>
 *            Tipo dos objetos a serem manipulados.
 */
public class ManipuladorArquivo<T extends Serializable> {

	/** Caminho no qual está o arquivo a ser manipulado. */
	private String caminho;

	/**
	 * Inicia um manipulador de arquivos, para persistir os objetos do tipo
	 * indicado.
	 * 
	 * @param caminho
	 *            Caminho aonde está o arquivo a ser manipulado.
	 */
	public ManipuladorArquivo(String caminho) {
		this.caminho = caminho;
	}

	/**
	 * Escreve o objeto indicado no arquivo. Se necessário, o arquivo será
	 * sobrescrito.
	 * 
	 * @param objeto
	 *            Objeto a ser escrito no arquivo.
	 * @throws IOException
	 */
	public void escrever(T objeto) throws IOException {
		// Inicia o fluxo de escrita.
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
				caminho));

		// Escreve o objeto para o arquivo.
		out.writeObject(objeto);

		// Fecha a conexão com o arquivo.
		out.close();
	}

	/**
	 * Le o arquivo e recupera o objeto que nele foi escrito.
	 * 
	 * @return Objeto recuperado do arquivo.
	 * @throws FileNotFoundException
	 *             Lançada caso não haja arquivo no local indicado.
	 * @throws IOException
	 *             E caso acha erros na leitura e manipulação do arquivo
	 * @throws ClassNotFoundException
	 *             É lançada se o os dados encontrados no arquivo não puderem
	 *             ser mapeados para o tipo inficado.
	 */
	@SuppressWarnings("unchecked")
	public T carregar() throws FileNotFoundException, IOException,
			ClassNotFoundException {
		// Inicia o fluxo de leitura.
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(
				caminho));

		// Lê o objeto.
		T objeto = (T) in.readObject();

		// Fecha a conexão com o arquivo.
		in.close();

		// Retorna o objeto lido.
		return objeto;
	}

	/**
	 * Cria um arquivo vazio.
	 * 
	 * @throws IOException
	 *             Lançada caso haja alguma falha ao criar o arquivo.
	 */
	public void criarArquivo() throws IOException {
		File file = new File(caminho);
		file.createNewFile();
	}
	
	/**
	 * Apaga o arquivo que está sendo manipulado.
	 */
	public void apagarArquivo() {
		File file = new File(caminho);
		file.delete();
	}
}
