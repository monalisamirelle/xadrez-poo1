package br.edu.ifes.poo1.ciu.cih;

import br.edu.ifes.poo1.cln.cdp.Jogador;
import br.edu.ifes.poo1.cln.cdp.Peca;
import br.edu.ifes.poo1.cln.cdp.Posicao;
import br.edu.ifes.poo1.cln.cdp.Tabuleiro;

/**
 * Interface para o Prompt do Windows.
 */
public class Terminal extends Cli {

	@Override
	// TODO: Terminar a implementação.
	public void atualizar(Tabuleiro tabuleiro, Jogador brancas, Jogador pretas) {
		// Imprime as peças capturadas pelo jogador das brancas.
		System.out.println("---------------");
		System.out.println("| " + getDescricaoPecasCapturadas(brancas));

		// Imprime as peças capturadas pelo jogador das pretas.
		System.out.println("---------------");
		System.out.println("| " + getDescricaoPecasCapturadas(pretas));

		// Imprime o tabuleiro
		for (int linha = 8; linha >= 1; linha--) {
			// Imprime o cabeçalho da linha.
			System.out
					.println("-----------------------------------------------------------------");
			for (int coluna = 1; coluna <= 8; coluna++) {
				System.out.print("|");

				// Descobre a peça que está no tabuleiro.
				Peca peca = tabuleiro.espiarPeca(new Posicao(coluna, linha));

				// Imprime a peça e quem a controla.
				if (peca == null) // Se estiver vazio, imprime o espaço da casa.
					System.out.print("       ");
				else { // Se estiver ocupada, impreme sua representação.
					System.out.print(PecaToString(peca));
					if (peca.getJogador() == brancas)
						System.out.print(" (br.)");
					else
						System.out.print(" (pr.)");
				}
			}
			System.out.println("|");
		}

		// Termina a moldura do tabuleiro.
		System.out
				.println("-----------------------------------------------------------------");

	}

	@Override
	public String PecaToString(Peca peca) {
		if (peca == null)
			return " ";

		switch (peca.getTipoPeca()) {
		case PEAO:
			return "♟";
		case TORRE:
			return "♜";
		case BISPO:
			return "♝";
		case CAVALO:
			return "♞";
		case REI:
			return "♚";
		case RAINHA:
			return "♛";
		default:
			return "Erro!";
		}
	}

}
