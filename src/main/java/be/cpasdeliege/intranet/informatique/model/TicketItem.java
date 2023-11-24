package be.cpasdeliege.intranet.informatique.model;

public class TicketItem {
	int idTicketItem = 0;
	int idticket = 0; // c'est "idticket" dans la DB, du coup on adapte...
	int idPlanning = 0;
	String texte = "";
	String user = "";
	String date = "";

	public int getIdTicketItem() {
		return idTicketItem;
	}

	public void setIdTicketItem(int idTicketItem) {
		this.idTicketItem = idTicketItem;
	}

	public int getIdticket() {
		return this.idticket;
	}

	public void setIdticket(int idticket) {
		this.idticket = idticket;
		this.idTicketItem = idticket; // vu que c'est "idticket" dans l'admin, on redirige le set vers IdTicketItem
	}

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
