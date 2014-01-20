package br.edu.ifes.poo1.cln.cdp;

public class IARandomica extends Maquina {

	public IARandomica(String nome, CorJogador cor) {
		super(nome, cor);
	}

	/**
	 * Método que irá definir uma jogada para ser realizada pela máquina
	 * 
	 * @return
	 * @throws CasaOcupadaException
	 * @throws CloneNotSupportedException
	 * @throws JogadaInvalidaException
	 */
	public Jogada escolherJogada(Tabuleiro tabuleiroAtual)
			throws CasaOcupadaException, JogadaInvalidaException,
			CloneNotSupportedException {
		return tabuleiroAtual.recomendaJogada(this.cor);
	}
}
