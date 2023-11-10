package be.cpasdeliege.intranet.DemServInfo.controler;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.DemServInfo.dao.DaoDemServInf;
import be.cpasdeliege.intranet.DemServInfo.model.Dsigti;
import be.cpasdeliege.intranet.informatique.model.PrivilegeInformatique;
import be.cpasdeliege.intranet.informatique.model.Tache;
import be.cpasdeliege.intranet.informatique.model.Utilisateur;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class Index implements Controller {

	DomainInterface metier = null;
	DaoDemServInf daoDsi = null;

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		request.getSession().setAttribute("retour", "index.dsi");
		String action = request.getParameter("action");
		String orderby = request.getParameter("orderby");

		String orderbyAccept = "";

		if(orderby == null) {
			orderby = "idDemandes";
			orderbyAccept = "dateExecSouhaiteeSecrConf";
		} else {
			orderbyAccept = orderby;
		}

		HashMap modele = new HashMap();
		PrivilegeInformatique privilege = (PrivilegeInformatique)request.getSession().getAttribute("privilegeInformatique");

		if(action == null) {
			action = "";
		}

		if(action.equals("supprimer")) {
			daoDsi.deleteDemande(request.getParameter("idDemandes"));
		} 

		if(action.equals("fini")) {
			if(privilege.isDsiUser()) {
				Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute("utilisateur");
				String nom = utilisateur.getNom();
				String prenom = utilisateur.getPrenom();

				modele.put("listeDsiValidInfo", daoDsi.getDemandesValidInfo(nom, prenom, orderby));
				modele.put("listeDsiAttente", daoDsi.getDemandesAttente(nom, prenom, orderby));
				modele.put("listeDsiConf", daoDsi.getDemandesConf(nom, prenom, orderby));

				modele.put("statistiques", metier.getStatistiques());
				modele.put("listeDsiNouvelles", daoDsi.getDemandesNouvellesUser(nom, prenom, orderby));
				modele.put("listeDsiValidUser", daoDsi.getDemandesValidationUser(nom, prenom, orderby));
				modele.put("listeDsiAcceptees", daoDsi.getDemandesAccepteesUser(nom, prenom, orderbyAccept));
				modele.put("listeDsiExecutees", daoDsi.getDemandesExecuteesUser(nom, prenom, orderby));
				modele.put("listeDsiRefusees", daoDsi.getDemandesRefuseesUser(nom, prenom, orderby));
				return new ModelAndView("finiUser", modele);
			} else if(privilege.isDsiChef()) {
				Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute("utilisateur");
				String nom = utilisateur.getNom();
				String prenom = utilisateur.getPrenom();

				modele.put("listeDsiValidInfo", daoDsi.getDemandesValidInfo(nom, prenom, orderby));
				modele.put("listeDsiAttente", daoDsi.getDemandesAttente(nom, prenom, orderby));
				modele.put("listeDsiConf", daoDsi.getDemandesConf(nom, prenom, orderby));

				modele.put("statistiques", metier.getStatistiques());
				modele.put("listeDsiNouvelles", daoDsi.getDemandesNouvellesChef(nom, prenom, orderby));
				modele.put("listeDsiValidUser", daoDsi.getDemandeValidationChef(nom, prenom, orderby));
				modele.put("listeDsiAcceptees", daoDsi.getDemandesAccepteesChef(nom, prenom, orderbyAccept));
				modele.put("listeDsiExecutees", daoDsi.getDemandesExecuteesChef(nom, prenom, orderby));
				modele.put("listeDsiRefusees", daoDsi.getDemandesRefuseesChef(nom, prenom, orderby));
				return new ModelAndView("finiChefDep", modele);
			} else {
				String nom = "%";
				String prenom = "%";

				modele.put("statistiques", metier.getStatistiques());
				modele.put("listeDsiNouvelles", daoDsi.getDemandesNouvelles(nom, prenom, orderby));
				modele.put("listeDsiValidInfo", daoDsi.getDemandesValidInfo(nom, prenom, orderby));
				modele.put("listeDsiValidUser", daoDsi.getDemandesValidUser(nom, prenom, orderby));
				modele.put("listeDsiConf", daoDsi.getDemandesConf(nom, prenom, orderby));
				modele.put("listeDsiAcceptees", daoDsi.getDemandesAcceptees(nom, prenom, orderbyAccept));
				modele.put("listeDsiExecutees", daoDsi.getDemandesExecutees(nom, prenom, orderby));
				modele.put("listeDsiAttente", daoDsi.getDemandesAttente(nom, prenom, orderby));
				modele.put("listeDsiRefusees", daoDsi.getDemandesRefusees(nom, prenom, orderby));
				return new ModelAndView("fini", modele);
			}
		} else if(action.equals("refusees")) {
			String nom = "%";
			String prenom = "%";

			modele.put("statistiques", metier.getStatistiques());
			modele.put("listeDsiRefusees", daoDsi.getDemandesRefusees(nom, prenom, orderby));
			return new ModelAndView("refusees", modele);
			
		} else if(action.equals("supprimees")) {
			String nom = "%";
			String prenom = "%";

			modele.put("statistiques", metier.getStatistiques());
			modele.put("listeDsiSupprimees", daoDsi.getDemandesSupprimees(nom, prenom, orderby));
			return new ModelAndView("supprimees", modele);
			
		} else {
			if(privilege.isDsiUser()) {
				Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute("utilisateur");
				String nom = utilisateur.getNom();
				String prenom = utilisateur.getPrenom();
				
				modele.put("StatDem", daoDsi.getStat());

				modele.put("listeDsiValidInfo", daoDsi.getDemandesValidInfo(nom, prenom, orderby));
				modele.put("listeDsiAttente", daoDsi.getDemandesAttente(nom, prenom, orderby));
				modele.put("listeDsiConf", daoDsi.getDemandesConf(nom, prenom, orderby));

				modele.put("statistiques", metier.getStatistiques());
				modele.put("listeDsiNouvelles", daoDsi.getDemandesNouvellesUser(nom, prenom, orderby));
				modele.put("listeDsiValidUser", daoDsi.getDemandesValidationUser(nom, prenom, orderby));
				
				modele.put("listeDsiValidUserEnAttente", daoDsi.getDemandesValidationUserEnAttente(nom, prenom, orderby));
				
				modele.put("listeDsiAcceptees", daoDsi.getDemandesAccepteesUser(nom, prenom, orderbyAccept));
				modele.put("listeDsiExecutees", daoDsi.getDemandesExecuteesUser(nom, prenom, orderby));
				modele.put("listeDsiRefusees", daoDsi.getDemandesRefuseesUser(nom, prenom, orderby));
				return new ModelAndView("indexUser", modele);
			} else if(privilege.isDsiChef()) {
				Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute("utilisateur");
				String nom = utilisateur.getNom();
				String prenom = utilisateur.getPrenom();
				
				modele.put("StatDem", daoDsi.getStat());

				modele.put("listeDsiValidInfo", daoDsi.getDemandesValidInfo(nom, prenom, orderby));
				modele.put("listeDsiAttente", daoDsi.getDemandesAttente(nom, prenom, orderby));
				modele.put("listeDsiConf", daoDsi.getDemandesConf(nom, prenom, orderby));

				modele.put("statistiques", metier.getStatistiques());
				modele.put("listeDsiNouvelles", daoDsi.getDemandesNouvellesChef(nom, prenom, orderby));
				modele.put("listeDsiValidUser", daoDsi.getDemandeValidationChef(nom, prenom, orderby));
				
				modele.put("listeDsiValidUserEnAttente", daoDsi.getDemandesValidationChefEnAttente(nom, prenom, orderby));
				
				modele.put("listeDsiAcceptees", daoDsi.getDemandesAccepteesChef(nom, prenom, orderbyAccept));
				modele.put("listeDsiExecutees", daoDsi.getDemandesExecuteesChef(nom, prenom, orderby));
				modele.put("listeDsiRefusees", daoDsi.getDemandesRefuseesChef(nom, prenom, orderby));
				
				return new ModelAndView("indexChefDep", modele);
			} else {
				String nom = "%";
				String prenom = "%";
				
				daoDsi.getStat();

				modele.put("StatDem", daoDsi.getStat());
				modele.put("statistiques", metier.getStatistiques());
				modele.put("listeDsiNouvelles", daoDsi.getDemandesNouvelles(nom, prenom, orderby));
				
				modele.put("listeDsiValidInfo", daoDsi.getDemandesValidInfo(nom, prenom, orderby));
				modele.put("listeDsiValidInfoEnAttente", daoDsi.getDemandesValidInfoEnAttente(nom, prenom, orderby));
				
				modele.put("listeDsiValidUser", daoDsi.getDemandesValidUser(nom, prenom, orderby));
				modele.put("listeDsiConf", daoDsi.getDemandesConf(nom, prenom, orderby));
				modele.put("listeDsiAcceptees", daoDsi.getDemandesAcceptees(nom, prenom, orderbyAccept));
				modele.put("listeDsiExecutees", daoDsi.getDemandesExecutees(nom, prenom, orderby));
				modele.put("listeDsiAttente", daoDsi.getDemandesAttente(nom, prenom, orderby));
				modele.put("listeDsiRefusees", daoDsi.getDemandesRefusees(nom, prenom, orderby));
				List<Dsigti> liste = daoDsi.getDsigti();
				updateDSIGTI(liste);
				modele.put("dsigti", daoDsi.getDsigti());
				return new ModelAndView("index", modele);
			}
		}
	}

	private void updateDSIGTI(List<Dsigti> liste) {
		for (Dsigti dsigti : liste) {
			Tache tache = metier.getTache(dsigti.getIdPlanning());
			if (tache.getDateFin() == null) {
				if (dsigti.getFini().equals("1")) {
					dsigti.setFini("0");
					daoDsi.updateDsigti(dsigti);
				}
			} else {
				if (dsigti.getFini().equals("0")) {
					dsigti.setFini("1");
					daoDsi.updateDsigti(dsigti);
				}
			}
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

}
