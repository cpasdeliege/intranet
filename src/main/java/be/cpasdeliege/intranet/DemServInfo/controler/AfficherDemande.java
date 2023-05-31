package be.cpasdeliege.intranet.DemServInfo.controler;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.DemServInfo.dao.DaoDemServInf;
import be.cpasdeliege.intranet.DemServInfo.model.DemServInf;
import be.cpasdeliege.intranet.DemServInfo.model.Remarque;
import be.cpasdeliege.intranet.informatique.model.Personnel;
import be.cpasdeliege.intranet.informatique.model.PrivilegeInformatique;
import be.cpasdeliege.intranet.informatique.model.Utilisateur;
import be.cpasdeliege.intranet.informatique.model.dao.DaoMySQL;
import be.cpasdeliege.intranet.informatique.model.dao.DaoPersonnel;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class AfficherDemande implements Controller {

	DomainInterface metier = null;
	DaoDemServInf daoDsi = null;
	DaoMySQL dao = null;

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String remarque = request.getParameter("remarque");
		String action = request.getParameter("action");
		PrivilegeInformatique privilege = (PrivilegeInformatique)request.getSession().getAttribute("privilegeInformatique");
		DemServInf demande = daoDsi.getDemande(Integer.parseInt(request.getParameter("idDemandes")));
		Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute("utilisateur");
		//System.out.println("Freeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeed : on load");
		if(remarque != null) {
			daoDsi.addRemarque(getFormulaireRemarque(request));
			/**
			 * Notifiaction de la remarque à tous les organisateurs concernés
			 */
			//List<DaoPersonnel> listeDirection = metier.getPersonnelDsiDirection();
//			List<DaoPersonnel> listeInfo = metier.getPersonnelDsiInfo();


			List<Remarque> remarques = daoDsi.getRemarques(request.getParameter("idDemandes"));
			Remarque dernirereRemarque = remarques.get(remarques.size() - 1);
			// Notification à tous

			// USER
			Personnel user = metier.getPersonnel(demande.getNom(), demande.getPrenom());
			mailAjoutRemarques(request, demande, user.getEmail(), dernirereRemarque);
			// Responsble
			Personnel chef = metier.getPersonnel(demande.getNomChef(), demande.getPrenomChef());
			if(user.getNom().equals(chef.getNom()) && user.getPrenom().equals(chef.getPrenom())) {
				
			} else {
				mailAjoutRemarques(request, demande, chef.getEmail(), dernirereRemarque);
			}
			
			// Direction
//			for (DaoPersonnel direction : listeDirection) {
//				mailAjoutRemarques(request, demande, direction.getEmail(), dernirereRemarque);
//			}
			// Informatique
//			for (DaoPersonnel info : listeInfo) {
//				mailAjoutRemarques(request, demande, info.getEmail(), dernirereRemarque);
//			}
			
			mailAjoutRemarques(request, demande, "notif.dsi@cpasdeliege.be", dernirereRemarque);


			//			//NOTIFICATION DE CELUI QUI BLOQUE
			//			
			//			if(demande.getValidationChef().equals("none")) {
			//				Personnel chef = metier.getPersonnel(demande.getNomChef(), demande.getPrenomChef());
			//				mailAjoutRemarques(request, demande, chef.getEmail());
			//			} else if (demande.getValidationSecr().equals("none")) {
			//				for (DaoPersonnel direction : listeDirection) {
			//					mailAjoutRemarques(request, demande, direction.getEmail());
			//				}
			//			} else if (demande.getValidationInfor().equals("none")) {
			//				for (DaoPersonnel info : listeInfo) {
			//					mailAjoutRemarques(request, demande, info.getEmail());
			//				}	
			//			} else if (demande.getValidationSecrConf().equals("none")) {
			//				for (DaoPersonnel direction : listeDirection) {
			//					mailAjoutRemarques(request, demande, direction.getEmail());
			//				}
			//			} else if (demande.getDateExecEffective() == null) {
			//				for (DaoPersonnel info : listeInfo) {
			//					mailAjoutRemarques(request, demande, info.getEmail());
			//				}
			//			}

			response.sendRedirect("afficherDemande.dsi?idDemandes=" + request.getParameter("idDemandes") + "#fin");
		}
		if(action != null) {
			if(action.equals("Enregistrer")) {
				//			if(action.equals("modifier")) {


				String validationChef = request.getParameter("validationChef");
				String dateExecSouhaiteeChef = request.getParameter("dateExecSouhaiteeChef");
				String validationSecr = request.getParameter("validationSecr");
				String dateExecSouhaiteeSecr = request.getParameter("dateExecSouhaiteeSecr");
				String validationInfor = request.getParameter("validationInfor");
				String dateExecPrevue = request.getParameter("dateExecPrevue");
				String validationSecrConf = request.getParameter("validationSecrConf");
				String dateExecSouhaiteeSecrConf = request.getParameter("dateExecSouhaiteeSecrConf");

				String dateExecEffective = request.getParameter("dateExecEffective");
				
				String enAttente = request.getParameter("enAttente");
				System.out.println("===========> " + enAttente);

								System.out.println("validationSecr : " + validationSecr);
								System.out.println("dateExecSouhaiteeSecr :--" + dateExecSouhaiteeSecr + "--");
								System.out.println("validationInfor : " + validationInfor);
								System.out.println("dateExecPrevue : " + dateExecPrevue);
								System.out.println("validationSecrConf : " + validationSecrConf);
								System.out.println("dateExecEffective : " + dateExecEffective);

				if(privilege.isDsiChef()) {
					System.out.println("dateExecSouhaiteeChef: " + dateExecSouhaiteeChef);
					if(verifDate(request, dateExecSouhaiteeChef) && demande.getValidationChef().equals("none")){

						if(validationChef != null) {
							ajoutRemarque(""+demande.getIdDemandes(), utilisateur, "Validation de la demande du R à " + validationChef + "");
						} 
						demande.setValidationChef(validationChef);
						demande.setDateExecSouhaiteeChef(dateExecSouhaiteeChef);
						// si accord DG non nécessaire passage à l'étape suivante
						if(!dao.isAccordDG_DSI(demande.getTypeDemande())) {
							demande.setValidationSecr("oui");
							demande.setDateExecSouhaiteeSecr(demande.getDateExecSouhaitee());
							Utilisateur tmp = new Utilisateur();
							tmp.setNom("Message automatique");
							tmp.setPrenom("");
							ajoutRemarque(""+demande.getIdDemandes(), tmp, "Validation implicite de la demande du D à oui");
							mailValidDGInfo(demande);
						}
					} 
				}
				if(privilege.isDsiDirection()) {
					System.out.println("dateExecSouhaiteeSecr: " + dateExecSouhaiteeSecr);
					if(verifDate(request, dateExecSouhaiteeSecr) && verifDate(request, dateExecSouhaiteeSecrConf)){
						if(demande.getValidationSecr().equals("none")) {

							if(validationSecr != null) {
								ajoutRemarque(""+demande.getIdDemandes(), utilisateur, "Validation de la demande du D à " + validationSecr + "");
								mailValidDGInfo(demande);
							} 
							demande.setValidationSecr(validationSecr);
							demande.setDateExecSouhaiteeSecr(dateExecSouhaiteeSecr);
						} else if(demande.getValidationSecrConf().equals("none")) {

							if(validationSecrConf != null) {
								ajoutRemarque(""+demande.getIdDemandes(), utilisateur, "Validation de la demande du D à " + validationSecrConf + "");
							}
							demande.setValidationSecrConf(validationSecrConf);
							demande.setDateExecSouhaiteeSecrConf(dateExecSouhaiteeSecrConf);
						}
					}
				}
				if(privilege.isDsiInfo()) {
					System.out.println("dateExecPrevue: " + dateExecPrevue);
					System.out.println("dateExecEffective: " + dateExecEffective);
					
					if(verifDate(request, dateExecPrevue) && verifDate(request, dateExecEffective)){
						if(demande.getValidationInfor().equals("none")) {

							if(validationInfor != null) {
								ajoutRemarque(""+demande.getIdDemandes(), utilisateur, "Validation de la demande du I à " + validationInfor + "");
							} 
							demande.setValidationInfor(validationInfor);
							demande.setDateExecPrevue(dateExecPrevue);
							
							// regarde si en attente
							
							if(enAttente == null) {
								demande.setEnAttente("0");
							} else {
								demande.setEnAttente("1");
							}
							
							// validation confirmation auto si D et I sont d'accord
							if(demande.getValidationSecr().equals(validationInfor)) {
								if((demande.getDateExecSouhaiteeSecr() != null && dateExecPrevue != null) && (demande.getDateExecSouhaiteeSecr().equals(dateExecPrevue) || validationInfor.equals("non"))) {
									demande.setDateExecSouhaiteeSecrConf(dateExecPrevue);
									demande.setValidationSecrConf(validationInfor);
								} 
							}
						} else {
							demande.setDateExecEffective(dateExecEffective);
							//TI 19505
							ajoutRemarque(""+demande.getIdDemandes(), utilisateur, "Nous venons de clôturer cette DSI. Si cela vous semble prématuré, il vous est toujours possible d'y ajouter une éventuelle remarque dans les 15 jours à venir. Passé ce délai, une nouvelle DSI devra être introduite.");
						}
					}
				}

				daoDsi.updateDemande(demande);

				// notification
				if(demande.getValidationChef().equals("non") || demande.getValidationSecrConf().equals("non")) {
					mailRefus(demande);
				}
				if(demande.getValidationSecrConf().equals("oui") && demande.getDateExecEffective() == null) {  // !!!!! réduire condition d'envoi
					mailAcceptation(demande);
				}
				if(demande.getDateExecEffective() != null) {
					mailExecution(demande);
				}
			} else if(action.equals("Modifier échéance")) {
				String dateModif = request.getParameter("dateModifEcheance");
				System.out.println("dateModif: " + dateModif);
				
				if(verifDate(request, dateModif)){
					if(!dateModif.equals(demande.getDateExecSouhaiteeSecrConf()) && !dateModif.equals("")) {

						String idDemandes = request.getParameter("idDemandes");
						String texte = "Notification automatique du changement de la date d'échéance : ancienne date : " 
							+ demande.getDateExecSouhaiteeSecrConf() + ", nouvelle date : " + dateModif +
							", Une notification par mail a été envoyée aux différents intervenants de cette demande.";
						Remarque rem = new Remarque();
						GregorianCalendar now = new GregorianCalendar();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						rem.setIdDemande(idDemandes);
						rem.setTexte(texte);
						rem.setUser(utilisateur.getPrenom() + " " + utilisateur.getNom());
						rem.setDate(sdf.format(now.getTime()));

						demande.setDateExecSouhaiteeSecrConf(dateModif);
						daoDsi.updateDemande(demande);
						daoDsi.addRemarque(rem);

						mailModifEcheance(demande);
					}
				}
			} else if(action.equals("ajouterAnnexe")) {
				uploadFile(request);
			} else if(action.equals("supprimerAnnexe")) {
				supprimerAnnexe(request);
			} else if(action.equals("supprimer")) {
				return supprimerDemande(request);
			}
		}

		request.getSession().setAttribute("retour", "index.dsi");
		HashMap modele = new HashMap();
		modele.put("statistiques", metier.getStatistiques());
		modele.put("remarques", daoDsi.getRemarques(request.getParameter("idDemandes")));
		modele.put("dsi", daoDsi.getDemande(Integer.parseInt(request.getParameter("idDemandes"))));
		modele.put("dsigti", daoDsi.getDsigti(request.getParameter("idDemandes")));
		modele.put("annexes", getAnnexes(request));
		return new ModelAndView("afficherDemande", modele);
	}

	private void supprimerAnnexe(HttpServletRequest request) {
		int numAnnexe = Integer.parseInt(request.getParameter("numAnnexe"));

		File tmp = new File("c:\\fop");
		if (!tmp.exists()) {
			File rep = new File("/opt/fop/DSI/" + request.getParameter("idDemandes"));
			File[] annexes = rep.listFiles();
			annexes[numAnnexe].delete();

		} else {
			File rep = new File("c:\\fop\\DSI\\" + request.getParameter("idDemandes"));
			File[] annexes = rep.listFiles();
			annexes[numAnnexe].delete();
		}

	}

	private void uploadFile(HttpServletRequest request) throws UnsupportedEncodingException {
		Object fileObject1 = request.getAttribute("annexe");
		if (fileObject1 != null) {
			FileItem fileItem = (FileItem) fileObject1;
			File dest = null;
			File tmp = new File("c:\\fop");
			if (!tmp.exists()) {
				byte[] isoByte = fileItem.getName().getBytes("UTF-8");
				String utf8String = new String(isoByte, "UTF-8");

				new File("/opt/fop/DSI/" + request.getParameter("idDemandes") + "/").mkdirs();
				dest = new File("/opt/fop/DSI/" + request.getParameter("idDemandes") + "/" + utf8String);

			} else {
				new File("c:\\fop\\DSI\\" + request.getParameter("idDemandes") + "\\").mkdirs();
				dest = new File("c:\\fop\\DSI\\" + request.getParameter("idDemandes") + "\\" + fileItem.getName());
			}
			try {
				fileItem.write(dest);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private ModelAndView supprimerDemande(HttpServletRequest request) {
		String idDemande = request.getParameter("idDemandes");
		String justification = request.getParameter("justification");
		if(justification == null) {
			HashMap modele = new HashMap();
			modele.put("statistiques", metier.getStatistiques());
			modele.put("idDemandes", idDemande);
			modele.put("dsi", daoDsi.getDemande(Integer.parseInt(idDemande)));
			return new ModelAndView("supprimerDemande", modele);
		} else {
			if(justification.equals("")) {
				HashMap modele = new HashMap();
				modele.put("erreur", "Vous devez motiver la suppression de la demande !");
				modele.put("statistiques", metier.getStatistiques());
				modele.put("idDemandes", idDemande);
				modele.put("dsi", daoDsi.getDemande(Integer.parseInt(idDemande)));
				return new ModelAndView("supprimerDemande", modele);
			} else {
				Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute("utilisateur");
				String idDemandes = request.getParameter("idDemandes");
				String texte = request.getParameter("justification");
				Remarque rem = new Remarque();
				GregorianCalendar now = new GregorianCalendar();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				rem.setIdDemande(idDemandes);
				rem.setTexte("SUPPRESSION DEMANDE<br><br>motif : " + texte);
				rem.setUser(utilisateur.getPrenom() + " " + utilisateur.getNom());
				rem.setDate(sdf.format(now.getTime()));

				daoDsi.addRemarque(rem);

				DemServInf demande = daoDsi.getDemande(Integer.parseInt(request.getParameter("idDemandes")));
				demande.setDateExecEffective(sdf.format(now.getTime()));

				if(demande.getValidationChef().equals("none")) {
					demande.setValidationChef("supp");
				}

				if(demande.getValidationInfor().equals("none")) {
					demande.setValidationInfor("supp");
				}

				if(demande.getValidationSecr().equals("none")) {
					demande.setValidationSecr("supp");
				}

				if(demande.getValidationSecrConf().equals("none")) {
					demande.setValidationSecrConf("supp");
				}
				demande.setValidationSecrConf("supp");

				daoDsi.updateDemande(demande);


				List<DaoPersonnel> listeDirection = metier.getPersonnelDsiDirection();
//				List<DaoPersonnel> listeInfo = metier.getPersonnelDsiInfo();
//
//				for (DaoPersonnel info : listeInfo) {
//					mailSuppDemande(demande, info.getEmail());
//				}	
				mailSuppDemande(demande, "notif.dsi@cpasdeliege.be");
				for (DaoPersonnel direction : listeDirection) {
					mailSuppDemande(demande, direction.getEmail());
				}

				Personnel chef = metier.getPersonnel(demande.getNomChef(), demande.getPrenomChef());
				mailSuppDemande(demande, chef.getEmail());

				Personnel user = metier.getPersonnel(demande.getNom(), demande.getPrenom());
				mailSuppDemande(demande, user.getEmail());



				HashMap modele = new HashMap();
				modele.put("statistiques", metier.getStatistiques());
				modele.put("remarques", daoDsi.getRemarques(request.getParameter("idDemandes")));
				modele.put("dsi", daoDsi.getDemande(Integer.parseInt(idDemande)));
				modele.put("dsigti", daoDsi.getDsigti(request.getParameter("idDemandes")));
				modele.put("annexes", getAnnexes(request));
				return new ModelAndView("afficherDemande", modele);
			}
		}
	}

	private String[] getAnnexes(HttpServletRequest request) {
		File tmp = new File("c:\\fop");
		if (!tmp.exists()) {
			File rep = new File("/opt/fop/DSI/" + request.getParameter("idDemandes"));
			String[] liste = rep.list();
			if (liste == null) {
				return new String[0];
			} else {
				return rep.list();
			}
		} else {
			File rep = new File("c:\\fop\\DSI\\" + request.getParameter("idDemandes"));
			String[] liste = rep.list();
			if (liste == null) {
				return new String[0];
			} else {
				return rep.list();
			}
		}
	}

	private void mailSuppDemande(DemServInf demande, String adresse) {
		try {
			Email email = new SimpleEmail();
			email.setHostName("mail.cpasdeliege.be");
			email.setSmtpPort(25);
			email.setAuthenticator(new DefaultAuthenticator("frederic.delree", "rksZRfh$"));
			// email.setSSLOnConnect(true);
			// email.setTLS(true);ww
			email.setFrom("no-reply@cpasdeliege.be", "DSI - Intranet");
			email.setSubject("DSI n°" + demande.getIdDemandes() + " - Une demande a été supprimée");
			email.setMsg("Bonjour,\n\nUne demande a été supprimée : demande n°" + demande.getIdDemandes() + " ("
					+ demande.getTitre() + ").\n"
					+ "Le motif de cette suppression peut être lu dans les remarques de la demande.\n\n"
					+ "Voir la demande sur l'intranet : http://intranet/afficherDemande.dsi?idDemandes="
					+ demande.getIdDemandes() + "\n\nBonne journée.");
			email.addTo(adresse);
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

	private void mailModifEcheance(DemServInf demande) {
		try {
			Personnel user = metier.getPersonnel(demande.getNom(), demande.getPrenom());
			Personnel chef = metier.getPersonnel(demande.getNomChef(), demande.getPrenomChef());
			Email email = new SimpleEmail();
			email.setHostName("mail.cpasdeliege.be");
			email.setSmtpPort(25);
			email.setAuthenticator(new DefaultAuthenticator("frederic.delree", "rksZRfh$"));
			// email.setSSLOnConnect(true);
			// email.setTLS(true);
			email.setFrom("no-reply@cpasdeliege.be", "DSI - Intranet");
			email.setSubject("DSI n°" + demande.getIdDemandes() + " - L'échéance de votre demande a été modifiée");
			email.setMsg("Bonjour,\n\nL'échéance de votre demande n°" + demande.getIdDemandes() + " ("
					+ demande.getTitre() + ") a été modifiée.\n\n"
					+ "Voir la demande sur l'intranet : http://intranet/afficherDemande.dsi?idDemandes="
					+ demande.getIdDemandes() + "\n\nBonne journée.");
			email.addTo(user.getEmail());
			email.addTo(chef.getEmail());
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

	private void mailAjoutRemarques(HttpServletRequest request, DemServInf demande, String adresse, Remarque remarque) {

		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		Personnel pers = metier.getPersonnel(utilisateur.getNom(), utilisateur.getPrenom());
		if (!pers.getEmail().equals(adresse)) {
			try {
				Email email = new SimpleEmail();
				email.setHostName("mail.cpasdeliege.be");
				email.setSmtpPort(25);
				email.setAuthenticator(new DefaultAuthenticator("frederic.delree", "rksZRfh$"));
				// email.setSSLOnConnect(true);
				// email.setTLS(true);ww
				email.setFrom("no-reply@cpasdeliege.be", "DSI - Intranet");
				email.setSubject("Ajout remarque : " + demande.getTitre());
				email.setMsg("Bonjour,\n\nUne remarque a été ajoutée sur la demande n°" + demande.getIdDemandes() + " ("
						+ demande.getTitre() + ").\n\n" + remarque.getUser() + " a écrit : \n" + remarque.getTexte()
						+ "\n\n" + "Voir la demande sur l'intranet : http://intranet/afficherDemande.dsi?idDemandes="
						+ demande.getIdDemandes() + "\n\nBonne journée.");
				email.addTo(adresse);
				email.send();
			} catch (EmailException e) {
				e.printStackTrace();
			}
		}
	}

	private void mailRefus(DemServInf demande) {
		try {
			Personnel user = metier.getPersonnel(demande.getNom(), demande.getPrenom());
			Personnel chef = metier.getPersonnel(demande.getNomChef(), demande.getPrenomChef());
			Email email = new SimpleEmail();
			email.setHostName("mail.cpasdeliege.be");
			email.setSmtpPort(25);
			email.setAuthenticator(new DefaultAuthenticator("frederic.delree", "rksZRfh$"));
			// email.setSSLOnConnect(true);
			// email.setTLS(true);ww
			email.setFrom("no-reply@cpasdeliege.be", "DSI - Intranet");
			email.setSubject(
					"DSI n°" + demande.getIdDemandes() + " - Votre demande a été refusée : " + demande.getTitre());
			email.setMsg("Bonjour,\n\nVotre demande n°" + demande.getIdDemandes() + " (" + demande.getTitre()
					+ ") a été refusée.\n\n"
					+ "Voir la demande sur l'intranet : http://intranet/afficherDemande.dsi?idDemandes="
					+ demande.getIdDemandes() + "\n\nBonne journée.");
			email.addTo(user.getEmail());
			email.addTo(chef.getEmail());
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

	private void mailValidDGInfo(DemServInf demande) {
		try {
			System.out.println("mailValidDGInfo");
			Email email = new SimpleEmail();
			email.setHostName("mail.cpasdeliege.be");
			email.setSmtpPort(25);
			email.setAuthenticator(new DefaultAuthenticator("frederic.delree", "rksZRfh$"));
			// email.setSSLOnConnect(true);
			// email.setTLS(true);
			email.setFrom("no-reply@cpasdeliege.be", "DSI - Intranet");
			email.setSubject("Nouvelle demande : n°" + demande.getIdDemandes() + " (" + demande.getTitre() + ")");
			email.setMsg("Bonjour,\n\nLa demande n°" + demande.getIdDemandes() + " (" + demande.getTitre()
					+ "), a été validée par le DG.\n\n"
					+ "Voir la demande sur l'intranet : http://intranet/afficherDemande.dsi?idDemandes="
					+ demande.getIdDemandes() + "\n\nBonne journée.");
//			List<DaoPersonnel> listeInfo = metier.getPersonnelDsiInfo();
//			for (DaoPersonnel info : listeInfo) {
//				email.addTo(info.getEmail());
//			}
			email.addTo("notif.dsi@cpasdeliege.be");
			// email.addTo("lionel.rose@cpasdeliege.be");
			email.send();
			System.out.println("mailValidDGInfo: SEND");
		} catch (EmailException e) {
			System.out.println("EmailException: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private void mailAcceptation(DemServInf demande) {
		try {
			Personnel user = metier.getPersonnel(demande.getNom(), demande.getPrenom());
			Personnel chef = metier.getPersonnel(demande.getNomChef(), demande.getPrenomChef());
			Email email = new SimpleEmail();
			email.setHostName("mail.cpasdeliege.be");
			email.setSmtpPort(25);
			email.setAuthenticator(new DefaultAuthenticator("frederic.delree", "rksZRfh$"));
			// email.setSSLOnConnect(true);
			// email.setTLS(true);
			email.setFrom("no-reply@cpasdeliege.be", "DSI - Intranet");
			email.setSubject(
					"DSI n°" + demande.getIdDemandes() + " - Votre demande a été acceptée : " + demande.getTitre());
			email.setMsg("Bonjour,\n\nVotre demande n°" + demande.getIdDemandes() + " (" + demande.getTitre()
					+ ") a été acceptée.\n\n"
					+ "Voir la demande sur l'intranet : http://intranet/afficherDemande.dsi?idDemandes="
					+ demande.getIdDemandes() + "\n\nBonne journée.");
			email.addTo(user.getEmail());
			email.addTo(chef.getEmail());
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

	private void mailExecution(DemServInf demande) {
		try {
			Personnel user = metier.getPersonnel(demande.getNom(), demande.getPrenom());
			Personnel chef = metier.getPersonnel(demande.getNomChef(), demande.getPrenomChef());
			Email email = new SimpleEmail();
			email.setHostName("mail.cpasdeliege.be");
			email.setSmtpPort(25);
			email.setAuthenticator(new DefaultAuthenticator("frederic.delree", "rksZRfh$"));
			// email.setSSLOnConnect(true);
			// email.setTLS(true);
			email.setFrom("no-reply@cpasdeliege.be", "DSI - Intranet");
			email.setSubject(
					"DSI n°" + demande.getIdDemandes() + " - Votre demande a été exécutée : " + demande.getTitre());
			email.setMsg("Bonjour,\n\nVotre demande n°" + demande.getIdDemandes() + " (" + demande.getTitre()
					+ ") a été exécutée.\n\n"
					+ "Si cela vous semble prématuré, il vous est toujours possible d'y ajouter une éventuelle remarque dans les 15 jours à venir. Passé ce délai, une nouvelle DSI devra être introduite.\n\n"
					+ "Voir la demande sur l'intranet : http://intranet/afficherDemande.dsi?idDemandes="
					+ demande.getIdDemandes() + "\n\nBonne journée.");
			email.addTo(user.getEmail());
			email.addTo(chef.getEmail());
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

	private void ajoutRemarque(String idDemande, Utilisateur utilisateur, String texte) {
		Remarque rem = new Remarque();
		GregorianCalendar now = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		rem.setIdDemande(idDemande);
		rem.setTexte(texte);
		rem.setUser(utilisateur.getPrenom() + " " + utilisateur.getNom());
		rem.setDate(sdf.format(now.getTime()));
		daoDsi.addRemarque(rem);
	}

	private Remarque getFormulaireRemarque(HttpServletRequest request) {
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		String idDemandes = request.getParameter("idDemandes");
		String texte = request.getParameter("remarque");
		Remarque rem = new Remarque();
		GregorianCalendar now = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		rem.setIdDemande(idDemandes);
		rem.setTexte(texte);
		rem.setUser(utilisateur.getPrenom() + " " + utilisateur.getNom());
		rem.setDate(sdf.format(now.getTime()));
		return rem;
	}

	private boolean verifDate(HttpServletRequest request, String date) {
		System.out.println("date: " + date);
		boolean verif = false;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			sdf.setLenient(false);
			sdf.parse(date);
			verif = true;
		} catch (NullPointerException e) {
			verif = true;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			request.setAttribute("erreur", "La date n'est pas au bon format : AAAA-MM-JJ :");
		}

		return verif;
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
