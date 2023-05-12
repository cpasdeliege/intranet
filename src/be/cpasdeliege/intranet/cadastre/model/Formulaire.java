package be.cpasdeliege.intranet.cadastre.model;

public class Formulaire {
	String id_formulaire = "";
	String nom = "";
	String path = "";

	public String getId_formulaire() {
		return id_formulaire;
	}

	public void setId_formulaire(String id_formulaire) {
		this.id_formulaire = id_formulaire;
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
}
