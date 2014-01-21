package br.edu.ifes.poo1.cln.cdp;

/**
 * Enumera os motivos pelos quais uma partida pode acabar.
 */
public enum MotivoFimDaPartida {
	VITORIA, EMPATE, DESISTENCIA, PAUSA;

	public String toString() {
		switch (this) {
		case VITORIA:
			return "VITORIA";
		case EMPATE:
			return "EMPATE";
		case DESISTENCIA:
			return "DESISTENCIA";
		case PAUSA:
			return "PAUSA";
		}
		return null;
	}

}
