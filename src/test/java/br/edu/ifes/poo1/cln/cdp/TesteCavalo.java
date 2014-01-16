package br.edu.ifes.poo1.cln.cdp;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TesteCavalo {
	Tabuleiro tabuleiro;
	Cavalo cavaloBranco;
	Peao peaoPreto;
	Peao peaoBranco;

	@Before
	public void before() throws CasaOcupadaException {
		tabuleiro = new Tabuleiro();
		cavaloBranco = new Cavalo(CorJogador.BRANCO);
		peaoPreto = new Peao(CorJogador.PRETO);
		peaoBranco = new Peao(CorJogador.BRANCO);

		/** Colocar cavalo no tabuleiro */
		tabuleiro.colocarPeca(new Posicao(4, 4), cavaloBranco);

		/** Coloca peças inimigas no tabuleiro */
		tabuleiro.colocarPeca(new Posicao(6, 5), peaoPreto);
		tabuleiro.colocarPeca(new Posicao(8, 8), peaoPreto);
		tabuleiro.colocarPeca(new Posicao(5, 3), peaoPreto);

		/** Coloca peça aliada no tabuleiro */
		tabuleiro.colocarPeca(new Posicao(2, 3), peaoBranco);
	}

	@Test
	public void podeAndar() throws CasaOcupadaException {
		// Verifica se o cavalo pode realizar determinado movimento.
		Assert.assertTrue(cavaloBranco.podeAndar(new Posicao(4, 4),
				new Posicao(5, 6), tabuleiro));
		Assert.assertTrue(cavaloBranco.podeAndar(new Posicao(4, 4),
				new Posicao(5, 2), tabuleiro));
		Assert.assertTrue(cavaloBranco.podeAndar(new Posicao(4, 4),
				new Posicao(6, 3), tabuleiro));
		Assert.assertTrue(cavaloBranco.podeAndar(new Posicao(4, 4),
				new Posicao(3, 6), tabuleiro));
		Assert.assertTrue(cavaloBranco.podeAndar(new Posicao(4, 4),
				new Posicao(3, 2), tabuleiro));
		Assert.assertTrue(cavaloBranco.podeAndar(new Posicao(4, 4),
				new Posicao(2, 5), tabuleiro));
	}

	@Test
	public void podeAtacar() throws CasaOcupadaException {

		// Verifica se o cavalo pode realizar determinado ataque.
		Assert.assertTrue(cavaloBranco.podeAtacar(new Posicao(4, 4),
				new Posicao(6, 5), tabuleiro));

		// Verifica se o cavalo pode atacar uma posição vazia.
		Assert.assertFalse(cavaloBranco.podeAtacar(new Posicao(4, 4),
				new Posicao(2, 5), tabuleiro));

		// Verifica se o cavalo pode atacar uma posição com peça aliada.
		Assert.assertFalse(cavaloBranco.podeAtacar(new Posicao(4, 4),
				new Posicao(2, 3), tabuleiro));
	}
}
