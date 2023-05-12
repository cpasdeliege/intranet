package be.cpasdeliege.intranet.annuaire.controler;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class OrdreAlphabetiqueControler implements Controller {

	DomainInterface metier = null;

	@SuppressWarnings("unchecked")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		session.setAttribute("retour", request.getRequestURL() + "?" + request.getQueryString());
		HashMap modele = new HashMap();
		if(request.getParameter("lettre")!=null) {
			List pers = metier.getListePersonnelServiceAlphabetique(request.getParameter("lettre"));
			modele.put("listeEmail", metier.getListeEmail(pers));
			modele.put("listePathPhoto", metier.getListePathPhoto(pers));
			modele.put("listeCodeAS", metier.getListeCodeAS(pers));
			modele.put("listePersonnel", pers);
		}
		return new ModelAndView("ordreAlphabetique", modele);
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}

}
