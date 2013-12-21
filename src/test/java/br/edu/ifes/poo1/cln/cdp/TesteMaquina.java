package br.edu.ifes.poo1.cln.cdp;

import org.junit.Before;

public class TesteMaquina {
	Maquina maquina;
	Tabuleiro tabuleiro;
	Rainha rainhaPreta;
	Torre torrePreta;
	Peao peaoBranco;
	Jogada jogada;

	@Before
	public void before() throws CasaOcupadaException {
		maquina = new Maquina("Zeus", CorJogador.PRETO);
		tabuleiro = new Tabuleiro();
		rainhaPreta = new Rainha(new Jogador("Teste", CorJogador.PRETO));
		torrePreta = new Torre(new Jogador("Teste", CorJogador.PRETO));
		peaoBranco = new Peao(new Jogador("Teste", CorJogador.BRANCO));
		maquina.setTabuleiro(tabuleiro);

		/** Coloca peças no tabuleiro */
		tabuleiro.colocarPeca(new Posicao(6, 4), rainhaPreta);
		tabuleiro.colocarPeca(new Posicao(7, 2), torrePreta);
		tabuleiro.colocarPeca(new Posicao(7, 7), peaoBranco);
	}

	// TODO fazer teste

	/**
	 * @Test public void escolherJogada() {
	 *       Assert.assertTrue(maquina.escolherJogada()); }
	 */
}
