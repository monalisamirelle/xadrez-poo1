package br.edu.ifes.poo1.cln.cdp;

import java.io.Serializable;
import java.util.Calendar;

import br.edu.ifes.poo1.cln.cgt.AplJogo;

public class DadosPartida implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dataPartida;
	private AplJogo jogo;


	public DadosPartida(AplJogo jogo) {
		this.jogo = jogo;
		this.dataPartida = Calendar.getInstance().getTime().toString();
	}

	public String getDataPartida() {
		return dataPartida;
	}

	public AplJogo getJogo() {
		return jogo;
	}

}
