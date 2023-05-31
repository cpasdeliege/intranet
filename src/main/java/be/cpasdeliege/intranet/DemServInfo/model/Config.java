package be.cpasdeliege.intranet.DemServInfo.model;

public class Config {

	String login = "";
	String nom = "";
	String prenom = "";

	public Config() {

	}

	public Config(String login) {
		super();
		this.login = login;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
}
