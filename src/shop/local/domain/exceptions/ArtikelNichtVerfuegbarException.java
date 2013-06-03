package shop.local.domain.exceptions;
/**
 * Exceptions, die sagt, dass die eingegebene Artikelnummer (Artikel ID) nicht existiert
 */
public class ArtikelNichtVerfuegbarException extends Exception{
	private int artID;
	public ArtikelNichtVerfuegbarException(int artID){
		super("Artikel Nummer (" + artID + ") unbekannt.");
	}
}
