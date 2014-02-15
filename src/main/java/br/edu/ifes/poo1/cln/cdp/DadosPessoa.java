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

	public int getPartidasVencidas() {
		return partidasVencidas;
	}

	public int getPartidasPerdidas() {
		return partidasPerdidas;
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
		List<DadosPessoa> listaPessoas = new ArrayList<DadosPessoa>();
		// TODO está incluindo as IA's, mas isso pode ser modificado aqui gerando
		// uma lista que capture apenas as pessoas
		for(DadosPartida partida: listaPartidas){
			// Verifica se o jogador branco está na lista (se não estiver, o
			// insere)
			listaPessoas = inserePessoa(listaPessoas, partida
					.getJogo().getJogadorPretas().getNome());
			// Verifica se o jogador preto está na lista (se não estiver, o
			// insere)
			listaPessoas = inserePessoa(listaPessoas, partida
					.getJogo().getJogadorBrancas().getNome());
		}
		
		// Para todas as partidas na lista de partidas
		for (DadosPartida partida : listaPartidas) {
			// Se o nome do vencedor for igual ao nome do jogador branco
			if (partida.getJogo().getJogadorBrancas().getNome()
					.equals(partida.getJogo().getNomeVencedor())) {
				// Insere a vitória ao jogador branco
				listaPessoas = insereVitoriaDadosPessoa(listaPessoas, partida
						.getJogo().getJogadorBrancas().getNome());
				// Insere a derrota ao jogador preto
				listaPessoas = insereDerrotaDadosPessoa(listaPessoas, partida
						.getJogo().getJogadorPretas().getNome());
			}
			// Se o nome do vencedor for igual ao nome do jogador preto
			else if (partida.getJogo().getJogadorPretas().getNome()
					.equals(partida.getJogo().getNomeVencedor())) {
				// Insere a vitória ao jogador preto
				listaPessoas = insereVitoriaDadosPessoa(listaPessoas, partida
						.getJogo().getJogadorPretas().getNome());
				// Insere a derrota ao jogador branco
				listaPessoas = insereDerrotaDadosPessoa(listaPessoas, partida
						.getJogo().getJogadorBrancas().getNome());
			}
			// Se for um empate
			else {
				
			}
		}
		return listaPessoas;
	}

	/**
	 * Método responsável por inserir ou pontuar um jogador na lista com uma
	 * vitória
	 * 
	 * @param listaJogador
	 * @return
	 */
	private List<DadosPessoa> insereVitoriaDadosPessoa(
			List<DadosPessoa> listaPessoas, String nomeCandidato) {
		boolean existePessoa = false;
		for (DadosPessoa dadosPessoa : listaPessoas)
			if (dadosPessoa.nomePessoa.equals(nomeCandidato)) {
				dadosPessoa.setPartidasVencidas();
				existePessoa = true;
			}
		if (!existePessoa) {
			DadosPessoa dadosPessoa = new DadosPessoa(nomeCandidato);
			dadosPessoa.setPartidasVencidas();
			listaPessoas.add(dadosPessoa);
		}
		return listaPessoas;
	}

	/**
	 * Método responsável por inserir ou pontuar um jogador na lista com uma
	 * derrota
	 * 
	 * @param listaJogador
	 * @return
	 */
	private List<DadosPessoa> insereDerrotaDadosPessoa(
			List<DadosPessoa> listaPessoas, String nomeCandidato) {
		boolean existePessoa = false;
		for (DadosPessoa dadosPessoa : listaPessoas)
			if (dadosPessoa.nomePessoa.equals(nomeCandidato)) {
				dadosPessoa.setPartidasPerdidas();
				existePessoa = true;
			}
		if (!existePessoa) {
			DadosPessoa dadosPessoa = new DadosPessoa(nomeCandidato);
			dadosPessoa.setPartidasPerdidas();
			listaPessoas.add(dadosPessoa);
		}
		return listaPessoas;
	}

	/**
	 * Método responsável por inserir um jogador na lista
	 * @param listaPessoas
	 * @param nomeCandidato
	 * @return
	 */
	private List<DadosPessoa> inserePessoa(List<DadosPessoa> listaPessoas,
			String nomeCandidato) {
		boolean existePessoa = false;
		for (DadosPessoa dadosPessoa : listaPessoas)
			if (dadosPessoa.nomePessoa.equals(nomeCandidato)) {
				existePessoa = true;
			}
		if (!existePessoa) {
			DadosPessoa dadosPessoa = new DadosPessoa(nomeCandidato);
			listaPessoas.add(dadosPessoa);
		}
		return listaPessoas;
	}
}
