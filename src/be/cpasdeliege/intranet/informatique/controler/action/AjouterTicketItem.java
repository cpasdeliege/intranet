package be.cpasdeliege.intranet.informatique.controler.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.informatique.model.TicketItem;
import be.cpasdeliege.intranet.informatique.model.Utilisateur;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class AjouterTicketItem implements Controller {

	DomainInterface metier = null;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		TicketItem ticket = new TicketItem();

		String texte = request.getParameter("texte");
		if (!texte.trim().equals("")) {
			ticket.setTexte(texte);
			ticket.setIdPlanning(Integer.parseInt(request.getParameter("idPlanning")));
			ticket.setUser(utilisateur.getNom());
			metier.addTicketItem(ticket);
		}
		response.sendRedirect("gestionTache.admin?idPlanning=" + request.getParameter("idPlanning") + "#fin");
		return null;
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}

}
