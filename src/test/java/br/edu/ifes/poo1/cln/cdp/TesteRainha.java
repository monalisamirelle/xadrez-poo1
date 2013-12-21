package br.edu.ifes.poo1.cln.cdp;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TesteRainha {
	Tabuleiro tabuleiro;
	Rainha rainhaBranca;
	Peao peaoPreto;

	@Before
	public void before() {
		tabuleiro = new Tabuleiro();
		rainhaBranca = new Rainha(new Jogador(CorJogador.BRANCO, tabuleiro));
		peaoPreto = new Peao(new Jogador(CorJogador.PRETO, tabuleiro));
	}

	@Test
	public void podeAndar() throws CasaOcupadaException {

		// Verifica se o rainha pode realizar determinado movimento
		Assert.assertTrue(rainhaBranca.podeAndar(new Posicao(4, 4),
				new Posicao(5, 5), tabuleiro));
		Assert.assertTrue(rainhaBranca.podeAndar(new Posicao(4, 4),
				new Posicao(4, 8), tabuleiro));
		Assert.assertTrue(rainhaBranca.podeAndar(new Posicao(4, 4),
				new Posicao(8, 4), tabuleiro));
		Assert.assertTrue(rainhaBranca.podeAndar(new Posicao(4, 4),
				new Posicao(7, 7), tabuleiro));
		Assert.assertTrue(rainhaBranca.podeAndar(new Posicao(4, 4),
				new Posicao(6, 2), tabuleiro));
		Assert.assertTrue(rainhaBranca.podeAndar(new Posicao(4, 4),
				new Posicao(1, 1), tabuleiro));

		// Verifica se a peça realiza movimento atravessando o tabuleiro
		Assert.assertFalse(rainhaBranca.podeAndar(new Posicao(4, 4),
				new Posicao(9, 9), tabuleiro));
		Assert.assertFalse(rainhaBranca.podeAndar(new Posicao(4, 4),
				new Posicao(4, 0), tabuleiro));

		// Verifica se a peça realiza movimento não saindo da posição
		Assert.assertFalse(rainhaBranca.podeAndar(new Posicao(5, 5),
				new Posicao(5, 5), tabuleiro));
		Assert.assertFalse(rainhaBranca.podeAndar(new Posicao(7, 7),
				new Posicao(7, 7), tabuleiro));

		// Verifica se a peça anda para uma posição que não está vazia
		// Instancia uma peça qualquer.
		Peca peca = new Peao(null);

		// Coloca a peça no tabuleiro.
		tabuleiro.colocarPeca(new Posicao(8, 8), peca);
		Assert.assertFalse(rainhaBranca.podeAndar(new Posicao(4, 4),
				new Posicao(8, 8), tabuleiro));

		// Coloca a peça no tabuleiro.
		tabuleiro.colocarPeca(new Posicao(4, 5), peca);
		Assert.assertFalse(rainhaBranca.podeAndar(new Posicao(4, 4),
				new Posicao(4, 5), tabuleiro));

	}

	@Test
	public void podeAtacar() throws CasaOcupadaException {

		// Coloca a peça no tabuleiro.
		tabuleiro.colocarPeca(new Posicao(6, 4), peaoPreto);
		// Coloca a peça no tabuleiro.
		tabuleiro.colocarPeca(new Posicao(6, 7), peaoPreto);
		// Coloca a peça no tabuleiro.
		tabuleiro.colocarPeca(new Posicao(5, 3), peaoPreto);

		// Verifica se o rainha pode realizar determinado ataque
		Assert.assertTrue(rainhaBranca.podeAtacar(new Posicao(6, 3),
				new Posicao(6, 4), tabuleiro));

		// Assert.assertTrue(rainhaBranca.podeAtacar(new Posicao(6, 3),
		// new Posicao(6, 7), tabuleiro));
		Assert.assertTrue(rainhaBranca.podeAtacar(new Posicao(6, 3),
				new Posicao(5, 3), tabuleiro));

		Assert.assertFalse(rainhaBranca.podeAtacar(new Posicao(6, 3),
				new Posicao(6, 5), tabuleiro));

		Assert.assertFalse(rainhaBranca.podeAtacar(new Posicao(6, 3),
				new Posicao(6, 2), tabuleiro));

		Assert.assertFalse(rainhaBranca.podeAtacar(new Posicao(6, 3),
				new Posicao(3, 6), tabuleiro));

	}
}
