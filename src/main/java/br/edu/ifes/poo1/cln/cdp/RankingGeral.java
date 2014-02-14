package br.edu.ifes.poo1.cln.cdp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifes.poo1.cgd.Arquivo;

public class RankingGeral {
	Arquivo<DadosPartida> arquivo = new Arquivo<DadosPartida>();

	/**
	 * Método que cria um rank com os vencedores
	 * 
	 * @return
	 */
	public List<RankParticipante> criaRankVencedores() {
		List<DadosPartida> listaPartida = new ArrayList<DadosPartida>();
		try {
			listaPartida = arquivo.leiaJogos();
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Não há jogos gravados\n");
			return new ArrayList<RankParticipante>();
		}
		List<RankParticipante> listaVencedor = new ArrayList<RankParticipante>();
		for (DadosPartida partida : listaPartida)
			if (partida.getJogo().getMotivoDeFinalizacao() == TipoSituacaoPartida.VITORIA
					|| partida.getJogo().getMotivoDeFinalizacao() == TipoSituacaoPartida.DESISTENCIA) {
				boolean jaExisteVencedor = false;
				for (RankParticipante vencedorRank : listaVencedor) {
					if (partida.getJogo().getVencedor()
							.equals(vencedorRank.getVencedorRank()))
						vencedorRank.setPartidasVencidas();
				}
				if (!jaExisteVencedor) {
					RankParticipante novoVencedor = new RankParticipante(
							partida.getJogo().getVencedor().getNome());
					listaVencedor.add(novoVencedor);
				}
			}
		listaVencedor = this.ordenaLista(listaVencedor);
		return listaVencedor;
	}

	/**
	 * Método que ordena os vencedores em rank
	 * 
	 * @param listaDesordenada
	 * @return
	 */
	// TODO método que ordena lista
	private List<RankParticipante> ordenaLista(
			List<RankParticipante> listaDesordenada) {
		List<RankParticipante> listaOrdenada = new ArrayList<RankParticipante>();
		return listaOrdenada;
	}

}
