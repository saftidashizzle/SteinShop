package shop.local.domain.exceptions;

public class LoginFehlgeschlagenException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginFehlgeschlagenException() {
		super("Falscher Benutzername oder falsches Passwort.");
	}
}
