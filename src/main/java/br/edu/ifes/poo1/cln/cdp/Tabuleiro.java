package br.edu.ifes.poo1.cln.cdp;

import java.util.ArrayList;
import java.util.List;

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
	 * Inicia um tabuleiro padrão de xadrez, com as peças já posicionadas e com
	 * suas referências para os jogadores corretos.
	 * 
	 * @param brancas
	 *            Jogador que controla as peças brancas.
	 * @param pretas
	 *            Jogador que controla as peças pretas.
	 * @throws CasaOcupadaException
	 */
	public Tabuleiro(Jogador brancas, Jogador pretas) {
		try {
			// Posiciona as peças brancas, exceto os peões.
			this.colocarPeca(new Posicao(1, 1), new Torre(brancas));
			this.colocarPeca(new Posicao(2, 1), new Cavalo(brancas));
			this.colocarPeca(new Posicao(3, 1), new Bispo(brancas));
			this.colocarPeca(new Posicao(4, 1), new Rainha(brancas));
			this.colocarPeca(new Posicao(5, 1), new Rei(brancas));
			this.colocarPeca(new Posicao(6, 1), new Bispo(brancas));
			this.colocarPeca(new Posicao(7, 1), new Cavalo(brancas));
			this.colocarPeca(new Posicao(8, 1), new Torre(brancas));

			// Posiciona os peões brancos.
			for (int coluna = 1; coluna <= 8; coluna++) {
				this.colocarPeca(new Posicao(coluna, 2), new Peao(brancas));
			}

			// Posiciona as peças brancas, exceto os peões.
			this.colocarPeca(new Posicao(1, 8), new Torre(pretas));
			this.colocarPeca(new Posicao(2, 8), new Cavalo(pretas));
			this.colocarPeca(new Posicao(3, 8), new Bispo(pretas));
			this.colocarPeca(new Posicao(4, 8), new Rei(pretas));
			this.colocarPeca(new Posicao(5, 8), new Rainha(pretas));
			this.colocarPeca(new Posicao(6, 8), new Bispo(pretas));
			this.colocarPeca(new Posicao(7, 8), new Cavalo(pretas));
			this.colocarPeca(new Posicao(8, 8), new Torre(pretas));

			// Posiciona os peões pretos.
			for (int coluna = 1; coluna <= 8; coluna++) {
				this.colocarPeca(new Posicao(coluna, 7), new Peao(pretas));
			}
		} catch (CasaOcupadaException e) {
			// Se a excessão for lançada, o construtor está tentando posicionar
			// peças onde já há alguma e deve ser refeito.
			e.printStackTrace();
		}
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
	 * Indica se uma determinada posição do tabuleiro está ocupada por um
	 * inimigo ou não.
	 * 
	 * @param posicao
	 *            Posição no tabuleiro.
	 * @return Se está vazio.
	 */
	public boolean estaInimigo(Jogador jogador, Posicao destino) {
		if (this.espiarPeca(new Posicao(destino.getColuna(), destino.getLinha())) != null
				&& this.espiarPeca(
						new Posicao(destino.getColuna(), destino.getLinha()))
						.getJogador().getCor() != jogador.getCor())
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
		// Verifica se há alguma peça da cor indicada que ameaça a posição
		// indicada.
		for (int coluna = 1; coluna <= 8; coluna++) {
			for (int linha = 0; linha <= 8; linha++) {
				Posicao origem = new Posicao(coluna, linha);
				Peca peca = espiarPeca(origem);

				// Pula as peças que não forem da cor indicada.
				if (peca.getJogador().getCor() != cor)
					continue;

				// Verifica se a pessa pode atacar a posição indicada.
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
	 * @param posicao
	 *            Posição a ser verificada.
	 * @return Se a posição está fora do tabuleiro (true), ou dentro (false).
	 */
	public static boolean estaForaDoTabuleiro(Posicao posicao) {
		if (posicao.getLinha() > 0 & posicao.getLinha() <= 8
				& posicao.getColuna() > 0 & posicao.getColuna() <= 8)
			return false;
		else
			return true;
	}

	/**
	 * Verifica se a peça pode se movimentar até o local desejado
	 * 
	 * @param origem
	 * @param destino
	 * @return
	 */
	// FIXME: Conferir se o método está sendo usado adequadamente.
	public boolean podeRealizarMovimentacao(Posicao origem, Posicao destino) {
		int linha = origem.getLinha();
		int coluna = origem.getColuna();
		int movimentoHorizontal = (int) Math.signum(destino.getLinha()
				- origem.getLinha());
		int movimentoVertical = (int) Math.signum(destino.getColuna()
				- origem.getColuna());
		while ((linha != destino.getLinha() || coluna != destino.getColuna())) {
			// Se não tivermos chegado na posição
			linha = linha + movimentoHorizontal;
			coluna = coluna + movimentoVertical;
			if (estaForaDoTabuleiro(new Posicao(coluna, linha)) == true) {
				return false;
			}
			if (!(linha == destino.getLinha() && coluna == destino.getColuna())) {
				// Se a posição no tabuleiro não for nula, informe que o
				// movimento é proibido
				if (pecas[coluna - 1][linha - 1] != null) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Verifica se pode ser realizado o roque menor
	 * 
	 * @param jogador
	 * @return
	 */
	public boolean roqueMenor(Jogador jogador) {
		Posicao posicaoDesejadaRei;
		Posicao posicaoDesejadaTorre;
		// Se for um roque menor do jogador de peças brancas
		if (jogador.getCor() == CorJogador.BRANCO) {
			// Se o rei e a torre ainda não se moveram
			if (this.espiarPeca(new Posicao(5, 1)).getJaMoveu() == false
					& this.espiarPeca(new Posicao(8, 1)).getJaMoveu() == false) {
				// Se as casas que serão ocupadas por eles estão vazias
				posicaoDesejadaRei = new Posicao(7, 1);
				posicaoDesejadaTorre = new Posicao(6, 1);
				if (this.estaVazio(posicaoDesejadaRei) == true
						&& this.estaVazio(posicaoDesejadaTorre) == true)
					return true;
			}
		// Se for um roque menor do jogador de peças pretas
		} else {
			// Se o rei e a torre ainda não se moveram
			if (this.espiarPeca(new Posicao(5, 8)).getJaMoveu() == false
					& this.espiarPeca(new Posicao(8, 8)).getJaMoveu() == false) {
				// Se as casas que serão ocupadas por eles estão vazias
				posicaoDesejadaRei = new Posicao(7, 8);
				posicaoDesejadaTorre = new Posicao(6, 8);
				if (this.estaVazio(posicaoDesejadaRei) == true
						&& this.estaVazio(posicaoDesejadaTorre) == true)
					return true;
			}
		}
		return false;
	}

	/**
	 * Verifica se pode ser realizado o roque maior
	 * 
	 * @param jogador
	 * @return
	 */
	public boolean roqueMaior(Jogador jogador) {
		Posicao posicaoDesejadaRei;
		Posicao posicaoDesejadaTorre;
		// Se for um roque maior do jogador de peças brancas
		if (jogador.getCor() == CorJogador.BRANCO) {
			// Se o rei e a torre ainda não se moveram
			if (this.espiarPeca(new Posicao(5, 1)).getJaMoveu() == false
					& this.espiarPeca(new Posicao(1, 1)).getJaMoveu() == false) {
				// Se as casas que serão ocupadas por eles estão vazias
				posicaoDesejadaRei = new Posicao(3, 1);
				posicaoDesejadaTorre = new Posicao(4, 1);
				if (this.estaVazio(posicaoDesejadaRei) == true
						&& this.estaVazio(posicaoDesejadaTorre) == true)
					return true;
			}
		// Se for um roque maior do jogador de peças pretas
		} else {
			// Se o rei e a torre ainda não se moveram
			if (this.espiarPeca(new Posicao(5, 8)).getJaMoveu() == false
					& this.espiarPeca(new Posicao(1, 8)).getJaMoveu() == false) {
				// Se as casas que serão ocupadas por eles estão vazias
				posicaoDesejadaRei = new Posicao(3, 8);
				posicaoDesejadaTorre = new Posicao(4, 8);
				if (this.estaVazio(posicaoDesejadaRei) == true
						&& this.estaVazio(posicaoDesejadaTorre) == true)
					return true;
			}
		}
		return false;
	}

	/**
	 * Verifica se o rei dá cor indicada está em Xeque.
	 * 
	 * @param cor
	 *            Cor do rei.
	 * @return Se a o rei está em Xeque.
	 */
	public boolean verificarXeque(CorJogador cor) {
		// Encontra a posição do rei.
		Posicao posicaoRei = encontrarRei(cor);

		// Retorna se a posição do rei está ameaçada.
		if (cor == CorJogador.BRANCO)
			return estaAmeacadoPor(posicaoRei, CorJogador.PRETO);
		else
			return estaAmeacadoPor(posicaoRei, CorJogador.BRANCO);
	}

	/**
	 * Encontra a posição no tabuleiro em que o rei da cor indicada está. Se o
	 * rei não for encontrado (o que é impossível numa partida de xadrez), o
	 * método retorna 'null'.
	 * 
	 * @param cor
	 *            Cor do rei a procurar.
	 * @return Onde está o rei.
	 */
	public Posicao encontrarRei(CorJogador cor) {
		// Varre o tabuleiro procurando o rei da cor indicada.
		for (int coluna = 1; coluna <= 8; coluna++) {
			for (int linha = 0; linha <= 8; linha++) {
				// Forma a posição que estamos a verificar.
				Posicao posicao = new Posicao(coluna, linha);

				// Pula se não houver peça ali.
				if (estaVazio(posicao))
					continue;

				// Pega a peça que está ali.
				Peca peca = espiarPeca(posicao);

				// Verifica se é o rei da cor indicada. E retorna-o.
				if (peca.getTipoPeca() == TipoPeca.REI
						&& peca.getJogador().getCor() == cor) {
					return posicao;
				}
			}
		}

		// Se o rei for encontrado (o que não acontece em uma partida de
		// xadrez), retorna 'null'.
		return null;
	}

	/**
	 * Retorna uma lista de posições válidas que estão ao redor do rei da cor
	 * indicada, mesmo vazias ou não.
	 * 
	 * @param cor
	 *            Cor do rei
	 * @return Posições ao redor do rei.
	 */
	private List<Posicao> posicoesAoRedorDoRei(CorJogador cor) {
		// Encontra a posição do rei.
		Posicao posicaoRei = encontrarRei(cor);

		// Inicia uma lista de posições ao redor.
		List<Posicao> posicoesAoRedor = new ArrayList<Posicao>();

		// Adiciona todas as posições acima e abaixo do rei.
		for (int coluna = posicaoRei.getColuna() - 1; coluna < posicaoRei
				.getColuna() + 1; coluna++) {
			// Acima do rei.
			posicoesAoRedor.add(new Posicao(coluna, posicaoRei.getLinha() + 1));

			// Abaixo do rei.
			posicoesAoRedor.add(new Posicao(coluna, posicaoRei.getLinha() - 1));
		}

		// Adiciona a posição a esquerda do rei.
		posicoesAoRedor.add(new Posicao(posicaoRei.getColuna(), posicaoRei
				.getLinha() - 1));

		// Adiciona a posição a direita do rei.
		posicoesAoRedor.add(new Posicao(posicaoRei.getColuna(), posicaoRei
				.getLinha() + 1));

		// Retira da lista as posições que estão fora do tabuleiro.
		for (Posicao posicao : posicoesAoRedor) {
			if (estaForaDoTabuleiro(posicao))
				posicoesAoRedor.remove(posicao);
		}

		// Retorna a lista pronta.
		return posicoesAoRedor;
	}

	/**
	 * Verifica se houve Xeque Mate na cor indicada.
	 * 
	 * @param cor
	 *            Cor do rei que está em Xeque Mate.
	 * @return Se a cor indicada sofreu Xeque Mate.
	 */
	public boolean verificarXequeMate(CorJogador cor) {
		// Encontra o rei.
		Posicao posicaoRei = encontrarRei(cor);
		Peca rei = espiarPeca(posicaoRei);

		// Pega as posições que estão ao redor do rei.
		List<Posicao> posicoes = posicoesAoRedorDoRei(cor);

		// Verifica se há alguma posição para onde o rei pode se mover.
		boolean podeSeMover = false;
		for (Posicao posicao : posicoes) {
			// Verifica se ele pode se mover ou atacar, para sair da posição
			// atual.
			if (rei.podeAndar(posicaoRei, posicao, this)
					|| rei.podeAtacar(posicaoRei, posicao, this))
				podeSeMover = true;
		}

		// É Xeque Mate se o rei está em Xeque e não há para onde ele se mover.
		// FIXME: Na verdade, para que o Xeque Mate ocorra, nenhuma peça pode
		// conseguir ajudar o rei. Mas isto está fora do escopo do trabalho.
		return verificarXeque(cor) && !podeSeMover;
	}
}