package shop.local.domain.exceptions;
/**
 * Exception, die eine Fehleingabe des zu l�schenden Mitarbeiters mitteilt
 */
public class MitarbeiterLoeschenFehlerException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public MitarbeiterLoeschenFehlerException() {
		super("Zu l�schender Mitarbeiter nicht vorhanden");
	}
}
