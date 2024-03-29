package be.cpasdeliege.intranet.informatique.controler.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.informatique.model.TicketItem;
import be.cpasdeliege.intranet.informatique.model.Utilisateur;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class SupprimerTicketItem implements Controller {

	DomainInterface metier = null;

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		metier.getLogger().log(new LogRecordIntranet(Level.INFO, request.getParameter("service"), request));
		Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute("utilisateur");
		int idTicketItem = Integer.parseInt(request.getParameter("ticket"));
		TicketItem ticketItem = metier.getTicketItem(idTicketItem);
		metier.supprimerTicketItem(idTicketItem, utilisateur.getLogin());
		//response.sendRedirect((String) request.getSession().getAttribute("retour"));
		response.sendRedirect("gestionTache.admin?idPlanning=" + ticketItem.getIdPlanning());
		return null;

	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}
}