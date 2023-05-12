package be.cpasdeliege.intranet.informatique.controler;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.informatique.model.dao.DaoMySQL;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class GestionOrdinateurs implements Controller {

	DomainInterface metier = null;

	DaoMySQL dao = null;

	@SuppressWarnings("unchecked")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HashMap modele = new HashMap();
		
		String param = request.getParameter("param");
//		if(param == null) {
//			modele.put("statistiques", metier.getStatistiques());
//			modele.put("listeOrdinateurs", dao.getListeOrdinateursActifs("%", "%"));
//			request.getSession().setAttribute("retour", "gestionOrdinateurs.admin");
//		} else if(param.equals("nonactifs")) {
//			modele.put("statistiques", metier.getStatistiques());
//			modele.put("listeOrdinateurs", dao.getListeOrdinateursNonActifs("%", "%"));
//			request.getSession().setAttribute("retour", "gestionOrdinateurs.admin?param=nonactif");
//		} else if(param.equals("actifs")) {
//			modele.put("statistiques", metier.getStatistiques());
//			modele.put("listeOrdinateurs", dao.getListeOrdinateursActifs("%", "%"));
//			request.getSession().setAttribute("retour", "gestionOrdinateurs.admin?param=actif");
//		} else if(param.equals("tous")) {
//			modele.put("statistiques", metier.getStatistiques());
//			modele.put("listeOrdinateurs", metier.getListeOrdinateurs());
//			request.getSession().setAttribute("retour", "gestionOrdinateurs.admin?param=tous");
//		}
		
		if(param == null) {
			modele.put("statistiques", metier.getStatistiques());
			modele.put("listeOrdinateurs", dao.getListeOrdinateursActifs("%", "%"));
			request.getSession().setAttribute("retour", "gestionOrdinateurs.admin");
		} else if(param.equals("stock")) {
			modele.put("statistiques", metier.getStatistiques());
			modele.put("listeOrdinateurs", dao.getListeOrdinateursNonActifs("%", "%"));
			request.getSession().setAttribute("retour", "gestionOrdinateurs.admin?param=nonactif");
		} else {
			modele.put("statistiques", metier.getStatistiques());
			modele.put("listeOrdinateurs", dao.getListeOrdinateurs());
			request.getSession().setAttribute("retour", "gestionOrdinateurs.admin?param=tous");
		}
		
		return new ModelAndView("gestionOrdinateurs", modele);
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}

	public DaoMySQL getDao() {
		return dao;
	}

	public void setDao(DaoMySQL dao) {
		this.dao = dao;
	}

}
