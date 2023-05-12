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

public class RechercheControler implements Controller {

	DomainInterface metier = null;

	@SuppressWarnings("unchecked")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		session.setAttribute("retour", request.getRequestURL() + "?" + request.getQueryString());
		HashMap modele = new HashMap();
//		if(request.getParameter("requete")!=null) {
//			modele.put("listePersonnel", metier.getListePersonnelServiceRequete(request.getParameter("requete")));
//			modele.put("requete", request.getParameter("requete"));
//		}
		if(request.getParameter("requete")!=null) {
			String[] temp = request.getParameter("requete").split(" ");
			List resultatBrut = new ArrayList();
			for(int i=0; i<temp.length; i++) {
				resultatBrut.addAll(metier.getListePersonnelServiceRequete(temp[i]));
			}
			List resultat = new ArrayList();
			for(int i=0; i<resultatBrut.size(); i++) {
				if(!resultat.contains(resultatBrut.get(i))) {
					resultat.add(resultatBrut.get(i));
				}
			}
			
			
			modele.put("listeEmail", metier.getListeEmail(resultat));
			modele.put("listePathPhoto", metier.getListePathPhoto(resultat));
			modele.put("listeCodeAS", metier.getListeCodeAS(resultat));
			modele.put("listePersonnel", resultat);
			modele.put("requete", request.getParameter("requete"));
		}
		return new ModelAndView("recherche", modele);
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}
}
