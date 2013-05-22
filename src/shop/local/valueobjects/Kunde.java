package shop.local.valueobjects;


public class Kunde extends User {
	private String strasseUndHausnr;
	private int plz;
	private String ort;
	private String land;
	private Warenkorb warenkorb = new Warenkorb();
	
	public Kunde(String name, String passwort, int nr, String anrede, String vorUndZuName, String strasse, int plz, String ort, String land) {
		super(name, passwort, nr, anrede, vorUndZuName);
		this.strasseUndHausnr = strasse;
		this.plz = plz;
		this.ort = ort;
		this.land = land;
	}
	public String toString() {
		return ("Kunde Nr: " + nr + " | Benutzername: " + name + " | Anrede: " + anrede + " | Name: " + vorUndZuName + " | Strasse und Nr: " + strasseUndHausnr + " | PLZ: " + plz + " | Ort: " + ort + " | Land: " + land);
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
	public String getAdresse() {
		return strasseUndHausnr;
	}
	/**
	 * Methode die die aktuelle Artikelliste zur�ckgibt
	 * @return die aktuelle Artikelliste.
	 */
	public Warenkorb getWarenkorb() {
		return warenkorb;
	}
}
