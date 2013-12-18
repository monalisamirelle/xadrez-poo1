package br.edu.ifes.poo1.ciu.cih;

import br.edu.ifes.poo1.cln.cdp.CorJogador;
import br.edu.ifes.poo1.cln.cdp.Peca;
import br.edu.ifes.poo1.cln.cdp.Posicao;
import br.edu.ifes.poo1.cln.cdp.Tabuleiro;

/**
 * Interface para o Prompt do Windows.
 */
public class Prompt extends Cli {

	@Override
	protected void imprimirTabuleiro(Tabuleiro tabuleiro) {
		for (int linha = 8; linha >= 1; linha--) {
			// Imprime o cabeçalho da linha.
			System.out
					.println("-------------------------------------------------------------------------");
			for (int coluna = 1; coluna <= 8; coluna++) {
				System.out.print("|");

				// Descobre a peça que está no tabuleiro.
				Peca peca = tabuleiro.espiarPeca(new Posicao(coluna, linha));

				// Imprime a peça e quem a controla.
				if (peca == null)
					System.out.print("        ");
				else {
					System.out.print(PecaToString(peca));
					if (peca.getJogador().getCor() == CorJogador.BRANCO)
						System.out.print(" branco");
					else
						System.out.print(" preto ");
				}
			}
			System.out.println("|");
		}
		System.out
				.println("-------------------------------------------------------------------------");
	}

	@Override
	public String PecaToString(Peca peca) {
		if (peca == null)
			return " ";

		switch (peca.getTipoPeca()) {
		case PEAO:
			return "P";
		case TORRE:
			return "T";
		case BISPO:
			return "B";
		case CAVALO:
			return "C";
		case REI:
			return "R";
		case RAINHA:
			return "D";
		default:
			return "Erro!";
		}
	}

}
