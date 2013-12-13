package br.edu.ifes.poo1.cln.cdp;

/**
 * Um tabuleiro é composto por 64 casas, estas podem estar ocupadas por uma peça
 * ou vazias.
 * 
 * @author lucas
 * 
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
	 * Cria um tabuleiro já preenchido com as peças necessárias para uma partida
	 * de xadrez comum. Para isso é necessário saber quem são os jogadores que
	 * controlarão as peças criadas.
	 * 
	 * @param brancas
	 *            Jogador que controla as peças brancas.
	 * @param pretas
	 *            Jogador que controla as peças pretas.
	 */
	// FIXME: Refazer isso aqui tudo de forma que não use as peças palhaço. Mas
	// crie um tabuleiro padrão de xadrez.
	public Tabuleiro(Jogador brancas, Jogador pretas) {
		// Inicia um tabuleiro vazio.
		this();

		// Posiciona as peças brancas.
		for (int c = 1; c <= 8; c++) { // Coluna
			try {
				colocarPeca(new Posicao(c - 1, 1), new Palhaco(brancas));
			} catch (CasaOcupadaException e) {
				// Não deveria haver casa sendo sobrescrita com peças na etapa
				// de construção do tabuleiro. Se isso acontecer, o código deve
				// ser verificado.
				e.printStackTrace();
			}
		}

		// Posiciona as peças pretas.
		for (int c = 1; c <= 8; c++) { // Coluna
			try {
				colocarPeca(new Posicao(c - 1, 1), new Palhaco(pretas));
			} catch (CasaOcupadaException e) {
				// Não deveria haver casa sendo sobrescrita com peças na etapa
				// de construção do tabuleiro. Se isso acontecer, o código deve
				// ser verificado.
				e.printStackTrace();
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
	 * @throws JogadaInvalidaException
	 *             Lançada se for impossível que a peça se mova para o local
	 *             desejado. Ou se o local de destino não estiver vazio.
	 */
	@Deprecated
	public void andarPeca(Posicao origem, Posicao destino)
			throws JogadaInvalidaException {
		// TODO: Implementar.
	}

	/**
	 * Move a peça indicada para atacar a casa desejada.
	 * 
	 * @param origem
	 *            Posição onde está a peça a qual será usada para atacar.
	 * @param destino
	 *            Posição a onde está a peça que será atacada.
	 * @throws JogadaInvalidaException
	 *             Lançada se for impossível atacar o local com a peça
	 *             selecionada, ou se não há peça na casa para ser atacada.
	 */
	@Deprecated
	public void atacarPeca(Posicao origem, Posicao destino)
			throws JogadaInvalidaException {
		// Casa casaOrigem = casas[origem.getColuna()][origem.getLinha()];
		// Casa casaDestino = casas[destino.getColuna()][destino.getLinha()];
		// Peca pecaAtacante = casaOrigem.getPeca();
		// Peca pecaAtacada = casaDestino.getPeca();
		//
		// if (pecaAtacante.podeAtacar(casaDestino)) {
		// // TODO: Implementar.
		// }
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
}
