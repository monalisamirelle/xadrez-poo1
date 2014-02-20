package br.edu.ifes.poo1.cln.cdp.ia;

import java.util.List;
import java.util.Random;

import br.edu.ifes.poo1.cln.cdp.CasaOcupadaException;
import br.edu.ifes.poo1.cln.cdp.Jogada;
import br.edu.ifes.poo1.cln.cdp.JogadaInvalidaException;
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

	public IARandomica(TipoCorJogador cor) {
		super("", cor, TipoJogador.IARANDOMICA);
	}

	/**
	 * Método que irá definir uma jogada para ser realizada pela máquina
	 * 
	 * @return
	 */
	public Jogada escolherJogada(TabuleiroXadrez tabuleiroAtual) {
		// Criamos uma lista de estados possíveis
		List<Estado> estadosPossiveis = null;
		try {
			estadosPossiveis = tabuleiroAtual.getGeraEstado()
					.proximosEstadosPossiveis(tabuleiroAtual, this.cor);
		} catch (CasaOcupadaException | JogadaInvalidaException e) {
			return null;
		}
		Random random = new Random();
		if (!estadosPossiveis.isEmpty()) {
			return estadosPossiveis
					.get(random.nextInt(estadosPossiveis.size())).getJogada();
		}
		// Não há recomendação
		return null;
	}
}
