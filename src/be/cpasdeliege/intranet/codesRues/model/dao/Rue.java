package be.cpasdeliege.intranet.codesRues.model.dao;

public class Rue {
	String codeRue = "";
	String codePostal = "";
	String nomRue = "";
	String typeRue = "";
	String codeAntenne = "";
	String antenne = "";
	String remarque = "";

	public String getCodeRue() {
		return codeRue;
	}

	public void setCodeRue(String codeRue) {
		this.codeRue = codeRue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getNomRue() {
		return nomRue;
	}

	public void setNomRue(String nomRue) {
		this.nomRue = nomRue;
	}

	public String getTypeRue() {
		return typeRue;
	}

	public void setTypeRue(String typeRue) {
		this.typeRue = typeRue;
	}

	public String getCodeAntenne() {
		return codeAntenne;
	}

	public void setCodeAntenne(String codeAntenne) {
		this.codeAntenne = codeAntenne;
	}

	public String getAntenne() {
		return antenne;
	}

	public void setAntenne(String antenne) {
		this.antenne = antenne;
	}

	public String getRemarque() {
		return remarque;
	}

	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}

}
