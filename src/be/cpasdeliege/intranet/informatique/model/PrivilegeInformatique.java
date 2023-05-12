package be.cpasdeliege.intranet.informatique.model;

public class PrivilegeInformatique {

	String login = "";
	boolean administrateur = false;
	boolean telephonie = false;
	boolean formations = false;
	boolean dsiUser = false;
	boolean dsiChef = false;
	boolean dsiInfo = false;
	boolean dsiDirection = false;
	boolean cadastreMP = false;
	boolean admVM = false;
	boolean wifiUser = false;
	boolean wifiAdmin = false;
	boolean gpi = false;

	public boolean isGpi() {
		return gpi;
	}

	public void setGpi(boolean gpi) {
		this.gpi = gpi;
	}

	public boolean isFormations() {
		return formations;
	}

	public void setFormations(boolean formations) {
		this.formations = formations;
	}

	public void setFormations(String formations) {
		if (formations.equals("true")) {
			this.formations = true;
		} else {
			this.formations = false;
		}
	}

	public boolean isAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}

	public void setAdministrateur(String administrateur) {
		if (administrateur.equals("true")) {
			this.administrateur = true;
		} else {
			this.administrateur = false;
		}
	}

	public boolean isTelephonie() {
		return telephonie;
	}

	public void setTelephonie(boolean telephonie) {
		this.telephonie = telephonie;
	}

	public void setTelephonie(String telephonie) {
		if (telephonie.equals("true")) {
			this.telephonie = true;
		} else {
			this.telephonie = false;
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public boolean isDsiUser() {
		return dsiUser;
	}

	public void setDsiUser(boolean dsiUser) {
		this.dsiUser = dsiUser;
	}

	public void setDsiUser(String dsiUser) {
		if (dsiUser.equals("true")) {
			this.dsiUser = true;
		} else {
			this.dsiUser = false;
		}
	}

	public boolean isDsiChef() {
		return dsiChef;
	}

	public void setDsiChef(boolean dsiChef) {
		this.dsiChef = dsiChef;
	}

	public void setDsiChef(String dsiChef) {
		if (dsiChef.equals("true")) {
			this.dsiChef = true;
		} else {
			this.dsiChef = false;
		}
	}

	public boolean isDsiInfo() {
		return dsiInfo;
	}

	public void setDsiInfo(boolean dsiInfo) {
		this.dsiInfo = dsiInfo;
	}

	public void setDsiInfo(String dsiInfo) {
		if (dsiInfo.equals("true")) {
			this.dsiInfo = true;
		} else {
			this.dsiInfo = false;
		}
	}

	public boolean isDsiDirection() {
		return dsiDirection;
	}

	public void setDsiDirection(boolean dsiDirection) {
		this.dsiDirection = dsiDirection;
	}

	public void setDsiDirection(String dsiDirection) {
		if (dsiDirection.equals("true")) {
			this.dsiDirection = true;
		} else {
			this.dsiDirection = false;
		}
	}

	public boolean isCadastreMP() {
		return cadastreMP;
	}

	public void setCadastreMP(boolean cadastreMP) {
		this.cadastreMP = cadastreMP;
	}

	public void setCadastreMP(String cadastreMP) {
		if (cadastreMP.equals("true")) {
			this.cadastreMP = true;
		} else {
			this.cadastreMP = false;
		}
	}

	public boolean isAdmVM() {
		return admVM;
	}

	public void setAdmVM(boolean admVM) {
		this.admVM = admVM;
	}

	public void setAdmVM(String admVM) {
		if (admVM.equals("true")) {
			this.admVM = true;
		} else {
			this.admVM = false;
		}
	}

	public boolean isWifiUser() {
		return wifiUser;
	}

	public void setWifiUser(boolean wifiUser) {
		this.wifiUser = wifiUser;
	}

	public void setWifiUser(String wifiUser) {
		if (wifiUser.equals("true")) {
			this.wifiUser = true;
		} else {
			this.wifiUser = false;
		}
	}

	public boolean isWifiAdmin() {
		return wifiAdmin;
	}

	public void setWifiAdmin(boolean wifiAdmin) {
		this.wifiAdmin = wifiAdmin;
	}

	public void setWifiAdmin(String wifiAdmin) {
		if (wifiAdmin.equals("true")) {
			this.wifiAdmin = true;
		} else {
			this.wifiAdmin = false;
		}
	}
}