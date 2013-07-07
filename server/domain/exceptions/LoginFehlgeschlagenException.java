package domain.exceptions;
/**
 * Exception, die sagt, dass der eingegebene Benutzername oder das eingegebene 
 * Passwort falsch ist
 */
public class LoginFehlgeschlagenException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public LoginFehlgeschlagenException() {
		super("Falscher Benutzername oder falsches Passwort.");
	}
}
