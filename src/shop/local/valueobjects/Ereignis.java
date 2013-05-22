package shop.local.valueobjects;

public class Ereignis {
	private User akteur;
	private int jahrestag;
	private Artikel artikel;
	private int anzahl;
	private String aktion;
	
//	public Ereignis(String titel, int menge) {
//	// nur noch hinkriegen das ich aus den uebergebenen parametern die attribute einstelle und gut ist.	
//	// optimal: User akteur, int jahrestag, Artikel artikel, int anzahl	
//	}
//	public Ereignis(int nummer, int anzahl) {
//		
//	}
	public Ereignis(User akteur, int jahrestag, Artikel artikel, int anzahl	, String aktion) {
		this.akteur = akteur;
		this.jahrestag = jahrestag;
		this.artikel = artikel;
		this.anzahl = anzahl;
		this.aktion = aktion;
	}
	public String toString() {
		return ("User: " + akteur + " | Jahrestag: " + jahrestag + " | Artikel: " + artikel + " | Anzahländerung: " + anzahl + " | Aktion: " + aktion);
//		return ("User Nr: " + akteur.getNummer() + "| User Name: " + akteur.getName() + "| Jahrestag: " + jahrestag + "| Artikel Nr: " + artikel.getNummer() + " | Artikel Name: " + artikel.getName() + " | Anzahl: " + anzahl);
		
	}
}
