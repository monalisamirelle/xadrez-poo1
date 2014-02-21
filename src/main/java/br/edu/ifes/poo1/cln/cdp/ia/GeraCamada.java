package br.edu.ifes.poo1.cln.cdp.ia;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifes.poo1.cln.cdp.CasaOcupadaException;
import br.edu.ifes.poo1.cln.cdp.JogadaInvalidaException;

public class GeraCamada implements Runnable {

	// Captura o primeiro nó
	private int comecoElementos;
	// Captura o ultimo nó
	private int fimElementos;
	// Continua enquanto não acabar o tempo
	private boolean acabou = false;
	// Lista de nós atuais
	private List<NoArvore> listaNos;
	private List<NoArvore> novaListaNos = new ArrayList<NoArvore>();
	private GeraEstado geraEstado = new GeraEstado();

	/**
	 * Método construtor
	 * 
	 * @param comeco
	 * @param fim
	 * @param listaNos
	 */
	public GeraCamada(int comeco, int fim, List<NoArvore> listaNos) {
		this.comecoElementos = comeco;
		this.fimElementos = fim;
		this.listaNos = listaNos;
	}

	/**
	 * Método run
	 */
	public void run() {
		for (int indiceLista = comecoElementos; indiceLista < fimElementos; indiceLista++)
			// Se não acabou o tempo
			if (!acabou) {
				try {
					novaListaNos = geraFilhos(listaNos.get(indiceLista),
							novaListaNos);
				} catch (CasaOcupadaException | CloneNotSupportedException
						| JogadaInvalidaException e) {
					return;
				}
			} else
				return;
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
			listaEstados = geraEstado.proximosEstadosPossiveis(noPai
					.getEstado().getTabuleiro(), noPai.getCorNo());
			// Para cada estado da lista de estados
			for (Estado estado : listaEstados) {
				// Crie um nó que reconheça seu pai e armazene o estado
				NoArvore no = new NoArvore(noPai, estado);
				// Faça esse nó ser adicionado a nova lista de nós
				novaListaNos.add(no);
			}
		} else
			novaListaNos.add(noPai);
		return novaListaNos;
	}

	public void setAcabou() {
		acabou = true;
	}

}
