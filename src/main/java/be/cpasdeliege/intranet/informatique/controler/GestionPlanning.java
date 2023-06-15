package be.cpasdeliege.intranet.informatique.controler;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//import sun.nio.cs.StandardCharsets;

//import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import be.cpasdeliege.intranet.informatique.model.TacheListe;
import be.cpasdeliege.intranet.informatique.model.Utilisateur;
import be.cpasdeliege.intranet.informatique.model.dao.DaoMySQL;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;
import be.cpasdeliege.intranet.utils.Utils;

public class GestionPlanning implements Controller {

	DomainInterface metier = null;
	DaoMySQL dao = null;

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		String action = arg0.getParameter("action");
		if(action != null && action.equals("incidents")) {
			
			String triOuvert = arg0.getParameter("triOuvert");
			String triFerme = arg0.getParameter("triFerme");
			if(triOuvert == null) {
				triOuvert = "idPlanning";
			}
			if(triFerme == null) {
				triFerme = "dateFin";
			}
			
			HashMap modele = new HashMap();
			modele.put("incidentsOuverts", dao.getListeIncidentsOuverts(triOuvert));
			modele.put("incidentsFermes", dao.getListeIncidentsFermes(triFerme));
			arg0.getSession().setAttribute("retour", "gestionPlanning.admin?action=incidents");
			return new ModelAndView("gestionIncidents", modele);
		} else {
			HashMap modele = new HashMap();
			
			String csv = arg0.getParameter("csv");
			String triEnCours = arg0.getParameter("triEnCours");
			String triEnAttente = arg0.getParameter("triEnAttente");
			String triEnFini = arg0.getParameter("triEnFini");
			
			if(triEnAttente == null) {
				triEnAttente = "idPlanning";
			}
			
			Utilisateur utilisateur = (Utilisateur)arg0.getSession().getAttribute("utilisateur");
			
			String tachePerso = arg0.getParameter("tachePerso");
			String titreMesTaches = "";
			
			if(tachePerso == null) {
				tachePerso = (String)arg0.getSession().getAttribute("tachePerso");
			} else {
				arg0.getSession().setAttribute("tachePerso", tachePerso);
			}
						
			if(tachePerso == null) {
				tachePerso = utilisateur.getNom();
				titreMesTaches = "Liste de mes tâches";
			} else {
				titreMesTaches = "Liste des tâches de " + tachePerso;
			}
			
			modele.put("listePersInfo", metier.getListePersonnelDepartement(8, "%", "%"));
			modele.put("titreMesTaches", titreMesTaches);
			List listeMesTaches = dao.getListeMesTaches("%", "%", "%", "%", triEnCours + ", idPlanning", tachePerso);
			
			modele.put("listeMesTaches", listeMesTaches);
			//modele.put("listeTacheEnCours", metier.getListeTacheEnCours("%", "%", "%", "%", triEnCours + ", idPlanning"));
			modele.put("listeTacheEnAttente", metier.getListeTacheEnAttente("%", "%", "%", "%", triEnAttente));
			//modele.put("listeTachesFinies", metier.getListeTacheFinie("%", "%", "%", "%", triEnFini));
			
			arg0.getSession().setAttribute("retour", "gestionPlanning.admin");
			
			if(csv != null) {
				String[] params = new String[] { "idPlanning", "titre", "nomInfo", "idDemande" };
				Charset UTF8_CHARSET = Charset.forName("UTF-8");
				byte[] bytes = Utils.toCSV(listeMesTaches,params).getBytes(UTF8_CHARSET);
				arg1.setHeader("Content-disposition", "attachment;filename=\"planning.csv\"");
				arg1.setContentType("application/csv");
				arg1.setContentLength(bytes.length);
				arg1.setCharacterEncoding("UTF-8");
			    ServletOutputStream outputStream = arg1.getOutputStream();
			    outputStream.write(bytes);
			    outputStream.flush();
			    return null;
			}
			
			
			return new ModelAndView("gestionPlanning", modele);
		}
		
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
