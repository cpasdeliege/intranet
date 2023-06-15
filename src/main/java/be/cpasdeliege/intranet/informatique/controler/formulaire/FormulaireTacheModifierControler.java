package be.cpasdeliege.intranet.informatique.controler.formulaire;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
//import org.apache.commons.mail.EException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import be.cpasdeliege.intranet.informatique.model.Personnel;
import be.cpasdeliege.intranet.informatique.model.Tache;
import be.cpasdeliege.intranet.informatique.model.TicketItem;
import be.cpasdeliege.intranet.informatique.model.Utilisateur;
import be.cpasdeliege.intranet.informatique.model.dao.DaoMySQL;
import be.cpasdeliege.intranet.informatique.model.dao.DaoPersonnel;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class FormulaireTacheModifierControler extends SimpleFormController {
	DomainInterface metier = null;
	DaoMySQL dao = null;

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object formulaire,
			BindException exception) throws Exception {
		/*
		 * ((Tache) formulaire).setIncident(new String(((Tache)
		 * formulaire).getIncident().getBytes(),"UTF-8")); ((Tache)
		 * formulaire).setPersonnel(new String(((Tache)
		 * formulaire).getPersonnel().getBytes(),"UTF-8")); ((Tache)
		 * formulaire).setPersonnelInfo(new String(((Tache)
		 * formulaire).getPersonnelInfo().getBytes(),"UTF-8")); ((Tache)
		 * formulaire).setService(new String(((Tache)
		 * formulaire).getService().getBytes(),"UTF-8")); ((Tache)
		 * formulaire).setTitre(new String(((Tache)
		 * formulaire).getTitre().getBytes(),"UTF-8")); ((Tache) formulaire).setType(new
		 * String(((Tache) formulaire).getType().getBytes(),"UTF-8"));
		 */

		System.out.println("-------------- DEBUG PIERRE BEFORE TASK FORM -----------");

		((Tache) formulaire).setIncident(((Tache) formulaire).getIncident());
		((Tache) formulaire).setPersonnel(((Tache) formulaire).getPersonnel());
		((Tache) formulaire).setPersonnelInfo(((Tache) formulaire).getPersonnelInfo());
		((Tache) formulaire).setService(((Tache) formulaire).getService());
		((Tache) formulaire).setTitre(((Tache) formulaire).getTitre());
		((Tache) formulaire).setType(((Tache) formulaire).getType());
		request.getSession().setAttribute("formulaireTacheModifier", formulaire);

		String tmp = request.getParameter("enAttente");
		if (tmp == null) {
			((Tache) formulaire).setEnAttente(false);
		}
		if (testFormulaire(request, formulaire)) {

			Tache tache = (Tache) formulaire;

			Tache oldTache = metier.getTache("" + tache.getIdPlanning());
			((Tache) formulaire).setIncident(oldTache.getIncident());
			((Tache) formulaire).setHeureIncident(oldTache.getHeureIncident());
			((Tache) formulaire).setMinuteIncident(oldTache.getMinuteIncident());

			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
			// System.out.println("Freeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeed :
			// nom="+utilisateur.getNom()+" prenom="+utilisateur.getPrenom());
			// mail si tache terminée
			if (oldTache.getDateFin() == null && !tache.getDateFin().equals("")) {

				DaoPersonnel from = ((List<DaoPersonnel>) dao.getListePersonnels(utilisateur.getNom(),
						utilisateur.getPrenom())).get(0);
				// Ajout ticket de fin de tache
				TicketItem ticket = new TicketItem();
				ticket.setIdPlanning(tache.getIdPlanning());
				ticket.setTexte("<font color=\"#af391f\">Tâche cloturée par " + utilisateur.getPrenom() + " "
						+ utilisateur.getNom() + " le " + tache.getDateFin() + ".</font>");
				ticket.setUser(utilisateur.getNom());
				metier.addTicketItem(ticket);
				/**********************************************/
				// decommenté le 26/05/2021
				mailFinTache(tache, "juan.hernandez@cpasdeliege.be", from);
				mailFinTache(tache, "nicolas.radoux@cpasdeliege.be", from);
				mailFinTache(tache, "vanessa.verschoren@cpasdeliege.be", from);
				/* ************************************ */
			}

			if (!oldTache.getEcheance().equals(tache.getEcheance())) {
				// Ajout ticket de modification d'échéance
				TicketItem ticket = new TicketItem();
				ticket.setIdPlanning(tache.getIdPlanning());
				ticket.setTexte("<font color=\"#af391f\">Modification échéance ==>   " + tache.getEcheance()
						+ "   (ancienne " + oldTache.getEcheance() + ")</font>");
				ticket.setUser(utilisateur.getNom());
				metier.addTicketItem(ticket);
			}

			if (!oldTache.getPersonnelInfo().equals(tache.getPersonnelInfo())) {
				// Ajout ticket de modification de coordinateur
				TicketItem ticket = new TicketItem();
				ticket.setIdPlanning(tache.getIdPlanning());
				ticket.setTexte("<font color=\"#af391f\">Modification Coordinateur ==> " + tache.getPersonnelInfo()
						+ "   (ancien " + oldTache.getPersonnelInfo() + ")</font>");
				/* ********************************************** */
				System.out.println("modification ****************************");
				// System.out.println(" user c coordinateur ==>" + tache.getPersonnelInfo());
				System.out.println(" user c coordinateur ==>" + tache.getPersonnelInfo());
				System.out.println(" user c coordinateur ==>" + new String(tache.getPersonnelInfo().getBytes(), "UTF-8"));
				// recuperre id de l'utuilisateur : tache.getPersonnelInfo()
				// mettre à jour id du coordinateur dans la table tache
				/* *********************************************/
				String[] coord = tache.getPersonnelInfo().split(",");
				String[] nomPrenomPerso = tache.getPersonnel().split(",");

				// Personnel p = domain.getPersonnel(coord[0], coord[1]);

				if (coord.length == 2) {
					// System.out.println(" user c coordinateur nom ==>" + coord[0] + " , Prenom =>"
					// + coord[1]);
					System.out.println(" user c coordinateur  nom ==>" + new String(coord[0].getBytes(), "UTF-8")
							+ " , Prenom =>" + new String(coord[1].getBytes(), "UTF-8"));
					// Personnel coordinateur = metier.getPersonnel(new
					// String(coord[0].getBytes(),"UTF-8").trim(), new
					// String(coord[1].getBytes(),"UTF-8").trim());
					Personnel coordinateur = metier.getPersonnel(coord[0].trim(), coord[1].trim());
					System.out.println(" id =>" + coordinateur.getId_personnel() + " nom =>" + coordinateur.getNom()
							+ " , login window ==>" + coordinateur.getLoginWindows());
					tache.setId_coordinateur(coordinateur.getId_personnel());
				}
				if (nomPrenomPerso.length == 2) {
					Personnel p = metier.getPersonnel(nomPrenomPerso[0].trim(), nomPrenomPerso[1].trim());
					// Personnel p = metier.getPersonnel(new
					// String(nomPrenomPerso[0].getBytes(),"UTF-8").trim(), new
					// String(nomPrenomPerso[1].getBytes(),"UTF-8").trim());
					System.out.println("Personnel : id ==> " + p.getId_personnel() + "nom =>" + p.getNom());
					tache.setId_personnel(p.getId_personnel());
				}

				ticket.setUser(utilisateur.getNom());
				metier.addTicketItem(ticket);
			}

			tache.setDsi(oldTache.isDsi());

			metier.updateTache(tache);
			request.getSession().setAttribute("formulaireTacheModifier", null);
			response.sendRedirect(
					(String) request.getSession().getAttribute("retour") + "#" + ((Tache) formulaire).getIdPlanning());
		} else {
			response.sendRedirect("gestionTache.admin?idplanning=" + ((Tache) formulaire).getIdPlanning());
		}
		return super.onSubmit(request, response, formulaire, exception);
	}

	// @Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		Tache tache = (Tache) request.getSession().getAttribute("formulaireTacheModifier");
		String idPlanning = request.getParameter("idPlanning");
		if (tache == null) {
			tache = metier.getTache(idPlanning);
		}
		return tache;
	}

	@SuppressWarnings("unchecked")
	protected Map referenceData(HttpServletRequest request) throws Exception {

		Map data = new HashMap();
		// int[] services=new int[] {34,127,128};
		// data.put("listePersInfo", metier.getListePersonnelService(34, "%", "%"));
		// data.put("listePersInfo", metier.getListePersonnelService(128, "%", "%"));
		// data.put("listePersInfo", metier.getListePersonnelService(services, "%",
		// "%"));
		data.put("listePersInfo", metier.getListePersonnelService());
		data.put("listeService", metier.getListeServices());
		data.put("listeOrdinateur", metier.getListeOrdinateurs());
		data.put("listeEmploye", metier.getListePersonnelTexte());
		data.put("listeTicketItem", metier.getListeTicketItem(Integer.parseInt(request.getParameter("idPlanning"))));
		data.put("listePersonnelInfo", metier.getListePersonnelInfoTexte());
		data.put("annexes", getAnnexes(request));
		data.put("listeType", dao.getTypeDemandeGTI());
		return data;
	}

	private String[] getAnnexes(HttpServletRequest request) {
		File tmp = new File("c:\\fop");
		if (!tmp.exists()) {
			File rep = new File("/opt/fop/GTI/" + request.getParameter("idPlanning"));
			String[] liste = rep.list();
			if (liste == null) {
				return new String[0];
			} else {
				return rep.list();
			}
		} else {
			File rep = new File("c:\\fop\\GTI\\" + request.getParameter("idPlanning"));
			String[] liste = rep.list();
			if (liste == null) {
				return new String[0];
			} else {
				return rep.list();
			}
		}
	}

	private void mailFinTache(Tache tache, String dest, DaoPersonnel from) {
		try {
			Email email = new SimpleEmail();
			email.setHostName("mail.cpasdeliege.be");
			email.setSmtpPort(25);
			email.setAuthenticator(new DefaultAuthenticator("frederic.delree", "rksZRfh$"));
//			email.setSSLOnConnect(true);
//			email.setTLS(true);
			email.setFrom(from.getEmail(), from.getPrenom() + " " + from.getNom());
			email.setSubject("TI " + tache.getIdPlanning() + " ** 9.CLOTURE** - " + tache.getTitre());
			email.setMsg("Bonjour,\n\nNotification de la clôture de la tâche n°" + tache.getIdPlanning() + " ("
					+ tache.getTitre() + ") " + "\n\n"
					+ "Voir la tâche sur l'intranet : http://intranet/gestionTache.admin?idPlanning="
					+ tache.getIdPlanning() + "\n\nBonne journée.");
			email.addTo(dest);
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

	public DomainInterface getMetier() {
		return metier;
	}

	private boolean testFormulaire(HttpServletRequest request, Object formulaire) {
		Tache tache = (Tache) formulaire;
		if (tache.getService().equals("-")) {
			request.getSession().setAttribute("erreurFormulaireTacheModifier",
					"Il faut que la tâche soit associée à un service !");
			return false;
		} else {
			request.getSession().setAttribute("erreurFormulaireTacheModifier", null);
			return true;
		}
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
