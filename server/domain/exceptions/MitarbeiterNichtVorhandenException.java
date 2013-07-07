package domain.exceptions;
/**
 * Exception, die eine Fehleingabe des zu löschenden Mitarbeiters mitteilt
 */
public class MitarbeiterNichtVorhandenException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public MitarbeiterNichtVorhandenException() {
		super("Zu löschender Mitarbeiter nicht vorhanden");
	}
}
