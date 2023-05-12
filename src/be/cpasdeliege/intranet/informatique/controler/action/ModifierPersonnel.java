package be.cpasdeliege.intranet.informatique.controler.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.informatique.controler.formulaire.FormulairePersonnel;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class ModifierPersonnel implements Controller {

	DomainInterface metier = null;

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
//			metier.getLogger().log(new LogRecordIntranet(Level.INFO, ((FormulairePersonnel)request.getSession().getAttribute("formulairePersonnelModifier")).getNom() + ", " + ((FormulairePersonnel)request.getSession().getAttribute("formulairePersonnelModifier")).getPrenom(), request));
			metier.modifierPersonnel(
					(FormulairePersonnel) request.getSession().getAttribute("formulairePersonnelModifier"));
			response.sendRedirect((String) request.getSession().getAttribute("retour"));
			return null;
		} finally {
			request.getSession().setAttribute("formulairePersonnelModifier", null);
		}
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}

}