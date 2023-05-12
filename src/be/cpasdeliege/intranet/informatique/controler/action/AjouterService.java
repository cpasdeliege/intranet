package be.cpasdeliege.intranet.informatique.controler.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.FormSubmitEvent;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.informatique.controler.formulaire.FormulaireService;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class AjouterService implements Controller {

	DomainInterface metier = null;

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
//				metier.getLogger().log(new LogRecordIntranet(Level.INFO, ((FormulaireService)request.getSession().getAttribute("formulaireServiceAjouter")).getNom(), request));
			metier.addService((FormulaireService) request.getSession().getAttribute("formulaireServiceAjouter"));

			response.sendRedirect("gestionServices.admin");
			return null;
		} finally {

			request.getSession().setAttribute("formulaireServiceAjouter", null);
		}

	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}
}
