package be.cpasdeliege.intranet.informatique.controler;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class GestionImprimantes implements Controller {

	DomainInterface metier = null;

	@SuppressWarnings("unchecked")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse arg1) throws Exception {
		HashMap modele = new HashMap();
		modele.put("statistiques", metier.getStatistiques());
		modele.put("listeImprimantes", metier.getListeImprimante("%", "%"));
		request.getSession().setAttribute("retour", "gestionImprimantes.admin");
		return new ModelAndView("gestionImprimantes", modele);
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}

}
