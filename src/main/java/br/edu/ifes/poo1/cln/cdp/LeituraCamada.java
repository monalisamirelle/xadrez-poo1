package br.edu.ifes.poo1.cln.cdp;

import java.util.ArrayList;
import java.util.List;

public class LeituraCamada implements Runnable {

	private List<NoArvore> novaListaNos = new ArrayList<NoArvore>();
	private int comeco;
	private int fim;
	private List<NoArvore> listaNos;
	
	/**
	 * Método construtor
	 * @param comeco
	 * @param fim
	 * @param listaNos
	 */
	public LeituraCamada(int comeco, int fim, List<NoArvore> listaNos) {
		this.comeco = comeco;
		this.fim = fim;
		this.listaNos = listaNos;
	}

	/**
	 * Método run
	 */
	public void run() {
		for (int i = comeco; i < fim; i++)
			try {
				novaListaNos = geraFilhos(listaNos.get(i), novaListaNos);
			} catch (CasaOcupadaException | CloneNotSupportedException
					| JogadaInvalidaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public List<NoArvore> getNovaListaNos() {
		return novaListaNos;
	}

	/**
	 * Método que gera, dado um nó, os filhos desse nó
	 * 
	 * @param listaNos
	 * @param novaListaNos
	 * @return
	 * @throws CasaOcupadaException
	 * @throws CloneNotSupportedException
	 * @throws JogadaInvalidaException
	 */
	public List<NoArvore> geraFilhos(NoArvore noPai, List<NoArvore> novaListaNos)
			throws CasaOcupadaException, CloneNotSupportedException,
			JogadaInvalidaException {
		// Cria uma lista de tabuleiros que contém todos os estados possíveis a
		// serem gerados dado o nó pai
		List<Estado> listaEstados = new ArrayList<Estado>();
		// Se o nó pai não está em xeque-mate, devemos considerar seus filhos
		if (noPai.isXequeMate() == false) {
			// Crie uma lista de tabuleiros com todas as jogadas possíveis de
			// serem realizadas naquele tabuleiro
			listaEstados = noPai.getEstado().getTabuleiro()
					.proximosEstadosPossiveis(noPai.getCorNo());
			// Para cada estado da lista de estados
			for (Estado estado : listaEstados) {
				noPai.getCorNo();
				// Se o tabuleiro pertencente a esse estado não estiver em xeque
				// ou xeque mate. Exemplo: Se a preta acabou de mover, o jogo só
				// deve incluir os movimentos que não deixam o rei preto em
				// xeque ou xeque-mate
				if (!(estado.getTabuleiro().verificarXeque(noPai.getCorNo()) || estado
						.getTabuleiro().verificarXequeMate(noPai.getCorNo()))) {
					// Crie um nó que reconheça seu pai e armazene o estado
					NoArvore no = new NoArvore(noPai, estado);
					// Faça esse nó ser adicionado a nova lista de nós
					novaListaNos.add(no);
				}
			}
		} else
			novaListaNos.add(noPai);
		return novaListaNos;
	}
}
