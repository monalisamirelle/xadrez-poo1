package br.edu.ifes.poo1.cln.cdp;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TesteMaquina {
	Maquina maquina;
	Tabuleiro tabuleiro;
	Rainha rainhaPreta;
	Peao peaoBranco;
	Jogada jogada;
	CorJogador cor;

	@Before
	public void before() throws CasaOcupadaException {
		maquina = new Maquina("Zeus",cor.PRETO);
		tabuleiro = new Tabuleiro();
		rainhaPreta = new Rainha(new Jogador("Teste", CorJogador.PRETO));
		peaoBranco = new Peao(new Jogador("Teste", CorJogador.BRANCO));

		/** Coloca peças no tabuleiro */
		tabuleiro.colocarPeca(new Posicao(6, 4), rainhaPreta);
		tabuleiro.colocarPeca(new Posicao(8, 8), peaoBranco);
	}

	@Test
	public void escolherJogada() {
		Assert.assertTrue(maquina.escolherJogada());
	}
}
