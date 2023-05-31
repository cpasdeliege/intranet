package be.cpasdeliege.intranet.informatique.model;

public class TypeOrdinateur {

	private String carteMere = null;
	private String processeur = null;
	private String memoireRam = null;
	private String disqueDur = null;
	private String carteReseau = null;
	private String carteGraphique = null;

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

	public String getProcesseur() {
		return processeur;
	}

	public void setProcesseur(String processeur) {
		this.processeur = processeur;
	}
}
