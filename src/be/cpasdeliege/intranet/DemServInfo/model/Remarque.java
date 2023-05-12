package be.cpasdeliege.intranet.DemServInfo.model;

public class Remarque {
	int idRemarque = 0;
	String idDemande = null;
	String texte = null;
	String user = null;
	String date = null;

	public int getIdRemarque() {
		return idRemarque;
	}

	public void setIdRemarque(int idRemarque) {
		this.idRemarque = idRemarque;
	}

	public String getIdDemande() {
		return idDemande;
	}

	public void setIdDemande(String idDemande) {
		this.idDemande = idDemande;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
