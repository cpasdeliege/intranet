package be.cpasdeliege.intranet.informatique.controler.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class SupprimerUtilisateurIntranet implements Controller {

	DomainInterface metier = null;

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		metier.getLogger().log(new LogRecordIntranet(Level.INFO, request.getParameter("login"), request));
		metier.supprimerUtilisateur(request.getParameter("login"));
		response.sendRedirect((String) request.getSession().getAttribute("retour") + "#accesIntranet");
		return null;
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}

}
