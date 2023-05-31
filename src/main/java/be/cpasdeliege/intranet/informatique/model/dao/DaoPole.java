package be.cpasdeliege.intranet.informatique.model.dao;

public class DaoPole {
	private long id_pole;
	private String nom;

	public void setId_pole(long id_pole) {
		this.id_pole = id_pole;
	}

	public long getId_pole() {
		return id_pole;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}
}
