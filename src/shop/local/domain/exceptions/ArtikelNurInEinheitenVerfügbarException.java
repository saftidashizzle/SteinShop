package shop.local.domain.exceptions;

public class ArtikelNurInEinheitenVerfügbarException extends Exception {

	private int packungsgroesse;
	
	public ArtikelNurInEinheitenVerfügbarException(int packungsgroesse) {
		super("Dieser Artikel ist nur in Einheiten von " + packungsgroesse + " verfuegbar.");
	}
}
