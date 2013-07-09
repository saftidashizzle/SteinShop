package domain;

import java.util.Comparator;

import valueobjects.Artikel;

public class comperatorArtikelNummer implements Comparator<Artikel> {
	/**
	 * Methode, um nach Nummern zu sortieren
	 */
	public comperatorArtikelNummer() {
		super();
	}
	public int compare(Artikel arg0, Artikel arg1) {
		if (arg0.getNummer() > arg1.getNummer()) {
			return 1;
		} else {
			if (arg0.getNummer() == arg1.getNummer()){
				return 0;
			} else {
				return -1;
			}
		} 	
	}
}
