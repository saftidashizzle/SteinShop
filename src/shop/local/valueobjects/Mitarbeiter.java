package shop.local.valueobjects;

public class Mitarbeiter extends User {

	public Mitarbeiter(String name, String passwort, int nr, String anrede, String vorUndZuName) {
		super(name, passwort, nr, anrede, vorUndZuName);
	}

	public String toString() {
		return ("Mitarbeiter Nr: " + nr + " | " + "Benutzername: " + name + " | Anrede: " + anrede + " | Name: " + vorUndZuName);
	}
	/**
	 * Getter für den Namen.
	 * @return gibt den Namen des Benutzerobjekts zurück.
	 */
	public String getName(){
		return name;
	}
	/**
	 * Getter für das Passwort
	 * @return gibt das Passwort des Benutzerobjekts zurück.
	 */
	public String getPasswort(){
		return passwort;
	}
	/** 
	 * Methode die die Nummer des Userobjektes zurück gibt.
	 * @return Die zurückgegebene Nummer.
	 */
	public int getNummer() {
		return nr;
	}
}
