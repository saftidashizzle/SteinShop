package domain.exceptions;

public class MitarbeiterNichtVorhandenException extends Exception {
	/**
	 * Exception, die eine Fehleingabe des zu l�schenden Mitarbeiters mitteilt
	 */
	private static final long serialVersionUID = 1L;

	public MitarbeiterNichtVorhandenException(int nr) {
		super("Zu l�schender Mitarbeiter" + nr + " nicht vorhanden");
	}
}
