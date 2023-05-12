package be.cpasdeliege.intranet.informatique.controler.formulaire;

import java.io.UnsupportedEncodingException;

public class FormulairePersonnel {
	static final long serialVersionUID = 1;
	private int id_personnel = 0;
	private String nom = "";
	private String prenom = "";
	private String loginWindows = "";
	private String loginAS400 = "";
	private String loginCPAS2000 = "";
	private String loginNCC = "";
	private String loginGRH = "";
	private String loginPublilink = "";
	private String mdpPublilink = "";
	private String email = "";
	private String mdpEmail = "";
	private String emailAlias = "";
	private String wifi = "";
	private String pathPhoto = "";
	private String codeAS = "";

	public int getId_personnel() {
		return id_personnel;
	}

	public void setId_personnel(int id_personnel) {
		this.id_personnel = id_personnel;
	}

	public String getMdpEmail() {
		return mdpEmail;
	}

	public void setMdpEmail(String mdpEmail) {
		this.mdpEmail = mdpEmail;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailAlias() {
		return emailAlias;
	}

	public void setEmailAlias(String emailAlias) {
		this.emailAlias = emailAlias;
	}

	public String getLoginAS400() {
		return loginAS400;
	}

	public void setLoginAS400(String loginAS400) {
		this.loginAS400 = loginAS400;
	}

	public String getLoginCPAS2000() {
		return loginCPAS2000;
	}

	public void setLoginCPAS2000(String loginCPAS2000) {
		this.loginCPAS2000 = loginCPAS2000;
	}

	public String getLoginGRH() {
		return loginGRH;
	}

	public void setLoginGRH(String loginGRH) {
		this.loginGRH = loginGRH;
	}

	public String getLoginNCC() {
		return loginNCC;
	}

	public void setLoginNCC(String loginNCC) {
		this.loginNCC = loginNCC;
	}

	public String getLoginPublilink() {
		return loginPublilink;
	}

	public void setLoginPublilink(String loginPublilink) {
		this.loginPublilink = loginPublilink;
	}

	public String getLoginWindows() {
		return loginWindows;
	}

	public void setLoginWindows(String loginWindows) {
		this.loginWindows = loginWindows;
	}

	public String getMdpPublilink() {
		return mdpPublilink;
	}

	public void setMdpPublilink(String mdpPublilink) {
		this.mdpPublilink = mdpPublilink;
	}

	public String getNom() {
		// return new String(this.nom.getBytes(),"UTF-8");
		return nom;
	}

	public void setNom(String nom) throws UnsupportedEncodingException {
		this.nom = nom;
		// this.nom=new String(nom.getBytes(),"UTF-8");
	}

	public String getPrenom() {
		// return new String(prenom.getBytes(),"UTF-8");
		return prenom;
	}

	public void setPrenom(String prenom) throws UnsupportedEncodingException {
		this.prenom = prenom;
		// this.prenom=new String(prenom.getBytes(),"UTF-8");
	}

	public String getWifi() {
		return wifi;
	}

	public void setWifi(String wifi) {
		this.wifi = wifi;
	}

	public String getPathPhoto() {
		return pathPhoto;
	}

	public void setPathPhoto(String pathPhoto) {
		this.pathPhoto = pathPhoto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCodeAS() {
		return codeAS;
	}

	public void setCodeAS(String codeAS) {
		this.codeAS = codeAS;
	}
}
