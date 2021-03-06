package domain.exceptions;

public class ArtikelNurInEinheitenVerf�gbarException extends Exception {
	/**
	 * Exception, die sagt, dass der Artikel nur in Packungen mit X Anzahl verf�gbar ist
	 * und nicht einzeln verkauft wird
	 */
	private static final long serialVersionUID = 5218106989297259947L;

	public ArtikelNurInEinheitenVerf�gbarException(int packungsgroesse) {
		super("Dieser Artikel ist nur in Einheiten von " + packungsgroesse + " verfuegbar.");
	}
}
