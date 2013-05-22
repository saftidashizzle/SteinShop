package shop.local.domain;

import java.util.List;
import java.util.Vector;

import shop.local.valueobjects.Artikel;
import shop.local.valueobjects.Ereignis;
import shop.local.valueobjects.User;

public class EreignisVerwaltung {
	private List<Ereignis> protokoll = new Vector<Ereignis>();
	private int jahrestag;

	public void ereignisEinfuegen(User akteur, int nummer, Artikel derWars, int anzahl, String aktion) {
		Ereignis ereignis = new Ereignis(akteur, jahrestag, derWars, anzahl, aktion);
		protokoll.add(ereignis);
	}
	public List<Ereignis> gibProtokoll() {
		return protokoll;
	}

}
