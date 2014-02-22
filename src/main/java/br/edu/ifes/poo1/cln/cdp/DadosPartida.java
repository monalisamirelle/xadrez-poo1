package br.edu.ifes.poo1.cln.cdp;

import java.io.Serializable;
import java.text.Collator;
import java.util.GregorianCalendar;

import br.edu.ifes.poo1.cln.cgt.AplJogo;

public class DadosPartida implements Serializable, Comparable<DadosPartida> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Armazena um jogo */
	private AplJogo jogo;
	
	/** Armazena a data de criação de uma partida */
	private GregorianCalendar dataCriacaoPartida;
	
	/** Armazena a data de registro pra uma partida */
	private GregorianCalendar dataRegistroPartida;

	public DadosPartida(){};
	
	public DadosPartida(AplJogo apl) {
		this.jogo = apl;
		this.dataCriacaoPartida = apl.getDataCriacao();
		this.dataRegistroPartida = new GregorianCalendar();
	}

	public AplJogo getJogo() {
		return jogo;
	}

	public GregorianCalendar getDataInicioPartida() {
		return dataCriacaoPartida;
	}

	public GregorianCalendar getDataRegistroPartida() {
		return dataRegistroPartida;
	}

	@Override
	public int compareTo(DadosPartida o) {
		return Collator.getInstance().compare(this.jogo.getNomeVencedor(),
				o.jogo.getNomeVencedor());
	}

}
