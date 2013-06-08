package shop.local.valueobjects;


import java.io.Serializable;
import java.util.Date;

	
	public class Ereignis implements Serializable {
		private User akteur;
		private Date datum;
		private Artikel artikel;
		private String aktion;
		private int anzahl;
		/**
		 * Konstruktor: erzeugt ein Ereignis
		 * @param akteur Benutzer
		 * @param datum
		 * @param artikel
		 * @param anzahl 
		 * @param aktion Ob hinzugefügt oder abgezogen
		 */
		
		public Ereignis(User akteur, Artikel artikel, int anzahl	, String aktion) {
			this.akteur = akteur;
			this.datum = new Date();
			this.artikel = artikel;
			this.anzahl = anzahl;
			this.aktion = aktion;
		}
		public Date getDate() {
			return this.datum;
		}
		/**
		 * Methode die die bisher angefallenen Ereignisse in einem String übergibt/ausgibt
		 */
		public String toString() {
			return ("Datum: " + this.datum + " User: " + akteur + " Artikel: " + artikel.getName() + " | Anzahl: " + anzahl + " | " + " Aktion: " + aktion);
		}
}
