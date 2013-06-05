package shop.local.valueobjects;


public class Kunde extends User{
	private String strasseUndHausnr;
	private int plz;
	private String ort;
	private String land;
	private Warenkorb warenkorb = new Warenkorb();
	/**
	 * Konstruktor: erzeugt einen Kunden mit Strasse, Hausnr, PLZ, Ort und Land
	 * @param name geerbt von der Klasse User
	 * @param passwort geerbt von der Klasse User
	 * @param nr BenutzerID geerbt von der Klasse User
	 * @param anrede geerbt von der Klasse User
	 * @param vorUndZuName geerbt von der Klasse User
	 * @param strasse
	 * @param plz
	 * @param ort
	 * @param land
	 */
	public Kunde(String name, String passwort, int nr, String anrede, String vorUndZuName, String strasse, int plz, String ort, String land) {
		super(name, passwort, nr, anrede, vorUndZuName);
		this.strasseUndHausnr = strasse;
		this.plz = plz;
		this.ort = ort;
		this.land = land;
	}
	/**
	 * Methode, die einen String mit allen angegebenen Kundendaten ausgibt
	 */
	public String toString() {
		return ("Kunde Nr: " + nr + " | Benutzername: " + name + " | Anrede: " + anrede + " | Name: " + vorUndZuName + " | Strasse und Nr: " + strasseUndHausnr + " | PLZ: " + plz + " | Ort: " + ort + " | Land: " + land);
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
	/**
	 * Methode die die Strasse und die Hausnr des Nutzers zurückgibt.
	 * @return die Strasse und Hausnr
	 */
	public String getAdresse() {
		return strasseUndHausnr;
	}
	/**
	 * Methode die den aktuellen Warenkorb zurückgibt
	 * @return den aktuellen Warenkorb.
	 */
	public Warenkorb getWarenkorb() {
		return warenkorb;
	}
}
