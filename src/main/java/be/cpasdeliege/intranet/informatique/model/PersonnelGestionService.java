package be.cpasdeliege.intranet.informatique.model;

import java.util.ArrayList;
import java.util.List;

public class PersonnelGestionService {
	private String service = "";
	private String nom = "";
	private String prenom = "";
	private String fonction = "";
	private String telephone = "";
	private String extension = "";
	private String rem = "";
	private List<String> ordinateur = new ArrayList<String>();

	public String getRem() {
		return rem;
	}

	public void setRem(String rem) {
		this.rem = rem;
	}

	public List<String> getOrdinateur() {
		return ordinateur;
	}

	public void setOrdinateur(List<String> ordinateur) {
		this.ordinateur = ordinateur;
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

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
}
