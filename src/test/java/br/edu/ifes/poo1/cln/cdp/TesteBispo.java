package br.edu.ifes.poo1.cln.cdp;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TesteBispo {
	private Tabuleiro tabuleiro;
	private Bispo bispoBranco;
	private Jogador brancas;
	private Jogador pretas;

	@Before
	public void before() throws CasaOcupadaException {
		// Inicia um tabuleiro vazio.
		tabuleiro = new Tabuleiro();

		// Inicia os jogadores.
		brancas = new Jogador("TesteBranco", CorJogador.BRANCO);
		pretas = new Jogador("TestePreto", CorJogador.PRETO);

		// Inicia o bispo.
		bispoBranco = new Bispo(brancas.getCor());

		// Coloca peças inimigas no tabuleiro.
		tabuleiro.colocarPeca(new Posicao(6, 4), new Peao(pretas.getCor()));
		tabuleiro.colocarPeca(new Posicao(8, 8), new Peao(pretas.getCor()));
		tabuleiro.colocarPeca(new Posicao(5, 3), new Peao(pretas.getCor()));

		// Coloca peça aliada no tabuleiro.
		tabuleiro.colocarPeca(new Posicao(2, 2), new Peao(brancas.getCor()));
	}

	@Test
	public void podeAndar() throws CasaOcupadaException {
		// Verifica se o bispo pode realizar determinados movimentos (não se
		// importando em diferenciar bispos de casas pretas e brancas).

		Assert.assertTrue(bispoBranco.podeAndar(new Posicao(4, 4), new Posicao(
				5, 5), tabuleiro));
		Assert.assertTrue(bispoBranco.podeAndar(new Posicao(4, 4), new Posicao(
				7, 7), tabuleiro));
		Assert.assertTrue(bispoBranco.podeAndar(new Posicao(4, 4), new Posicao(
				3, 5), tabuleiro));
		Assert.assertTrue(bispoBranco.podeAndar(new Posicao(5, 8), new Posicao(
				3, 6), tabuleiro));
		Assert.assertTrue(bispoBranco.podeAndar(new Posicao(5, 8), new Posicao(
				6, 7), tabuleiro));
		Assert.assertTrue(bispoBranco.podeAndar(new Posicao(5, 8), new Posicao(
				7, 6), tabuleiro));
		Assert.assertTrue(bispoBranco.podeAndar(new Posicao(8, 1), new Posicao(
				1, 8), tabuleiro));
		Assert.assertTrue(bispoBranco.podeAndar(new Posicao(7, 7), new Posicao(
				5, 5), tabuleiro));
	}

	@Test
	public void podeAtacar() throws CasaOcupadaException {
		// Verifica se o bispo pode realizar determinado ataque.
		Assert.assertTrue(bispoBranco.podeAtacar(new Posicao(7, 3),
				new Posicao(6, 4), tabuleiro));
		Assert.assertTrue(bispoBranco.podeAtacar(new Posicao(6, 6),
				new Posicao(8, 8), tabuleiro));
		Assert.assertTrue(bispoBranco.podeAtacar(new Posicao(3, 5),
				new Posicao(5, 3), tabuleiro));

		// Verifica se o bispo pode atacar uma posição vazia.
		// Deve ser possível, pois podeAndar(..) não deve verificar a o que há
		// no destino.
		Assert.assertTrue(bispoBranco.podeAtacar(new Posicao(3, 7),
				new Posicao(6, 4), tabuleiro));
		Assert.assertTrue(bispoBranco.podeAtacar(new Posicao(8, 4),
				new Posicao(6, 2), tabuleiro));
		Assert.assertTrue(bispoBranco.podeAtacar(new Posicao(1, 8),
				new Posicao(3, 6), tabuleiro));

		// Verifica se o bispo pode atacar uma posição com peça aliada.
		// Deve ser possível, pois podeAndar(..) não deve verificar a o que há
		// no destino.
		Assert.assertTrue(bispoBranco.podeAtacar(new Posicao(5, 5),
				new Posicao(2, 2), tabuleiro));
	}
}
