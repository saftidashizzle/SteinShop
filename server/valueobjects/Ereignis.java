package valueobjects;


import java.io.Serializable;
import java.util.Date;

	
	public class Ereignis implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -8372776763681771875L;
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
		/**
		 * Methode die den Artikel liefert
		 * @return Der Artikel
		 */
		public Artikel getArtikel() {
			return this.artikel;
		}
		/**
		 * Methode id das Datum liefert
		 * @return Das Datum
		 */
		public Date getDate() {
			return this.datum;
		}
		/**
		 * Methode die den Usernamen liefert
		 * @return User akteur
		 */
		public User getUser() {
			return akteur;
		}
		/**
		 * Methode die die veränderte Anzahl liefert
		 * @return int Anzahl
		 */
		public int getMenge() {
			return anzahl;
		}
		/**
		 * Methode die die Aktion liefert
		 * @return String die Aktion
		 */
		public String getAktion() {
			return aktion;
		}
		/**
		 * Methode die die bisher angefallenen Ereignisse in einem String übergibt/ausgibt
		 */
		public String toString() {
			return ("Datum: " + this.datum + " User: " + akteur + " Artikel: " + artikel.getName() + " | Anzahl: " + anzahl + " | " + " Aktion: " + aktion);
		}
}
