package br.edu.ifes.poo1.cln.cdp;

/**
 * Representação de um jogador. Há no máximo 2 jogadores para o jogo de xadrez.
 */
public class Pessoa extends Jogador {

	public Pessoa(String nome, CorJogador cor){
		super(nome, cor);
	}

	/**
	 * Faz a movimentação de uma peça e também as verificações necessárias.
	 * 
	 * @param jogada
	 *            Jogada que deve ser aplicada.
	 * @throws JogadaInvalidaException
	 */
	public void movimentarPeca(Jogada jogada) throws JogadaInvalidaException {
		// Espia o que há na origem e no destino.
		Peca pecaOrigem = tabuleiro.espiarPeca(jogada.getOrigem());
		Peca pecaDestino = tabuleiro.espiarPeca(jogada.getDestino());

		// Verifica se há uma peça na origem.
		if (pecaOrigem == null)
			throw new JogadaInvalidaException(
					"Não há uma peça na origem do movimento.");

		// E verifica se a peça em origem é do jogador
		if (pecaOrigem.getJogador() != this)
			throw new JogadaInvalidaException(
					"A peça que você está tentando mover não é sua.");

		// Se a jogada for uma promoção de peão, na origem do movimento deve
		// estar um peão e o destino deve ser o a linha do lado oposto ao de
		// início das peças.
		// Faz as verificações relevantes a promoção dos peões.
		if (jogada.ehPromocao()) {
			// Sendo uma promoção, na origem deve estar um peão.
			if (pecaOrigem.getTipoPeca() != TipoPeca.PEAO) {
				throw new JogadaInvalidaException(
						"Só pode haver promoção, se a peça de origem for um peão.");
			}

			// E este peão deve estar se movimentando para a última linha do
			// tabuleiro.
			Posicao destino = jogada.getDestino();
			if ((cor == CorJogador.BRANCO && destino.getLinha() != 8)
					|| (cor == CorJogador.PRETO && destino.getLinha() != 1)) {
				throw new JogadaInvalidaException(
						"Para haver uma promoção, o peão deve estar se movimentando para a última linha do lado oposto do tabuleiro.");
			}
		}

		// Faz as verificações para o ataque ou um simples andar da peça.
		if (jogada.ehAtaque()) {
			// Se não houver peça no destino e for um ataque, a jogada é
			// inválida.
			if (pecaDestino == null)
				throw new JogadaInvalidaException(
						"Não há peça para ser atacada, na casa indicada.");

			// E a peça sendo atacada não pode pertencer ao jogador.
			if (pecaDestino.getJogador() == this)
				throw new JogadaInvalidaException(
						"A peça que você está tentando atacar é sua!");

			// Remove a peça do destino e acrescenta a lista de peças
			// capturadas, já que se trata de um ataque.
			this.pecasCapturadas
					.add(tabuleiro.retirarPeca(jogada.getDestino()));
		} else {
			// O destino deve estar livre, se a jogada não for um ataque.
			if (pecaDestino != null)
				throw new JogadaInvalidaException(
						"A casa, para a qual você está tentando mover, está ocupada por outra peça.");
		}

		// Move a peça para o destino.
		Peca pecaRetirada = tabuleiro.retirarPeca(jogada.getOrigem());
		pecaRetirada.setJaMoveu(); // Marca a peça como já movida.
		try {
			tabuleiro.colocarPeca(jogada.getDestino(), pecaRetirada);
		} catch (CasaOcupadaException e) {
			// A excessão acima não deveria ter sido lançada em hipótese alguma,
			// pois as verificações com respeito ao destino já foram feitas. Se
			// ela for lançada, o código deve ser revisto.
			throw new JogadaInvalidaException(
					"Erro gravíssimo. Entre em contato com os administradores do sistema! :)");
		}
	}

	public void aplicarRoqueMenor() throws JogadaInvalidaException {
		// Verifica se o rei e a torre não se moveram.
		Peca esperadoTorre = tabuleiro.espiarPeca(new Posicao(8, 1));
		Peca esperadoRei = tabuleiro.espiarPeca(new Posicao(5, 1));
		if (esperadoRei == null || esperadoTorre == null)
			throw new JogadaInvalidaException(
					"Não é possível fazer o Roque Menor, pois o rei e a torre não estão nas posições corretas.");
		if (esperadoRei.getJaMoveu() || esperadoTorre.getJaMoveu())
			throw new JogadaInvalidaException(
					"Não é possível fazer o Roque Menor, pois o rei e a torre já se moveram.");

		// Verifica se o comanho está livre para fazer o roque.
		if (!tabuleiro.estaVazio(new Posicao(6, 1))
				|| !tabuleiro.estaVazio(new Posicao(7, 1)))
			throw new JogadaInvalidaException(
					"O caminho para realizar o Roque Menor deve estar livre.");

		// TODO: O rei não pode entrar em xeque com a jogada. Então, essa
		// verificação deve ser feita.

		// Executa o roque.
		Peca torre = tabuleiro.retirarPeca(new Posicao(8, 1));
		Peca rei = tabuleiro.retirarPeca(new Posicao(5, 1));
		try {
			tabuleiro.colocarPeca(new Posicao(7, 1), rei);
			tabuleiro.colocarPeca(new Posicao(6, 1), torre);
		} catch (CasaOcupadaException e) {
			throw new JogadaInvalidaException(
					"O caminho para fazer o Roque Menor não está livre.");
		}
	}

}
