package br.edu.ifes.poo1.cln.cdp;

public class IARandomica extends Maquina {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IARandomica(String nome, TipoCorJogador cor) {
		super(nome, cor, TipoJogador.IARANDOMICA);
	}

	/**
	 * Método que irá definir uma jogada para ser realizada pela máquina
	 * 
	 * @return
	 */
	public Jogada escolherJogada(Tabuleiro tabuleiroAtual) {
		return tabuleiroAtual.recomendaJogada(this.cor);
	}
}
