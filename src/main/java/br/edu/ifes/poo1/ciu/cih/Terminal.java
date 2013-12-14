package br.edu.ifes.poo1.ciu.cih;

import br.edu.ifes.poo1.cln.cdp.Jogador;
import br.edu.ifes.poo1.cln.cdp.Peca;
import br.edu.ifes.poo1.cln.cdp.Tabuleiro;

/**
 * Interface para Terminal do Unix.
 */
// TODO: Implementar as cores no terminal.
public class Terminal extends Cli {

	@Override
	// TODO: Terminar a implementação.
	public void atualizar(Tabuleiro tabuleiro, Jogador brancas, Jogador pretas) {
		// Imprime as peças capturadas pelo jogador das brancas.
		System.out.println("---------------");
		System.out.println("| " + brancas.getNome() + ": "
				+ descricaoPecasCapturadas(brancas) + "= "
				+ pontuacaoTotal(brancas));

		// Imprime as peças capturadas pelo jogador das pretas.
		System.out.println("---------------");
		System.out.println("| " + pretas.getNome() + ": "
				+ descricaoPecasCapturadas(pretas) + "= "
				+ pontuacaoTotal(pretas));

		// Imprime o tabuleiro
		System.out.println("---------------");
		for (int l = 1; l <= 8; l++) { // Linha
			for (int c = 1; c <= 8; c++) { // Coluna
				Peca peca = tabuleiro.espiarPeca(c, l);
				Jogador jogador = peca.getJogador();

				// FIXME: Deve ser impresso qual é a peça, e não o seu valor.
				System.out.print(peca.getValor() + "(" + jogador.getNome()
						+ ") ");
			}
			System.out.println(""); // Quebra para a próxima linha do tabuleiro.
		}

		// Termina a moldura do tabuleiro.
		System.out.println("---------------");

	}

	private String descricaoPecasCapturadas(Jogador jogador) {
		String descricao = "";

		// Junta cada peça que foi capturada em uma string.
		for (Peca peca : jogador.getPecasCapturadas()) {
			switch (peca.getValor()) {
			case 1: // Peão
				descricao += "P ";
				break;
			// TODO: Acrescentar as outras peças.

			default:
				break;
			}
		}
		return descricao;
	}

	private int pontuacaoTotal(Jogador jogador) {
		int pontuacao = 0;
		for (Peca peca : jogador.getPecasCapturadas()) {
			pontuacao += peca.getValor();
		}
		return pontuacao;
	}

}
