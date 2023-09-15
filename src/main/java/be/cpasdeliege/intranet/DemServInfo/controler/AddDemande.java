package be.cpasdeliege.intranet.DemServInfo.controler;

import java.io.File;
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
import be.cpasdeliege.intranet.DemServInfo.model.Config;
import be.cpasdeliege.intranet.DemServInfo.model.DemServInf;
import be.cpasdeliege.intranet.DemServInfo.model.Remarque;
import be.cpasdeliege.intranet.codesRues.model.dao.DaoException;
import be.cpasdeliege.intranet.informatique.model.PrivilegeInformatique;
import be.cpasdeliege.intranet.informatique.model.Utilisateur;
import be.cpasdeliege.intranet.informatique.model.dao.DaoMySQL;
import be.cpasdeliege.intranet.informatique.model.dao.DaoPersonnelService;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class AddDemande implements Controller {

	DomainInterface metier = null;
	DaoDemServInf daoDsi = null;
	DaoMySQL dao = null;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.getSession().setAttribute("retour", "index.dsi");
		PrivilegeInformatique privilege = (PrivilegeInformatique)request.getSession().getAttribute("privilegeInformatique");
		String typeDemande1 = request.getParameter("typeDemande1");
		String typeDemande2 = request.getParameter("typeDemande2");
		String typeDemande3 = request.getParameter("typeDemande3");
		HashMap modele = new HashMap();
		modele.put("statistiques", metier.getStatistiques());
		modele.put("listeChefs", daoDsi.getListeChefs());
		modele.put("listeServices", metier.getListeServices());
		
		modele.put("listeType", dao.getTypeDemandeDSI());
		modele.put("listeTypeChoix1", dao.getTypeChoix1DemandeDSI());
		
		Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute("utilisateur");
		String service = ((List<DaoPersonnelService>)metier.getListePersonnelService("%", utilisateur.getNom(), utilisateur.getPrenom())).get(0).getService();
//		modele.put("service", service);
		request.setAttribute("service", service);
		
		Config config = daoDsi.getconfig(privilege.getLogin());
		request.setAttribute("chef", config.getNom() + "_" + config.getPrenom());
		
		if(privilege.isDsiUser()) {
			
			if(typeDemande1 == null) {
				return new ModelAndView("addDemandeUser", modele);
			} else {
				if(verifFormulaire(request)) {
					try {
						int id = daoDsi.addDemande(getFormulaire(request));
						uploadFile(request, id);
					} catch (DaoException e) {
						System.out.println(e.getMessage());
					}
					response.sendRedirect("index.dsi");
					
					return null;
				} else {
					transfertParametres(request);
					List listeTypeChoix2 = dao.getTypeChoix2DemandeDSI(typeDemande1);
					modele.put("listeTypeChoix2", listeTypeChoix2);
					List listeTypeChoix3 = dao.getTypeChoix3DemandeDSI(typeDemande1 + "-" + typeDemande2);
					modele.put("listeTypeChoix3", listeTypeChoix3);
					return new ModelAndView("addDemandeUser", modele);
				}
			}
		} else if(privilege.isDsiChef()) {
			if(typeDemande1 == null) {
				return new ModelAndView("addDemandeChef", modele);
			} else {
				if(verifFormulaire(request)) {
					try {
						DemServInf demande = getFormulaire(request);
						demande.setValidationChef("oui");
						demande.setDateExecSouhaiteeChef(demande.getDateExecSouhaitee());
						int id = daoDsi.addDemande(demande);
						// si accord DG non nécessaire passage à l'étape suivante
						if(!dao.isAccordDG_DSI(demande.getTypeDemande())) {
							DemServInf demandeModif = daoDsi.getDemande(id);
							demandeModif.setValidationSecr("oui");
							demandeModif.setDateExecSouhaiteeSecr(demande.getDateExecSouhaitee());
							daoDsi.updateDemande(demandeModif);
							Utilisateur tmp = new Utilisateur();
							tmp.setNom("Message automatique");
							tmp.setPrenom("");
							ajoutRemarque(""+demandeModif.getIdDemandes(), tmp, "Validation implicite de la demande du D à oui");
							mailValidDGInfo(demandeModif);
						}
						uploadFile(request, id);
					} catch (DaoException e) {
						System.out.println(e.getMessage());
					}
					response.sendRedirect("index.dsi");
					
					return null;
				} else {
					transfertParametres(request);
					List listeTypeChoix2 = dao.getTypeChoix2DemandeDSI(typeDemande1);
					modele.put("listeTypeChoix2", listeTypeChoix2);
					List listeTypeChoix3 = dao.getTypeChoix3DemandeDSI(typeDemande1 + "-" + typeDemande2);
					modele.put("listeTypeChoix3", listeTypeChoix3);
					return new ModelAndView("addDemandeChef", modele);
				}
			}
		} else {
			if(typeDemande1 == null) {
				return new ModelAndView("addDemande", modele);
			} else {
				if(verifFormulaire(request)) {
					try {
						DemServInf demande = getFormulaire(request);
						demande.setValidationChef("oui");
						demande.setDateExecSouhaiteeChef(demande.getDateExecSouhaitee());
						int id = daoDsi.addDemande(demande);
						// si DG ou si accord DG non nécessaire passage à l'étape suivante
						if(privilege.isDsiDirection()) {
							demande.setValidationSecr("oui");
							demande.setDateExecSouhaiteeSecr(demande.getDateExecSouhaitee());
						} else if(!dao.isAccordDG_DSI(demande.getTypeDemande())) {
							DemServInf demandeModif = daoDsi.getDemande(id);
							demandeModif.setValidationSecr("oui");
							demandeModif.setDateExecSouhaiteeSecr(demande.getDateExecSouhaitee());
							daoDsi.updateDemande(demandeModif);
							Utilisateur tmp = new Utilisateur();
							tmp.setNom("Message automatique");
							tmp.setPrenom("");
							ajoutRemarque(""+demandeModif.getIdDemandes(), tmp, "Validation implicite de la demande du D à oui");
							mailValidDGInfo(demandeModif);
						}
						uploadFile(request, id);
					} catch (DaoException e) {
						System.out.println(e.getMessage());
					}
					response.sendRedirect("index.dsi");
					
					return null;
				} else {
					transfertParametres(request);
					List listeTypeChoix2 = dao.getTypeChoix2DemandeDSI(typeDemande1);
					modele.put("listeTypeChoix2", listeTypeChoix2);
					List listeTypeChoix3 = dao.getTypeChoix3DemandeDSI(typeDemande1 + "-" + typeDemande2);
					modele.put("listeTypeChoix3", listeTypeChoix3);
					return new ModelAndView("addDemande", modele);
				}
			}
		}
	}

	private DemServInf getFormulaire(HttpServletRequest request) {
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		String typeDemande1 = request.getParameter("typeDemande1");
		String typeDemande2 = request.getParameter("typeDemande2");
		String typeDemande3 = request.getParameter("typeDemande3");
		String titre = request.getParameter("titre");
		String echeanceSouhaitee = request.getParameter("echeanceSouhaitee");
		String description = request.getParameter("description");
		String chef = request.getParameter("chef");

		String service = request.getParameter("service");
		String remarqueEcheance = request.getParameter("remarqueEcheance");
		DemServInf dem = new DemServInf();
		GregorianCalendar now = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		dem.setDateDemande(sdf.format(now.getTime()));
		dem.setDateExecSouhaitee(echeanceSouhaitee);
		dem.setDescription(description);
		if (typeDemande3.equals("")) {
			dem.setTypeDemande(typeDemande1 + "-" + typeDemande2);
		} else {
			dem.setTypeDemande(typeDemande1 + "-" + typeDemande2 + "-" + typeDemande3);
		}

		String tmpNom = chef.split("_")[0];
		String tmpPrenom = chef.split("_")[1];
		System.out.println(tmpPrenom);
		System.out.println(tmpNom);
		/*
		 * ============================================================= transfert du
		 * validateur mis le 15-04-2021 par Théo
		 * 
		 */
		/*
		 * System.out.println(" donnée du chef *********************");
		 * System.out.println(chef);
		 * System.out.println(" données de l'utilisateur demandeur ********************"
		 * ); System.out.println(utilisateur.getNom()+ " "+ utilisateur.getPrenom() );
		 * 
		 * if(tmpNom.trim().equals("Nuda") && tmpPrenom.trim().equals("Delphine")) {
		 * System.out.println("Changement du validateur"); tmpNom="François";
		 * tmpPrenom="Geoffrey"; }
		 * 
		 * // Assigner un validateur à une dsi en fonction de l'utilisateur
		 * if(utilisateur.getNom().trim().equals("Nzaramba") &&
		 * utilisateur.getPrenom().trim().equals("Théodore")) { tmpNom ="Hernandez";
		 * tmpPrenom ="Juan"; }
		 * 
		 */

		/* ============================================================ */
		System.out.println("\n *********************************");
		System.out.println(" service dsi=>" + service);
		System.out.println("\n *********************************");

		dem.setTitre(titre);
		dem.setNom(utilisateur.getNom());
		dem.setPrenom(utilisateur.getPrenom());
		dem.setService(service);
		dem.setRemarqueEcheance(remarqueEcheance);
		// dem.setNomChef(chef.split(" ")[0]);
		dem.setNomChef(tmpNom);

		dem.setPrenomChef(tmpPrenom);
		return dem;
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

	private void uploadFile(HttpServletRequest request, int idDemande) {
		Object fileObject1 = request.getAttribute("annexe1");
		if (fileObject1 != null) {
			FileItem fileItem = (FileItem) fileObject1;
			File dest = null;
			File tmp = new File("c:\\fop");
			if (!tmp.exists()) {
				new File("/opt/fop/DSI/" + idDemande + "/").mkdirs();
				dest = new File("/opt/fop/DSI/" + idDemande + "/" + fileItem.getName());

			} else {
				new File("c:\\fop\\DSI\\" + idDemande + "\\").mkdirs();
				dest = new File("c:\\fop\\DSI\\" + idDemande + "\\" + fileItem.getName());
			}
			try {
				fileItem.write(dest);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Object fileObject2 = request.getAttribute("annexe2");
		if (fileObject2 != null) {
			FileItem fileItem = (FileItem) fileObject2;
			File dest = null;
			File tmp = new File("c:\\fop");
			if (!tmp.exists()) {
				new File("/opt/fop/DSI/" + idDemande + "/").mkdirs();
				dest = new File("/opt/fop/DSI/" + idDemande + "/" + fileItem.getName());

			} else {
				new File("c:\\fop\\DSI\\" + idDemande + "\\").mkdirs();
				dest = new File("c:\\fop\\DSI\\" + idDemande + "\\" + fileItem.getName());
			}
			try {
				fileItem.write(dest);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Object fileObject3 = request.getAttribute("annexe3");
		if (fileObject3 != null) {
			FileItem fileItem = (FileItem) fileObject3;
			File dest = null;
			File tmp = new File("c:\\fop");
			if (!tmp.exists()) {
				new File("/opt/fop/DSI/" + idDemande + "/").mkdirs();
				dest = new File("/opt/fop/DSI/" + idDemande + "/" + fileItem.getName());

			} else {
				new File("c:\\fop\\DSI\\" + idDemande + "\\").mkdirs();
				dest = new File("c:\\fop\\DSI\\" + idDemande + "\\" + fileItem.getName());
			}
			try {
				fileItem.write(dest);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private void transfertParametres(HttpServletRequest request) {
		request.setAttribute("titre", request.getParameter("titre"));
		request.setAttribute("echeanceSouhaitee", request.getParameter("echeanceSouhaitee"));
		request.setAttribute("description", request.getParameter("description"));
		request.setAttribute("chef", request.getParameter("chef"));
		request.setAttribute("typeDemande1", request.getParameter("typeDemande1"));
		request.setAttribute("typeDemande2", request.getParameter("typeDemande2"));
		request.setAttribute("typeDemande3", request.getParameter("typeDemande3"));
		request.setAttribute("service", request.getParameter("service"));
		request.setAttribute("remarqueEcheance", request.getParameter("remarqueEcheance"));
	}

	private boolean verifFormulaire(HttpServletRequest request) {
		String typeDemande1 = request.getParameter("typeDemande1");
		if (typeDemande1.equals("choisir un type de demande")) {
			request.setAttribute("erreur", "Vous devez choisir un type de de demande");
			return false;
		}
		String typeDemande2 = request.getParameter("typeDemande2");
		if (typeDemande2.equals("----->")) {
			request.setAttribute("erreur", "Vous devez choisir un type de de demande");
			return false;
		}
		String typeDemande3 = request.getParameter("typeDemande3");
		if (typeDemande3.equals("----->")) {
			request.setAttribute("erreur", "Vous devez choisir un type de de demande");
			return false;
		}
		String chef = request.getParameter("chef");
		if (chef.equals("choisir un responsable")) {
			request.setAttribute("erreur", "Vous devez choisir un responsable");
			return false;
		}
		String titre = request.getParameter("titre");
		if (titre.equals("")) {
			request.setAttribute("erreur", "Vous devez mettre un titre");
			return false;
		}
		String echeanceSouhaitee = request.getParameter("echeanceSouhaitee");
		if (echeanceSouhaitee == null && echeanceSouhaitee.equals("")) {
			request.setAttribute("erreur", "Vous devez mettre une échéance");
			return false;
		}

		/*
		 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); try {
		 * sdf.setLenient(false); sdf.parse(echeanceSouhaitee); } catch (ParseException
		 * e) { // TODO Auto-generated catch block request.setAttribute("erreur",
		 * "La date n'est pas au bon format : AAAA-MM-JJ :" + echeanceSouhaitee); return
		 * false; }
		 */

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			sdf.setLenient(false);
			sdf.parse(echeanceSouhaitee);

		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			request.setAttribute("erreur", "La date d'échéance doit être définie : AAAA-MM-JJ");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			request.setAttribute("erreur", "La date n'est pas au bon format : AAAA-MM-JJ");
			return false;
		}

		String description = request.getParameter("description");
		if (description.equals("")) {
			request.setAttribute("erreur", "Vous devez mettre une description");
			return false;
		}
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		Config tmp = daoDsi.getconfig(utilisateur.getLogin());
		// tmp.setLogin(utilisateur.getLogin());
		tmp.setNom(chef.split("_")[0]);
		tmp.setPrenom(chef.split("_")[1]);
		daoDsi.updateConfig(tmp);
		return true;
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
					+ "), a été validée implicitement par le DG.\n\n"
					+ "Voir la demande sur l'intranet : http://intranet/afficherDemande.dsi?idDemandes="
					+ demande.getIdDemandes() + "\n\nBonne journée.");
//			List<DaoPersonnel> listeInfo = metier.getPersonnelDsiInfo();
//			for (DaoPersonnel info : listeInfo) {
//				email.addTo(info.getEmail());
//			}
			email.addTo("notif.dsi@cpasdeliege.be");
			/* ******************** */
			// email.addTo("lionel.rose@cpasdeliege.be");
			// email.addTo("theodore.nzaramba@cpasdeliege.be");
			/* **************************************** */
			email.send();
			System.out.println("mailValidDGInfo: SEND");
		} catch (EmailException e) {
			System.out.println("EmailException: " + e.getMessage());
			e.printStackTrace();
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
