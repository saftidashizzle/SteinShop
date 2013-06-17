package shop.local.domain;

import shop.local.valueobjects.Artikel;
import java.util.Comparator;

	public class comperatorArtikelName implements Comparator<Artikel>{
		public comperatorArtikelName() {
			super();
		}
		@Override
		public int compare(Artikel arg0, Artikel arg1) {
			return arg0.getName().compareTo(arg1.getName());
		}
}
