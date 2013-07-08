package domain.exceptions;

public class UserIstSchonEingeloggtException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1516691880528497413L;

	public UserIstSchonEingeloggtException() {
		super("Es ist noch ein Benutzer mit diesen Daten eingeloggt.");
	}
}
