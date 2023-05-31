package be.cpasdeliege.intranet.utils;

import java.lang.reflect.Field;
import java.util.List;

import be.cpasdeliege.intranet.informatique.model.Tache;
import be.cpasdeliege.intranet.informatique.model.TacheListe;

import java.text.Normalizer;

public class Utils {

	public static String replaceAccentedCharacters(String s) {
		s = Normalizer.normalize(s, Normalizer.Form.NFD);
		s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
		return s;
    }

	public static String formatDateAffichage(String date) {
		String[] tmp = date.split("-");
		return tmp[2] + "-" + tmp[1] + "-" + tmp[0];
	}

	public static boolean checkMDP(String mdp) {
		return false;
	}

	public static String toCSV(List list, String[] params) {

		int size = list.size();
		String csv = "";

		// on �crit le header
		if (size > 0) {
			TacheListe o = (TacheListe) list.get(0);
			Class c = o.getClass();

			for (int i = 1; i <= params.length; i++) {
				String name = params[i - 1];
				csv += name;
				if (i != params.length) {
					csv += ";";
				}
			}
			csv += "\n";
		}

		for (int i = 0; i < size; i++) {
			Object o = list.get(i);
			Class c = o.getClass();

			for (int j = 1; j <= params.length; j++) {

				String name = params[j - 1];
				System.out.println("name: " + name);
				try {
					Field f = c.getDeclaredField(name);
					f.setAccessible(true);
					Object v = f.get(o);
					System.out.println("value : " + v);
					if (v != null) {
						csv += v.toString();
						if (j != params.length) {
							csv += ";";
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Exception: " + e.getMessage());
				}
			}
			csv += "\n";
		}
		return csv;
	}
}
