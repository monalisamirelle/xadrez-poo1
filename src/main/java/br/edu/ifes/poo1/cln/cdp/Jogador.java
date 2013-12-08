package br.edu.ifes.poo1.cln.cdp;

import java.util.ArrayList;
import java.util.List;

/**
 * Representação de um jogador. Há no máximo 2 jogadores para o jogo de xadrez.
 * 
 * @author lucas
 * 
 */
public class Jogador {

	private String nome;
	List<Peca> pecasCapturadas = new ArrayList<Peca>();

	/**
	 * Constrói um jogador.
	 * 
	 * @param nome
	 *            Nome do jogador.
	 */
	public Jogador(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
