package be.cpasdeliege.intranet.formations.controler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.formations.model.Formation;
import be.cpasdeliege.intranet.formations.model.FormationService;
import be.cpasdeliege.intranet.formations.model.dao.DaoFormations;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class RechercheServices implements Controller {

	DomainInterface metier = null;
	DaoFormations daoFormations = null;

	@SuppressWarnings("unchecked")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		session.setAttribute("retour", request.getRequestURL() + "?" + request.getQueryString());
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
		
		String service = request.getParameter("service");
		if(service != null) {
			List<FormationService> listeFormationService = daoFormations.getFormationService(service);
			List<Formation> listeFormations = new ArrayList<Formation>();
			for (FormationService formationService : listeFormationService) {
				listeFormations.add(daoFormations.getFormation(formationService.getIdFormation()));
			}
			if(listeFormations.size() > 0) {
				modele.put("listeFormations", listeFormations);
			}
			modele.put("service", metier.getService(request.getParameter("service")));
		}
		return new ModelAndView("services", modele);
	}

	public DaoFormations getDaoFormations() {
		return daoFormations;
	}

	public void setDaoFormations(DaoFormations daoFormations) {
		this.daoFormations = daoFormations;
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}

}
