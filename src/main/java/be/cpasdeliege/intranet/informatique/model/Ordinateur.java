package be.cpasdeliege.intranet.informatique.model;

import java.util.ArrayList;

public class Ordinateur {
	static final long serialVersionUID = 1;
	private String id_ordinateur = null;
	private String nom = null;
	private String systemeExploitation = null;
	private String numeroPrise = null;
	private String carteMere = null;
	private String processeur = null;
	private String memoireRam = null;
	private String disqueDur = null;
	private String carteReseau = null;
	private String carteGraphique = null;
	private String service = null;
	private String ecran = null;
	private String graveur = null;
	private String numCpas2000 = "";
	private String actif = "0";

	private ArrayList<Imprimante> imprimantes = null;

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

	public ArrayList<Imprimante> getImprimantes() {
		return imprimantes;
	}

	public void setImprimantes(ArrayList<Imprimante> imprimantes) {
		this.imprimantes = imprimantes;
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
		this.nom = nom;
	}

	public String getNumeroPrise() {
		return numeroPrise;
	}

	public void setNumeroPrise(String numeroPrise) {
		this.numeroPrise = numeroPrise;
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
		this.actif = actif;
	}

	public void setId_ordinateur(String id_ordinateur) {
		this.id_ordinateur = id_ordinateur;
	}

	public String getId_ordinateur() {
		return id_ordinateur;
	}

}
