package domain.exceptions;
/**
 * Exceptions, die sagt, dass die eingegebene Artikelnummer (Artikel ID) nicht existiert
 */
public class ArtikelNichtVerfuegbarException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6564810364191464127L;
	public ArtikelNichtVerfuegbarException(int artID){
		super("Artikel Nummer (" + artID + ") unbekannt.");
	}
}
