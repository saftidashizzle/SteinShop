package shop.local.domain;

import shop.local.valueobjects.Artikel;
import java.util.Comparator;

	public class comperatorArtikelName implements Comparator<Artikel>{
		public comperatorArtikelName() {
			super();
		}
		@Override
		public int compare(Artikel arg0, Artikel arg1) {
//			for (int i=0; i<arg0.getName().length();) {
//				// hier gehe ich den namen durch
//				for (int j=0; i<arg1.getName().length();) {
//					if (arg0.getName().charAt(i) > arg1.getName().charAt(j)) {
//						return 1;
//					} else {
//						return -1;
//					} 				
//				}
//				break;
//			}
//			return 0;
			return arg0.getName().compareTo(arg1.getName());
		}
}
