package br.edu.ifes.poo1.ciu.cih;

import br.edu.ifes.poo1.cln.cdp.CorJogador;
import br.edu.ifes.poo1.cln.cdp.Jogador;
import br.edu.ifes.poo1.cln.cdp.Peca;
import br.edu.ifes.poo1.cln.cdp.Posicao;
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
		for (int l = 8; l >= 1; l--) { // Linha
			for (int c = 1; c <= 8; c++) { // Coluna
				imprimePosicao(tabuleiro, new Posicao(c, l));

				// Dá um espaço para a próxima casa.
				System.out.print(" ");
			}
			System.out.println(""); // Quebra para a próxima linha do tabuleiro.
		}

		// Termina a moldura do tabuleiro.
		System.out.println("---------------");

	}

	private void imprimePosicao(Tabuleiro tabuleiro, Posicao posicao) {
		// Imprime uma casa vazia e só, se não houver peça.
		if (tabuleiro.estaVazio(posicao)) {
			System.out.print(" ");
			return;
		}
		
		Peca peca = tabuleiro.espiarPeca(posicao);

		// Imprime a peça...
		switch (peca.getTipoPeca()) {
		case PEAO:
			System.out.print("P");
			break;
		case TORRE:
			System.out.print("T");
			break;
		case BISPO:
			System.out.print("B");
			break;
		case CAVALO:
			System.out.print("C");
			break;
		case REI:
			System.out.print("R");
			break;
		case RAINHA:
			System.out.print("D");
			break;
		}

		// ... e o seu jogador.
		Jogador jogador = peca.getJogador();
		CorJogador corJogador = jogador.getCor();
		System.out.print("(" + corJogador.toString() + ")");
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
