package shop.local.domain.exceptions;

public class ArtikelNurInEinheitenVerf�gbarException extends Exception {

	private int packungsgroesse;
	
	public ArtikelNurInEinheitenVerf�gbarException(int packungsgroesse) {
		super("Dieser Artikel ist nur in Einheiten von " + packungsgroesse + " verfuegbar.");
	}
}
