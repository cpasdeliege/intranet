package be.cpasdeliege.intranet.informatique.model.dao;

public class DaoPersonnelService {
	private String service = "";
	private String nom = "";
	private String prenom = "";
	private String fonction = "";
	private String telephone = "";
	private String rem = "";
	private String extension = "";
	private String id_personnel = "";

	public String getId_personnel() {
		return id_personnel;
	}

	public void setId_personnel(String idPersonnel) {
		id_personnel = idPersonnel;
	}

	public String getRem() {
		return rem;
	}

	public void setRem(String rem) {
		this.rem = rem;
	}

	public String getFonction() {
		return fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
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

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Override
	public boolean equals(Object obj) {
		if (!obj.getClass().getName().equals(this.getClass().getName())) {
			return false;
		} else if (!this.service.equals(((DaoPersonnelService) obj).getService())) {
			return false;
		} else if (!this.nom.equals(((DaoPersonnelService) obj).getNom())) {
			return false;
		} else if (!this.prenom.equals(((DaoPersonnelService) obj).getPrenom())) {
			return false;
		} else {
			return true;
		}
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

}
