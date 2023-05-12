package be.cpasdeliege.intranet.informatique.controler;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class GestionEmails implements Controller {

	DomainInterface metier = null;

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		HashMap modele = new HashMap();
		modele.put("listeInternet", metier.getListeAccesInternet());
		modele.put("listeEmailGenerique", metier.getListeEmailGenerique());
		arg0.getSession().setAttribute("retour", "gestionEmails.admin");
		return new ModelAndView("gestionEmails", modele);
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}

}
