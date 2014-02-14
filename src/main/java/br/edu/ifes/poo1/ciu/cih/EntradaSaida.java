package br.edu.ifes.poo1.ciu.cih;

import java.util.Scanner;

/**
 * Classe usada para a comunicação básica com o usuário. Possui métodos básicos
 * para a entrada e saída de dados via a linha de comando.
 */
public class EntradaSaida {
	/** Scanner usado para capturar as entradas do usuário. */
	private Scanner s = new Scanner(System.in);

	/**
	 * Exibe a mensagem indicada por parâmetro ao usuário e em seguida solicita
	 * uma resposta do usuário, que é retornada no formato de uma String.
	 * 
	 * @param solicitacao
	 *            Mensagem expressando um pedido ao usuário.
	 * @return Reposta do usuário.
	 */
	public String pedir(String solicitacao) {
		System.out.println(solicitacao);
		System.out.print(" >> ");
		String resposta = s.nextLine();
		System.out.println();

		return resposta;
	}

	/**
	 * Exibe o texto recebido por parâmetro. Será escrito exatamente como
	 * recebido no parâmetro, porém com a adição de uma quebra de linha no
	 * final.
	 * 
	 * @param texto
	 *            Texto a ser exibido.
	 */
	public void imprimirLinha(String texto) {
		System.out.println(texto);
	}

	/**
	 * Exibe o texto recebido por parâmetro. Será escrito exatamente como
	 * recebido no parâmetro. *
	 * 
	 * @param texto
	 *            Texto a ser exibido.
	 */
	public void imprimir(String texto) {
		System.out.print(texto);
	}

}
