package be.cpasdeliege.intranet.DemServInfo.model;

public class DemServInf {

	int idDemandes = 0;
	String idPlanning = "";
	String typeDemande = "";
	String description = "";
	String dateDemande = null;
	String dateExecSouhaitee = null;
	String dateExecPrevue = null;
	String dateExecSouhaiteeSecr = null;
	String dateExecSouhaiteeSecrConf = null;
	String dateExecEffective = null;
	String validationSecr = "none";
	String validationInfor = "none";
	String validationSecrConf = "none";
	String validationChef = "none";
	String nomChef = "";
	String prenomChef = "";
	String service = "";
	String dateExecSouhaiteeChef = null;
	String nom = "";
	String prenom = "";
	String titre = "";
	String typeDemandeAffichage = "";
	String remarqueEcheance = "";
	String enAttente = "0";

	public int getIdDemandes() {
		return idDemandes;
	}

	public void setIdDemandes(int idDemandes) {
		this.idDemandes = idDemandes;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDateDemande() {
		return dateDemande;
	}

	public void setDateDemande(String dateDemande) {
		this.dateDemande = dateDemande;
	}

	public String getDateExecSouhaiteeSecrConfAffichage() {
		if (dateExecSouhaiteeSecrConf == null) {
			return getDateExecPrevue();
		} else {
			return dateExecSouhaiteeSecrConf;
		}
	}

	public String getDateExecSouhaiteeSecrConf() {
		return dateExecSouhaiteeSecrConf;
	}

	public void setDateExecSouhaiteeSecrConf(String dateExecSouhaiteeSecrConf) {
		if (dateExecSouhaiteeSecrConf == null || dateExecSouhaiteeSecrConf.equals("")) {
			this.dateExecSouhaiteeSecrConf = null;
		} else {
			this.dateExecSouhaiteeSecrConf = dateExecSouhaiteeSecrConf;
		}
	}

	public String getDateExecSouhaitee() {
		return dateExecSouhaitee;
	}

	public void setDateExecSouhaitee(String dateExecSouhaitee) {
		if (dateExecSouhaitee == null || dateExecSouhaitee.equals("")) {
			this.dateExecSouhaitee = null;
		} else {
			this.dateExecSouhaitee = dateExecSouhaitee;
		}
	}

	public String getDateExecPrevueAffichage() {
		if (dateExecPrevue == null) {
			return getDateExecSouhaiteeSecr();
		} else {
			return dateExecPrevue;
		}
	}

	public String getDateExecPrevue() {
		return dateExecPrevue;
	}

	public void setDateExecPrevue(String dateExecPrevue) {
		if (dateExecPrevue == null || dateExecPrevue.equals("")) {
			this.dateExecPrevue = null;
		} else {
			this.dateExecPrevue = dateExecPrevue;
		}
	}

	public String getDateExecSouhaiteeSecrAffichage() {
		if (dateExecSouhaiteeSecr == null) {
			return getDateExecSouhaiteeChef();
		} else {
			return dateExecSouhaiteeSecr;
		}
	}

	public String getDateExecSouhaiteeSecr() {
		return dateExecSouhaiteeSecr;
	}

	public void setDateExecSouhaiteeSecr(String dateExecSouhaiteeSecr) {
		if (dateExecSouhaiteeSecr == null || dateExecSouhaiteeSecr.equals("")) {
			this.dateExecSouhaiteeSecr = null;
		} else {
			this.dateExecSouhaiteeSecr = dateExecSouhaiteeSecr;
		}
	}

	public String getDateExecEffective() {
		return dateExecEffective;
	}

	public void setDateExecEffective(String dateExecEffective) {
		if (dateExecEffective == null || dateExecEffective.equals("")) {
			this.dateExecEffective = null;
		} else {
			this.dateExecEffective = dateExecEffective;
		}
	}

	public String getValidationSecr() {
		return validationSecr;
	}

	public void setValidationSecr(String validationSecr) {
		if (validationSecr == null) {
			this.validationSecr = "none";
		} else {
			this.validationSecr = validationSecr;
		}
	}

	public String getValidationInfor() {
		return validationInfor;
	}

	public void setValidationInfor(String validationInfor) {
		if (validationInfor == null) {
			this.validationInfor = "none";
		} else {
			this.validationInfor = validationInfor;
		}
	}

	public String getValidationSecrConf() {
		return validationSecrConf;
	}

	public void setValidationSecrConf(String validationSecrConf) {
		if (validationSecrConf == null) {
			this.validationSecrConf = "none";
		} else {
			this.validationSecrConf = validationSecrConf;
		}
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

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getTypeDemande() {
		return typeDemande;
	}

	public void setTypeDemande(String typeDemande) {
		setTypeDemandeAffichage(typeDemande.split("[(]")[0]);
		this.typeDemande = typeDemande;
	}

	public String getValidationChef() {
		return validationChef;
	}

	public void setValidationChef(String validationChef) {
		if (validationChef == null) {
			this.validationChef = "none";
		} else {
			this.validationChef = validationChef;
		}
	}

	public String getNomChef() {
		return nomChef;
	}

	public void setNomChef(String nomChef) {
		this.nomChef = nomChef;
	}

	public String getPrenomChef() {
		return prenomChef;
	}

	public void setPrenomChef(String prenomChef) {
		this.prenomChef = prenomChef;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getDateExecSouhaiteeChefAffichage() {
		if (dateExecSouhaiteeChef == null) {
			return dateExecSouhaitee;
		} else {
			return dateExecSouhaiteeChef;
		}
	}

	public String getDateExecSouhaiteeChef() {
		return dateExecSouhaiteeChef;
	}

	public void setDateExecSouhaiteeChef(String dateExecSouhaiteeChef) {
		if (dateExecSouhaiteeChef == null || dateExecSouhaiteeChef.equals("")) {
			this.dateExecSouhaiteeChef = null;
		} else {
			this.dateExecSouhaiteeChef = dateExecSouhaiteeChef;
		}
	}

	public String getTypeDemandeAffichage() {
		return typeDemandeAffichage;
	}

	public void setTypeDemandeAffichage(String typeDemandeAffichage) {
		this.typeDemandeAffichage = typeDemandeAffichage;
	}

	public String getIdPlanning() {
		return idPlanning;
	}

	public void setIdPlanning(String idPlanning) {
		this.idPlanning = idPlanning;
	}

	public String getRemarqueEcheance() {
		return remarqueEcheance;
	}

	public void setRemarqueEcheance(String remarqueEcheance) {
		this.remarqueEcheance = remarqueEcheance;
	}

	public String getEnAttente() {
		return enAttente;
	}

	public void setEnAttente(String enAttente) {
		this.enAttente = enAttente;
	}
}
