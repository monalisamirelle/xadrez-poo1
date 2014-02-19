package br.edu.ifes.poo1.cln.cdp;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;

public class DadosPessoa implements Comparable<DadosPessoa> {
	private String nomePessoa;
	private int partidasVencidas;
	private int partidasPerdidas;

	// TODO posso fazer isso?
	public DadosPessoa() {
	};

	public DadosPessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
		this.partidasVencidas = 0;
		this.partidasPerdidas = 0;
	}

	/**
	 * Método que ordena os jogadores em ordem alfabética
	 * 
	 * @param listaDesordenada
	 * @return
	 */
	@Override
	public int compareTo(DadosPessoa o) {
		return Collator.getInstance().compare(this.nomePessoa, o.nomePessoa);
	}

	public void setPartidasVencidas() {
		partidasVencidas++;
	}

	public void setPartidasPerdidas() {
		partidasPerdidas++;
	}

	public String getNome() {
		return nomePessoa;
	}

	public String getPartidasVencidas() {
		return Integer.toString(partidasVencidas);
	}

	public String getPartidasPerdidas() {
		return Integer.toString(partidasPerdidas);
	}

	/**
	 * Método capaz de gerar a lista pessoas contendo o nome da pessoa, suas
	 * partidas vencidas e suas partidas perdidas
	 * 
	 * @param listaPartidas
	 * @return
	 */
	public List<DadosPessoa> geraListaDadosPessoa(
			List<DadosPartida> listaPartidas) {
		// Cria uma lista de pessoas
		List<DadosPessoa> listaPessoas = geraListaPessoa(listaPartidas);

		// Para todas as partidas na lista de partidas
		for (DadosPartida partida : listaPartidas) {
			listaPessoas = pontuaPessoas(partida, listaPessoas);
		}
		return listaPessoas;
	}

	/**
	 * Método que cria uma list de pessoas sem pontuações
	 * 
	 * @param listaPartidas
	 * @return
	 */
	private List<DadosPessoa> geraListaPessoa(List<DadosPartida> listaPartidas) {
		List<DadosPessoa> listaPessoas = new ArrayList<DadosPessoa>();
		for (DadosPartida partida : listaPartidas) {
			listaPessoas = inserePessoa(listaPessoas, partida.getJogo()
					.getJogadorPretas().getNome());
			listaPessoas = inserePessoa(listaPessoas, partida.getJogo()
					.getJogadorBrancas().getNome());
		}
		return listaPessoas;
	}

	/**
	 * Método responsável por inserir um jogador na lista caso ele já não se
	 * encontre nela e nem seja uma IA
	 * 
	 * @param listaPessoas
	 * @param nomeCandidato
	 * @return
	 */
	private List<DadosPessoa> inserePessoa(List<DadosPessoa> listaPessoas,
			String nomeCandidato) {
		boolean existePessoa = false;
		// Exclui, primeiramente, se o jogador for uma IA
		if (ehHumano(nomeCandidato)) {
			for (DadosPessoa dadosPessoa : listaPessoas)
				if (dadosPessoa.nomePessoa.equals(nomeCandidato)) {
					existePessoa = true;
				}
			if (!existePessoa) {
				DadosPessoa dadosPessoa = new DadosPessoa(nomeCandidato);
				listaPessoas.add(dadosPessoa);
			}
		}
		return listaPessoas;
	}

	/**
	 * Verifica se o jogador é humano ou uma IA
	 * 
	 * @param nomeCandidato
	 * @return
	 */
	private boolean ehHumano(String nomeCandidato) {
		if (nomeCandidato.equals("CÉRBERO") || nomeCandidato.equals("DIONÍSIO")
				|| nomeCandidato.equals("ARES") || nomeCandidato.equals("ZEUS")
				|| nomeCandidato.equals("PROMETEU"))
			return false;
		return true;
	}

	/**
	 * Método responsável por dar pontos de derrota ou vitória a pessoas
	 * 
	 * @param partida
	 * @param listaPessoas
	 * @return
	 */
	private List<DadosPessoa> pontuaPessoas(DadosPartida partida,
			List<DadosPessoa> listaPessoas) {
		DadosPessoa jogadorBranco = null;
		DadosPessoa jogadorPreto = null;
		for (DadosPessoa pessoa : listaPessoas) {
			if (pessoa.getNome().equals(
					partida.getJogo().getJogadorBrancas().getNome()))
				jogadorBranco = pessoa;
			if (pessoa.getNome().equals(
					partida.getJogo().getJogadorPretas().getNome()))
				jogadorPreto = pessoa;
		}
		// Se o vencedor foi o jogador branco
		if (jogadorBranco.getNome().equals(partida.getJogo().getNomeVencedor())) {
			jogadorBranco.setPartidasVencidas();
			jogadorPreto.setPartidasPerdidas();
		}
		// Se o vencedor foi o jogador preto
		if (jogadorPreto.getNome().equals(partida.getJogo().getNomeVencedor())) {
			jogadorPreto.setPartidasVencidas();
			jogadorBranco.setPartidasPerdidas();
		}
		return listaPessoas;
	}
}
