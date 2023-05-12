package be.cpasdeliege.intranet.informatique.controler.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.informatique.controler.formulaire.FormulairePersonnel;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class AjouterPersonnel implements Controller {

	DomainInterface metier = null;

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
//			metier.getLogger().log(new LogRecordIntranet(Level.INFO, ((FormulairePersonnel)request.getSession().getAttribute("formulairePersonnelAjouter")).getNom() + ", " + ((FormulairePersonnel)request.getSession().getAttribute("formulairePersonnelAjouter")).getPrenom(), request));
			metier.addPersonnel((FormulairePersonnel) request.getSession().getAttribute("formulairePersonnelAjouter"));
			response.sendRedirect("gestionPersonnels.admin");
			return null;
		} finally {
			request.getSession().setAttribute("formulairePersonnelAjouter", null);
		}

	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}

}
