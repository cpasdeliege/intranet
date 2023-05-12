package be.cpasdeliege.intranet.DemServInfo.controler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.DemServInfo.dao.DaoDemServInf;
import be.cpasdeliege.intranet.DemServInfo.model.DemServInf;
import be.cpasdeliege.intranet.DemServInfo.model.Remarque;
import be.cpasdeliege.intranet.informatique.model.Utilisateur;
import be.cpasdeliege.intranet.informatique.model.dao.DaoMySQL;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class SplitDemande implements Controller {
	DomainInterface metier = null;
	DaoDemServInf daoDsi = null;
	DaoMySQL dao = null;

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute("utilisateur");
		HashMap modele = new HashMap();
		
		modele.put("listeType", dao.getTypeDemandeDSI());
		
		String idDemande = request.getParameter("idDemande");
		DemServInf demande = daoDsi.getDemande(idDemande);
		
		modele.put("typeDemande", demande.getTypeDemande());
		
		String action = request.getParameter("action");
		if(action == null) {
			modele.put("dsi", demande);
			return new ModelAndView("splitDemande", modele);
		} else if(action.equals("Spliter")){
			demande.setTitre(request.getParameter("titre"));
			demande.setDescription(request.getParameter("description"));
			demande.setTypeDemande(request.getParameter("typeDemande"));
			int id = daoDsi.addDemande(demande);
			
			
			GregorianCalendar now = new GregorianCalendar();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			// Ajout remarque dans Demande parent
			Remarque rem = new Remarque();
			rem.setIdDemande(idDemande);
			rem.setTexte("/!\\ Demande scind�e.<br><br>" +
					"<a href=\"afficherDemande.dsi?idDemandes=" + id + "\">Voir la demande fille (n� " + id + ")</a>");
			rem.setUser(utilisateur.getPrenom() + " " + utilisateur.getNom());
			rem.setDate(sdf.format(now.getTime()));
			daoDsi.addRemarque(rem);
			
			// Ajout remarque dans Demande enfant
			rem = new Remarque();
			rem.setIdDemande(""+id);
			rem.setTexte("/!\\ Cette demande a �t� cr��e pour scinder la demande n� " + idDemande + ".<br><br>" +
					"<a href=\"afficherDemande.dsi?idDemandes=" + idDemande + "\">Voir la demande originale</a>");
			rem.setUser(utilisateur.getPrenom() + " " + utilisateur.getNom());
			rem.setDate(sdf.format(now.getTime()));
			daoDsi.addRemarque(rem);
			
			response.sendRedirect("afficherDemande.dsi?idDemandes=" + id);
			return null;
		} else {
			response.sendRedirect("afficherDemande.dsi?idDemandes=" + idDemande);
			return null;
		}
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}

	public DaoDemServInf getDaoDsi() {
		return daoDsi;
	}

	public void setDaoDsi(DaoDemServInf daoDsi) {
		this.daoDsi = daoDsi;
	}

	public DaoMySQL getDao() {
		return dao;
	}

	public void setDao(DaoMySQL dao) {
		this.dao = dao;
	}
}
