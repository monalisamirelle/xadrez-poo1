package br.edu.ifes.poo1.cln.cdp;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifes.poo1.cln.cdp.pecas.Bispo;
import br.edu.ifes.poo1.cln.cdp.pecas.Peao;

public class TesteBispo {
	private TabuleiroXadrez tabuleiro;
	private Bispo bispoBranco;

	@Before
	public void before() throws CasaOcupadaException {
		// Inicia um tabuleiro vazio.
		tabuleiro = new TabuleiroXadrez();

		// Inicia o bispo.
		bispoBranco = new Bispo(TipoCorJogador.BRANCO);

		// Coloca peças inimigas no tabuleiro.
		tabuleiro.colocarPeca(new Posicao(6, 4), new Peao(TipoCorJogador.PRETO));
		tabuleiro.colocarPeca(new Posicao(8, 8), new Peao(TipoCorJogador.PRETO));
		tabuleiro.colocarPeca(new Posicao(5, 3), new Peao(TipoCorJogador.PRETO));

		// Coloca peça aliada no tabuleiro.
		tabuleiro.colocarPeca(new Posicao(2, 2), new Peao(TipoCorJogador.BRANCO));
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
	public void podeAtacar() throws CasaOcupadaException,
			CloneNotSupportedException {
		// Verifica se o bispo pode realizar determinado ataque.
		Assert.assertTrue(bispoBranco.podeAtacar(new Posicao(7, 3),
				new Posicao(6, 4), tabuleiro));
		Assert.assertTrue(bispoBranco.podeAtacar(new Posicao(6, 6),
				new Posicao(8, 8), tabuleiro));
		Assert.assertTrue(bispoBranco.podeAtacar(new Posicao(3, 5),
				new Posicao(5, 3), tabuleiro));

		// Verifica se o bispo pode atacar uma posição vazia.
		Assert.assertFalse(bispoBranco.podeAtacar(new Posicao(3, 7),
				new Posicao(4, 5), tabuleiro));
		Assert.assertFalse(bispoBranco.podeAtacar(new Posicao(8, 4),
				new Posicao(6, 2), tabuleiro));
		Assert.assertFalse(bispoBranco.podeAtacar(new Posicao(1, 8),
				new Posicao(3, 6), tabuleiro));
	}
}
