package be.cpasdeliege.intranet.informatique.controler.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.informatique.controler.formulaire.FormulaireService;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class ModifierService implements Controller {

	DomainInterface metier = null;

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
//			metier.getLogger().log(new LogRecordIntranet(Level.INFO, ((FormulaireService)request.getSession().getAttribute("formulaireServiceModifier")).getNom(), request));
			metier.modifierService((FormulaireService) request.getSession().getAttribute("formulaireServiceModifier"));
			response.sendRedirect((String) request.getSession().getAttribute("retour"));
			return null;
		} finally {
			request.getSession().setAttribute("formulaireServiceModifier", null);
		}
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}

}
