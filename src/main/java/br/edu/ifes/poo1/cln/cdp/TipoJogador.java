package br.edu.ifes.poo1.cln.cdp;

public enum TipoJogador {
	PESSOA, IAELABORADA, IARANDOMICA;

	public String toString() {
		switch (this) {
		case PESSOA:
			return "PESSOA";
		case IAELABORADA:
			return "IAELABORADA";
		case IARANDOMICA:
			return "IARANDOMICA";
		default:
			return null;
		}
	}
}
