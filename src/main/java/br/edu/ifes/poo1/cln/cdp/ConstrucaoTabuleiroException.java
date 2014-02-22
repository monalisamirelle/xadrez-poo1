package br.edu.ifes.poo1.cln.cdp;

/**
 * Indica problemas na construção de um tabuleiro.
 */
public class ConstrucaoTabuleiroException extends Exception {

	private static final long serialVersionUID = 1013300824524559282L;

	public ConstrucaoTabuleiroException(String mensagem, Exception causa) {
		super(mensagem, causa);
	}

}
