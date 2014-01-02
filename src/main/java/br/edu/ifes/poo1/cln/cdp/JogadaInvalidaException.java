package br.edu.ifes.poo1.cln.cdp;

/**
 * Excessão lançada, quando uma jogada inválida é detectada.
 */
public class JogadaInvalidaException extends Exception {

	/**
	 * Constrói a excessão associando uma mensagem com o motivo do lançamento.
	 * 
	 * @param mensagem
	 *            Motivo pelo qual a excessão foi lançada.
	 */
	public JogadaInvalidaException(String mensagem) {
		super(mensagem);
	}

	/** Número de série, gerado automaticamente. */
	private static final long serialVersionUID = -3256365185119580367L;

}
