package be.cpasdeliege.intranet.informatique.controler.formulaire;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.validation.BindException;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import be.cpasdeliege.intranet.DemServInfo.dao.DaoDemServInf;
import be.cpasdeliege.intranet.DemServInfo.model.Dsigti;
import be.cpasdeliege.intranet.DemServInfo.model.Remarque;
import be.cpasdeliege.intranet.informatique.model.Tache;
import be.cpasdeliege.intranet.informatique.model.TicketItem;
import be.cpasdeliege.intranet.informatique.model.Utilisateur;
import be.cpasdeliege.intranet.informatique.model.dao.DaoMySQL;
import be.cpasdeliege.intranet.informatique.model.dao.DaoPersonnel;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;
import be.cpasdeliege.intranet.utils.Fichier;

public class FormulaireTacheAjouterControler extends SimpleFormController {
	DomainInterface metier = null;
	DaoMySQL dao = null;
	DaoDemServInf daoDsi = null;

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object formulaire,
			BindException exception) throws Exception {
		request.getSession().setAttribute("formulaireTacheAjouter", formulaire);

		System.out.println("01");
		Tache tmpformulaire = (Tache) formulaire;
		System.out.println("02");
		String incident = request.getParameter("incident");
		System.out.println("03");
		String heureIncident = request.getParameter("heureIncident");
		System.out.println("04");
		String minuteIncident = request.getParameter("minuteIncident");

		System.out.println("05");
		HttpSession session = request.getSession();
		System.out.println("06");
		session.setAttribute("incident", incident);
		System.out.println("07");
		session.setAttribute("heureIncident", heureIncident);
		System.out.println("08");
		session.setAttribute("minuteIncident", minuteIncident);

		System.out.println("09");
		if (incident != null) {
			tmpformulaire.setIncident("1");
		} else {
			tmpformulaire.setIncident("0");
		}

		System.out.println("10");
		if (heureIncident != null) {
			tmpformulaire.setHeureIncident(heureIncident);
		} else {
			tmpformulaire.setHeureIncident("");
		}

		System.out.println("11");
		if (minuteIncident != null) {
			tmpformulaire.setMinuteIncident(minuteIncident);
		} else {
			tmpformulaire.setMinuteIncident("");
		}

		System.out.println("12");
		if (testFormulaire(request, formulaire)) {
			session.setAttribute("incident", null);
			session.setAttribute("heureIncident", null);
			session.setAttribute("minuteIncident", null);
			int idPlanning = metier.addTache((Tache) formulaire);
			String texte = request.getParameter("travail");
			// if(!texte.trim().equals("")) {
			TicketItem ticket = new TicketItem();
			ticket.setIdPlanning(idPlanning);
			ticket.setTexte(texte);
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
			ticket.setUser(utilisateur.getNom());
			metier.addTicketItem(ticket);

			String idDemande = (String) request.getSession().getAttribute("idDemande");

			if (idDemande != null) {
				request.getSession().setAttribute("idDemande", null);
				// marquage de la tache comme venant du DSI

				Tache tmpTache = dao.getTache("" + idPlanning);
				tmpTache.setDsi(true);
				tmpTache.setDateFin("");
				dao.updateTache(tmpTache);

				// ajout relation entre DSI et GTI

				// DemServInf demande = daoDsi.getDemande(idDemande);
				// demande.setIdPlanning(""+idPlanning);
				// daoDsi.updateDemande(demande);

				Dsigti dsigti = new Dsigti();
				dsigti.setIdDemande(idDemande);
				dsigti.setIdPlanning("" + idPlanning);
				daoDsi.addDsigti(dsigti);

				// ajout remarque avec numéro de tache dans DSI
				//
				// Remarque rem = new Remarque();
				// GregorianCalendar now = new GregorianCalendar();
				// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				// rem.setIdDemande(idDemande);
				// rem.setTexte("/!\\&nbsp;&nbsp;/!\\&nbsp;&nbsp;<a
				// href=\"gestionTache.admin?idPlanning=" + idPlanning + "\">Tâche informatique
				// n°" + idPlanning + "</a>");
				// rem.setUser(utilisateur.getPrenom() + " " + utilisateur.getNom());
				// rem.setDate(sdf.format(now.getTime()));
				//
				// daoDsi.addRemarque(rem);

				// ajout de toutes les remarques du DSI dans le GTI

				List<Remarque> remarques = daoDsi.getRemarques(idDemande);
				for (Remarque remarque : remarques) {
					if (!remarque.getTexte().startsWith("/!\\&nbsp;&nbsp;/!\\")
							&& !remarque.getTexte().startsWith("Validation de la demande")) {
						ticket = new TicketItem();
						ticket.setIdPlanning(idPlanning);
						ticket.setTexte(remarque.getTexte());
						ticket.setUser(remarque.getUser());
						metier.addTicketItem(ticket);
					}
				}

				// ajout de toute les annexes du DSI dans le GTI

				File tmp = new File("c:\\fop");
				File repSource = new File("c:\\fop\\DSI\\" + idDemande);
				File repDest = new File("c:\\fop\\GTI\\" + idPlanning);

				if (!tmp.exists()) {
					repSource = new File("/opt/fop/DSI/" + idDemande);
					repDest = new File("/opt/fop/GTI/" + idPlanning);
				}
				if (repSource.exists()) {
					repDest.mkdirs();
					File listRepSource[] = repSource.listFiles();

					for (File file : listRepSource) {
						Fichier.copy(file, repDest);
					}
				}

				// mail à celui à qui est destinée la tâches
				Tache tache = (Tache) formulaire;
				tache.setIdPlanning(idPlanning);
				String[] tmpEmployeInfo = tache.getPersonnelInfo().split(", ");
				if (tmpEmployeInfo.length == 2) {
					DaoPersonnel pers = ((List<DaoPersonnel>) dao.getListePersonnels(tmpEmployeInfo[0],
							tmpEmployeInfo[1])).get(0);
					mailNouvelleTacheDSI(tache, pers.getEmail());
				}

			} else {
				// tâche non DSI --> envoi mail à juan si pas juan qui l'a encodé
				DaoPersonnel from = ((List<DaoPersonnel>) dao.getListePersonnels(utilisateur.getNom(),
						utilisateur.getPrenom())).get(0);
				Tache tache = (Tache) formulaire;
				tache.setIdPlanning(idPlanning);

				if (!utilisateur.getNom().equals("Hernandez")) {
					mailNouvelleTache(tache, "juan.hernandez@cpasdeliege.be", from);
					mailNouvelleTache(tache, "nicolas.radoux@cpasdeliege.be", from);
					// mailNouvelleTache(tache, "theodore.nzaramba@cpasdeliege.be",from);
				}

				// mail à celui à qui est destinée la tâches si c'est pas lui que la crée
				String[] tmpEmployeInfo = tache.getPersonnelInfo().split(", ");
				if (!utilisateur.getNom().equals(tmpEmployeInfo[0])
						&& !utilisateur.getPrenom().equals(tmpEmployeInfo[1])) {
					tache.setIdPlanning(idPlanning);
					if (tmpEmployeInfo.length == 2) {
						DaoPersonnel pers = ((List<DaoPersonnel>) dao.getListePersonnels(tmpEmployeInfo[0],
								tmpEmployeInfo[1])).get(0);
						// mailNouvelleTacheDSI(tache, pers.getEmail());
						mailNouvelleTache(tache, pers.getEmail(), from);
					}
				}
			}
			// }
			request.getSession().setAttribute("formulaireTacheAjouter", null);
			response.sendRedirect((String) request.getSession().getAttribute("retour") + "#" + idPlanning);
		} else {
			response.sendRedirect("formulaireTacheAjouter.admin");
		}
		System.out.println("13");
		return super.onSubmit(request, response, formulaire, exception);
	}

	private void mailNouvelleTacheDSI(Tache tache, String adresse) {

		try {
			Email email = new SimpleEmail();
			email.setHostName("mail.cpasdeliege.be");
			email.setSmtpPort(25);
			email.setAuthenticator(new DefaultAuthenticator("frederic.delree", "rksZRfh$"));
			// email.setSSLOnConnect(true);
			// email.setTLS(true);
			email.setFrom("juan.hernandez@cpasdeliege.be", "Juan Hernandez");
			email.setSubject("DSI - nouvelle tâche");
			email.setMsg("Bonjour,\n\nMerci de bien vouloir t'occuper de la tâche n°" + tache.getIdPlanning() + " ("
					+ tache.getTitre() + ") " + "et de me prévenir dès qu'elle est achevée.\n\n"
					+ "Voir la tâche sur l'intranet : http://intranet/gestionTache.admin?idPlanning="
					+ tache.getIdPlanning() + "\n\nBonne journée.");
			email.addTo(adresse);
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}

	}

	private void mailNouvelleTache(Tache tache, String dest, DaoPersonnel from) {

		try {
			Email email = new SimpleEmail();
			email.setHostName("mail.cpasdeliege.be");
			email.setSmtpPort(25);
			email.setAuthenticator(new DefaultAuthenticator("frederic.delree", "rksZRfh$"));
			// email.setSSLOnConnect(true);
			// email.setTLS(true);ww
			email.setFrom(from.getEmail(), from.getPrenom() + " " + from.getNom());
			email.setSubject("TI " + tache.getIdPlanning() + " ** 1.CREATION** - " + tache.getTitre());
			email.setMsg("Bonjour,\n\nNotification de la création de la tâche n°" + tache.getIdPlanning() + " ("
					+ tache.getTitre() + ") " + "\n\n"
					+ "Voir la tâche sur l'intranet : http://intranet/gestionTache.admin?idPlanning="
					+ tache.getIdPlanning() + "\n\nBonne journée.");
			email.addTo(dest);
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		Tache tache = (Tache) request.getSession().getAttribute("formulaireTacheAjouter");
		if (tache == null) {
			System.out.println(request.getParameterNames());
			tache = new Tache();
			// String service =
			// URLDecoder.decode(request.getParameter("serviceFormulaire"),"UTF-8");
			String service = request.getParameter("serviceFormulaire");
			String nom = request.getParameter("nomFormulaire");
			// String nom =
			// URLDecoder.decode(request.getParameter("nomFormulaire"),StandardCharsets.UTF_8.name());
			String prenom = request.getParameter("prenomFormulaire");
			String ordinateur = request.getParameter("ordinateurFormulaire");
			// String ordinateur =
			// URLDecoder.decode(request.getParameter("ordinateurFormulaire"),"UTF-8");

			String titre = request.getParameter("titreFormulaire");
			String description = request.getParameter("descriptionFormulaire");
			String echeance = request.getParameter("echeanceFormulaire");

			String idDemande = request.getParameter("idDemande");
			String typeDemande = request.getParameter("typeDemande");

			System.out.println("(" + typeDemande + ")");

			String incident = request.getParameter("incident");
			String heureIncident = request.getParameter("heureIncident");
			String minuteIncident = request.getParameter("minuteIncident");

			// System.out.println("i : " + incident);
			// System.out.println("h : " + heureIncident);
			// System.out.println("m : " + minuteIncident);

			if (incident != null) {
				tache.setIncident("1");
			} else {
				tache.setIncident("0");
			}

			if (heureIncident != null) {
				tache.setHeureIncident(heureIncident);
			} else {
				tache.setHeureIncident("");
			}

			if (minuteIncident != null) {
				tache.setMinuteIncident(minuteIncident);
			} else {
				tache.setMinuteIncident("");
			}

			if (service != null) {
				tache.setService(service);
			}
			if (nom != null && prenom != null) {
				tache.setPersonnel(nom + ", " + prenom);
			}
			if (ordinateur != null) {
				tache.setOrdinateur(ordinateur);
			}

			if (titre != null) {
				tache.setTitre(titre);
			}
			if (description != null) {
				request.setAttribute("description", description);
			}
			if (echeance != null) {
				tache.setEcheance(echeance);
			}
			if (idDemande != null) {
				request.getSession().setAttribute("idDemande", idDemande);
			}
			if (typeDemande != null) {
				tache.setType(typeDemande);
			}
		}
		return tache;
	}

	@SuppressWarnings("unchecked")
	protected Map referenceData(HttpServletRequest request) throws Exception {
		List<String> heure = new ArrayList<String>();
		List<String> minute = new ArrayList<String>();
		for (int i = 7; i < 18; i++) {
			if (i > 9) {
				heure.add("" + i);
			} else {
				heure.add("0" + i);
			}
		}

		for (int i = 0; i < 59; i = i + 5) {
			if (i > 9) {
				minute.add("" + i);
			} else {
				minute.add("0" + i);
			}
		}
		Map data = new HashMap();
		data.put("listeService", metier.getListeServices());
		data.put("listeOrdinateur", metier.getListeOrdinateurs());
		data.put("listeEmploye", metier.getListePersonnelTexte());
		data.put("listePersonnelInfo", metier.getListePersonnelInfoTexte());
		data.put("listeType", dao.getTypeDemandeGTI());
		data.put("listeHeure", heure);
		data.put("listeMinute", minute);
		return data;
	}

	public DomainInterface getMetier() {
		return metier;
	}

	private boolean testFormulaire(HttpServletRequest request, Object formulaire) {
		Tache tache = (Tache) formulaire;
		if (tache.getService().equals("-")) {
			request.getSession().setAttribute("erreurFormulaireTacheAjouter",
					"Il faut que la tâche soit associée à un service !");
			return false;
		} else if (tache.getIncident().equals("1")
				&& (tache.getHeureIncident().equals("-") || tache.getMinuteIncident().equals("-"))) {
			request.getSession().setAttribute("erreurFormulaireTacheAjouter",
					"Il faut indiquer l'heure de l'incident !");
			return false;
		} else {
			request.getSession().setAttribute("erreurFormulaireTacheAjouter", null);
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

	public DaoDemServInf getDaoDsi() {
		return daoDsi;
	}

	public void setDaoDsi(DaoDemServInf daoDsi) {
		this.daoDsi = daoDsi;
	}

}
