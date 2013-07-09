package domain.exceptions;

public class ArtikelNichtVerfuegbarException extends Exception{
	/**
	 * Exceptions, die sagt, dass die eingegebene Artikelnummer (Artikel ID) nicht existiert
	 */
	private static final long serialVersionUID = -6564810364191464127L;
	public ArtikelNichtVerfuegbarException(int artID){
		super("Artikel Nummer (" + artID + ") unbekannt.");
	}
}
