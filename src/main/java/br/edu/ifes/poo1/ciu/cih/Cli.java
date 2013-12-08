package br.edu.ifes.poo1.ciu.cih;

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

	public abstract String lerJogada();

	public abstract String lerNomeJogadorBranco();

	public abstract String lerNomeJogadorPreto();

	public abstract void exibirMenuPrincipal() throws EntradaMenuInvalida;
}
