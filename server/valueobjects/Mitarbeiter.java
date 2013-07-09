package valueobjects;


public class Mitarbeiter extends User {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3012471213077037986L;
	/**
	 * Konstruktor: erzeugt einen Mitarbeiter
	 * @param name geerbt von der Klasse User
	 * @param passwort geerbt von der Klasse User
	 * @param nr geerbt von der Klasse User
	 * @param anrede geerbt von der Klasse User
	 * @param vorUndZuName geerbt von der Klasse User
	 */
	public Mitarbeiter(String name, char[] passwort, int nr, String anrede, String vorUndZuName) {
		super(name, passwort, nr, anrede, vorUndZuName);
	}
	/**
	 * Methode die einen String mit allen Mitarbeiterdaten ausgibt
	 */
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
	 * Methode die die Nummer des Userobjektes zurück gibt.
	 * @return Die zurückgegebene Nummer.
	 */
	public int getNummer() {
		return nr;
	}
}
