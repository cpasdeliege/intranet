package be.cpasdeliege.intranet.informatique.controler.formulaire;

import java.util.ArrayList;
import java.util.List;

public class FormulaireService {
	static final long serialVersionUID = 1;
	private List poles = null;
	private List departements = null;

	private String nom = null;
	private String adresse = null;
	private String numero = null;
	private String fax = null;
	private String localite = null;
	private String codePostal = null;
	private String remarque = null;
	private String emailService = "";
	private String localisation = "";

	public FormulaireService() {

		this.poles = new ArrayList<String>();
		this.departements = new ArrayList<String>();

		this.setNom("");
		this.setFax("04/220.");
		this.setAdresse("place St. Jacques");
		this.setNumero("13");
		this.setLocalite("Liège");
		this.setCodePostal("4000");
		this.setRemarque("");
		this.setEmailService("");
		this.setLocalisation("");
	}

	public List getPoles() {
		return poles;
	}

	public void setPoles(List poles) {
		this.poles = poles;
	}

	public void setDepartements(List departements) {
		this.departements = departements;
	}

	public List getDepartements() {
		return departements;
	}

	public String getEmailService() {
		return emailService;
	}

	public void setEmailService(String emailService) {
		this.emailService = emailService;
	}

	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	public String getRemarque() {
		return remarque;
	}

	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getLocalite() {
		return localite;
	}

	public void setLocalite(String localite) {
		this.localite = localite;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
