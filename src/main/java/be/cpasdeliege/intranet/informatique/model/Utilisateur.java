package be.cpasdeliege.intranet.informatique.model;

public class Utilisateur {
	String login = "";
	String mdp = "";
	String nom = "";
	String prenom = "";
	boolean actif = false;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public void setActif(String actif) {
		if (actif.equals("true")) {
			this.actif = true;
		} else {
			this.actif = false;
		}
	}

}
