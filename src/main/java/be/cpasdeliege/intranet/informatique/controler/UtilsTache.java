package be.cpasdeliege.intranet.informatique.controler;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.DemServInfo.dao.DaoDemServInf;
import be.cpasdeliege.intranet.DemServInfo.model.DemServInf;
import be.cpasdeliege.intranet.DemServInfo.model.Dsigti;
import be.cpasdeliege.intranet.DemServInfo.model.Remarque;
import be.cpasdeliege.intranet.cadastre.dao.DaoCadastre;
import be.cpasdeliege.intranet.cadastre.model.Annexe;
import be.cpasdeliege.intranet.cadastre.model.Formulaire;
import be.cpasdeliege.intranet.cadastre.model.Lien;
import be.cpasdeliege.intranet.cadastre.model.TypeMarche;
import be.cpasdeliege.intranet.informatique.model.Personnel;
import be.cpasdeliege.intranet.informatique.model.Tache;
import be.cpasdeliege.intranet.informatique.model.TicketItem;
import be.cpasdeliege.intranet.informatique.model.Utilisateur;
import be.cpasdeliege.intranet.informatique.model.dao.DaoMySQL;
import be.cpasdeliege.intranet.informatique.model.dao.DaoPersonnel;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;
import java.net.URLDecoder;

import be.cpasdeliege.intranet.utils.Utils;

public class UtilsTache implements Controller {

	DomainInterface metier = null;
	DaoMySQL dao = null;
	DaoDemServInf daoDsi = null;
	DaoCadastre daoCadastre = null;

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String action = request.getParameter("action");
		String idPlanning = request.getParameter("idPlanning");

		/**
		 * envoimail
		 */
		if(action.equals("envoiemail")) {
			String maila = request.getParameter("pers");
			String nom = URLDecoder.decode(maila.split(",")[0],"ISO-8859-1");
			//String nom = Utils.replaceAccentedCharacters(maila.split(",")[0]);
			String prenom = URLDecoder.decode(maila.split(",")[1],"ISO-8859-1");
			//String prenom = Utils.replaceAccentedCharacters(maila.split(",")[1]);

			System.out.println("CA PLANTE ???? 2");
			System.out.println(prenom);
			//System.out.println(new String(nom, "ISO-8859-1").getBytes("UTF-8"));
			//System.out.println(new String(prenom.getBytes("ISO-8859-1"), "UTF-8"));
			/*
			System.out.println("Béatrice......");
			System.out.println(new String("Béatrice......".getBytes(),"UTF-8"));
			System.out.println(new String("Béatrice......".getBytes(),"ISO-8859-1"));
			*/

			Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute("utilisateur");
			DaoPersonnel from = ((List<DaoPersonnel>)dao.getListePersonnels(utilisateur.getNom(), utilisateur.getPrenom())).get(0);
			DaoPersonnel to = ((List<DaoPersonnel>)dao.getListePersonnels(nom, prenom)).get(0);
			Tache tmpTache = dao.getTache(idPlanning);
			
			mailTache(tmpTache, to.getEmail(), from, response); 

			
			request.getRequestDispatcher("/gestionTache.admin?idplanning=" + idPlanning).forward(request, response);
			//			request.getServletContext().getRequestDispatcher("/gestionTache.admin?idplanning=" + idPlanning).forward(request, response);
			//response.sendRedirect("gestionTache.admin?idplanning=" + idPlanning);
			
			
			
			
			
			
			
			
			/**
			
			
			String maila = request.getParameter("mail");
			Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute("utilisateur");
			DaoPersonnel from = ((List<DaoPersonnel>)dao.getListePersonnels(utilisateur.getNom(), utilisateur.getPrenom())).get(0);
			Tache tmpTache = dao.getTache(idPlanning);
			
			 

			if(maila.equals("juan")) {
				mailTache(tmpTache, "juan.hernandez@cpasdeliege.be", from, response);
			} else {
				String[] tmpEmployeInfo = tmpTache.getPersonnelInfo().split(", ");
				if(!tmpEmployeInfo[0].equals("")) {
					DaoPersonnel pers = ((List<DaoPersonnel>)dao.getListePersonnels(tmpEmployeInfo[0], tmpEmployeInfo[1])).get(0);
					mailTache(tmpTache, pers.getEmail(), from, response);
				}
			}
			request.getRequestDispatcher("/gestionTache.admin?idplanning=" + idPlanning).forward(request, response);
			//			request.getServletContext().getRequestDispatcher("/gestionTache.admin?idplanning=" + idPlanning).forward(request, response);
			//response.sendRedirect("gestionTache.admin?idplanning=" + idPlanning);
			
			*/
			
		/**
		 * ajouterAnnexe
		 */
		}  else if(action.equals("ajouterAnnexe")) {

			Object fileObject1 = request.getAttribute("annexe");
			if (fileObject1 != null) {
				FileItem fileItem = (FileItem) fileObject1;
				File dest = null;
				File tmp = new File("c:\\fop");
				System.out.println("file name = "+fileItem.getName());
				if(!tmp.exists()) {
					new File("/opt/fop/GTI/" + idPlanning + "/").mkdirs();
					dest = new File("/opt/fop/GTI/" + idPlanning + "/" + fileItem.getName());
				} else {
					int index=fileItem.getName().lastIndexOf('\\');
					System.out.println("index="+index);
					System.out.println(fileItem.getName().substring(index+1));
					new File("c:\\fop\\GTI\\" + idPlanning + "\\").mkdirs();
					dest = new File("c:\\fop\\GTI\\" + idPlanning + "\\" + fileItem.getName().substring(index+1));
				}
				try {
					fileItem.write(dest);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//request.getRequestDispatcher("/gestionTache.admin?idplanning=" + idPlanning).forward(request, response);
			response.sendRedirect("utilsTache.admin?action=redirectionGTI&idPlanning=" + idPlanning);
		/**
		 * ajouterAnnexeCadastre
		 */
		} else if(action.equals("ajouterAnnexeCadastre")) {
			String id_marche_public = request.getParameter("id_marche_public");
			String nom = request.getParameter("nom");
			String path = generate(20);
			Object fileObject1 = request.getAttribute("annexe");
			if (fileObject1 != null) {
				FileItem fileItem = (FileItem) fileObject1;
				path = path + fileItem.getName().substring(fileItem.getName().length()-4);
				nom = nom + fileItem.getName().substring(fileItem.getName().length()-4);
				File dest = null;
				File tmp = new File("c:\\fop");
				if(!tmp.exists()) {
					new File("/opt/fop/cadastre/" + id_marche_public + "/").mkdirs();
					dest = new File("/opt/fop/cadastre/" + id_marche_public + "/" + path); 

				} else {
					new File("c:\\fop\\cadastre\\" + id_marche_public + "\\").mkdirs();
					dest = new File("c:\\fop\\cadastre\\" + id_marche_public + "\\" + path);
				}
				try {
					fileItem.write(dest);

				} catch (Exception e) {
					e.printStackTrace();
				}
				Annexe annexe = new Annexe();
				annexe.setId_marche_public(id_marche_public);
				annexe.setNom(nom);
				annexe.setPath(path);
				daoCadastre.addAnnexe(annexe);
			}
			//request.getRequestDispatcher("/gestionTache.admin?idplanning=" + idPlanning).forward(request, response);
			response.sendRedirect("index.cad?action=afficherMP&id_marche_public=" + id_marche_public + "&retour=" + request.getParameter("retour"));
		/**
		 * ajouterLienCadastre
		 */
		} else if(action.equals("ajouterLienCadastre")) {
			String id_marche_public = request.getParameter("id_marche_public");
			String nom = request.getParameter("nom");
			String lien = request.getParameter("lien");
			
			
			if((nom != null && !nom.equals("")) && (lien != null && !lien.equals(""))) {
				Lien tmp = new Lien();
				tmp.setLien(lien);
				tmp.setNom(nom);
				tmp.setId_marche_public(id_marche_public);
				daoCadastre.addLien(tmp);
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			//request.getRequestDispatcher("/gestionTache.admin?idplanning=" + idPlanning).forward(request, response);
			response.sendRedirect("index.cad?action=afficherMP&id_marche_public=" + id_marche_public + "&retour=" + request.getParameter("retour"));
		/**
		 * ajouterFormulaireCadastre
		 */
		} else if(action.equals("ajouterFormulaireCadastre")) {
			String nom = request.getParameter("nom").replace(".", "-");
			String path = generate(20);
			String extension ="";
			Object fileObject1 = request.getAttribute("annexe");
			if (fileObject1 != null) {
				FileItem fileItem = (FileItem) fileObject1;
				path = path + fileItem.getName().substring(fileItem.getName().length()-4);
				//String[] tab=fileItem.getName().split(".");
				/******************************************************/
				//File f = (File) request.getAttribute("annexe"); 
			
				System.out.print("*****************************");
				System.out.println(" nom ==>"+ nom);
				//System.out.println(" tab ==>"+ tab.length);
				
				System.out.print("*****************************");
				// String fileN = fileItem.toString(); 
				String filename = fileItem.getName();
				int index = filename.lastIndexOf("."); 
				if(index >0) {
					 extension = filename.substring(index +1); 
					
					System.out.print("\n ********************************");
					System.out.println(" file name ==>" + filename); 
					//System.out.println("\n file N ==>" + fileN); 
					System.out.println(" Extention files is ==>" + extension + " extention length ==>" +extension.length()); 
					
					
					
				}
				
				 //nom = nom+"."+tab[1];
						
				//nom = nom + fileItem.getName().substring(fileItem.getName().length()-5);
				
				//if(extension.length() >0) {
					nom = nom+'.'+ extension;
					System.out.print("*****************************");
					System.out.println(" path ==>"+ path);
					System.out.println(" nom ==>"+ nom);
					System.out.println(" longueur ==>" + fileItem.getName().substring(fileItem.getName().length()-4));
					System.out.print("*****************************");
					/************************************************************/
					File dest = null;
					File tmp = new File("c:\\fop");
					if(!tmp.exists()) {
						new File("/opt/fop/cadastre/formulaires/").mkdirs();
						dest = new File("/opt/fop/cadastre/formulaires/" + path); 
	
					} else {
						new File("c:\\fop\\cadastre\\formulaires\\").mkdirs();
						dest = new File("c:\\fop\\cadastre\\formulaires\\" + path);
					}
					try {
						fileItem.write(dest);
	
					} catch (Exception e) {
						e.printStackTrace();
					}
					Formulaire formulaire = new Formulaire();
					formulaire.setNom(nom);
					formulaire.setPath(path);
					daoCadastre.addFormulaire(formulaire);
				//}
			}
			//request.getRequestDispatcher("/gestionTache.admin?idplanning=" + idPlanning).forward(request, response);
			response.sendRedirect("index.cad");
		/**
		 * modifierVMAction
		 */
		} else if(action.equals("modifierVMAction")) {
			String nom = "VM_action_sociale.pdf";
			Object fileObject1 = request.getAttribute("fichier");
			if (fileObject1 != null) {
				FileItem fileItem = (FileItem) fileObject1;
				File dest = null;
				File tmp = new File("c:\\fop");
				if(!tmp.exists()) {
					dest = new File("/opt/fop/" + nom); 

				} else {
					dest = new File("c:\\fop\\" + nom);
				}
				dest.delete();
				try {
					fileItem.write(dest);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			response.sendRedirect("index.htm?rep=personnelCPAS");
			/**
			 *  modifierVMAide
			 */
		} else if(action.equals("modifierVMAide")) {
			String nom = "VM_aide_sociale.pdf";
			Object fileObject1 = request.getAttribute("fichier");
			if (fileObject1 != null) {
				FileItem fileItem = (FileItem) fileObject1;
				File dest = null;
				File tmp = new File("c:\\fop");
				if(!tmp.exists()) {
					dest = new File("/opt/fop/" + nom); 

				} else {
					dest = new File("c:\\fop\\" + nom);
				}
				dest.delete();
				try {
					fileItem.write(dest);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			response.sendRedirect("index.htm?rep=personnelCPAS");
			/**
			 * modifierVMMaintien
			 */
		} else if(action.equals("modifierVMMaintien")) {
			String nom = "VM_Maintien.pdf";
			Object fileObject1 = request.getAttribute("fichier");
			if (fileObject1 != null) {
				FileItem fileItem = (FileItem) fileObject1;
				File dest = null;
				File tmp = new File("c:\\fop");
				if(!tmp.exists()) {
					dest = new File("/opt/fop/" + nom); 

				} else {
					dest = new File("c:\\fop\\" + nom);
				}
				dest.delete();
				try {
					fileItem.write(dest);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			response.sendRedirect("index.htm?rep=personnelCPAS");
		
		} else if(action.equals("ajouterPhoto")) {
			/**
			 * ajouterPhoto
			 */
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String path = generate(20);
			Object fileObject1 = request.getAttribute("photo");
			if (fileObject1 != null) {
				FileItem fileItem = (FileItem) fileObject1;
				path = path + fileItem.getName().substring(fileItem.getName().length()-4);
				
				File dest = null;
				File tmp = new File("c:\\fop");
				if(!tmp.exists()) {
					new File("/var/www/html/photos/").mkdirs();
					dest = new File("/var/www/html/photos/" + path); 
				} else {
					new File("C:\\Users\\delree.CPASLIEGE\\Documents\\workspace_old\\cpas\\WebContent\\images\\photos\\").mkdirs();
					dest = new File("C:\\Users\\delree.CPASLIEGE\\Documents\\workspace_old\\cpas\\WebContent\\images\\photos\\" + path);
				}
				try {
					fileItem.write(dest);

				} catch (Exception e) {
					e.printStackTrace();
				}
				if(!tmp.exists()) {
					resize("/var/www/html/photos/" + path, "/var/www/html/photosAnnuaire/" + path);
				} else {
				}
				DaoPersonnel pers = (DaoPersonnel)dao.getListePersonnels(nom, prenom).get(0);
				pers.setPathPhoto(path);
				dao.supprimerPersonnel(nom, prenom);
				dao.addPersonnel(pers);
				
			}
			response.sendRedirect("gestionPersonnel.admin?nom=" + nom + "&prenom=" + prenom);
		/**
		 * supprimerPhoto
		 */
		} else if(action.equals("supprimerPhoto")) {
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			DaoPersonnel pers = (DaoPersonnel)dao.getListePersonnels(nom, prenom).get(0);
			
			File tmp = new File("c:\\fop");
			if(!tmp.exists()) {
				File del = new File("/var/www/html/photos/" + pers.getPathPhoto());
				del.delete();
			} else {
				File del = new File("C:\\Users\\delree.CPASLIEGE\\Documents\\workspace_old\\cpas\\WebContent\\images\\photos\\" + pers.getPathPhoto());
				del.delete();
			}
			pers.setPathPhoto("");
			dao.supprimerPersonnel(nom, prenom);
			dao.addPersonnel(pers);
			response.sendRedirect("gestionPersonnel.admin?nom=" + nom + "&prenom=" + prenom);
		/**
		 * ajouterTypeCadastre
		 */
		} else if(action.equals("ajouterTypeCadastre")) {
			String path = generate(20);
			Object fileObject1 = request.getAttribute("image");
			if (fileObject1 != null) {
				FileItem fileItem = (FileItem) fileObject1;
				path = path + fileItem.getName().substring(fileItem.getName().length()-4);
				File dest = null;
				File tmp = new File("c:\\fop");
				if(!tmp.exists()) {
					new File("/opt/fop/cadastre/type/").mkdirs();
					dest = new File("/opt/fop/cadastre/type/" + path); 

				} else {
					new File("C:\\Users\\delree.CPASLIEGE\\Documents\\workspace_old\\cpas\\WebContent\\images\\cadastre\\").mkdirs();
					dest = new File("C:\\Users\\delree.CPASLIEGE\\Documents\\workspace_old\\cpas\\WebContent\\images\\cadastre\\" + path);
				}
				try {
					fileItem.write(dest);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(verifFormulaireTypeMarche(request)) {
				TypeMarche typeMarche = getFormulaireType(request);
				typeMarche.setPathImage(path);
				daoCadastre.addTypeMarche(typeMarche);
				response.sendRedirect("index.cad");
			} else {
				HashMap modele = new HashMap();
				modele.put("retour", "index.cad");
				modele.put("typeMarche", getFormulaireType(request));
				response.sendRedirect("index.cad?action=formAjouterType&erreur=Intitul�vide&type_marche=" + request.getParameter("id_type_marche"));
//				return new ModelAndView("formulaireAjouterType", modele);
			}
			//request.getRequestDispatcher("/gestionTache.admin?idplanning=" + idPlanning).forward(request, response);
		/**
		 * modifierTypeCadastre
		 */
		} else if(action.equals("modifierTypeCadastre")) {
			String path = generate(20);
			Object fileObject1 = request.getAttribute("image");
			if (fileObject1 != null) {
				FileItem fileItem = (FileItem) fileObject1;
				path = path + fileItem.getName().substring(fileItem.getName().length()-4);
				File dest = null;
				File tmp = new File("c:\\fop");
				if(!tmp.exists()) {
					new File("/opt/fop/cadastre/type/").mkdirs();
					dest = new File("/opt/fop/cadastre/type/" + path); 

				} else {
					new File("C:\\Users\\delree.CPASLIEGE\\Documents\\workspace_old\\cpas\\WebContent\\images\\cadastre\\").mkdirs();
					dest = new File("C:\\Users\\delree.CPASLIEGE\\Documents\\workspace_old\\cpas\\WebContent\\images\\cadastre\\" + path);
				}
				try {
					fileItem.write(dest);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(verifFormulaireTypeMarche(request)) {
				TypeMarche typeMarche = daoCadastre.getTypeMarche(request.getParameter("id_type_marche"));
				typeMarche.setType_marche(request.getParameter("type_marche"));
				if(fileObject1 != null) {
					typeMarche.setPathImage(path);
				}
				daoCadastre.modifierType(typeMarche);
				response.sendRedirect("index.cad");
			} else {
				HashMap modele = new HashMap();
				modele.put("retour", "index.cad");
				modele.put("typeMarche", daoCadastre.getTypeMarche(request.getParameter("id_type_marche")));
				response.sendRedirect("index.cad?action=formAjouterType&amp;erreur=Intitul� vide&amp;type_marche=" + request.getParameter("id_type_marche"));
//				return new ModelAndView("formulaireModifierType", modele);
			}
			//request.getRequestDispatcher("/gestionTache.admin?idplanning=" + idPlanning).forward(request, response);
		/**
		 * redirectionGTI
		 */
		} else if(action.equals("redirectionGTI")) {
			request.getRequestDispatcher("/gestionTache.admin?idplanning=" + idPlanning).forward(request, response);
		/**
		 * supprimerAnnexe
		 */
		} else if(action.equals("supprimerAnnexe")) {
			supprimerAnnexe(request);
			response.sendRedirect("utilsTache.admin?action=redirectionGTI&idPlanning=" + idPlanning);
		/**
		 * notifDG
		 */
		} else if(action.equals("notifDG")) {
			//TI 8608 : � supprimer
			notifDG(request);
			response.sendRedirect("afficherDemande.dsi?idDemandes=" + request.getParameter("idDemande"));
		/**
		 * accordDG
		 */
		} else if(action.equals("accordDG")) {
			accordDG(request);
			response.sendRedirect("afficherDemande.dsi?idDemandes=" + request.getParameter("idDemande"));
		/**
		 * avisInfo
		 */
		} else if(action.equals("avisInfo")) {
			avisInfo(request);
			response.sendRedirect("afficherDemande.dsi?idDemandes=" + request.getParameter("idDemande"));
		/**
		 * associerGTI2DSI
		 */
		} else if(action.equals("associerGTI2DSI")) {
			associerGTI2DSI(request);
			response.sendRedirect("afficherDemande.dsi?idDemandes=" + request.getParameter("idDemandes"));
		/**
		 * supAssoGTI2DSI
		 */
		} else if(action.equals("supAssoGTI2DSI")) {
			supAssoGTI2DSI(request);
			response.sendRedirect("afficherDemande.dsi?idDemandes=" + request.getParameter("idDemandes"));
		/**
		 * Modifier Code AS
		 */
		} else if(action.equals("modifierCodeAS")) {
			modifierCodeAS(request);
			response.sendRedirect("gestionPersonnel.admin?nom=" + request.getParameter("nom") + "&prenom=" + request.getParameter("prenom"));
		}

		HashMap modele = new HashMap();
		return new ModelAndView("telephonie/index", modele);

	}

	private void modifierCodeAS(HttpServletRequest request) {
		Personnel pers = metier.getPersonnel(request.getParameter("nom"), request.getParameter("prenom"));
		pers.setCodeAS(request.getParameter("codeAS"));
		dao.updateCodeAS(pers);
	}

	private boolean verifFormulaireTypeMarche(HttpServletRequest request) {
		String type_marche = request.getParameter("type_marche");
		if (type_marche.equals("")) {
			request.setAttribute("erreur", "Vous devez indiquer un intitul�");
			return false;
		}
		return true;
	}

	private TypeMarche getFormulaireType(HttpServletRequest request) {
		String type_marche = request.getParameter("type_marche");
		TypeMarche typeMarche = new TypeMarche();
		typeMarche.setType_marche(type_marche);
		return typeMarche;
	}

	public String generate(int length) {
		String chars = "1234567890abcdefghijklmnopqrstuvwxyz";
		System.out.println("----------------->>>>   " + chars.length());
		String pass = "";
		for (int x = 0; x < length; x++) {
			int i = (int) Math.floor(Math.random() * chars.length());
			pass += chars.charAt(i);
		}
		return pass;
	}

	private void supAssoGTI2DSI(HttpServletRequest request) {
		daoDsi.deleteDsigti(request.getParameter("idDemandes"), request.getParameter("idPlanning"));
	}

	private void associerGTI2DSI(HttpServletRequest request) {
		Tache tache = dao.getTache(request.getParameter("idPlanning"));
		if (tache != null) {
			Dsigti dsigti = new Dsigti();
			dsigti.setIdDemande(request.getParameter("idDemandes"));
			dsigti.setIdPlanning(request.getParameter("idPlanning"));
			try {
				daoDsi.addDsigti(dsigti);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void avisInfo(HttpServletRequest request) {
		String idDemande = request.getParameter("idDemande");
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		DemServInf demande = daoDsi.getDemande(idDemande);
		ajoutRemarque(idDemande, utilisateur, "Avis au service informatique demand�.");

		try {
			Email email = new SimpleEmail();
			email.setHostName("mail.cpasdeliege.be");
			email.setSmtpPort(25);
			email.setAuthenticator(new DefaultAuthenticator("frederic.delree", "rksZRfh$"));

			email.setFrom("no-reply@cpasdeliege.be", "DSI - Intranet");

			email.setSubject(
					"Demande d'avis sur la demande n� " + demande.getIdDemandes() + " : " + demande.getTitre());
			email.setMsg("Bonjour,\n\nVotre attention est demand�e sur la demande n�" + demande.getIdDemandes() + " ("
					+ demande.getTitre() + ") " + "\n\n"
					+ "Voir la demande sur l'intranet : http://intranet/afficherDemande.dsi?idDemandes="
					+ demande.getIdDemandes() + "\n\nBonne journ�e.");
			email.addTo("juan.hernandez@cpasdeliege.be");
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

	private void accordDG(HttpServletRequest request) {
		String idDemande = request.getParameter("idDemande");
		DemServInf demande = daoDsi.getDemande(idDemande);
		if (demande.getValidationSecr().equals("none")) {
			demande.setValidationSecr("oui");
			demande.setDateExecSouhaiteeSecr(demande.getDateExecSouhaitee());
		} else if (demande.getValidationSecrConf().equals("none")) {
			demande.setValidationSecrConf("oui");
			demande.setDateExecSouhaiteeSecrConf(demande.getDateExecSouhaitee());
		}
		Utilisateur tmp = new Utilisateur();
		tmp.setNom("Message automatique");
		tmp.setPrenom("");
		ajoutRemarque("" + demande.getIdDemandes(), tmp, "Validation implicite de la demande du D � oui");
		daoDsi.updateDemande(demande);
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

	private void notifDG(HttpServletRequest request) {
		String idDemande = request.getParameter("idDemande");
		DemServInf demande = daoDsi.getDemande(idDemande);

		List<Remarque> remarques = daoDsi.getRemarques(idDemande);
		Remarque dernirereRemarque = remarques.get(remarques.size() - 1);
		try {
			Email email = new SimpleEmail();
			email.setHostName("mail.cpasdeliege.be");
			email.setSmtpPort(25);
			email.setAuthenticator(new DefaultAuthenticator("frederic.delree", "rksZRfh$"));

			email.setFrom("no-reply@cpasdeliege.be", "DSI - Intranet");

			email.setSubject("Notification sur la demande n� " + demande.getIdDemandes() + " : " + demande.getTitre());
			email.setMsg("Bonjour,\n\nVotre attention est demand�e sur la demande n�" + demande.getIdDemandes() + " ("
					+ demande.getTitre() + ") " + "\n\n" + "Voici la derni�re remarque : \n\n"
					+ dernirereRemarque.getTexte() + "\n\n\n"
					+ "Voir la demande sur l'intranet : http://intranet/afficherDemande.dsi?idDemandes="
					+ demande.getIdDemandes() + "\n\nBonne journ�e.");
			// email.addTo("frederic.delree@gmail.com");
			email.addTo("jmjalhay@cpasdeliege.be");
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

	private void supprimerAnnexe(HttpServletRequest request) {
		int numAnnexe = Integer.parseInt(request.getParameter("numAnnexe"));

		File tmp = new File("c:\\fop");
		if (!tmp.exists()) {
			File rep = new File("/opt/fop/GTI/" + request.getParameter("idPlanning"));
			File[] annexes = rep.listFiles();
			annexes[numAnnexe].delete();

		} else {
			File rep = new File("c:\\fop\\GTI\\" + request.getParameter("idPlanning"));
			File[] annexes = rep.listFiles();
			annexes[numAnnexe].delete();
		}

	}

	private void mailTache(Tache tache, String dest, DaoPersonnel from, HttpServletResponse response) {
		List<TicketItem> listticket = (List<TicketItem>) dao.getListeTicketItem(tache.getIdPlanning());
		TicketItem rem = listticket.get(listticket.size() - 1);
		String subject = "TI " + tache.getIdPlanning() + " ** 2.NOTIFICATION** -" + tache.getTitre();

		String message = "Bonjour,\n\nNotification sur la tâche n°" + tache.getIdPlanning() + " (" + tache.getTitre()
				+ ") " + "\n\n" + "Voici le dernier ticket : \n\n" + rem.getTexte() + "\n\n\n"
				+ "Voir la tâche sur l'intranet : http://intranet/gestionTache.admin?idPlanning="
				+ tache.getIdPlanning() + "\n\nBonne journée.";
		try {
			System.out.println("-------------- DEST ---------------");
			System.out.println(dest);
			System.out.println(URLEncoder.encode(dest, "UTF8"));
//			response.sendRedirect("https://mail.cpasdeliege.be/?to=" + dest+ "&view=compose&body=" + message + "&subject=" + subject + "#1");
			//https://outlook.office.com/mail/deeplink/compose?body=Hello%20World&subject=Test%20Email&to=test@example.com&cc=testcc@example.com
			response.sendRedirect("https://outlook.office.com/mail/deeplink/compose?to=" + Utils.replaceAccentedCharacters(URLEncoder.encode(dest, "UTF8")) + "&body="
					+ URLEncoder.encode(message, "UTF8") + "&subject=" + URLEncoder.encode(subject, "UTF8") + "#1");
		} catch (IOException e) {
			e.printStackTrace();

		}

		/*
		 * List<TicketItem> listticket =
		 * (List<TicketItem>)dao.getListeTicketItem(tache.getIdPlanning()); TicketItem
		 * rem = listticket.get(listticket.size()-1); String subject =
		 * "Notification sur la t�che : " + tache.getIdPlanning() + " - " +
		 * tache.getTitre();
		 * 
		 * 
		 * 
		 * if(from.getNom().equals("Hernandez")) { String message =
		 * "Bonjour,\n\nNotification sur la t�che n�" + tache.getIdPlanning() + " (" +
		 * tache.getTitre() + ") " + "\n\n" + "Voici le dernier ticket : \n\n" +
		 * rem.getTexte() + "\n\n\n" +
		 * "Voir la t�che sur l'intranet : http://intranet/gestionTache.admin?idPlanning="
		 * + tache.getIdPlanning() +"\n\nBonne journ�e."; try { //
		 * response.sendRedirect("https://mail.cpasdeliege.be/?to=" + dest+
		 * "&view=compose&body=" + message + "&subject=" + subject + "#1");
		 * response.sendRedirect("https://mail.cpasdeliege.be/?to=" + dest+
		 * "&view=compose&body=" + URLEncoder.encode(message, "UTF8") + "&subject=" +
		 * URLEncoder.encode(subject, "UTF8") + "#1"); } catch (IOException e) {
		 * e.printStackTrace();
		 * 
		 * } } else { String message = "Bonjour,\n\nNotification sur la t�che n�" +
		 * tache.getIdPlanning() + " (" + tache.getTitre() + ") " + "\n\n" +
		 * "Voici le dernier ticket : \n\n" + rem.getTexte() + "\n\n\n" +
		 * "Voir la t�che sur l'intranet : http://intranet/gestionTache.admin?idPlanning="
		 * + tache.getIdPlanning() +"\n\nBonne journ�e."; try {
		 * 
		 * Email email = new SimpleEmail(); email.setHostName("mail.cpasdeliege.be");
		 * email.setSmtpPort(25); email.setAuthenticator(new
		 * DefaultAuthenticator("frederic.delree", "rksZRfh$")); //
		 * email.setSSLOnConnect(true); // email.setTLS(true);
		 * email.setFrom(from.getEmail(), from.getPrenom() + " " + from.getNom());
		 * email.setSubject(subject); email.setMsg(message); email.addTo(dest);
		 * email.send(); } catch (EmailException e) { e.printStackTrace(); } }
		 */

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

	public DaoDemServInf getDaoDsi() {
		return daoDsi;
	}

	public void setDaoDsi(DaoDemServInf daoDsi) {
		this.daoDsi = daoDsi;
	}

	public DaoCadastre getDaoCadastre() {
		return daoCadastre;
	}

	public void setDaoCadastre(DaoCadastre daoCadastre) {
		this.daoCadastre = daoCadastre;
	}

	public BufferedImage scale(BufferedImage bi, double scaleValue) {
		AffineTransform tx = new AffineTransform();
		tx.scale(scaleValue, scaleValue);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		BufferedImage biNew = new BufferedImage((int) (bi.getWidth() * scaleValue), (int) (bi.getHeight() * scaleValue),
				bi.getType());
		return op.filter(bi, biNew);

	}

	public BufferedImage getScaledInstance(BufferedImage img, int targetWidth, int targetHeight,
			boolean higherQuality) {

		int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB
				: BufferedImage.TYPE_INT_ARGB;
		BufferedImage ret = (BufferedImage) img;
		int w, h;
		if (higherQuality) {
			// Use multi-step technique: start with original size, then
			// scale down in multiple passes with drawImage()
			// until the target size is reached
			w = img.getWidth();
			h = img.getHeight();
		} else {
			// Use one-step technique: scale directly from original
			// size to target size with a single drawImage() call
			w = targetWidth;
			h = targetHeight;
		}

		int pasW = w / 10;
		int pasH = h / 10;

		do {
			if (higherQuality && w > targetWidth) {
//            w /= 2;
				w -= pasW;
				if (w < targetWidth) {
					w = targetWidth;
				}
			}

			if (higherQuality && h > targetHeight) {
//            h /= 2;
				h -= pasH;
				if (h < targetHeight) {
					h = targetHeight;
				}
			}

			BufferedImage tmp = new BufferedImage(w, h, type);
			Graphics2D g2 = tmp.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			g2.drawImage(ret, 0, 0, w, h, null);
			g2.dispose();

			ret = tmp;
		} while (w != targetWidth || h != targetHeight);

		return ret;
	}

	public void resize(String inputImagePath, String outputImagePath) throws IOException {
		// reads input image
		File inputFile = new File(inputImagePath);
		BufferedImage inputImage = ImageIO.read(inputFile);

		// creates output image
		BufferedImage outputImage = getScaledInstance(inputImage, 120, 150, true);

		// extracts extension of output file
		String formatName = outputImagePath.substring(outputImagePath.lastIndexOf(".") + 1);

		// writes to output file
		ImageIO.write(outputImage, formatName, new File(outputImagePath));
	}
}