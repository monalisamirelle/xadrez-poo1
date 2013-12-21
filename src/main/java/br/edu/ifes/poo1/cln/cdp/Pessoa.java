package br.edu.ifes.poo1.cln.cdp;

/**
 * Representação de um jogador. Há no máximo 2 jogadores para o jogo de xadrez.
 */
public class Pessoa extends Jogador {

	public Pessoa(String nome, CorJogador cor){
		super(nome, cor);
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
