package be.cpasdeliege.intranet.informatique.controler.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class SupprimerPersonnel implements Controller {

	DomainInterface metier = null;

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		metier.getLogger().log(new LogRecordIntranet(Level.INFO, request.getParameter("nom") + ", " + request.getParameter("prenom"), request));
		metier.supprimerPersonnel(request.getParameter("nom"), request.getParameter("prenom"));
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