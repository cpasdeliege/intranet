package be.cpasdeliege.intranet.informatique.model;

public class Departement {
	private long id_departement;
	private String nom = null;

	public long getId_departement() {
		return id_departement;
	}

	public void setId_departement(long id_departement) {
		this.id_departement = id_departement;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
