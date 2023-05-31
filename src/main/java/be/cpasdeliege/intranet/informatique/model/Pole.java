package be.cpasdeliege.intranet.informatique.model;

public class Pole {
	private long id_pole;
	private String nom = null;

	public void setId_pole(long id_pole) {
		this.id_pole = id_pole;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public long getId_pole() {
		return id_pole;
	}

	public String getNom() {
		return nom;
	}
}
