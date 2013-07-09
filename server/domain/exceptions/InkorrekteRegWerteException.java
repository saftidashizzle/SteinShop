package domain.exceptions;

public class InkorrekteRegWerteException extends Exception {
	/**
	 * Exception, die sagt, dass die angegebenen Daten f�r den Login fehlschlugen
	 */
	private static final long serialVersionUID = 1L;

	public InkorrekteRegWerteException() {
	
	super("Falsche Werte bei der Registrierung. Wahrscheinlich ist die PLZ ung�ltig.");

	}
}