package be.cpasdeliege.intranet.informatique.model.dao;

public class DaoDepartement {
	private long id_departement;
	private String nom;

	public void setId_departement(long id_departement) {
		this.id_departement = id_departement;
	}

	public long getId_departement() {
		return id_departement;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}
}
