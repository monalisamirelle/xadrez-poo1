package br.edu.ifes.poo1.cln.cdp;

/**
 * Um tabuleiro é composto por 64 casas, estas podem estar ocupadas por uma peça
 * ou vazias.
 */
public class Tabuleiro {

	/**
	 * Casas do tabuleiro. Com colunas e linhas variando de 0 a 7. Mas deve
	 * haver um controle interno para que quando as outras classes forem se
	 * comunicar com o tabuleiro, enxerguem as linhas e colunas como de 1 a 8. O
	 * primeiro índice da matriz é a coluna e em seguida a linha, igual no
	 * xadrez.
	 */
	private Peca[][] pecas;

	/**
	 * Inicia um tabuleiro vazio, sem peça alguma.
	 */
	public Tabuleiro() {
		pecas = new Peca[8][8];
	}

	/**
	 * Espia a peça que está na posição indicada. Se não houver peça na posição
	 * indicada, será retornado 'null'.
	 * 
	 * @param posicao
	 *            Posição do tabuleiro aonde deseja-se espiar.
	 * @return A peça que ocupa aquele posição. Ou 'null', se não houver peça
	 *         ali.
	 */
	public Peca espiarPeca(Posicao posicao) {
		return pecas[posicao.getColuna() - 1][posicao.getLinha() - 1];
	}

	/**
	 * Retira a peça da posição indicada e tal peça é retornada. Se não houver
	 * qualquer peça no local indicado, nada será feito e será retornado 'null'.
	 * 
	 * @param posicao
	 *            Posição da qual deseja-se retirar a peça.
	 * @return A peça que "outrora" ocupava a posição indicada. Ou 'null' caso
	 *         não haja peça na posição indicada.
	 */
	public Peca retirarPeca(Posicao posicao) {
		Peca peca = pecas[posicao.getColuna() - 1][posicao.getLinha() - 1];
		pecas[posicao.getColuna() - 1][posicao.getLinha() - 1] = null;
		return peca;
	}

	/**
	 * Posiciona uma peça no tabuleiro. Se o lugar já estiver ocupado, lança uma
	 * excessão.
	 * 
	 * @param posicao
	 *            Local onde a peça será colocada.
	 * @param peca
	 *            Peça que será posicionada no tabuleiro.
	 * @throws CasaOcupadaException
	 */
	public void colocarPeca(Posicao posicao, Peca peca)
			throws CasaOcupadaException {
		if (pecas[posicao.getColuna() - 1][posicao.getLinha() - 1] != null)
			throw new CasaOcupadaException();
		pecas[posicao.getColuna() - 1][posicao.getLinha() - 1] = peca;
	}

	/**
	 * Indica se uma determinada posição do tabuleiro está vazia ou não.
	 * 
	 * @param posicao
	 *            Posição no tabuleiro.
	 * @return Se está vazio.
	 */
	public boolean estaVazio(Posicao posicao) {
		// Está vazio quando não houver peça ali.
		return espiarPeca(posicao) == null;
	}
	
	/**
	 * Indica se uma determinada posição do tabuleiro está ocupada por um inimigo ou não.
	 * 
	 * @param posicao
	 *            Posição no tabuleiro.
	 * @return Se está vazio.
	 */
	// TODO verificar se está funcionando
	public boolean estaInimigo(Jogador jogador, Posicao destino) {
		if(pecas[destino.getColuna()-1][destino.getLinha()-1].getJogador().getCor() != null && pecas[destino.getColuna()-1][destino.getLinha()-1].getJogador().getCor() != jogador.getCor())
			return true;
		return false;
	}
	
	/**
	 * Indica se alguma peça da cor indicada ameaça a posição indicada
	 * 
	 * @param posicao
	 *            Posição a ser verificada.
	 * @param cor
	 *            Cor do jogador que pode estar ameaçando a posição.
	 * @return Se a o jogador da cor indicada está ameçando a posição com alguma
	 *         peça.
	 */
	public boolean estaAmeacadoPor(Posicao posicao, CorJogador cor) {
		// Verifica se alguma peça da cor indicada ameaça a posição indicada.
		for (int coluna = 1; coluna <= 8; coluna++) {
			for (int linha = 0; linha <= 8; linha++) {
				Posicao origem = new Posicao(coluna, linha);
				Peca peca = espiarPeca(origem);

				// Pula as peças que não forem do jogador indicado.
				if (peca.getJogador().getCor() != cor)
					continue;

				// FIXME: Isso não está funcionando completamente. Pense no caso
				// do peão. É preciso um método que use apenas a possível
				// movimentação da peça, e não faça verificações do tipo: se a
				// há uma peça para poder ser atacada. Mas faça verificação de
				// se não há peças no meio do caminho.
				if (peca.podeAtacar(origem, posicao, this))
					return true;
			}
		}

		// Caso não haja peça que possa ameaçar a posição, retorna falso.
		return false;
	}

	/**
	 * Verifica se a posição indicada está fora dos limites do tabuleiro.
	 * 
	 * @param destino
	 *            Posição que deseja-se verificar
	 * @return Se a posição está fora do tabuleiro (true), ou dentro (false).
	 */
	public boolean atravessouTabuleiro(Posicao destino) {
		if (destino.getLinha() >= 0 & destino.getLinha() < 8
				& destino.getColuna() >= 0 & destino.getColuna() < 8)
			return true;
		else
			return false;
	}

	/**
	 * Verifica se a peça pode se movimentar até o local desejado
	 * 
	 * @param origem
	 * @param destino
	 * @return
	 */
	// FIXME: E conferir se o método está sendo usado adequadamente.
	public boolean podeRealizarMovimentacao(Posicao origem, Posicao destino) {
		int linha = origem.getLinha();
		int coluna = origem.getColuna();
		int movimentoHorizontal = (int) Math.signum(destino.getLinha()
				- origem.getLinha());
		int movimentoVertical = (int) Math.signum(destino.getColuna()
				- origem.getColuna());
		do {
			linha = linha + movimentoHorizontal;
			coluna = coluna + movimentoVertical;
			if (pecas[coluna - 1][linha - 1] != null)
				return false;
		} while (linha != destino.getLinha() || coluna != destino.getColuna());
		return true;
	}

	/**
	 * Verifica se a peça realmente se movimentou ou se permaneceu no mesmo
	 * local
	 * 
	 * @param origem
	 * @param destino
	 * @return
	 */
	public boolean saiuPosicao(Posicao origem, Posicao destino) {
		if (origem.getLinha() == destino.getLinha()
				&& origem.getColuna() == destino.getColuna())
			return false;
		return true;
	}

	/**
	 * Verifica se a peça realmente se movimentou ou se permaneceu no mesmo
	 * local
	 * 
	 * @param origem
	 * @param destino
	 * @return
	 */
	// FIXME: Usar 'Posicao' ao invés de 'Casa'. E conferir se o método está
	// sendo usado adequadamente.
	public boolean relizaMovimento(Posicao origem, Posicao destino) {
		if (origem.getLinha() == destino.getLinha()
				&& origem.getColuna() == destino.getColuna())
			return false;
		return true;
	}
	
	/**
	 * Verifica se pode ser realizado o roque menor
	 * @param jogador
	 * @return
	 */
	public boolean RoqueMenor(Pessoa jogador) {
		Posicao posicaoRei;
		Posicao posicaoTorre;
		// Se for um roque menor do jogador de peças brancas
		if (jogador.getCor().toString().equals(("Branca")))
			// Se o rei e a torre ainda não se moveram
			if (pecas[4][0].getJaMoveu() == false
					& pecas[7][0].getJaMoveu() == false) {
				// Se as casas que serão ocupadas por eles estão vazias
				posicaoRei = new Posicao(1, 7);
				posicaoTorre = new Posicao(1, 6);
				if (this.estaVazio(posicaoRei) == true
						&& this.estaVazio(posicaoTorre) == true)
					return true;
			}
			// Se for um roque menor do jogador de peças pretas
			else {
				// Se o rei e a torre ainda não se moveram
				if (pecas[4][7].getJaMoveu() == false
						& pecas[7][7].getJaMoveu() == false) {
					// Se as casas que serão ocupadas por eles estão vazias
					posicaoRei = new Posicao(8, 7);
					posicaoTorre = new Posicao(8, 6);
					if (this.estaVazio(posicaoRei) == true
							&& this.estaVazio(posicaoTorre) == true)
						return true;
				}
			}
		return false;
	}
	
	/**
	 * Verifica se pode ser realizado o roque maior 
	 * @param jogador
	 * @return
	 */
	public boolean RoqueMaior(Pessoa jogador) {
		Posicao posicaoRei;
		Posicao posicaoTorre;
		// Se for um roque menor do jogador de peças brancas
		if (jogador.getCor().toString().equals(("Branca")))
			// Se o rei e a torre ainda não se moveram
			if (pecas[4][0].getJaMoveu() == false
					& pecas[0][0].getJaMoveu() == false) {
				// Se as casas que serão ocupadas por eles estão vazias
				posicaoRei = new Posicao(1, 3);
				posicaoTorre = new Posicao(1, 4);
				if (this.estaVazio(posicaoRei) == true
						&& this.estaVazio(posicaoTorre) == true)
					return true;
			}
			// Se for um roque menor do jogador de peças pretas
			else {
				// Se o rei e a torre ainda não se moveram
				if (pecas[4][7].getJaMoveu() == false
						& pecas[0][7].getJaMoveu() == false) {
					// Se as casas que serão ocupadas por eles estão vazias
					posicaoRei = new Posicao(8, 3);
					posicaoTorre = new Posicao(8, 4);
					if (this.estaVazio(posicaoRei) == true
							&& this.estaVazio(posicaoTorre) == true)
						return true;
				}
			}
		return false;
	}
}