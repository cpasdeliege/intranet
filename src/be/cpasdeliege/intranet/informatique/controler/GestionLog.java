package be.cpasdeliege.intranet.informatique.controler;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class GestionLog implements Controller {

	DomainInterface metier = null;

	@SuppressWarnings("unchecked")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String requete = request.getParameter("requete");
		
		if(requete.equals("statsAnnuaire")) {
			HashMap modele = new HashMap();
			modele.put("statistiquesAnnuaire", metier.getStatistiquesAnnuaire());
			request.getSession().setAttribute("retour", "gestionLog.admin");
			return new ModelAndView("statsAnnuaire", modele);
			
		} else if(requete.equals("statsFormations")) {
			HashMap modele = new HashMap();
			modele.put("statistiquesFormations", metier.getStatistiquesFormations());
			request.getSession().setAttribute("retour", "gestionLog.admin");
			return new ModelAndView("statsFormations", modele);
			
		} else if(requete.equals("statsRues")) {
			HashMap modele = new HashMap();
			modele.put("statistiquesAnnuaire", metier.getStatistiquesRues());
			request.getSession().setAttribute("retour", "gestionLog.admin");
			return new ModelAndView("statsRues", modele);
			
		} else if(requete.equals("statsCPAS")) {
			HashMap modele = new HashMap();
			modele.put("statistiques", metier.getStatistiques());
			request.getSession().setAttribute("retour", "gestionLog.admin");
			return new ModelAndView("statsCPAS", modele);
			
		} else if(requete.equals("statsIntranet")) {
			HashMap modele = new HashMap();
			modele.put("statsIntranet", metier.getStatistiquesIntranet());
			request.getSession().setAttribute("retour", "gestionLog.admin");
			return new ModelAndView("statsIntranet", modele);
			
		} else if(requete.equals("logAdmin")) {
			HashMap modele = new HashMap();
			modele.put("logs", metier.getLog("%.admin"));	
			request.getSession().setAttribute("retour", "gestionLog.admin");
			return new ModelAndView("logAdmin", modele);
			
		} else if(requete.equals("logAnnuaire")) {
			HashMap modele = new HashMap();
			modele.put("logs", metier.getLog("%.annuaire"));	
			request.getSession().setAttribute("retour", "gestionLog.admin");
			return new ModelAndView("logAnnuaire", modele);
			
		} else if(requete.equals("logRues")) {
			HashMap modele = new HashMap();
			modele.put("logs", metier.getLog("%.rues"));	
			request.getSession().setAttribute("retour", "gestionLog.admin");
			return new ModelAndView("logRues", modele);
			
		} else if(requete.equals("logFormations")) {
			HashMap modele = new HashMap();
			modele.put("logs", metier.getLogFormationUtilisateur());
			request.getSession().setAttribute("retour", "gestionLog.admin");
			return new ModelAndView("logFormations", modele);
			
		} else if(requete.equals("logFormationsAdministration")) {
			HashMap modele = new HashMap();
			modele.put("logs", metier.getLogFormationAdministration());
			request.getSession().setAttribute("retour", "gestionLog.admin");
			return new ModelAndView("logFormationsAdministration", modele);
			
		} else if(requete.equals("logMessage")) {
			HashMap modele = new HashMap();
			modele.put("logs", metier.getWarnings());	
			request.getSession().setAttribute("retour", "gestionLog.admin");
			return new ModelAndView("logMessage", modele);
			
		} else {
			HashMap modele = new HashMap();	
			request.getSession().setAttribute("retour", "gestionLog.admin");
			return new ModelAndView("gestionLog", modele);
		}
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}

}
