package shop.local.domain.exceptions;
/**
 * Exception, die sagt, dass der Artikel nur in Packungen mit X Anzahl verfügbar ist
 * und nicht einzeln verkauft wird
 */
public class ArtikelNurInEinheitenVerfügbarException extends Exception {

	private int packungsgroesse;
	
	public ArtikelNurInEinheitenVerfügbarException(int packungsgroesse) {
		super("Dieser Artikel ist nur in Einheiten von " + packungsgroesse + " verfuegbar.");
	}
}
