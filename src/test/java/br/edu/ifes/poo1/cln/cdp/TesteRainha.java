package br.edu.ifes.poo1.cln.cdp;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifes.poo1.cln.cdp.pecas.Peao;
import br.edu.ifes.poo1.cln.cdp.pecas.Rainha;
import br.edu.ifes.poo1.cln.cdp.tipos.TipoCorJogador;

public class TesteRainha {
	TabuleiroXadrez tabuleiro;
	Rainha rainhaBranca;
	Peao peaoPreto;
	Peao peaoBranco;

	@Before
	public void before() throws CasaOcupadaException {
		tabuleiro = new TabuleiroXadrez();
		rainhaBranca = new Rainha(TipoCorJogador.BRANCO);
		peaoPreto = new Peao(TipoCorJogador.PRETO);
		peaoBranco = new Peao(TipoCorJogador.BRANCO);

		/** Coloca peças inimigas no tabuleiro */
		tabuleiro.colocarPeca(new Posicao(6, 4), peaoPreto);
		tabuleiro.colocarPeca(new Posicao(8, 8), peaoPreto);
		tabuleiro.colocarPeca(new Posicao(5, 3), peaoPreto);

		/** Coloca peça aliada no tabuleiro */
		tabuleiro.colocarPeca(new Posicao(2, 2), peaoBranco);

	}

	@Test
	public void podeAndar() throws CasaOcupadaException {
		/** Verifica se a rainha pode realizar determinado movimento */
		Assert.assertTrue(rainhaBranca.podeAndar(new Posicao(4, 4),
				new Posicao(5, 5), tabuleiro));
		Assert.assertTrue(rainhaBranca.podeAndar(new Posicao(4, 4),
				new Posicao(4, 8), tabuleiro));
		Assert.assertTrue(rainhaBranca.podeAndar(new Posicao(4, 4),
				new Posicao(7, 7), tabuleiro));
		Assert.assertTrue(rainhaBranca.podeAndar(new Posicao(4, 4),
				new Posicao(6, 6), tabuleiro));
	}

	@Test
	public void podeAtacar() throws CasaOcupadaException {

		/** Verifica se a rainha pode realizar determinado ataque */
		Assert.assertTrue(rainhaBranca.podeAtacar(new Posicao(6, 3),
				new Posicao(6, 4), tabuleiro));
		Assert.assertTrue(rainhaBranca.podeAtacar(new Posicao(6, 6),
				new Posicao(8, 8), tabuleiro));
		Assert.assertTrue(rainhaBranca.podeAtacar(new Posicao(6, 3),
				new Posicao(5, 3), tabuleiro));

		/**
		 * Verifica se a rainha pode atacar uma posição vazia
		 * 
		 * Deve ser possível, pois podeAndar(..) não deve verificar a o que há
		 * no destino.
		 */
		Assert.assertFalse(rainhaBranca.podeAtacar(new Posicao(6, 3),
				new Posicao(6, 2), tabuleiro));
		Assert.assertFalse(rainhaBranca.podeAtacar(new Posicao(6, 3),
				new Posicao(3, 6), tabuleiro));

		/**
		 * Verifica se a rainha pode se mover havendo um peão no meio do
		 * caminho.
		 */
		Assert.assertFalse(rainhaBranca.podeAtacar(new Posicao(6, 3),
				new Posicao(6, 5), tabuleiro));
		
		/**
		 * Verifica se a rainha pode atacar uma posição com peça aliada
		 * 
		 * Deve ser possível, pois podeAndar(..) não deve verificar a o que há
		 * no destino.
		 */
		Assert.assertFalse(rainhaBranca.podeAtacar(new Posicao(4, 4),
				new Posicao(2, 2), tabuleiro));
	}
}
