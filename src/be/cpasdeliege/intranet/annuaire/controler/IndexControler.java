package be.cpasdeliege.intranet.annuaire.controler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class IndexControler implements Controller {

	DomainInterface metier = null;

	@SuppressWarnings("unchecked")
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		HttpSession session = arg0.getSession(false);
		session.setAttribute("retour", arg0.getRequestURL() + "?" + arg0.getQueryString());
		HashMap modele = new HashMap();
		List listeService =  metier.getListeServices();
		int taille = (listeService.size()/3)+1;
		List colonne1 = new ArrayList();		
		List colonne2 = new ArrayList();
		List colonne3 = new ArrayList();
		for(int i=0; i<taille; i++) {
			colonne1.add(listeService.get(i));
		}
		for(int i=taille; i<taille*2; i++) {
			colonne2.add(listeService.get(i));
		}
		for(int i=taille*2; i<listeService.size(); i++) {
			colonne3.add(listeService.get(i));
		}
		modele.put("colonne1", colonne1);
		modele.put("colonne2", colonne2);
		modele.put("colonne3", colonne3);
		return new ModelAndView("index", modele);
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}

}
