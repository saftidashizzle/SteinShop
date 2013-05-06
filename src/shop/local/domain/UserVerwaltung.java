package shop.local.domain;

import java.util.List;
import java.util.Vector;

import shop.local.valueobjects.User;
/**
 * UserVerwaltung: Verwaltet die Userliste und f�gt neue User ein.
 * 
 * @author philipp, kevin, jan
 *
 */


public class UserVerwaltung {
	private List<User> userBestand = new Vector<User>();
	
	/**
	 * Methode um einen neuen User in die Liste einzuf�gen.
	 * @param einUser der neue Nutzer der �bergeben wird.
	 */
	public void einfuegen(User einUser) {
		userBestand.add(einUser);
	}
	/**
	 * Methode, die die aktuelle Liste von Benutzern zur�ck gibt.
	 * @return gibt die Liste userBestand zur�ck.
	 */
	public List<User> getUserBestand() {
		return userBestand;
	}

}
