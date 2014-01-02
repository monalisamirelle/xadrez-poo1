package br.edu.ifes.poo1.cln.cdp;

import junit.framework.Assert;

import org.junit.Test;

public class TestePosicao {

	@Test
	public void construtor() {
		Posicao posicao = new Posicao(1, 2);
		Assert.assertEquals(1, posicao.getColuna());
		Assert.assertEquals(2, posicao.getLinha());
	}

	@Test
	public void equals_testaIguais() {
		// Cria algumas posições iguais.
		Posicao a1 = new Posicao(1, 1);
		Posicao a2 = new Posicao(1, 1);
		
		Posicao b1 = new Posicao(2, 3);
		Posicao b2 = new Posicao(2, 3);
		
		Posicao c1 = new Posicao(8, 1);
		Posicao c2 = new Posicao(8, 1);
		
		Posicao d1 = new Posicao(7, 3);
		Posicao d2 = new Posicao(7, 3);
		
		// Testa se as posições iguais são realmente iguais.
		Assert.assertTrue(a1.equals(a2));
		Assert.assertTrue(b1.equals(b2));
		Assert.assertTrue(c1.equals(c2));
		Assert.assertTrue(d1.equals(d2));
		
	}
	
	@Test
	public void equals_testaDiferentes() {
		// Cria algumas posições diferentes.
		Posicao x1 = new Posicao(4, 4); Posicao x2 = new Posicao(5, 5);
		Posicao y1 = new Posicao(2, 3); Posicao y2 = new Posicao(3, 2);
		Posicao w1 = new Posicao(8, 1); Posicao w2 = new Posicao(8, 4);
		Posicao k1 = new Posicao(7, 3); Posicao k2 = new Posicao(6, 3);
		
		// Testa se as posições diferentes são realmente diferentes.
		Assert.assertFalse(x1.equals(x2));
		Assert.assertFalse(y1.equals(y2));
		Assert.assertFalse(w1.equals(w2));
		Assert.assertFalse(k1.equals(k2));
	}
}
