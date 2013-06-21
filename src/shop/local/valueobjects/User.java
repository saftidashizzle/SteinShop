package shop.local.valueobjects;

import java.io.Serializable;


/**
 * Klasse User
 * 
 * @author philipp, kevin, jan
 *@version 0.0.0.1
 */


public abstract class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -202117093157771368L;
	protected String name;
	protected String passwort;
	protected int nr;
	protected String vorUndZuName;
	protected String anrede;
	

	/** Konstruktor: wenn nur Name und Passwort �bergeben werden, ist der boolean mitarbeiter per default auf false gesetzt. 
	 * 
	 * @param name Der Benutzername des neuen Users.
	 * @param passwort Das Passwort des neuen Users.
	 * @param vorUndZuName2 
	 * @param anrede2 
	 */
	public User(String name, String passwort, int nr, String anrede, String vorUndZuName) {
		this.name = name;
		this.passwort = passwort;
		this.nr = nr;
		this.anrede = anrede;
		this.vorUndZuName = vorUndZuName;
	}

	/** Gibt einen String zur�ck in dem Name und Mitarbeiter stehen.
	 * 
	 */
	public String toString() {
		return null;
	}
	/**
	 * Getter f�r den Namen.
	 * @return gibt den Namen des Benutzerobjekts zur�ck.
	 */
	public String getName(){
		return name;
	}
	/**
	 * Getter f�r das Passwort
	 * @return gibt das Passwort des Benutzerobjekts zur�ck.
	 */
	public String getPasswort(){
		return passwort;
	}
	/** 
	 * Methode die die Nummer des Userobjektes zur�ck gibt.
	 * @return Die zur�ckgegebene Nummer.
	 */
	public int getNummer() {
		return nr;
	}
	/**
	 * Methode die einen leeren String zur�ck gibt
	 * @return
	 */
	public String getAdresse() {
		return null;
	}

	public String getAnrede() {
		return anrede;
	}

	public String getVorUndZuName() {
		return vorUndZuName;
	}
}