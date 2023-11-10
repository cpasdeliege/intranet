package be.cpasdeliege.intranet.cadastre.model;

import java.util.List;

public class Util {

	public static String getTypeMarche(List<TypeMarche> listeTypeMarche, String id_type_marche) {
		for (TypeMarche typeMarche : listeTypeMarche) {
			if (typeMarche.getId_type_marche().equals(id_type_marche)) {
				return typeMarche.getType_marche();
			}
		}
		return "";
	}

	public static String getIcone(String nomFichier) {
		String word = "images/cadastre/Word-icon.png";
		String excel = "images/cadastre/Excel-icon.png";
		String calc = "images/cadastre/libreoffice-calc-icon.png";
		String writer = "images/cadastre/libreoffice-writer-icon.png";
		String pdf = "images/cadastre/pdf.png";
		String autre = "images/cadastre/ark2.png";

		String[] tmp = nomFichier.split("\\p{Punct}");
		String extension = tmp[tmp.length - 1];
		if (extension.equalsIgnoreCase("doc") || extension.equalsIgnoreCase("docx")) {
			return word;
		} else if (extension.equalsIgnoreCase("xls") || extension.equalsIgnoreCase("xlsx")) {
			return excel;
		} else if (extension.equalsIgnoreCase("odt")) {
			return writer;
		} else if (extension.equalsIgnoreCase("ods")) {
			return calc;
		} else if (extension.equalsIgnoreCase("pdf")) {
			return pdf;
		} else {
			return autre;
		}
	}
}
