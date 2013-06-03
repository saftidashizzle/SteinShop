package shop.local.domain.exceptions;

public class ArtikelMengeReichtNichtException extends Exception {
	/**
	 * Exception, die sagt, dass die gew�nschte Artikelmenge nicht vorhanden ist
	 */
	private static final long serialVersionUID = 1L;

	public ArtikelMengeReichtNichtException(int gewollteMenge, int vorhandeneMenge) {
		super("Die gew�nschte Menge ist nicht vorhanden. Gew�nschte Menge: " + gewollteMenge + " Verf�gbare Menge: " + vorhandeneMenge);
	}
}
