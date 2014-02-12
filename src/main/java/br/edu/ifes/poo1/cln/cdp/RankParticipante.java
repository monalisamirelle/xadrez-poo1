package br.edu.ifes.poo1.cln.cdp;

public class RankParticipante {
	private String vencedorRank;
	private int partidasVencidas;

	public RankParticipante(String vencedor){
		this.vencedorRank = vencedor;
		this.partidasVencidas = 1;
	}
	
	public int getPartidasVencidas() {
		return partidasVencidas;
	}

	public String getVencedorRank() {
		return vencedorRank;
	}
	
	public void setPartidasVencidas(){
		partidasVencidas++;
	}
}
