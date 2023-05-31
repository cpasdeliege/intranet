package be.cpasdeliege.intranet.informatique.model;

public class Tache {
	int idPlanning = 0;
	String ordinateur = "";
	String personnel = "";
	String service = "";
	String echeance = "";
	String dateFin = "";
	String titre = "";
	String personnelInfo = "";
	String type = "";
	boolean dsi = false;
	String incident = "0";
	String heureIncident = "";
	String minuteIncident = "";
	/***** ***************** */
	int id_coordinateur = 0;
	int id_personnel = 0;

	boolean enAttente = false;

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

	public String getPersonnel() {
		return personnel;
	}

	public void setPersonnel(String personnel) {
		this.personnel = personnel;
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

	public String getPersonnelInfo() {
		return personnelInfo;
	}

	public void setPersonnelInfo(String personnelInfo) {
		this.personnelInfo = personnelInfo;
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

	public int getId_coordinateur() {
		return id_coordinateur;
	}

	public void setId_coordinateur(int id_coordinateur) {
		this.id_coordinateur = id_coordinateur;
	}

	public int getId_personnel() {
		return id_personnel;
	}

	public void setId_personnel(int id_personnel) {
		this.id_personnel = id_personnel;
	}

}
