package shop.local.domain.exceptions;

public class ArtikelNichtVerfuegbarException extends Exception{
	private int artID;
	public ArtikelNichtVerfuegbarException(int artID){
		super("Artikel Nummer (" + artID + ") unbekannt.");
	}
}
