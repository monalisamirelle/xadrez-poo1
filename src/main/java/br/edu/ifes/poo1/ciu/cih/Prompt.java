package br.edu.ifes.poo1.ciu.cih;

import br.edu.ifes.poo1.cln.cdp.TipoCorJogador;
import br.edu.ifes.poo1.cln.cdp.Posicao;
import br.edu.ifes.poo1.cln.cdp.TabuleiroXadrez;
import br.edu.ifes.poo1.cln.cdp.pecas.Peca;

/**
 * Interface para o Prompt do Windows.
 */
public class Prompt extends Cli {

	@Override
	protected void imprimirTabuleiro(TabuleiroXadrez tabuleiro) {
		//Imprime o número das colunas.
		imprimirLinha("      1        2        3        4        5        6        7        8     ");
		
		for (int linha = 8; linha >= 1; linha--) {
			// Imprime o sepador de linhas.
			imprimirLinha("  -------------------------------------------------------------------------");
			
			// Imprime os número a margem esquerda do tabuleiro.
			imprimir(linha + " "); 
			
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
				else if (peca.getCorJogador() == TipoCorJogador.BRANCO)
					imprimir(" branco");
				else
					imprimir(" preto ");
			}
			// Imprime separador de casas, os numero a margem direita e pula para a próxima linha.
			imprimirLinha("| " + linha);
		}
		// Imprime o sepador de linhas.
		imprimirLinha("  -------------------------------------------------------------------------");
		
		//Imprime o número das colunas.
		imprimirLinha("      1        2        3        4        5        6        7        8     ");
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
