package br.edu.ifes.sr.xadrez.model;

import java.util.List;

/**
 * Contém as peças em jogo que ainda não foram capturadas.
 * 
 * @author lucas
 * 
 */
public class Tabuleiro {
	private final int numeroDeColunas = 8;
	private final int numeroDeLinhas = 8;
	private Peca[][] tabuleiro = new Peca[numeroDeColunas][numeroDeLinhas];
	
	public List<Posicao> posicoesAmeacadasPorJogador(Jogador jogador) {

	}
}
