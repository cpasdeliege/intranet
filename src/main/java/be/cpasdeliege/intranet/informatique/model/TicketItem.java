package be.cpasdeliege.intranet.informatique.model;

public class TicketItem {
	int idPlanning = 0;
	String texte = "";
	String user = "";
	String date = "";

	public int getIdPlanning() {
		return idPlanning;
	}

	public void setIdPlanning(int idPlanning) {
		this.idPlanning = idPlanning;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDate() {
		return date;
	}

	public String getDateAffichage() {
		return date.substring(0, date.length() - 5);
	}

	public void setDate(String date) {
		this.date = date;
	}

}
