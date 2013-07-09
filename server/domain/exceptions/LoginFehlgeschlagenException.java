package domain.exceptions;

public class LoginFehlgeschlagenException extends Exception {
	/**
	 * Exception, die sagt, dass der eingegebene Benutzername oder das eingegebene 
	 * Passwort falsch ist
	 */
	private static final long serialVersionUID = 1L;

	public LoginFehlgeschlagenException() {
		super("Falscher Benutzername oder falsches Passwort.");
	}
}
