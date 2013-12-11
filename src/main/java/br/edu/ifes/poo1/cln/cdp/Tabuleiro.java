package br.edu.ifes.poo1.cln.cdp;

/**
 * Um tabuleiro é composto por 64 casas, estas podem estar ocupadas por uma peça
 * ou vazias.
 * 
 * @author lucas
 * 
 */
public class Tabuleiro {

	private Jogador jogadorBrancas;
	private Jogador jogadorPretas;

	/**
	 * Casas do tabuleiro. Com colunas e linhas variando de 0 a 7. Mas deve
	 * haver um controle interno para que quando as outras classes forem se
	 * comunicar com o tabuleiro, enxerguem as linhas e colunas como de 1 a 8. O
	 * primeiro índice da matriz é a coluna e em seguida a linha, igual no
	 * xadrez.
	 */
	private Casa[][] casas = new Casa[8][8];

	/**
	 * Cria um tabuleiro com suas 64 casas vazias e com a referência de quem são
	 * os jogadores.
	 */
	public Tabuleiro(Jogador brancas, Jogador pretas) {
		this.jogadorBrancas = brancas;
		this.jogadorPretas = pretas;
		for (int i = 0; i < 8; i++) { // Coluna
			for (int j = 0; j < 8; j++) { // Linha

				// Como as casas conhecem suas linhas e colunas de 1 a 8
				// (diferente do tabuleiro, que conhece de 0 a 7), temos que
				// fazer um incremento nos índice, antes de instanciar a casa.
				casas[i][j] = new Casa(++i, ++j);
			}
		}
	}

	/**
	 * Anda com a peça indicada para a casa desejada.
	 * 
	 * @param origem
	 *            Posição a onde se encontra a peça a qual se deseja mover.
	 * @param destino
	 *            Posição para a qual deseja-se andar com a peça.
	 * @throws JogadaInvalida
	 *             Lançada se for impossível que a peça se mova para o local
	 *             desejado. Ou se o local de destino não estiver vazio.
	 */
	public void andarPeca(Posicao origem, Posicao destino)
			throws JogadaInvalida {
		// TODO: Implementar.
	}

	/**
	 * Move a peça indicada para atacar a casa desejada.
	 * 
	 * @param origem
	 *            Posição onde está a peça a qual será usada para atacar.
	 * @param destino
	 *            Posição a onde está a peça que será atacada.
	 * @throws JogadaInvalida
	 *             Lançada se for impossível atacar o local com a peça
	 *             selecionada, ou se não há peça na casa para ser atacada.
	 */
	public void atacarPeca(Posicao origem, Posicao destino)
			throws JogadaInvalida {
		Casa casaOrigem = casas[origem.getColuna()][origem.getLinha()];
		Casa casaDestino = casas[destino.getColuna()][destino.getLinha()];
		Peca pecaAtacante = casaOrigem.getPeca();
		Peca pecaAtacada = casaDestino.getPeca();

		if (pecaAtacante.podeAtacar(casaDestino)) {
			// TODO: Implementar.
		}
	}

	/**
	 * Verifica se a peça atravessou o tabuleiro
	 * 
	 * @param casaDesejada
	 * @return
	 */
	public static boolean atravessouTabuleiro(Casa casaDesejada) {
		if (casaDesejada.getPosicao().getLinha() > 0
				& casaDesejada.getPosicao().getLinha() <= 8
				& casaDesejada.getPosicao().getColuna() > 0
				& casaDesejada.getPosicao().getColuna() <= 8)
			return true;
		else
			return false;
	}

	/**
	 * Verifica se a peça pode se movimentar até o local desejado
	 * 
	 * @param casaAtual
	 * @param casaDesejada
	 * @return
	 */
	public boolean podeRealizarMovimentacao(Casa casaAtual, Casa casaDesejada) {
		// Verifica se todas as posições intermediárias estão vazias
		int linha = casaAtual.getPosicao().getLinha();
		int coluna = casaAtual.getPosicao().getColuna();
		int movimentoHorizontal = (int) Math.signum(casaDesejada.getPosicao()
				.getLinha() - casaAtual.getPosicao().getLinha());
		int movimentoVertical = (int) Math.signum(casaDesejada.getPosicao()
				.getColuna() - casaAtual.getPosicao().getColuna());
		do {
			linha = linha + movimentoHorizontal;
			coluna = coluna + movimentoVertical;
			if (casas[linha][coluna].getPeca() != null)
				return false;
		} while (linha != casaDesejada.getPosicao().getLinha()
				|| coluna != casaDesejada.getPosicao().getColuna());
		return true;
	}

	/**
	 * Verifica se a peça pode realmente ocupar a posição que deseja
	 * 
	 * @param casaAtual
	 * @param casaDesejada
	 * @return
	 */
	public boolean podeAlcancarCasa(Casa casaAtual, Casa casaDesejada) {
		if (casas[casaDesejada.getPosicao().getLinha()][casaDesejada
				.getPosicao().getColuna()].getPeca() == null
				|| casas[casaDesejada.getPosicao().getLinha()][casaDesejada
						.getPosicao().getColuna()].getPeca().getJogador() == casaAtual
						.getPeca().getJogador())
			return true;
		return false;
	}

	public boolean relizaMovimento(Casa casaAtual, Casa casaDesejada) {
		if (casaAtual.getPosicao().getLinha() == casaDesejada.getPosicao()
				.getLinha()
				&& casaAtual.getPosicao().getColuna() == casaDesejada
						.getPosicao().getColuna())
			return false;
		return true;
	}

}