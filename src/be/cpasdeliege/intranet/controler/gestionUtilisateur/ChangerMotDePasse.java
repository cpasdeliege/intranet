package be.cpasdeliege.intranet.controler.gestionUtilisateur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.informatique.model.Utilisateur;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class ChangerMotDePasse implements Controller {

	DomainInterface metier = null;

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		if (utilisateur != null) {
			if (utilisateur.getLogin().equals(request.getParameter("login"))) {
				metier.changerMotDePasseUtilisateur(request.getParameter("login"), request.getParameter("mdp"));
			}
		}
		response.sendRedirect((String) request.getSession().getAttribute("retour"));
		return null;
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}
}
