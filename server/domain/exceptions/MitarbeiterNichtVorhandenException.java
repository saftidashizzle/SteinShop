package domain.exceptions;

public class MitarbeiterNichtVorhandenException extends Exception {
	/**
	 * Exception, die eine Fehleingabe des zu löschenden Mitarbeiters mitteilt
	 */
	private static final long serialVersionUID = 1L;

	public MitarbeiterNichtVorhandenException(int nr) {
		super("Zu löschender Mitarbeiter" + nr + " nicht vorhanden");
	}
}
