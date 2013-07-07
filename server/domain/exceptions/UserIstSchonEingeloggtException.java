package domain.exceptions;

public class UserIstSchonEingeloggtException extends Exception {

	public UserIstSchonEingeloggtException() {
		super("Es ist noch ein Benutzer mit diesen Daten eingeloggt.");
	}
}
