package be.cpasdeliege.intranet.informatique.model.dao;

public class DaoImprimante {
	private String numeroSerie = "";
	private String type = "";
	private String ordinateurLocal = "";
	private String service = "";

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public String getOrdinateurLocal() {
		return ordinateurLocal;
	}

	public void setOrdinateurLocal(String ordinateurLocal) {
		this.ordinateurLocal = ordinateurLocal;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
