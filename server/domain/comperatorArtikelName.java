package domain;

import java.util.Comparator;

import valueobjects.Artikel;

	public class comperatorArtikelName implements Comparator<Artikel>{
		/**
		 * Methode, um nach Namen zu sortieren
		 */
		public comperatorArtikelName() {
			super();
		}
		@Override
		public int compare(Artikel arg0, Artikel arg1) {
			return arg0.getName().compareTo(arg1.getName());
		}
}
