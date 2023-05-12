package be.cpasdeliege.intranet.formations.controler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.formations.model.dao.DaoFormations;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class Index implements Controller {

	DomainInterface metier = null;
	DaoFormations daoFormations = null;

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.getSession().setAttribute("retour", "index.formations");
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
		
		List listeTypeFormation = daoFormations.getTypeFormation();
		List colonneType1 = new ArrayList();		
		List colonneType2 = new ArrayList();
		List colonneType3 = new ArrayList();
		taille = (listeTypeFormation.size()/3)+1;
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
		return new ModelAndView("index", modele);
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
