package be.cpasdeliege.intranet.informatique.model;

public class Stat {

	String service = "";
	String type = "";
	String nbre = "";

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return service + " -> " + type + " -> " + nbre;
	}

	public String getNbre() {
		return nbre;
	}

	public void setNbre(String nbre) {
		this.nbre = nbre;
	}

}
