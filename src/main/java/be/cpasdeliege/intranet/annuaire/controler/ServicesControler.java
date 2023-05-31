package be.cpasdeliege.intranet.annuaire.controler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.informatique.model.Departement;
import be.cpasdeliege.intranet.informatique.model.Pole;
import be.cpasdeliege.intranet.informatique.model.Service;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class ServicesControler implements Controller {

	DomainInterface metier = null;

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
		if(request.getParameter("service")!=null) {
			String orderBy = "nom";
			if(request.getParameter("orderBy")!=null) {
				orderBy = request.getParameter("orderBy");
			}
			List pers = metier.getListePersonnelService(request.getParameter("service"), "%", "%", orderBy);
			//List pers = metier.getListePersonnelService(new String(request.getParameter("service").getBytes(), "UTF-8"), "%", "%", orderBy);
			modele.put("listePersonnel", pers);
			modele.put("listeEmail", metier.getListeEmail(pers));
			modele.put("listePathPhoto", metier.getListePathPhoto(pers));
			modele.put("listeCodeAS", metier.getListeCodeAS(pers));
			Service service = metier.getService(request.getParameter("service"));
			//Service service = metier.getService(new String(request.getParameter("service").getBytes(), "UTF-8"));
			Pole pole = metier.getPoleOfService(service.getId_service());
			Departement departement = metier.getDepartementOfService(service.getId_service());
			modele.put("service", service);
			modele.put("departement", departement);
			modele.put("pole", pole);
			
			
		}
		return new ModelAndView("services", modele);
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}

}
