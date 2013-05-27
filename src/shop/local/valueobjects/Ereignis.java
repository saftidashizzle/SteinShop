package shop.local.valueobjects;

public class Ereignis {
	private User akteur;
	private int jahrestag;
	private Artikel artikel;
	private String aktion;
	
	public Ereignis(User akteur, int jahrestag, Artikel artikel, int anzahl	, String aktion) {
		this.akteur = akteur;
		this.jahrestag = jahrestag;
		this.artikel = artikel;
		this.artikel.setMenge(anzahl);
		this.aktion = aktion;
	}
	public String toString() {
		return ("Datum: " + jahrestag + " User: " + akteur + " Artikel: " + artikel + " Aktion: " + aktion);
	}
}
