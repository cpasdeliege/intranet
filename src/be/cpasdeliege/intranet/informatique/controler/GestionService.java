package be.cpasdeliege.intranet.informatique.controler;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.informatique.model.Departement;
import be.cpasdeliege.intranet.informatique.model.Pole;
import be.cpasdeliege.intranet.informatique.model.PrivilegeInformatique;
import be.cpasdeliege.intranet.informatique.model.Service;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class GestionService implements Controller {

	DomainInterface metier = null;

	@SuppressWarnings("unchecked")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HashMap modele = new HashMap();
		System.out.println("*******************************");
		System.out.println(request.getParameter("service"));
		System.out.println("*******************************");
		//Service service = metier.getService(new String(request.getParameter("service").getBytes(), "UTF-8"));
		Service service = metier.getService(request.getParameter("service"));
		
		long id_service = service.getId_service();
		Departement departement = metier.getDepartementOfService(id_service);
		Pole pole = metier.getPoleOfService(id_service);
		
		modele.put("service", service);
		modele.put("departement", departement);
		modele.put("pole", pole);		
		
		//modele.put("listePersonnel", metier.getListePersonnelGestionService(new String(request.getParameter("service").getBytes(), "UTF-8")));
		modele.put("listePersonnel", metier.getListePersonnelGestionService(request.getParameter("service")));
		//modele.put("listeOrdinateurs", metier.getListeOrdinateurService(new String(request.getParameter("service").getBytes(), "UTF-8")));
		modele.put("listeOrdinateurs", metier.getListeOrdinateurService(request.getParameter("service")));
		//modele.put("listeImprimantes", metier.getListeImprimanteService(new String(request.getParameter("service").getBytes(), "UTF-8")));
		modele.put("listeImprimantes", metier.getListeImprimanteService(request.getParameter("service")));
		request.getSession().setAttribute("retour", "gestionService.admin?service=" + request.getParameter("service"));
		String triEnCours = request.getParameter("triEnCours");
		String triEnAttente = request.getParameter("triEnAttente");
		String triEnFini = request.getParameter("triEnFini");
		if(triEnCours == null) {
			triEnCours = "echeance";
		}
		if(triEnAttente == null) {
			triEnAttente = "idPlanning";
		}
		if(triEnFini == null) {
			triEnFini = "dateFin";
		}
		//modele.put("listeTacheEnCours", metier.getListeTacheEnCours(new String(request.getParameter("service").getBytes(), "UTF-8"), "%", "%", "%", triEnCours));
		modele.put("listeTacheEnCours", metier.getListeTacheEnCours(request.getParameter("service"), "%", "%", "%", triEnCours));
		//modele.put("listeTacheEnAttente", metier.getListeTacheEnAttente(new String(request.getParameter("service").getBytes(), "UTF-8"), "%", "%", "%", triEnAttente));
		modele.put("listeTacheEnAttente", metier.getListeTacheEnAttente(request.getParameter("service"), "%", "%", "%", triEnAttente));
		//modele.put("listeTachesFinies", metier.getListeTacheFinie(new String(request.getParameter("service").getBytes(), "UTF-8"), "%", "%", "%", triEnFini));
		modele.put("listeTachesFinies", metier.getListeTacheFinie(request.getParameter("service"), "%", "%", "%", triEnFini));
		
		if(((PrivilegeInformatique)request.getSession().getAttribute("privilegeInformatique")).isAdministrateur()) {
			return new ModelAndView("gestionService", modele);
		} else {
			return new ModelAndView("telephonie/gestionService", modele);
		}
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}

}
