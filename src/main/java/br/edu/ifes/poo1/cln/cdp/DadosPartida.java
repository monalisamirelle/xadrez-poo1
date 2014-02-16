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
	private AplJogo jogo;
	private GregorianCalendar dataCriacaoPartida;
	private GregorianCalendar dataRegistroPartida;

	// TODO posso fazer isso?
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

	public GregorianCalendar getDataTerminoPartida() {
		return dataRegistroPartida;
	}

	@Override
	public int compareTo(DadosPartida o) {
		return Collator.getInstance().compare(this.jogo.getNomeVencedor(),
				o.jogo.getNomeVencedor());
	}

}
