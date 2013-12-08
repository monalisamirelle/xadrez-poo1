package br.edu.ifes.poo1.ciu.cih;

import br.edu.ifes.poo1.cln.cdp.Jogador;
import br.edu.ifes.poo1.cln.cdp.Tabuleiro;

/**
 * Interface para Terminal do Unix.
 * 
 * @author lucas
 * 
 */
public class Terminal extends Cli {

	// TODO: Implementar as cores no terminal.

	@Override
	public void atualizar(Tabuleiro tabuleiro, Jogador brancas, Jogador pretas) {
		// TODO Auto-generated method stub

	}

	@Override
	public String lerJogada() {
		System.out.println("Entre com a jogada:");
		System.out.print(" >> ");
		return s.next();
	}

	@Override
	public String lerNomeJogadorBranco() {
		System.out.print("Entre com o nome do jogador das peças brancas:  ");
		return s.next();
	}

	@Override
	public String lerNomeJogadorPreto() {
		System.out.print("Entre com o nome do jogador das peças pretas:  ");
		return s.next();
	}

	@Override
	public void exibirMenuPrincipal() throws EntradaMenuInvalida {
		System.out.println("Menu Principal:");
		for (ItemMenuPrincipal item : ItemMenuPrincipal.values()) {
			System.out.println("\t" + item.getOrdem() + ". "
					+ item.getDescricao());
		}

		String escolha = s.next();

		// TODO: Filtrar a escolha do usuário.

	}

}
