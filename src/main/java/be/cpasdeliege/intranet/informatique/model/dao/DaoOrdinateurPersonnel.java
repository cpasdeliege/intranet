package be.cpasdeliege.intranet.informatique.model.dao;

public class DaoOrdinateurPersonnel {

	private String nom = "";
	private String prenom = "";
	private String ordinateur = "";

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getOrdinateur() {
		return ordinateur;
	}

	public void setOrdinateur(String ordinateur) {
		this.ordinateur = ordinateur;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

}
