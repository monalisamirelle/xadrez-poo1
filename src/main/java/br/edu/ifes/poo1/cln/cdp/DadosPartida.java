package br.edu.ifes.poo1.cln.cdp;

import java.io.Serializable;
import java.util.GregorianCalendar;

import br.edu.ifes.poo1.cln.cgt.AplJogo;

// FIXME está para ser depreciado
public class DadosPartida implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AplJogo jogo;
	private GregorianCalendar dataCriacaoPartida;
	private GregorianCalendar dataRegistroPartida;

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

}
