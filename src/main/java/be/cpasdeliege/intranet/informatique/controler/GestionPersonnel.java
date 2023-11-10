package be.cpasdeliege.intranet.informatique.controler;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.informatique.model.Personnel;
import be.cpasdeliege.intranet.informatique.model.PrivilegeInformatique;
import be.cpasdeliege.intranet.informatique.model.Utilisateur;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class GestionPersonnel implements Controller {

	DomainInterface metier = null;

	@SuppressWarnings("unchecked")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HashMap modele = new HashMap();
		//String nom=new String(request.getParameter("nom").getBytes(), "UTF-8");
		//String prenom=new String(request.getParameter("prenom").getBytes(), "UTF-8");
		String nom=request.getParameter("nom");
		String prenom=request.getParameter("prenom");
		//p.getNom().
		//p.setNom(new String(p.getNom().getBytes(),"UTF-8"));
		//p.setPrenom(new String(p.getPrenom().getBytes(),"UTF-8"));
		//modele.put("personnel", metier.getPersonnel(new String(request.getParameter("nom").getBytes(), "UTF-8"), new String(request.getParameter("prenom").getBytes(), "UTF-8")));
		modele.put("personnel", metier.getPersonnel(request.getParameter("nom"), request.getParameter("prenom")));
		//modele.put("personnel", p);
		//Utilisateur utilisateur = metier.getUtilisateur(new String(request.getParameter("nom").getBytes(), "UTF-8"),new String(request.getParameter("prenom").getBytes(), "UTF-8"));
		Utilisateur utilisateur = metier.getUtilisateur(request.getParameter("nom"),request.getParameter("prenom"));
		modele.put("comteUtilisateur", utilisateur);
		if(utilisateur != null) {
			modele.put("privilègeInformatique", metier.getPrivilegeInformatique(utilisateur.getLogin()));
		}
		//List listeService = metier.getListePersonnelService("%", new String(request.getParameter("nom").getBytes(), "UTF-8"), new String(request.getParameter("prenom").getBytes(), "UTF-8"));
		List listeService = metier.getListePersonnelService("%", request.getParameter("nom"), request.getParameter("prenom"));
		
		//List listeOrdinateur = metier.getListeOrdinateurPersonnel("%", new String(request.getParameter("nom").getBytes(), "UTF-8"),new String(request.getParameter("prenom").getBytes(), "UTF-8"));
		List listeOrdinateur = metier.getListeOrdinateurPersonnel("%", request.getParameter("nom"),request.getParameter("prenom"));
		modele.put("listeService", listeService);
		modele.put("listeOrdinateur", listeOrdinateur);
		if(listeService.size() > 0) {
			modele.put("serviceDefaultTache", listeService.get(0));
		}
		if(listeOrdinateur.size() > 0) {
			modele.put("ordinateurDefaultTache", listeOrdinateur.get(0));
		}
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
		//modele.put("listeTacheEnCours", metier.getListeTacheEnCours("%", new String(request.getParameter("nom").getBytes(), "UTF-8"), new String(request.getParameter("prenom").getBytes(), "UTF-8"), "%", triEnCours));
		modele.put("listeTacheEnCours", metier.getListeTacheEnCours("%", request.getParameter("nom"), request.getParameter("prenom"), "%", triEnCours));
		//modele.put("listeTacheEnAttente", metier.getListeTacheEnAttente("%", new String(request.getParameter("nom").getBytes(), "UTF-8"),new String(request.getParameter("prenom").getBytes(), "UTF-8"), "%", triEnAttente));
		modele.put("listeTacheEnAttente", metier.getListeTacheEnAttente("%", request.getParameter("nom"),request.getParameter("prenom"), "%", triEnAttente));
		//modele.put("listeTachesFinies", metier.getListeTacheFinie("%", new String(request.getParameter("nom").getBytes(), "UTF-8"),new String(request.getParameter("prenom").getBytes(), "UTF-8"), "%", triEnFini));
		modele.put("listeTachesFinies", metier.getListeTacheFinie("%", request.getParameter("nom"),request.getParameter("prenom"), "%", triEnFini));
		request.getSession().setAttribute("retour", "gestionPersonnel.admin?nom=" + nom + "&prenom=" + prenom);
		
		if(((PrivilegeInformatique)request.getSession().getAttribute("privilegeInformatique")).isAdministrateur()) {
			return new ModelAndView("gestionPersonnel", modele);
		} else {
			return new ModelAndView("telephonie/gestionPersonnel", modele);
		}
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}

}
