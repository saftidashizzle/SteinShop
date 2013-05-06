package shop.local.valueobjects;
/**
 * Klasse User
 * 
 * @author philipp, kevin, jan
 *@version 0.0.0.1
 */


public class User {
	private String name;
	private String passwort;
	private boolean mitarbeiter;

	/** Konstruktor: wenn nur Name und Passwort übergeben werden, ist der boolean mitarbeiter per default auf false gesetzt. 
	 * 
	 * @param name Der Benutzername des neuen Users.
	 * @param passwort Das Passwort des neuen Users.
	 */
	public User(String name, String passwort) {
		this(name, passwort, false);
	}
	
	/** Konstruktor des Users.
	 * 
	 * @param name Der Benutzername des neuen Users.
	 * @param passwort Das Passwort des neuen Users.
	 * @param mitarbeiter Boolean: wenn true, dann Mitarbeiter, wenn false, dann Kunde.
	 */
	public User(String name, String passwort, boolean angestellt) {
		this.name = name;
		this.passwort = passwort;
		this.mitarbeiter = angestellt;
	}
	/** Gibt einen String zurück in dem Name und Mitarbeiter stehen.
	 * 
	 */
	public String toString() {
		return ("Name: " + name + " | Mitarbeiter: " + mitarbeiter);
		
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
	 * Getter für boolean angestellt
	 * @return Gibt den boolean angestellt zurück.
	 */
	public boolean getRang(){
		return mitarbeiter;
	}
}