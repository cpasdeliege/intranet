package be.cpasdeliege.intranet.informatique.controler;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.informatique.model.dao.DaoMySQL;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class GestionOrdinateur implements Controller {

	DomainInterface metier = null;
	DaoMySQL dao = null;

	@SuppressWarnings("unchecked")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String nouvNomOrdi = request.getParameter("nouvNomOrdi");
		String ordinateur = request.getParameter("ordinateur");
		if(nouvNomOrdi != null) {
			dao.renomerOrdinateur(ordinateur, nouvNomOrdi);
			ordinateur = nouvNomOrdi;
		}
		
		HashMap modele = new HashMap();
		List listePersonnel = metier.getListeOrdinateurPersonnel(ordinateur, "%", "%");
		modele.put("ordinateur", metier.getOrdinateur(ordinateur));
		modele.put("listePersonnel", listePersonnel);
		modele.put("listeImprimantes", metier.getListeImprimante("%", ordinateur));
		modele.put("listeImprimantesReseau", metier.getListeImprimanteReseau(ordinateur));
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
		modele.put("listeTacheEnCours", metier.getListeTacheEnCours("%", "%", "%", ordinateur, triEnCours));
		modele.put("listeTacheEnAttente", metier.getListeTacheEnAttente("%", "%", "%", ordinateur, triEnAttente));
		modele.put("listeTachesFinies", metier.getListeTacheFinie("%", "%", "%", ordinateur, triEnFini));
		if(listePersonnel.size()>0) {
			modele.put("personnelDefaultTache", listePersonnel.get(0));
		}
		request.getSession().setAttribute("retour", "gestionOrdinateur.admin?ordinateur=" + ordinateur);
		return new ModelAndView("gestionOrdinateur", modele);
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
