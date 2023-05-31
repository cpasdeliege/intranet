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

public class RechercheTypeFormations implements Controller {

	DomainInterface metier = null;
	DaoFormations daoFormations = null;

	@SuppressWarnings("unchecked")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		session.setAttribute("retour", request.getRequestURL() + "?" + request.getQueryString());
		HashMap modele = new HashMap();
		List listeTypeFormation = daoFormations.getTypeFormation();
		List colonneType1 = new ArrayList();		
		List colonneType2 = new ArrayList();
		List colonneType3 = new ArrayList();
		int taille = (listeTypeFormation.size()/3)+1;
		for(int i=0; i<taille; i++) {
			colonneType1.add(listeTypeFormation.get(i));
		}
		for(int i=taille; i<taille*2; i++) {
			colonneType2.add(listeTypeFormation.get(i));
		}
		for(int i=taille*2; i<listeTypeFormation.size(); i++) {
			colonneType3.add(listeTypeFormation.get(i));
		}
		modele.put("colonneType1", colonneType1);
		modele.put("colonneType2", colonneType2);
		modele.put("colonneType3", colonneType3);
		
		String typeFormation = request.getParameter("typeFormation");
		if(typeFormation != null) {
			List<Formation> listeFormations = daoFormations.getFormationParType(typeFormation);
			List<String> listeService = new ArrayList<String>();
			for (Formation formation : listeFormations) {
				List<FormationService> listeFormationService = daoFormations.getServicesFormation(formation.getIdFormation());
				StringBuffer tmp = new StringBuffer();
				for (FormationService formationService : listeFormationService) {
					tmp.append(formationService.getService() + "\n");
				}
				listeService.add(tmp.toString());
			}
			if(listeFormations.size() > 0) {
				modele.put("listeFormations", listeFormations);
				modele.put("listeServices", listeService);
			}
			
		}
		return new ModelAndView("typeFormations", modele);
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
