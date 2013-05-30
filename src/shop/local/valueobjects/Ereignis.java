package shop.local.valueobjects;

public class Ereignis {
	private User akteur;
	private int jahrestag;
	private Artikel artikel;
	private String aktion;
	private int anzahl;
	
	public Ereignis(User akteur, int jahrestag, Artikel artikel, int anzahl	, String aktion) {
		this.akteur = akteur;
		this.jahrestag = jahrestag;
		this.artikel = artikel;
		this.anzahl = anzahl;
		this.aktion = aktion;
	}
	public String toString() {
		return ("Datum: " + jahrestag + " User: " + akteur + " Artikel: " + artikel.getName() + " | Anzahl: " + anzahl + " | " + " Aktion: " + aktion);
	}
}
