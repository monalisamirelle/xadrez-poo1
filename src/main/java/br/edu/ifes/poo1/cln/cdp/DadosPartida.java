package br.edu.ifes.poo1.cln.cdp;

import java.io.Serializable;
import java.util.Calendar;

import br.edu.ifes.poo1.cln.cgt.AplJogo;

public class DadosPartida implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AplJogo jogo;
	private String dataCriacaoPartida;
	private String dataRegistroPartida;

	public DadosPartida(AplJogo apl) {
		this.jogo = apl;
		this.dataCriacaoPartida = apl.getDataCriacao();
		this.dataRegistroPartida = Calendar.getInstance().getTime().toString();
	}

	public AplJogo getJogo() {
		return jogo;
	}

	public String getDataInicioPartida() {
		return dataCriacaoPartida;
	}

	public String getDataTerminoPartida() {
		return dataRegistroPartida;
	}

}
