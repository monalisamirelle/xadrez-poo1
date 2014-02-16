package br.edu.ifes.poo1.cln.cdp.ia;

import br.edu.ifes.poo1.cln.cdp.Jogada;
import br.edu.ifes.poo1.cln.cdp.TabuleiroXadrez;
import br.edu.ifes.poo1.cln.cdp.TipoCorJogador;
import br.edu.ifes.poo1.cln.cdp.TipoJogador;

public class IARandomica extends Maquina {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IARandomica(String nome, TipoCorJogador cor) {
		super(nome, cor, TipoJogador.IARANDOMICA);
	}

	/**
	 * Método que irá definir uma jogada para ser realizada pela máquina
	 * 
	 * @return
	 */
	public Jogada escolherJogada(TabuleiroXadrez tabuleiroAtual) {
		return tabuleiroAtual.recomendaJogada(this.cor);
	}
}
