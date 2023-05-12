package be.cpasdeliege.intranet.cadastre.model;

public class Annexe {

	String id_annexe = "";
	String id_marche_public = "";
	String nom = "";
	String path = "";

	public String getId_annexe() {
		return id_annexe;
	}

	public void setId_annexe(String id_annexe) {
		this.id_annexe = id_annexe;
	}

	public String getId_marche_public() {
		return id_marche_public;
	}

	public void setId_marche_public(String id_marche_public) {
		this.id_marche_public = id_marche_public;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getExtension() {
		return nom.substring(nom.length() - 4);
	}
}
