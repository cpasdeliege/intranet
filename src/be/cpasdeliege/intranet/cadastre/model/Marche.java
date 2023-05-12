package be.cpasdeliege.intranet.cadastre.model;

public class Marche {

	String id_marche_public = "";
	String id_type_marche = "";
	String intitule = "";
	String reference = "";
	String adjudicataire = "";
	String adresse = "";
	String description = "";
	String date_debut = "";
	String date_fin = "";
	String date_BP = "";
	String typeMarche = "";
	String adjudicateur = "";
	String flag = "";
	String date_debutFormat = "";
	String date_finFormat = "";
	String date_BPFormat = "";

//	public String getDateDebutFormat() {
//		String[] tmp = date_debut.split("-");
//		return tmp[2] + "-" + tmp[1] + "-" + tmp[0];
//	}
//	public String getDateFinFormat() {
//		String[] tmp = date_fin.split("-");
//		return tmp[2] + "-" + tmp[1] + "-" + tmp[0];
//	}
//	public String getDateBPFormat() {
//		String[] tmp = date_BP.split("-");
//		return tmp[2] + "-" + tmp[1] + "-" + tmp[0];
//	}
	public String formatDate(String date) {
		String[] tmp = date.split("-");
		return tmp[2] + "-" + tmp[1] + "-" + tmp[0];
	}

	public String getDescriptionHtml() {
		return description.replaceAll("\\r\\n|\\r|\\n", "<br>");
	}

	public String getId_marche_public() {
		return id_marche_public;
	}

	public void setId_marche_public(String id_marche_public) {
		this.id_marche_public = id_marche_public;
	}

	public String getId_type_marche() {
		return id_type_marche;
	}

	public void setId_type_marche(String id_type_marche) {
		this.id_type_marche = id_type_marche;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getAdjudicataire() {
		return adjudicataire;
	}

	public void setAdjudicataire(String adjudicataire) {
		this.adjudicataire = adjudicataire;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(String date_debut) {
		this.date_debut = date_debut;
		this.date_debutFormat = formatDate(date_debut);
	}

	public String getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(String date_fin) {
		this.date_fin = date_fin;
		this.date_finFormat = formatDate(date_fin);
	}

	public String getTypeMarche() {
		return typeMarche;
	}

	public void setTypeMarche(String typeMarche) {
		this.typeMarche = typeMarche;
	}

	public String getAdjudicateur() {
		return adjudicateur;
	}

	public void setAdjudicateur(String adjudicateur) {
		this.adjudicateur = adjudicateur;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getDate_BP() {
		return date_BP;
	}

	public void setDate_BP(String date_BP) {
		this.date_BP = date_BP;
		this.date_BPFormat = formatDate(date_BP);
	}

	public String getDate_debutFormat() {
		return date_debutFormat;
	}

	public void setDate_debutFormat(String date_debutFormat) {
		this.date_debutFormat = date_debutFormat;
	}

	public String getDate_finFormat() {
		return date_finFormat;
	}

	public void setDate_finFormat(String date_finFormat) {
		this.date_finFormat = date_finFormat;
	}

	public String getDate_BPFormat() {
		return date_BPFormat;
	}

	public void setDate_BPFormat(String date_BPFormat) {
		this.date_BPFormat = date_BPFormat;
	}
}
