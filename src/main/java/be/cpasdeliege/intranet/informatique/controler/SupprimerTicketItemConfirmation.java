package be.cpasdeliege.intranet.informatique.controler;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class SupprimerTicketItemConfirmation implements Controller {

	DomainInterface metier = null;

	@SuppressWarnings("unchecked")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HashMap modele = new HashMap();
		modele.put("ticket", metier.getTicketItem(Integer.parseInt(request.getParameter("ticket"))));
		return new ModelAndView("supprimerTicketItemConfirmation", modele);
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}

}
