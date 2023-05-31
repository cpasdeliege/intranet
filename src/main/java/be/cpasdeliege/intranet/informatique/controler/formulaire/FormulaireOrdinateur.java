package be.cpasdeliege.intranet.informatique.controler.formulaire;

public class FormulaireOrdinateur {

	private String nom = null;
	private String systemeExploitation = null;
	private String carteMere = null;
	private String processeur = null;
	private String memoireRam = null;
	private String disqueDur = null;
	private String carteReseau = null;
	private String carteGraphique = null;
	private String service = null;
	private String prise = null;
	private String ecran = null;
	private String graveur = null;
	private String numCpas2000 = "";
	private String actif = "";

	public FormulaireOrdinateur() {
		this.setNom("");
		this.setSystemeExploitation("");
		this.setCarteMere("");
		this.setProcesseur("");
		this.setMemoireRam("");
		this.setDisqueDur("");
		this.setCarteReseau("");
		this.setCarteGraphique("");
		this.setService("");
		this.setPrise("");
		this.setEcran("");
		this.setGraveur("");
		this.setNumCpas2000("");
		this.setActif("");
	}

	public String getNumCpas2000() {
		return numCpas2000;
	}

	public void setNumCpas2000(String numCpas2000) {
		this.numCpas2000 = numCpas2000;
	}

	public String getEcran() {
		return ecran;
	}

	public void setEcran(String ecran) {
		this.ecran = ecran;
	}

	public String getGraveur() {
		return graveur;
	}

	public void setGraveur(String graveur) {
		this.graveur = graveur;
	}

	public String getPrise() {
		return prise;
	}

	public void setPrise(String prise) {
		this.prise = prise;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getCarteGraphique() {
		return carteGraphique;
	}

	public void setCarteGraphique(String carteGraphique) {
		this.carteGraphique = carteGraphique;
	}

	public String getCarteMere() {
		return carteMere;
	}

	public void setCarteMere(String carteMere) {
		this.carteMere = carteMere;
	}

	public String getCarteReseau() {
		return carteReseau;
	}

	public void setCarteReseau(String carteReseau) {
		this.carteReseau = carteReseau;
	}

	public String getDisqueDur() {
		return disqueDur;
	}

	public void setDisqueDur(String disqueDur) {
		this.disqueDur = disqueDur;
	}

	public String getMemoireRam() {
		return memoireRam;
	}

	public void setMemoireRam(String memoireRam) {
		this.memoireRam = memoireRam;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom.toLowerCase();
	}

	public String getProcesseur() {
		return processeur;
	}

	public void setProcesseur(String processeur) {
		this.processeur = processeur;
	}

	public String getSystemeExploitation() {
		return systemeExploitation;
	}

	public void setSystemeExploitation(String systemeExploitation) {
		this.systemeExploitation = systemeExploitation;
	}

	public String getActif() {
		return actif;
	}

	public void setActif(String actif) {
		if (actif == null) {
			this.actif = "0";
		} else if (actif.equals("on")) {
			this.actif = "1";
		} else {
			this.actif = actif;
		}
	}

}
