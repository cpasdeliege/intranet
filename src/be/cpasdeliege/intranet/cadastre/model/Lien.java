package be.cpasdeliege.intranet.cadastre.model;

public class Lien {

	String id_lien = "";
	String id_marche_public = "";
	String nom = "";
	String lien = "";

	public String getId_lien() {
		return id_lien;
	}

	public void setId_lien(String id_lien) {
		this.id_lien = id_lien;
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

	public String getLien() {
		return lien;
	}

	public void setLien(String lien) {
		this.lien = lien;
	}

}
