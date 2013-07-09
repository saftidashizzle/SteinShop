package domain;

import java.util.Comparator;

import valueobjects.Artikel;
/**
 * Klasse zum sortieren nach ArtikelNamen.
 *
 */
	public class comperatorArtikelName implements Comparator<Artikel>{
		/**
		 *  Methode zum sortieren nach ArtikelNamen.
		 */
		public comperatorArtikelName() {
			super();
		}
		@Override
		public int compare(Artikel arg0, Artikel arg1) {
			return arg0.getName().compareTo(arg1.getName());
		}
}
