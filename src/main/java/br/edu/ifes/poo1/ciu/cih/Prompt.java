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
			// Imprime o sepador de linhas.
			imprimirLinha("-------------------------------------------------------------------------");
			for (int coluna = 1; coluna <= 8; coluna++) {
				// Imprime separador de casas.
				imprimir("|");

				// Descobre a peça que está no tabuleiro.
				Peca peca = tabuleiro.espiarPeca(new Posicao(coluna, linha));

				imprimir(PecaToString(peca));
				if (peca == null)
					// Quantidade de espaços equivalente ao texto da cor do
					// jogador nos casos logo abaixo.
					imprimir("       ");
				else if (peca.getJogador().getCor() == CorJogador.BRANCO)
					imprimir(" branco");
				else
					imprimir(" preto ");
			}
			// Imprime separador de casas e pula para a próxima linha.
			imprimirLinha("|");
		}
		// Imprime o sepador de linhas.
		imprimirLinha("-------------------------------------------------------------------------");
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
