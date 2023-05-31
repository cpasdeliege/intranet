package be.cpasdeliege.intranet.informatique.controler.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.informatique.controler.formulaire.FormulaireOrdinateur;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class AjouterOrdinateur implements Controller {

	DomainInterface metier = null;

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
//			metier.getLogger().log(new LogRecordIntranet(Level.INFO, ((FormulaireOrdinateur)request.getSession().getAttribute("formulaireOrdinateurAjouter")).getNom(), request));
			metier.addOrdinateur(
					(FormulaireOrdinateur) request.getSession().getAttribute("formulaireOrdinateurAjouter"));

			response.sendRedirect((String) request.getSession().getAttribute("retour") + "#listeOrdinateurs");
			return null;
		} finally {
			request.getSession().setAttribute("formulaireOrdinateurAjouter", null);
		}

	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}
}
