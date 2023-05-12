package be.cpasdeliege.intranet.informatique.model.domain;

import java.util.logging.Level;
import java.util.logging.LogRecord;

import javax.servlet.http.HttpServletRequest;

import be.cpasdeliege.intranet.informatique.model.Utilisateur;

public class LogRecordIntranet extends LogRecord {

	private static final long serialVersionUID = 1L;

	String utilisateur = "";

	public LogRecordIntranet(Level level, String msg) {
		super(level, msg);
		this.utilisateur = "";
	}

	public LogRecordIntranet(Level level, String msg, HttpServletRequest request) {
		super(level, msg);
		Utilisateur uti = (Utilisateur) request.getSession().getAttribute("utilisateur");
		if (uti != null) {
			utilisateur = uti.getLogin();
		} else {
			utilisateur = "";
		}
	}

	public String getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(String utilisateur) {
		this.utilisateur = utilisateur;
	}

}
