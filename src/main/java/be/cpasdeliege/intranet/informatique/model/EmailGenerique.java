package be.cpasdeliege.intranet.informatique.model;

public class EmailGenerique {
	String email = "";
	String mdp = "";
	String alias = "";
	String loginPublilink = "";
	String mdpPublilink = "";

	public String getLoginPublilink() {
		return loginPublilink;
	}

	public void setLoginPublilink(String loginPublilink) {
		this.loginPublilink = loginPublilink;
	}

	public String getMdpPublilink() {
		return mdpPublilink;
	}

	public void setMdpPublilink(String mdpPublilink) {
		this.mdpPublilink = mdpPublilink;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
}
