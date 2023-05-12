package be.cpasdeliege.intranet.informatique.model;

public class TacheListe {

	int idPlanning = 0;
	String ordinateur = "";
	String nom = "";
	String prenom = "";
	String nomInfo = "";
	String prenomInfo = "";
	String service = "";
	String echeance = "";
	String dateFin = "";
	String remarque = "";
	String travail = "";
	String titre = "";
	String type = "";
	boolean dsi = false;
	boolean enAttente = false;
	String incident = "0";
	String heureIncident = "";
	String minuteIncident = "";
	String idDemande = "";

	public boolean isEnAttente() {
		return enAttente;
	}

	public void setEnAttente(boolean enAttente) {
		this.enAttente = enAttente;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public int getIdPlanning() {
		return idPlanning;
	}

	public void setIdPlanning(int idPlanning) {
		this.idPlanning = idPlanning;
	}

	public String getOrdinateur() {
		return ordinateur;
	}

	public void setOrdinateur(String ordinateur) {
		this.ordinateur = ordinateur;
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

	public String getEcheance() {
		return echeance;
	}

	public void setEcheance(String echeance) {
		this.echeance = echeance;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public String getRemarque() {
		return remarque;
	}

	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}

	public String getTravail() {
		return travail;
	}

	public void setTravail(String travail) {
		this.travail = travail;
	}

	public String getNomInfo() {
		return nomInfo;
	}

	public void setNomInfo(String nomInfo) {
		this.nomInfo = nomInfo;
	}

	public String getPrenomInfo() {
		return prenomInfo;
	}

	public void setPrenomInfo(String prenomInfo) {
		this.prenomInfo = prenomInfo;
	}

	public String toString() {

		return idPlanning + " - " + titre + " " + nomInfo;
	}

	public boolean isDsi() {
		return dsi;
	}

	public void setDsi(boolean dsi) {
		this.dsi = dsi;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIncident() {
		return incident;
	}

	public void setIncident(String incident) {
		this.incident = incident;
	}

	public String getHeureIncident() {
		return heureIncident;
	}

	public void setHeureIncident(String heureIncident) {
		this.heureIncident = heureIncident;
	}

	public String getMinuteIncident() {
		return minuteIncident;
	}

	public void setMinuteIncident(String minuteIncident) {
		this.minuteIncident = minuteIncident;
	}

	public void setIdDemande(String idDemande) {
		this.idDemande = idDemande;
	}

	public String getIdDemande() {
		return idDemande;
	}
}
