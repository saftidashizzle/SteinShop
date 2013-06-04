package shop.local.domain.exceptions;
/**
 * Exception, die eine Fehleingabe des zu löschenden Mitarbeiters mitteilt
 */
public class MitarbeiterLoeschenFehlerException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public MitarbeiterLoeschenFehlerException() {
		super("Zu löschender Mitarbeiter nicht vorhanden");
	}
}
