package be.cpasdeliege.intranet.informatique.model;

public class Scanner {
	static final long serialVersionUID = 1;
	private String numeroSerie = null;
	private String type = null;
	private String ordinateurLocal = null;

	public String getOrdinateurLocal() {
		return ordinateurLocal;
	}

	public void setOrdinateurLocal(String ordinateurLocal) {
		this.ordinateurLocal = ordinateurLocal;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
