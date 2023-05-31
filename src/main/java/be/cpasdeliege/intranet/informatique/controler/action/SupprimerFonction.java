package be.cpasdeliege.intranet.informatique.controler.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class SupprimerFonction implements Controller {

	DomainInterface metier = null;

	@SuppressWarnings("unchecked")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String fonction = request.getParameter("supprimerFonction");
		metier.supprimerFonction(fonction);
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
