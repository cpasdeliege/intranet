package be.cpasdeliege.intranet.informatique.model;

public class AccesInternet {
	private String nom = "";
	private String prenom = "";
	private String email = "";
	private String mdpEmail = "";
	private String aliasEmail = "";
	private String loginPublilink = "";
	private String mdpPublilink = "";

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMdpEmail() {
		return mdpEmail;
	}

	public void setMdpEmail(String mdpEmail) {
		this.mdpEmail = mdpEmail;
	}

	public String getAliasEmail() {
		return aliasEmail;
	}

	public void setAliasEmail(String aliasEmail) {
		this.aliasEmail = aliasEmail;
	}

	public String getLoginPublilink() {
		return loginPublilink;
	}

	public void setLoginPublilink(String loginPublilink) {
		this.loginPublilink = loginPublilink;
	}

	public String getMdpPublilink() {
		return mdpPublilink;
	}

	public void setMdpPublilink(String mdpPublilink) {
		this.mdpPublilink = mdpPublilink;
	}

}
