package be.cpasdeliege.intranet.informatique.controler.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.informatique.model.Imprimante;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class AjouterImprimante implements Controller {

	DomainInterface metier = null;

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
//			metier.getLogger().log(new LogRecordIntranet(Level.INFO, ((Imprimante)request.getSession().getAttribute("formulaireImprimanteAjouter")).getNumeroSerie(), request));
			metier.addImprimante((Imprimante) request.getSession().getAttribute("formulaireImprimanteAjouter"));

			response.sendRedirect((String) request.getSession().getAttribute("retour") + "#imprimantesLocale");
			return null;
		} finally {
			request.getSession().setAttribute("formulaireImprimanteAjouter", null);
		}
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}
}
