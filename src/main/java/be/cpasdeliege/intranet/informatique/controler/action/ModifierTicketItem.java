package be.cpasdeliege.intranet.informatique.controler.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.informatique.controler.formulaire.FormulaireService;
import be.cpasdeliege.intranet.informatique.model.TicketItem;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class ModifierTicketItem implements Controller {

	DomainInterface metier = null;

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int idTicketItem = Integer.parseInt(request.getParameter("ticket"));
		TicketItem ticketItem = metier.getTicketItem(idTicketItem);
		
		if ("POST".equals(request.getMethod())) {
			metier.modifierTicketItem(idTicketItem, request.getParameter("texte"));
			response.sendRedirect("gestionTache.admin?idPlanning=" + ticketItem.getIdPlanning());
		}

		HashMap modele = new HashMap();
		modele.put("ticketItem", metier.getTicketItem(Integer.parseInt(request.getParameter("ticket"))));

		return new ModelAndView("modifierTicketItem", modele);		
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}

}
