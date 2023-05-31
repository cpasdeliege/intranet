package be.cpasdeliege.intranet.informatique.controler;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.informatique.model.Imprimante;
import be.cpasdeliege.intranet.informatique.model.Ordinateur;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class GestionImprimante implements Controller {

	DomainInterface metier = null;

	@SuppressWarnings("unchecked")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse arg1) throws Exception {
		HashMap modele = new HashMap();
		Imprimante imprimante = metier.getImprimante(request.getParameter("numeroSerie"));
		Ordinateur ordinateur;
		try {
			ordinateur = metier.getOrdinateur(imprimante.getOrdinateurLocal());
		} catch (Exception e) {
			ordinateur = new Ordinateur();;
		}
		modele.put("service", ordinateur.getService());
		modele.put("imprimante", imprimante);
		modele.put("listeOrdinateur", metier.getListeImprimanteOrdinateur("%", imprimante.getNumeroSerie()));
		request.getSession().setAttribute("retour", "gestionImprimante.admin?numeroSerie=" + request.getParameter("numeroSerie"));
		return new ModelAndView("gestionImprimante", modele);
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}

}
