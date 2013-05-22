package shop.local.domain.exceptions;

public class ArtikelMengeReichtNichtException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArtikelMengeReichtNichtException(int gewollteMenge, int vorhandeneMenge) {
		super("Die gewünschte Menge ist nicht vorhanden. Gewünschte Menge: " + gewollteMenge + " Verfügbare Menge: " + vorhandeneMenge);
	}
}
