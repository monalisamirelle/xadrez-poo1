package br.edu.ifes.poo1.ciu.cih;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.edu.ifes.poo1.cln.cdp.Jogador;
import br.edu.ifes.poo1.cln.cdp.Tabuleiro;

/**
 * Interface de Linha de Comando (CLI da sigla em inglês). É responsável pela
 * comunicação com os jogadores.
 * 
 * @author lucas
 * 
 */
public abstract class Cli {
	protected Scanner s = new Scanner(System.in);

	/**
	 * Renova os elementos da tela, tais como: o tabuleiro, o indicador de vez e
	 * qualquer outra coisa que seja de pura apresentação ao usuário dos
	 * elementos do jogo.
	 * 
	 * @param tabuleiro
	 *            Tabuleiro que está sendo usado na partida.
	 * @param brancas
	 *            Jogador que controla as peças brancas.
	 * @param pretas
	 *            Jogador que controla as peças pretas.
	 */
	public abstract void atualizar(Tabuleiro tabuleiro, Jogador brancas,
			Jogador pretas);

	/**
	 * Lê uma jogada da tela. Este método retorna uma String com a entrada pura
	 * do jogador, sem nenhuma verificação.
	 * 
	 * @return Retorna uma String com a jogada do usuário.
	 */
	public String lerJogada() {
		System.out.println("Entre com a jogada:");
		System.out.print(" >> ");
		return s.next();
	}

	/**
	 * Lê o nome do jogador que controla as peças brancas.
	 * 
	 * @return Nome do jogador.
	 */
	public String lerNomeJogadorBranco() {
		System.out.print("Entre com o nome do jogador das peças brancas:");
		System.out.print(" >> ");
		return s.next();
	}

	/**
	 * Lê o nome do jogador que controla as peças pretas.
	 * 
	 * @return Nome do jogador.
	 */
	public String lerNomeJogadorPreto() {
		System.out.print("Entre com o nome do jogador das peças pretas:");
		System.out.print(" >> ");
		return s.next();
	}

	public ItemMenuPrincipal exibirMenuPrincipal() throws EntradaMenuInvalida {
		System.out.println("Menu Principal:");
		for (ItemMenuPrincipal item : ItemMenuPrincipal.values()) {
			System.out.println("\t" + item.getOrdem() + ". "
					+ item.getDescricao());
		}

		int escolha;
		try {
			escolha = s.nextInt();
			for (ItemMenuPrincipal item : ItemMenuPrincipal.values()) {
				if (item.getOrdem() == escolha)
					return item;
			}

			// Se até este momento nenhum item foi retornado, é poque o jogador
			// escolheu um item que não está disponível no menu. Então lançamos
			// a excessão.
			throw new EntradaMenuInvalida();
		} catch (InputMismatchException e) {
			// Se o jogador entro com alguma sequência de caracteres que não
			// possa ser identificada como um inteiro, então dizemos que a
			// entrada escolhida foi inválida.
			throw new EntradaMenuInvalida();
		}

		// TODO: Filtrar a escolha do usuário.

	}
}
