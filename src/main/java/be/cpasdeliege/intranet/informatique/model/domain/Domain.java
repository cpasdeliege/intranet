
package be.cpasdeliege.intranet.informatique.model.domain;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.mysql.jdbc.ResultSet;

import pJSQL.JSQLException;

import be.cpasdeliege.intranet.informatique.controler.formulaire.FormulaireImprimanteOrdinateurAssigner;
import be.cpasdeliege.intranet.informatique.controler.formulaire.FormulaireOrdinateur;
import be.cpasdeliege.intranet.informatique.controler.formulaire.FormulaireOrdinateurPersonnelAssigner;
import be.cpasdeliege.intranet.informatique.controler.formulaire.FormulairePersonnel;
import be.cpasdeliege.intranet.informatique.controler.formulaire.FormulaireService;
import be.cpasdeliege.intranet.informatique.controler.formulaire.FormulaireServicePersonnelAssigner;
import be.cpasdeliege.intranet.informatique.model.Departement;
import be.cpasdeliege.intranet.informatique.model.EmailGenerique;
import be.cpasdeliege.intranet.informatique.model.Imprimante;
import be.cpasdeliege.intranet.informatique.model.IntranetLog;
import be.cpasdeliege.intranet.informatique.model.Ordinateur;
import be.cpasdeliege.intranet.informatique.model.Personnel;
import be.cpasdeliege.intranet.informatique.model.PersonnelGestionService;
import be.cpasdeliege.intranet.informatique.model.Pole;
import be.cpasdeliege.intranet.informatique.model.PrivilegeInformatique;
import be.cpasdeliege.intranet.informatique.model.Service;
import be.cpasdeliege.intranet.informatique.model.Statistiques;
import be.cpasdeliege.intranet.informatique.model.StatistiquesAnnuaire;
import be.cpasdeliege.intranet.informatique.model.StatistiquesFormations;
import be.cpasdeliege.intranet.informatique.model.Tache;
import be.cpasdeliege.intranet.informatique.model.TicketItem;
import be.cpasdeliege.intranet.informatique.model.Utilisateur;
import be.cpasdeliege.intranet.informatique.model.dao.DaoDepartement;
import be.cpasdeliege.intranet.informatique.model.dao.DaoException;
import be.cpasdeliege.intranet.informatique.model.dao.DaoInterface;
import be.cpasdeliege.intranet.informatique.model.dao.DaoOrdinateur;
import be.cpasdeliege.intranet.informatique.model.dao.DaoOrdinateurPersonnel;
import be.cpasdeliege.intranet.informatique.model.dao.DaoPersonnel;
import be.cpasdeliege.intranet.informatique.model.dao.DaoPersonnelService;
import be.cpasdeliege.intranet.informatique.model.dao.DaoPole;
import be.cpasdeliege.intranet.informatique.model.dao.DaoService;

public class Domain implements DomainInterface {

	DaoInterface dao = null;
//	public Logger logger = Logger.getLogger("be.cpasdeliege.intranet");

	public Domain() {
//		try {
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
//			String date = sdf.format(new Date());
//			FileHandler fh = new FileHandler("c:\\logIntranet\\log_" + date + ".xml", false);
//			logger.addHandler(fh);
//			fh.setFormatter(new LogFormatterIntranet());
//			logger.setLevel(Level.ALL);
//		} catch (Exception e) {
//			
//		}		
	}

	public DaoInterface getDao() {
		return dao;
	}

	public void setDao(DaoInterface dao) {
		this.dao = dao;
		dao.connect();
	}

	@Override
	public synchronized void addEmailGenerique(EmailGenerique email) {
		dao.addEmailGenerique(email);
	}

	@Override
	public synchronized List getListeEmailGenerique() {
		return dao.getListeEmailGenerique();
	}

	@Override
	public synchronized void supprimerEmailGenerique(String email) {
		dao.supprimerEmailGenerique(email);
	}

	public synchronized void addService(FormulaireService service) {
		if (dao.getListeServices(service.getNom()).size() == 0) {
			dao.addService(formulaireServiceToDaoService(service));
		} else {
			throw new DomainException("ce service (" + service.getNom() + ") existe déjà");
		}
	}

	@Override
	public List<String> getListeEmail(List pers) {
		List<DaoPersonnelService> temp = (List<DaoPersonnelService>) pers;
		List<String> listeEmail = new ArrayList<String>();
		for (DaoPersonnelService daoPersonnel : temp) {
			listeEmail.add(getPersonnel(daoPersonnel.getNom(), daoPersonnel.getPrenom()).getEmail());
		}
		return listeEmail;
	}

	public List<String> getListePathPhoto(List pers) {
		List<DaoPersonnelService> temp = (List<DaoPersonnelService>) pers;
		List<String> listeEmail = new ArrayList<String>();
		for (DaoPersonnelService daoPersonnel : temp) {
			Personnel tmp = getPersonnel(daoPersonnel.getNom(), daoPersonnel.getPrenom());
			listeEmail.add(tmp.getPathPhoto());
		}
		return listeEmail;
	}

	public List<String> getListeCodeAS(List pers) {
		List<DaoPersonnelService> temp = (List<DaoPersonnelService>) pers;
		List<String> codeAS = new ArrayList<String>();
		for (DaoPersonnelService daoPersonnel : temp) {
			Personnel tmp = getPersonnel(daoPersonnel.getNom(), daoPersonnel.getPrenom());
			codeAS.add(tmp.getCodeAS());
		}
		return codeAS;
	}

	private DaoService formulaireServiceToDaoService(FormulaireService formulaireService) {
		DaoService daoService = new DaoService();
		daoService.setNom(formulaireService.getNom());
		daoService.setAdresse(formulaireService.getAdresse());
		daoService.setNumero(formulaireService.getNumero());
		daoService.setCodePostal(formulaireService.getCodePostal());
		daoService.setLocalite(formulaireService.getLocalite());
		daoService.setFax(formulaireService.getFax());
		daoService.setRemarque(formulaireService.getRemarque());
		daoService.setEmailService(formulaireService.getEmailService());
		daoService.setLocalisation(formulaireService.getLocalisation());
		return daoService;
	}

	private FormulaireService daoServiceToFormulaireService(DaoService daoService) {
		FormulaireService formulaireService = new FormulaireService();
		formulaireService.setNom(daoService.getNom());
		formulaireService.setAdresse(daoService.getAdresse());
		formulaireService.setNumero(daoService.getNumero());
		formulaireService.setCodePostal(daoService.getCodePostal());
		formulaireService.setLocalite(daoService.getLocalite());
		formulaireService.setFax(daoService.getFax());
		formulaireService.setRemarque(daoService.getRemarque());
		formulaireService.setEmailService(daoService.getEmailService());
		formulaireService.setLocalisation(daoService.getLocalisation());
		return formulaireService;
	}

	private Pole daoPoleToPole(DaoPole daoPole) {
		Pole pole = new Pole();
		pole.setId_pole(daoPole.getId_pole());
		pole.setNom(daoPole.getNom());

		return pole;
	}

	private Departement daoDepartementToDepartement(DaoDepartement daoDepartement) {
		Departement departement = new Departement();
		departement.setId_departement(daoDepartement.getId_departement());
		departement.setNom(daoDepartement.getNom());

		return departement;
	}

	private Service daoServiceToService(DaoService daoService) {
		Service service = new Service();
		service.setId_service(daoService.getId_service());
		service.setNom(daoService.getNom());
		service.setAdresse(daoService.getAdresse());
		service.setNumero(daoService.getNumero());
		service.setCodePostal(daoService.getCodePostal());
		service.setLocalite(daoService.getLocalite());
		service.setFax(daoService.getFax());
		service.setRemarque(daoService.getRemarque());
		service.setEmailService(daoService.getEmailService());
		service.setLocalisation(daoService.getLocalisation());
		service.setVoip(daoService.getVoip());
		return service;
	}

	@Override
	public synchronized void ajouterFonction(String fonction) {

		try {
			dao.ajouterFonction(fonction);
		} catch (RuntimeException e) {

		}
	}

	@Override
	public synchronized void supprimerFonction(String fonction) {
		dao.supprimerFonction(fonction);
	}

	public synchronized List getListePersonnelServiceAlphabetique(String lettre) {
		return dao.getListePersonnelService("%", lettre + "%", "%", "nom");
	}

	public synchronized List getListePersonnelServiceRequete(String requete) {
		return dao.getListePersonnelServiceRequete("%" + requete + "%");
	}

	private DaoOrdinateur formulaireOrdinateurToDaoOrdinateur(FormulaireOrdinateur formulaireOrdinateur) {
		DaoOrdinateur daoOrdinateur = new DaoOrdinateur();
		daoOrdinateur.setNom(formulaireOrdinateur.getNom());
		daoOrdinateur.setSystemeExploitation(formulaireOrdinateur.getSystemeExploitation());
		daoOrdinateur.setCarteMere(formulaireOrdinateur.getCarteMere());
		daoOrdinateur.setProcesseur(formulaireOrdinateur.getProcesseur());
		daoOrdinateur.setMemoireRam(formulaireOrdinateur.getMemoireRam());
		daoOrdinateur.setDisqueDur(formulaireOrdinateur.getDisqueDur());
		daoOrdinateur.setCarteReseau(formulaireOrdinateur.getCarteReseau());
		daoOrdinateur.setCarteGraphique(formulaireOrdinateur.getCarteGraphique());
		daoOrdinateur.setService(formulaireOrdinateur.getService());
		daoOrdinateur.setNumeroPrise(formulaireOrdinateur.getPrise());
		daoOrdinateur.setEcran(formulaireOrdinateur.getEcran());
		daoOrdinateur.setGraveur(formulaireOrdinateur.getGraveur());
		daoOrdinateur.setNumCpas2000(formulaireOrdinateur.getNumCpas2000());
		daoOrdinateur.setActif(formulaireOrdinateur.getActif());
		return daoOrdinateur;
	}

	private FormulaireOrdinateur daoOrdinateurToFormulaireOrdinateur(DaoOrdinateur daoOrdinateur) {
		FormulaireOrdinateur formulaireOrdinateur = new FormulaireOrdinateur();
		formulaireOrdinateur.setNom(daoOrdinateur.getNom());
		formulaireOrdinateur.setSystemeExploitation(daoOrdinateur.getSystemeExploitation());
		formulaireOrdinateur.setCarteMere(daoOrdinateur.getCarteMere());
		formulaireOrdinateur.setProcesseur(daoOrdinateur.getProcesseur());
		formulaireOrdinateur.setMemoireRam(daoOrdinateur.getMemoireRam());
		formulaireOrdinateur.setDisqueDur(daoOrdinateur.getDisqueDur());
		formulaireOrdinateur.setCarteReseau(daoOrdinateur.getCarteReseau());
		formulaireOrdinateur.setCarteGraphique(daoOrdinateur.getCarteGraphique());
		formulaireOrdinateur.setPrise(daoOrdinateur.getNumeroPrise());
		formulaireOrdinateur.setService(daoOrdinateur.getService());
		formulaireOrdinateur.setEcran(daoOrdinateur.getEcran());
		formulaireOrdinateur.setGraveur(daoOrdinateur.getGraveur());
		formulaireOrdinateur.setNumCpas2000(daoOrdinateur.getNumCpas2000());
		formulaireOrdinateur.setActif(daoOrdinateur.getActif());
		return formulaireOrdinateur;
	}

	private Ordinateur daoOrdinateurToOrdinateur(DaoOrdinateur daoOrdinateur) {
		Ordinateur ordinateur = new Ordinateur();
		ordinateur.setNom(daoOrdinateur.getNom());
		ordinateur.setSystemeExploitation(daoOrdinateur.getSystemeExploitation());
		ordinateur.setCarteMere(daoOrdinateur.getCarteMere());
		ordinateur.setProcesseur(daoOrdinateur.getProcesseur());
		ordinateur.setMemoireRam(daoOrdinateur.getMemoireRam());
		ordinateur.setDisqueDur(daoOrdinateur.getDisqueDur());
		ordinateur.setCarteReseau(daoOrdinateur.getCarteReseau());
		ordinateur.setCarteGraphique(daoOrdinateur.getCarteGraphique());
		ordinateur.setNumeroPrise(daoOrdinateur.getNumeroPrise());
		ordinateur.setService(daoOrdinateur.getService());
		ordinateur.setEcran(daoOrdinateur.getEcran());
		ordinateur.setGraveur(daoOrdinateur.getGraveur());
		ordinateur.setNumCpas2000(daoOrdinateur.getNumCpas2000());
		ordinateur.setActif(daoOrdinateur.getActif());
		return ordinateur;
	}

	private DaoPersonnel formulairePersonnelToDaoPersonnel(FormulairePersonnel formulairePersonnel) {
		DaoPersonnel daoPersonnel = new DaoPersonnel();
		daoPersonnel.setId_personnel(formulairePersonnel.getId_personnel());
		daoPersonnel.setNom(formulairePersonnel.getNom());
		daoPersonnel.setPrenom(formulairePersonnel.getPrenom());
		daoPersonnel.setLoginWindows(formulairePersonnel.getLoginWindows());
		daoPersonnel.setLoginAS400(formulairePersonnel.getLoginAS400());
		daoPersonnel.setLoginCPAS2000(formulairePersonnel.getLoginCPAS2000());
		daoPersonnel.setLoginNCC(formulairePersonnel.getLoginNCC());
		daoPersonnel.setLoginGRH(formulairePersonnel.getLoginGRH());
		daoPersonnel.setLoginPublilink(formulairePersonnel.getLoginPublilink());
		daoPersonnel.setMdpPublilink(formulairePersonnel.getMdpPublilink());
		daoPersonnel.setEmail(formulairePersonnel.getEmail());
		daoPersonnel.setEmailAlias(formulairePersonnel.getEmailAlias());
		daoPersonnel.setMdpEmail(formulairePersonnel.getMdpEmail());
		daoPersonnel.setWifi(formulairePersonnel.getWifi());
		daoPersonnel.setPathPhoto(formulairePersonnel.getPathPhoto());
		daoPersonnel.setCodeAS(formulairePersonnel.getCodeAS());
		return daoPersonnel;
	}

	private FormulairePersonnel daoPersonnelToFormulairePersonnel(DaoPersonnel daoPersonnel)
			throws UnsupportedEncodingException {
		FormulairePersonnel formulairePersonnel = new FormulairePersonnel();
		formulairePersonnel.setId_personnel(daoPersonnel.getId_personnel());
		formulairePersonnel.setNom(daoPersonnel.getNom());
		formulairePersonnel.setPrenom(daoPersonnel.getPrenom());
		formulairePersonnel.setLoginWindows(daoPersonnel.getLoginWindows());
		formulairePersonnel.setLoginAS400(daoPersonnel.getLoginAS400());
		formulairePersonnel.setLoginCPAS2000(daoPersonnel.getLoginCPAS2000());
		formulairePersonnel.setLoginNCC(daoPersonnel.getLoginNCC());
		formulairePersonnel.setLoginGRH(daoPersonnel.getLoginGRH());
		formulairePersonnel.setLoginPublilink(daoPersonnel.getLoginPublilink());
		formulairePersonnel.setMdpPublilink(daoPersonnel.getMdpPublilink());
		formulairePersonnel.setEmail(daoPersonnel.getEmail());
		formulairePersonnel.setEmailAlias(daoPersonnel.getEmailAlias());
		formulairePersonnel.setMdpEmail(daoPersonnel.getMdpEmail());
		formulairePersonnel.setWifi(daoPersonnel.getWifi());
		formulairePersonnel.setPathPhoto(daoPersonnel.getPathPhoto());
		formulairePersonnel.setCodeAS(daoPersonnel.getCodeAS());
		return formulairePersonnel;
	}

	private Personnel daoPersonnelToPersonnel(DaoPersonnel daoPersonnel) {
		Personnel personnel = new Personnel();
		personnel.setId_personnel(daoPersonnel.getId_personnel());
		personnel.setNom(daoPersonnel.getNom());
		personnel.setPrenom(daoPersonnel.getPrenom());
		personnel.setLoginWindows(daoPersonnel.getLoginWindows());
		personnel.setLoginAS400(daoPersonnel.getLoginAS400());
		personnel.setLoginCPAS2000(daoPersonnel.getLoginCPAS2000());
		personnel.setLoginNCC(daoPersonnel.getLoginNCC());
		personnel.setLoginGRH(daoPersonnel.getLoginGRH());
		personnel.setLoginPublilink(daoPersonnel.getLoginPublilink());
		personnel.setMdpPublilink(daoPersonnel.getMdpPublilink());
		personnel.setEmail(daoPersonnel.getEmail());
		personnel.setEmailAlias(daoPersonnel.getEmailAlias());
		personnel.setMdpEmail(daoPersonnel.getMdpEmail());
		personnel.setWifi(daoPersonnel.getWifi());
		personnel.setPathPhoto(daoPersonnel.getPathPhoto());
		personnel.setCodeAS(daoPersonnel.getCodeAS());
		return personnel;
	}

//	private Personnel daoPersonnelServiceToPersonnel(DaoPersonnelService daoPersonnelService) {
//		Personnel personnel = new Personnel();
//		personnel.setNom(daoPersonnelService.getNom());
//		personnel.setPrenom(daoPersonnelService.getPrenom());
//		personnel.setService(daoPersonnelService.getService());
//		personnel.setFonction(daoPersonnelService.getFonction());
//		personnel.setTelephone(daoPersonnelService.getTelephone());
//		return personnel;
//	}

	@Override
	public TicketItem getTicketItem(int idTicketItem){
		return dao.getTicketItem(idTicketItem);
	}

	@Override
	public void addTicketItem(TicketItem ticket) {
		dao.addTicketItem(ticket);

	}

	@Override
	public List getListeTicketItem(int idPlanning) {
		return dao.getListeTicketItem(idPlanning);
	}

	public synchronized void supprimerTicketItem(int idTicketItem) {
		TicketItem ticketItem = dao.getTicketItem(idTicketItem);
		
		dao.supprimerTicketItem(ticketItem.getIdTicketItem());
	}

	private DaoOrdinateurPersonnel formulaireOrdinateurPersonnelAssignerToDaoOrdinateurPersonnel(
			FormulaireOrdinateurPersonnelAssigner formulaire) {
		DaoOrdinateurPersonnel personnelOrdinateur = new DaoOrdinateurPersonnel();
		personnelOrdinateur.setOrdinateur(formulaire.getOrdinateur());
		personnelOrdinateur.setNom(formulaire.getEmploye().split(",")[0].trim());
		personnelOrdinateur.setPrenom(formulaire.getEmploye().split(",")[1].trim());
		return personnelOrdinateur;
	}

	private DaoPersonnelService formulairePersonnelServiceAssignerToDaoPersonnelService(
			FormulaireServicePersonnelAssigner formulaire) {
		DaoPersonnelService personnelService = new DaoPersonnelService();
		personnelService.setService(formulaire.getService());
		personnelService.setNom(formulaire.getEmploye().split(",")[0].trim());
		personnelService.setPrenom(formulaire.getEmploye().split(",")[1].trim());
		personnelService.setFonction(formulaire.getFonction());
		personnelService.setTelephone(formulaire.getTelephone());
		personnelService.setExtension(formulaire.getExtension());
		personnelService.setRem(formulaire.getRem());
		return personnelService;
	}

	public synchronized void supprimerService(String service) {
		List temp = dao.getListeOrdinateurs("%", service);
		for (int i = 0; i < temp.size(); i++) {
			DaoOrdinateur ordinateur = (DaoOrdinateur) temp.get(i);
			ordinateur.setService("");
			dao.supprimerOrdinateur(ordinateur.getNom());
			dao.addOrdinateur(ordinateur);
		}
		desassignerPersonnelService(service, "%", "%");
		dao.supprimerService(service);
	}

	public synchronized void desassignerPersonnelService(String service, String nom, String prenom) {
		dao.desassignerPersonnel(service, nom, prenom);
	}

	public synchronized void modifierService(FormulaireService service) {
		// Fred Derlee
		/*
		 * dao.supprimerService(service.getNom());
		 * dao.addService(formulaireServiceToDaoService(service));
		 */
		dao.updateService(formulaireServiceToDaoService(service));
	}

	public synchronized List<Service> getListeServices() {
		List listDao = dao.getListeServices("%");
		List<Service> list = new ArrayList<Service>();
		Iterator iter = listDao.iterator();
		while (iter.hasNext()) {
			Service element = daoServiceToService((DaoService) iter.next());
			list.add(element);
		}
		return list;
	}

	public synchronized Service getService(String service) {
		List list = dao.getListeServices(service);
		if (list.size() != 0) {
			return daoServiceToService((DaoService) list.get(0));
		} else {
			throw new DomainException("ce service (" + service + ") n'existe pas");
		}
	}

	public synchronized FormulaireService getFormulaireService(String service) {
		List list = dao.getListeServices(service);
		if (list.size() != 0) {
			return daoServiceToFormulaireService((DaoService) list.get(0));
		} else {
			throw new DomainException("ce service (" + service + ") n'existe pas");
		}
	}

	public synchronized void addPersonnel(FormulairePersonnel employe) {
		if (dao.getListePersonnels(employe.getNom(), employe.getPrenom()).size() == 0) {
			dao.addPersonnel(formulairePersonnelToDaoPersonnel(employe));
		} else {
			throw new DomainException(
					"cette personne (" + employe.getNom() + ", " + employe.getPrenom() + ") existe déjà");
		}
	}

	public synchronized FormulairePersonnel getFormulairePersonnel(String nom, String prenom) {
		List list = dao.getListePersonnels(nom, prenom);
		// List list;
		// list = dao.getListePersonnels(new String(nom.getBytes(),"UTF-8"), new
		// String(prenom.getBytes(),"UTF-8"));
		if (list.size() != 0) {
			try {
				return daoPersonnelToFormulairePersonnel((DaoPersonnel) list.get(0));
			} catch (UnsupportedEncodingException e) {
				throw new DaoException(e.getMessage());
			}
		} else {
			throw new DomainException("cette personne (" + nom + ", " + prenom + ") n'existe pas");
		}
	}

	public synchronized Personnel getPersonnel(String nom, String prenom) {
		List list = dao.getListePersonnels(nom, prenom);
		if (list.size() != 0) {
			return daoPersonnelToPersonnel((DaoPersonnel) list.get(0));
		} else {
			throw new DomainException("cette personne (" + nom + ", " + prenom + ") n'existe pas");
		}
	}

	public synchronized void assignerPersonnelService(FormulaireServicePersonnelAssigner formulaire) {
		dao.assignerPersonnel(formulairePersonnelServiceAssignerToDaoPersonnelService(formulaire));
	}

	public List<DaoPersonnel> getPersonnelDsiUser() {
		return dao.getPersonnelDsiUser();
	}

	public List<DaoPersonnel> getPersonnelDsichef() {
		return dao.getPersonnelDsichef();
	}

	public List<DaoPersonnel> getPersonnelDsiDirection() {
		return dao.getPersonnelDsiDirection();
	}

	public List<DaoPersonnel> getPersonnelDsiInfo() {
		return dao.getPersonnelDsiInfo();
	}

	public synchronized void modifierPersonnelService(FormulaireServicePersonnelAssigner formulaire) {
		String nom = formulaire.getEmploye().split(",")[0].trim();
		String prenom = formulaire.getEmploye().split(",")[1].trim();
		dao.desassignerPersonnel(formulaire.getService(), nom, prenom);
		dao.assignerPersonnel(formulairePersonnelServiceAssignerToDaoPersonnelService(formulaire));
	}

	public synchronized List getListePersonnelService(String service, String nom, String prenom) {
//		List listDao = dao.getListePersonnelService(service, nom, prenom);
//		List<Personnel> list = new ArrayList<Personnel>();
//		Iterator iter = listDao.iterator();
//		while (iter.hasNext()) {
//			Personnel element = daoPersonnelServiceToPersonnel((DaoPersonnelService) iter.next());
//			
//			list.add(element);
//		}
//		return list;
		return dao.getListePersonnelService(service, nom, prenom);
	}

	public synchronized List getListePersonnelService(int id_service, String nom, String prenom) {
		return dao.getListePersonnelService(id_service, nom, prenom);
	}

	public synchronized List getListePersonnelService() {
		List listePers = dao.getListePersonnelService(34, "%", "%");
		listePers.addAll(dao.getListePersonnelService(127, "%", "%"));
		listePers.addAll(dao.getListePersonnelService(128, "%", "%"));
		List listePersReturn = dao.getListePersonnelService(-1, "%", "%");
		for (int i = 0; i < listePers.size(); i++) {
			if (listePersReturn.size() == 0) {
				listePersReturn.add((DaoPersonnelService) listePers.get(0));
			}

			boolean ok = true;

			for (int j = 0; j < listePersReturn.size() && ok; j++) {
				if (((DaoPersonnelService) listePers.get(i)).getId_personnel()
						.equals(((DaoPersonnelService) listePersReturn.get(j)).getId_personnel())) {
					ok = false;
				}
			}

			if (ok) {
				listePersReturn.add((DaoPersonnelService) listePers.get(i));
			}
		}

		return listePersReturn;
	}

	public synchronized List getListePersonnelDepartement(int id_departement, String nom, String prenom) {
		return dao.getListePersonnelDepartement(id_departement, nom, prenom);
	}

	public synchronized List getListePersonnelService(String service, String nom, String prenom, String orderBy) {
		return dao.getListePersonnelService(service, nom, prenom, orderBy);
	}

	@SuppressWarnings("unchecked")
	public synchronized List<PersonnelGestionService> getListePersonnelGestionService(String service) {
		List<PersonnelGestionService> list = new ArrayList<PersonnelGestionService>();
		List daoList = dao.getListePersonnelService(service, "%", "%");
		Iterator<DaoPersonnelService> iterator = daoList.iterator();
		while (iterator.hasNext()) {
			DaoPersonnelService element = iterator.next();
			PersonnelGestionService personnel = new PersonnelGestionService();
			personnel.setNom(element.getNom());
			personnel.setPrenom(element.getPrenom());
			personnel.setService(element.getService());
			personnel.setFonction(element.getFonction());
			personnel.setTelephone(element.getTelephone());
			personnel.setRem(element.getRem());
			personnel.setExtension(element.getExtension());

			List ordi = dao.getListeOrdinateurPersonnel("%", element.getNom(), element.getPrenom());
			Iterator<DaoOrdinateurPersonnel> iterOrdi = ordi.iterator();
			List<String> nomOrdi = new ArrayList<String>();
			while (iterOrdi.hasNext()) {
				DaoOrdinateurPersonnel elementOrdi = iterOrdi.next();
				Ordinateur ordinaTemp = getOrdinateur(elementOrdi.getOrdinateur());
				if (ordinaTemp.getService().equals(service)) {
					nomOrdi.add(elementOrdi.getOrdinateur());
				}
			}
			personnel.setOrdinateur(nomOrdi);
			list.add(personnel);
		}
		return list;
	}

	public synchronized List getListePersonnelServiceAssigner(String service) {
//		List<Personnel> listePersonnel = getListePersonnel();
//		Iterator<Personnel> firstIterator = listePersonnel.iterator();
//		List<Personnel> list = new ArrayList<Personnel>();
//		while (firstIterator.hasNext()) {
//			Personnel element = firstIterator.next();
//			List listDao = dao.getListePersonnelService(service, element.getNom(), element.getPrenom());
//			if(listDao.size() == 0) {
//				list.add(element);
//			}
//		}
//		return list;
		List<DaoPersonnel> listePersonnel = getListePersonnel();
		Iterator<DaoPersonnel> firstIterator = listePersonnel.iterator();
		List<DaoPersonnel> list = new ArrayList<DaoPersonnel>();
		while (firstIterator.hasNext()) {
			DaoPersonnel element = firstIterator.next();
			List listDao = dao.getListePersonnelService(service, element.getNom(), element.getPrenom());
			if (listDao.size() == 0) {
				list.add(element);
			}
		}
		return list;
	}

	public synchronized List getListeServicePersonnelAssigner(String nom, String prenom) {
		List<Service> listeService = getListeServices();
		Iterator<Service> firstIterator = listeService.iterator();
		List<Service> list = new ArrayList<Service>();
		while (firstIterator.hasNext()) {
			Service element = firstIterator.next();
			List listDao = dao.getListePersonnelService(element.getNom(), nom, prenom);
			if (listDao.size() == 0) {
				list.add(element);
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public synchronized List<DaoPersonnel> getListePersonnel() {
		return dao.getListePersonnels("%", "%");
	}

	public synchronized List<String> getListePersonnelTexte() {
		List<DaoPersonnel> listePers = dao.getListePersonnels("%", "%");
		List<String> liste = new ArrayList<String>();
		for (DaoPersonnel personnel : listePers) {
			liste.add(personnel.getNom() + ", " + personnel.getPrenom());
		}
		return liste;
	}

	public synchronized List<String> getListePersonnelInfoTexte() {
		List<DaoPersonnelService> listePers = dao.getListePersonnelDepartement(8, "%", "%");
		List<String> liste = new ArrayList<String>();
		for (DaoPersonnelService personnel : listePers) {
			liste.add(personnel.getNom() + ", " + personnel.getPrenom());
		}
		return liste;
	}

	public synchronized void modifierPersonnel(FormulairePersonnel employe) {
		dao.updatePersonnel(formulairePersonnelToDaoPersonnel(employe));
	}

	public synchronized void supprimerPersonnel(String nom, String prenom) {
		// TODO traitement de deuxième plan : ordi, etc ...
		desassignerOrdinateurPersonnel("%", nom, prenom);
		desassignerPersonnelService("%", nom, prenom);
		supprimerUtilisateur(nom, prenom);
		dao.supprimerPersonnel(nom, prenom);
	}

	public synchronized void addOrdinateur(FormulaireOrdinateur formulaireOrdinateur) {
		if (dao.getListeOrdinateurs(formulaireOrdinateur.getNom(), "%").size() == 0) {
			dao.addOrdinateur(formulaireOrdinateurToDaoOrdinateur(formulaireOrdinateur));
		} else {
			throw new DomainException("cet ordinateur (" + formulaireOrdinateur.getNom() + ") existe déjà");
		}
	}

	public synchronized int addTache(Tache tache) {
		return dao.addTache(tache);
	}

	public synchronized List getListeTache() {
		return dao.getListeTache();
	}

	public synchronized Tache getTache(String idPlanning) {
		return dao.getTache(idPlanning);
	}

	@Override
	public synchronized void updateTache(Tache tache) {
		dao.updateTache(tache);
	}

	@Override
	public List getListeTacheEnAttente(String service, String nom, String prenom, String ordinateur, String order) {
		return dao.getListeTacheEnAttente(service, nom, prenom, ordinateur, order);
	}

	@Override
	public synchronized List getListeTacheEnCours(String service, String nom, String prenom, String ordinateur,
			String order) {
		return dao.getListeTacheEnCours(service, nom, prenom, ordinateur, order);
	}

	@Override
	public synchronized List getListeTacheFinie(String service, String nom, String prenom, String ordinateur,
			String order) {
		return dao.getListeTacheFinie(service, nom, prenom, ordinateur, order);
	}

	public synchronized FormulaireOrdinateur getFormulaireOrdinateur(String ordinateur) {
		List list = dao.getListeOrdinateurs(ordinateur, "%");
		if (list.size() != 0) {
			// DaoOrdinateur tmp = (DaoOrdinateur)list.get(0);
			return daoOrdinateurToFormulaireOrdinateur((DaoOrdinateur) list.get(0));
		} else {
			throw new DomainException("cet ordinateur (" + ordinateur + ") n'existe pas");
		}
	}

	public synchronized List<Ordinateur> getListeOrdinateurs() {
		List listDao = dao.getListeOrdinateurs("%", "%");
		List<Ordinateur> list = new ArrayList<Ordinateur>();
		Iterator iter = listDao.iterator();
		while (iter.hasNext()) {
			Ordinateur element = daoOrdinateurToOrdinateur((DaoOrdinateur) iter.next());
			list.add(element);
		}
		return list;
	}

	public synchronized List<DaoPersonnel> getListeAccesInternet() {
		List<DaoPersonnel> listePersonnel = dao.getListePersonnels("%", "%");
		List<DaoPersonnel> liste = new ArrayList<DaoPersonnel>();
//		List<AccesInternet> listeAccesInternet = new ArrayList<AccesInternet>();
		for (DaoPersonnel personnel : listePersonnel) {
			if (!personnel.getEmail().equals("") || !personnel.getLoginPublilink().equals("")) {
//				AccesInternet acces = new AccesInternet();
//				acces.setNom(personnel.getNom());
//				acces.setPrenom(personnel.getPrenom());
//				acces.setEmail(personnel.getEmail());
//				acces.setMdpEmail(personnel.getM)
				liste.add(personnel);
			}
		}
		return liste;
	}

	public synchronized List<Ordinateur> getListeOrdinateurService(String service) {
		List listDao = dao.getListeOrdinateurs("%", service);
		List<Ordinateur> list = new ArrayList<Ordinateur>();
		Iterator iter = listDao.iterator();
		while (iter.hasNext()) {
			Ordinateur element = daoOrdinateurToOrdinateur((DaoOrdinateur) iter.next());
			list.add(element);
		}
		return list;
	}

	public synchronized Ordinateur getOrdinateur(String ordinateur) {
		List list = dao.getListeOrdinateurs(ordinateur, "%");
		if (list.size() != 0) {
			return daoOrdinateurToOrdinateur((DaoOrdinateur) list.get(0));
		} else {
			throw new DomainException("cet ordinateur (" + ordinateur + ") n'existe pas");
		}
	}

	public synchronized void modifierOrdinateur(FormulaireOrdinateur formulaireOrdinateur) {
		// dao.supprimerOrdinateur(formulaireOrdinateur.getNom());
		// dao.addOrdinateur(formulaireOrdinateurToDaoOrdinateur(formulaireOrdinateur));
		dao.modifierOrdinateur(formulaireOrdinateurToDaoOrdinateur(formulaireOrdinateur));
	}

	public synchronized void assignerOrdinateurPersonnel(FormulaireOrdinateurPersonnelAssigner formulaire) {
		dao.assignerOrdinateur(formulaireOrdinateurPersonnelAssignerToDaoOrdinateurPersonnel(formulaire));
	}

	public synchronized void desassignerOrdinateurPersonnel(String ordinateur, String nom, String prenom) {
		dao.desassignerOrdinateur(ordinateur, nom, prenom);
	}

	public synchronized List getListeOrdinateurPersonnel(String ordinateur, String nom, String prenom) {
		return dao.getListeOrdinateurPersonnel(ordinateur, nom, prenom);
	}

	public synchronized List getListeOrdinateurPersonnelAssigner(String nom, String prenom) {
		List<Ordinateur> listeService = getListeOrdinateurs();
		Iterator<Ordinateur> firstIterator = listeService.iterator();
		List<Ordinateur> list = new ArrayList<Ordinateur>();
		while (firstIterator.hasNext()) {
			Ordinateur element = firstIterator.next();
			List listDao = dao.getListeOrdinateurPersonnel(element.getNom(), nom, prenom);
			if (listDao.size() == 0) {
				list.add(element);
			}
		}
		return list;
	}

	public synchronized List getListePersonnelOrdinateurAssigner(String ordinateur) {
//		List<Personnel> listePersonnel = getListePersonnel();
//		Iterator<Personnel> firstIterator = listePersonnel.iterator();
//		List<Personnel> list = new ArrayList<Personnel>();
//		while (firstIterator.hasNext()) {
//			Personnel element = firstIterator.next();
//			List listDao = dao.getListeOrdinateurPersonnel(ordinateur, element.getNom(), element.getPrenom());
//			if(listDao.size() == 0) {
//				list.add(element);
//			}
//		}
//		return list;
		List<DaoPersonnel> listePersonnel = getListePersonnel();
		Iterator<DaoPersonnel> firstIterator = listePersonnel.iterator();
		List<DaoPersonnel> list = new ArrayList<DaoPersonnel>();
		while (firstIterator.hasNext()) {
			DaoPersonnel element = firstIterator.next();
			List listDao = dao.getListeOrdinateurPersonnel(ordinateur, element.getNom(), element.getPrenom());
			if (listDao.size() == 0) {
				list.add(element);
			}
		}
		return list;
	}

	public synchronized void supprimerOrdinateur(String ordinateur) {
		dao.desassignerImprimante(ordinateur, "%");
		desassignerOrdinateurPersonnel(ordinateur, "%", "%");
		List temp = dao.getListeImprimante("%", ordinateur);
		for (int i = 0; i < temp.size(); i++) {
			Imprimante imp = (Imprimante) temp.get(i);
			imp.setOrdinateurLocal("");
			modifierImprimante(imp);
		}
		dao.supprimerOrdinateur(ordinateur);
	}

	public synchronized List getTypeOrdinateur() {
		return dao.getTypeOrdinateur();
	}

	public synchronized List getTypeImprimante() {
		return dao.getTypeImprimante();
	}

	public synchronized List getTypeOS() {
		return dao.getTypeOS();
	}

	public synchronized List getTypeFonction() {
		return dao.getTypeFonction();
	}

	public synchronized List getTypeEcran() {
		return dao.getTypeEcran();
	}

	public synchronized List getTypeGraveur() {
		return dao.getTypeGraveur();
	}

	public synchronized Utilisateur authentification(String login, String mdp) {
		List temp = dao.getUtilisateur(login, "%", "%");
		if (temp.size() > 0) {
			Utilisateur utilisateur = (Utilisateur) temp.get(0);
			if (utilisateur.getMdp().equals(mdp)) {
				return (Utilisateur) temp.get(0);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public synchronized Utilisateur getUtilisateur(String nom, String prenom) {
		List list = dao.getUtilisateur("%", nom, prenom);
		if (list.size() != 0) {
			return (Utilisateur) list.get(0);
		} else {
			return null;
		}
	}

	public synchronized Utilisateur getUtilisateur(String login) {
		List list = dao.getUtilisateur(login, "%", "%");
		if (list.size() != 0) {
			return (Utilisateur) list.get(0);
		} else {
			return null;
		}
	}

	public synchronized void addUtilisateur(String nom, String prenom) {
		String temp = nom.toLowerCase();
		StringBuffer login = new StringBuffer();
		String lettre = "";
		int max = 6;
		for(int i = 0; i < max && i < temp.length() && !lettre.equals("/"); i++) {
			lettre = temp.substring(i, i+1);
			if(!lettre.equals(" ") && !lettre.equals("-") && !lettre.equals("/")) {
				login.append(lettre);
			} else {
				max++;
			}
		}
		if(dao.getUtilisateur(login.toString(), "%", "%").size() !=0) {
			String tempPrenom = prenom.toLowerCase();
			for(int i = 0; i < tempPrenom.length() && dao.getUtilisateur(login.toString(), "%", "%").size() !=0; i++) {
				lettre = tempPrenom.substring(i, i+1);
				if(!lettre.equals(" ")) {
					login.append(lettre);
				}
			}
		} 
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNom(nom);
		utilisateur.setPrenom(prenom);
		utilisateur.setMdp("cpas");
		utilisateur.setActif(true);
		utilisateur.setLogin(login.toString());
		dao.addUtilisateur(utilisateur);
		PrivilegeInformatique privilege = new PrivilegeInformatique();
		privilege.setLogin(login.toString());
		dao.addPrivilègeInformatique(privilege);


	}

	public synchronized void supprimerUtilisateur(String login) {
		dao.supprimerPrivilègeInformatique(login);
		dao.supprimerUtilisateur(login);
	}

	public synchronized void supprimerUtilisateur(String nom, String prenom) {
		Utilisateur utilisateur = getUtilisateur(nom, prenom);
		if (utilisateur != null) {
			supprimerUtilisateur(utilisateur.getLogin());
		}
	}

	public synchronized void reinitialiserMotDePasseUtilisateur(String login) {
		Utilisateur utilisateur = (Utilisateur) dao.getUtilisateur(login, "%", "%").get(0);
		utilisateur.setMdp("cpas");
		dao.supprimerUtilisateur(login);
		dao.addUtilisateur(utilisateur);
	}

	public synchronized void changerAccesIntranetUtilisateur(String login, String acces) {
		Utilisateur utilisateur = (Utilisateur) dao.getUtilisateur(login, "%", "%").get(0);
		utilisateur.setActif(acces);
		dao.supprimerUtilisateur(login);
		dao.addUtilisateur(utilisateur);
	}

	public synchronized void addPrivilègeInformatique(PrivilegeInformatique privilege) {
		dao.addPrivilègeInformatique(privilege);
	}

	public synchronized PrivilegeInformatique getPrivilegeInformatique(String login) {
		return dao.getPrivilegeInformatique(login);
	}

	public synchronized void supprimerPrivilègeInformatique(String login) {
		dao.supprimerPrivilègeInformatique(login);
	}

	public synchronized void modifierPrivilègeInformatique(PrivilegeInformatique privilege) {
		dao.supprimerPrivilègeInformatique(privilege.getLogin());
		dao.addPrivilègeInformatique(privilege);
	}

	public synchronized void changerMotDePasseUtilisateur(String login, String mdp) {
		Utilisateur utilisateur = (Utilisateur) dao.getUtilisateur(login, "%", "%").get(0);
		utilisateur.setMdp(mdp);
		dao.supprimerUtilisateur(login);
		dao.addUtilisateur(utilisateur);
	}

	public synchronized void addImprimante(Imprimante imprimante) {
		if (dao.getListeImprimante(imprimante.getNumeroSerie(), "%").size() == 0) {
			dao.addImprimante(imprimante);
		} else {
			throw new DomainException("cette imprimante (" + imprimante.getNumeroSerie() + ") existe déjà");
		}

	}

	public synchronized List getListeImprimante(String numeroSerie, String ordinateur) {
		return dao.getListeImprimante(numeroSerie, ordinateur);
	}

	public synchronized List getListeImprimanteService(String service) {
		return dao.getListeImprimanteService(service);
	}

	public synchronized void modifierImprimante(Imprimante imprimante) {
		Imprimante old = getImprimante(imprimante.getNumeroSerie());
		dao.supprimerImprimante(imprimante.getNumeroSerie());
		if (!old.getOrdinateurLocal().equals(imprimante.getOrdinateurLocal())) {
			dao.desassignerImprimante("%", imprimante.getNumeroSerie());
		}
		dao.addImprimante(imprimante);
//		 TODO traitement de deuxième plan : ordi distant, etc ...
	}

	public synchronized Imprimante getImprimante(String numeroSerie) {
		List list = dao.getListeImprimante(numeroSerie, "%");
		if (list != null && list.size() > 0) {
			return (Imprimante) list.get(0);
		}
		return null;
	}

	public synchronized void supprimerImprimante(String numeroSerie) {
		dao.desassignerImprimante("%", numeroSerie);
		dao.supprimerImprimante(numeroSerie);
	}

	public synchronized void testBD() {
		try {
			dao.getTypeEcran();
		} catch (Exception e) {

		}
	}

//	public synchronized Logger getLogger() {
//		return logger;
//	}
//
//	public synchronized void setLogger(Logger logger) {
//		this.logger = logger;
//	}

	public synchronized void assignerImprimante(FormulaireImprimanteOrdinateurAssigner imprimanteReseau) {
		dao.assignerImprimante(imprimanteReseau);
	}

	public synchronized void desassignerImprimante(String ordinateur, String imprimante) {
		dao.desassignerImprimante(ordinateur, imprimante);
	}

	public synchronized List getListeImprimanteOrdinateur(String ordinateur, String imprimante) {
		return dao.getListeImprimanteOrdinateur(ordinateur, imprimante);
	}

	@SuppressWarnings("unchecked")
	public synchronized List getListeImprimanteReseau(String ordinateur) {
		List imprOrdi = getListeImprimanteOrdinateur(ordinateur, "%");
		List imprReseau = new ArrayList();
		Iterator it = imprOrdi.iterator();
		while (it.hasNext()) {
			FormulaireImprimanteOrdinateurAssigner element = (FormulaireImprimanteOrdinateurAssigner) it.next();
			imprReseau.add(getImprimante(element.getNumeroSerie()));
		}
		return imprReseau;
	}

	@SuppressWarnings("unchecked")
	public synchronized List getListeOrdinateursImprimlanteAssigner(String numeroSerie) {
		Imprimante imp = getImprimante(numeroSerie);
		List ordinateurs = getListeOrdinateurs();
		Iterator it = ordinateurs.iterator();
		List ordiAssignables = new ArrayList();
		while (it.hasNext()) {
			Ordinateur ordi = (Ordinateur) it.next();
			List test = dao.getListeImprimanteOrdinateur(ordi.getNom(), numeroSerie);
			if (test.size() == 0 && !imp.getOrdinateurLocal().equals(ordi.getNom())) {
				ordiAssignables.add(ordi);
			}
		}
		return ordiAssignables;
	}

	@SuppressWarnings("unchecked")
	public synchronized List getListeImprimlanteOrdinateursAssigner(String ordinateur) {
		List imprimantesToute = dao.getListeImprimante("%", "%", "ordinateurLocal");
//		Ordinateur ordi = getOrdinateur(ordinateur);
		Iterator it = imprimantesToute.iterator();
		List imprimanteAssignables = new ArrayList();
		while (it.hasNext()) {
			Imprimante imp = (Imprimante) it.next();
			List test = dao.getListeImprimanteOrdinateur(ordinateur, imp.getNumeroSerie());
			if (test.size() == 0 && !imp.getOrdinateurLocal().equals(ordinateur)) {
				imprimanteAssignables.add(imp);
			}
		}

		return imprimanteAssignables;
	}

	@SuppressWarnings("unchecked")
	public synchronized Document getAnnuaireAlphabetiqueXML() {

		Document document = null;
		Text texte;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			document = db.newDocument();
		} catch (ParserConfigurationException e) {
			throw new DaoException(e.getMessage());
		}
		Element racine = document.createElement("cpas");
		document.appendChild(racine);
		Element listeAlphabetique = document.createElement("listeAlphabetique");
		racine.appendChild(listeAlphabetique);

		GregorianCalendar now = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd MMMM yyyy");
		Element date = document.createElement("date");
		texte = document.createTextNode(sdf.format(now.getTime()));
		date.appendChild(texte);
		listeAlphabetique.appendChild(date);

		List<String> listeLettre = new ArrayList<String>();
		listeLettre.add("A");
		listeLettre.add("B");
		listeLettre.add("C");
		listeLettre.add("D");
		listeLettre.add("E");
		listeLettre.add("F");
		listeLettre.add("G");
		listeLettre.add("H");
		listeLettre.add("I");
		listeLettre.add("J");
		listeLettre.add("K");
		listeLettre.add("L");
		listeLettre.add("M");
		listeLettre.add("N");
		listeLettre.add("O");
		listeLettre.add("P");
		listeLettre.add("Q");
		listeLettre.add("R");
		listeLettre.add("S");
		listeLettre.add("T");
		listeLettre.add("U");
		listeLettre.add("V");
		listeLettre.add("W");
		listeLettre.add("X");
		listeLettre.add("Y");
		listeLettre.add("Z");

		for (int i = 0; i < listeLettre.size(); i++) {
			Element lettre = document.createElement("lettre");

			Element alpha = document.createElement("alpha");
			texte = document.createTextNode(listeLettre.get(i));
			alpha.appendChild(texte);
			lettre.appendChild(alpha);

			// employe

			List<DaoPersonnelService> personnelService = dao.getListePersonnelService("%", listeLettre.get(i) + "%",
					"%", "%", "nom");
			for (int j = 0; j < personnelService.size(); j++) {
				Element employe = document.createElement("employe");

				Element nomEmploye = document.createElement("nom");
				texte = document.createTextNode(personnelService.get(j).getNom());
				nomEmploye.appendChild(texte);
				employe.appendChild(nomEmploye);

				Element prenomEmploye = document.createElement("prenom");
				texte = document.createTextNode(personnelService.get(j).getPrenom());
				prenomEmploye.appendChild(texte);
				employe.appendChild(prenomEmploye);

				Element serviceEmploye = document.createElement("service");
				texte = document.createTextNode(personnelService.get(j).getService());
				serviceEmploye.appendChild(texte);
				employe.appendChild(serviceEmploye);

				Element telEmploye = document.createElement("tel");
				String telephone = personnelService.get(j).getTelephone();
				if ((telephone.startsWith("04/220.") || telephone.startsWith("04/250."))
						&& !telephone.startsWith("04/220.23")) {
					telephone = telephone.substring(7);
				}
				texte = document.createTextNode(telephone);
				telEmploye.appendChild(texte);
				employe.appendChild(telEmploye);

				lettre.appendChild(employe);
			}
			listeAlphabetique.appendChild(lettre);
		}
		return document;
	}

	@SuppressWarnings("unchecked")
	public synchronized Document getAnnuaireServiceXML() {

		Document document = null;
		Text texte;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			document = db.newDocument();
		} catch (ParserConfigurationException e) {
			throw new DaoException(e.getMessage());
		}
		Element racine = document.createElement("cpas");
		document.appendChild(racine);
		Element listeServices = document.createElement("listeServices");
		racine.appendChild(listeServices);

		GregorianCalendar now = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd MMMM yyyy");
		Element date = document.createElement("date");
		texte = document.createTextNode(sdf.format(now.getTime()));
		date.appendChild(texte);
		listeServices.appendChild(date);

		List<DaoService> services = dao.getListeServices("%");

		// service

		for (int i = 0; i < services.size(); i++) {
			Element service = document.createElement("service");

			Element nomService = document.createElement("nom");
			texte = document.createTextNode(services.get(i).getNom());
			nomService.appendChild(texte);
			service.appendChild(nomService);

			Element adresseService = document.createElement("adresse");
			texte = document.createTextNode(services.get(i).getAdresse() + ", " + services.get(i).getNumero() + " - "
					+ services.get(i).getCodePostal() + " " + services.get(i).getLocalite());
			adresseService.appendChild(texte);
			service.appendChild(adresseService);

			Element localisationService = document.createElement("localisation");
			texte = document.createTextNode(services.get(i).getLocalisation());
			localisationService.appendChild(texte);
			service.appendChild(localisationService);

			Element faxService = document.createElement("fax");
			texte = document.createTextNode(services.get(i).getFax());
			faxService.appendChild(texte);
			service.appendChild(faxService);

			Element emailService = document.createElement("email");
			texte = document.createTextNode(services.get(i).getEmailService());
			emailService.appendChild(texte);
			service.appendChild(emailService);

			Element remarqueService = document.createElement("remarque");
			texte = document.createTextNode(services.get(i).getRemarque());
			remarqueService.appendChild(texte);
			service.appendChild(remarqueService);

			Element lettre = document.createElement("lettre");
			service.appendChild(lettre);

			List<DaoPersonnelService> personnelService = dao.getListePersonnelService(services.get(i).getNom(), "%",
					"%", "%", "nom");
			for (int j = 0; j < personnelService.size(); j++) {
				Element employe = document.createElement("employe");

				Element nomEmploye = document.createElement("nom");
				texte = document.createTextNode(personnelService.get(j).getNom());
				nomEmploye.appendChild(texte);
				employe.appendChild(nomEmploye);

				Element prenomEmploye = document.createElement("prenom");
				String remarque = (personnelService.get(j).getRem().equals("")) ? ""
						: " - " + personnelService.get(j).getRem();
				texte = document.createTextNode(personnelService.get(j).getPrenom() + remarque);
				prenomEmploye.appendChild(texte);
				employe.appendChild(prenomEmploye);

				Element fonctionEmploye = document.createElement("fonction");
				texte = document.createTextNode(personnelService.get(j).getFonction());
				fonctionEmploye.appendChild(texte);
				employe.appendChild(fonctionEmploye);

				Element telEmploye = document.createElement("tel");
				String telephone = personnelService.get(j).getTelephone();
				if ((telephone.startsWith("04/220.") || telephone.startsWith("04/250."))
						&& !telephone.startsWith("04/220.23")) {
					telephone = telephone.substring(7);
				}
				texte = document.createTextNode(telephone);
				telEmploye.appendChild(texte);
				employe.appendChild(telEmploye);

				lettre.appendChild(employe);
			}

			/*
			 * // type fonction
			 * 
			 * List<TypeFonction> listeFonction =
			 * dao.getFonctionService(services.get(i).getNom()); TypeFonction temp = new
			 * TypeFonction(); temp.setFonction("Superviseur");
			 * if(listeFonction.contains(temp)) { int index = listeFonction.indexOf(temp);
			 * listeFonction.remove(index); listeFonction.add(0, temp); } temp = new
			 * TypeFonction(); temp.setFonction("Superviseuse");
			 * if(listeFonction.contains(temp)) { int index = listeFonction.indexOf(temp);
			 * listeFonction.remove(index); listeFonction.add(0, temp); } temp = new
			 * TypeFonction(); temp.setFonction("Superviseuses");
			 * if(listeFonction.contains(temp)) { int index = listeFonction.indexOf(temp);
			 * listeFonction.remove(index); listeFonction.add(0, temp); } temp = new
			 * TypeFonction(); temp.setFonction("A.S. en chef");
			 * if(listeFonction.contains(temp)) { int index = listeFonction.indexOf(temp);
			 * listeFonction.remove(index); listeFonction.add(0, temp); } temp = new
			 * TypeFonction(); temp.setFonction("A.S. en chef f.f.");
			 * if(listeFonction.contains(temp)) { int index = listeFonction.indexOf(temp);
			 * listeFonction.remove(index); listeFonction.add(0, temp); } temp = new
			 * TypeFonction(); temp.setFonction("Graduée spécifique en chef");
			 * if(listeFonction.contains(temp)) { int index = listeFonction.indexOf(temp);
			 * listeFonction.remove(index); listeFonction.add(0, temp); } temp = new
			 * TypeFonction(); temp.setFonction("Chef de service");
			 * if(listeFonction.contains(temp)) { int index = listeFonction.indexOf(temp);
			 * listeFonction.remove(index); listeFonction.add(0, temp); } temp = new
			 * TypeFonction(); temp.setFonction("Chef de bureau administratif");
			 * if(listeFonction.contains(temp)) { int index = listeFonction.indexOf(temp);
			 * listeFonction.remove(index); listeFonction.add(0, temp); } temp = new
			 * TypeFonction(); temp.setFonction("Chef de bureau spécifique f.f.");
			 * if(listeFonction.contains(temp)) { int index = listeFonction.indexOf(temp);
			 * listeFonction.remove(index); listeFonction.add(0, temp); } temp = new
			 * TypeFonction(); temp.setFonction("Responsable");
			 * if(listeFonction.contains(temp)) { int index = listeFonction.indexOf(temp);
			 * listeFonction.remove(index); listeFonction.add(0, temp); } temp = new
			 * TypeFonction(); temp.setFonction("Coordinatrice");
			 * if(listeFonction.contains(temp)) { int index = listeFonction.indexOf(temp);
			 * listeFonction.remove(index); listeFonction.add(0, temp); } temp = new
			 * TypeFonction(); temp.setFonction("Coordinateur");
			 * if(listeFonction.contains(temp)) { int index = listeFonction.indexOf(temp);
			 * listeFonction.remove(index); listeFonction.add(0, temp); } temp = new
			 * TypeFonction(); temp.setFonction("Chef de bureau");
			 * if(listeFonction.contains(temp)) { int index = listeFonction.indexOf(temp);
			 * listeFonction.remove(index); listeFonction.add(0, temp); } temp = new
			 * TypeFonction(); temp.setFonction("Chef de service administratif");
			 * if(listeFonction.contains(temp)) { int index = listeFonction.indexOf(temp);
			 * listeFonction.remove(index); listeFonction.add(0, temp); } temp = new
			 * TypeFonction(); temp.setFonction("Directrice f.f.");
			 * if(listeFonction.contains(temp)) { int index = listeFonction.indexOf(temp);
			 * listeFonction.remove(index); listeFonction.add(0, temp); } temp = new
			 * TypeFonction(); temp.setFonction("Directrice");
			 * if(listeFonction.contains(temp)) { int index = listeFonction.indexOf(temp);
			 * listeFonction.remove(index); listeFonction.add(0, temp); } temp = new
			 * TypeFonction(); temp.setFonction("Secrétaire de cabinet");
			 * if(listeFonction.contains(temp)) { int index = listeFonction.indexOf(temp);
			 * listeFonction.remove(index); listeFonction.add(0, temp); } temp = new
			 * TypeFonction(); temp.setFonction("Receveur");
			 * if(listeFonction.contains(temp)) { int index = listeFonction.indexOf(temp);
			 * listeFonction.remove(index); listeFonction.add(0, temp); } temp = new
			 * TypeFonction(); temp.setFonction("Directrice de l'Aide sociale");
			 * if(listeFonction.contains(temp)) { int index = listeFonction.indexOf(temp);
			 * listeFonction.remove(index); listeFonction.add(0, temp); } temp = new
			 * TypeFonction(); temp.setFonction("Directrice de l'Action sociale");
			 * if(listeFonction.contains(temp)) { int index = listeFonction.indexOf(temp);
			 * listeFonction.remove(index); listeFonction.add(0, temp); } temp = new
			 * TypeFonction(); temp.setFonction("Secrétaire f.f.");
			 * if(listeFonction.contains(temp)) { int index = listeFonction.indexOf(temp);
			 * listeFonction.remove(index); listeFonction.add(0, temp); } temp = new
			 * TypeFonction(); temp.setFonction("Président");
			 * if(listeFonction.contains(temp)) { int index = listeFonction.indexOf(temp);
			 * listeFonction.remove(index); listeFonction.add(0, temp); } for(int k = 0; k <
			 * listeFonction.size(); k++) { Element fonction =
			 * document.createElement("fonction"); service.appendChild(fonction);
			 * 
			 * Element nomFonction = document.createElement("nomFonction"); texte =
			 * document.createTextNode(listeFonction.get(k).getFonction());
			 * nomFonction.appendChild(texte); fonction.appendChild(nomFonction);
			 * 
			 * // employe
			 * 
			 * List<DaoPersonnelService> personnelService =
			 * dao.getListePersonnelService(services.get(i).getNom(), "%", "%",
			 * listeFonction.get(k).getFonction(), "nom"); for(int j = 0; j <
			 * personnelService.size(); j++) { Element employe =
			 * document.createElement("employe");
			 * 
			 * Element nomEmploye = document.createElement("nom"); texte =
			 * document.createTextNode(personnelService.get(j).getNom());
			 * nomEmploye.appendChild(texte); employe.appendChild(nomEmploye);
			 * 
			 * Element prenomEmploye = document.createElement("prenom"); String remarque =
			 * (personnelService.get(j).getRem().equals(""))? "" : " - " +
			 * personnelService.get(j).getRem(); texte =
			 * document.createTextNode(personnelService.get(j).getPrenom() + remarque);
			 * prenomEmploye.appendChild(texte); employe.appendChild(prenomEmploye);
			 * 
			 * Element fonctionEmploye = document.createElement("fonction"); texte =
			 * document.createTextNode(personnelService.get(j).getFonction());
			 * fonctionEmploye.appendChild(texte); employe.appendChild(fonctionEmploye);
			 * 
			 * Element telEmploye = document.createElement("tel"); String telephone =
			 * personnelService.get(j).getTelephone(); if((telephone.startsWith("04/220.")
			 * || telephone.startsWith("04/250.")) && !telephone.startsWith("04/220.23")) {
			 * telephone = telephone.substring(7); } texte =
			 * document.createTextNode(telephone); telEmploye.appendChild(texte);
			 * employe.appendChild(telEmploye);
			 * 
			 * fonction.appendChild(employe); } }
			 */
			listeServices.appendChild(service);
		}
		return document;
	}

	@Override
	public synchronized void log(HttpServletRequest request, HttpServletResponse response) {
		log(request, response, "");
	}

	@Override
	public synchronized void log(HttpServletRequest request, HttpServletResponse response, String message) {
		String hostName = null;
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			cookies = new Cookie[0];
		}
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			String nomCookie = cookie.getName();
			if (nomCookie.equals("hostName")) {
				hostName = cookie.getValue();
			}
		}
		try {
			if (hostName == null) {
				hostName = (InetAddress.getByName(request.getRemoteHost())).getHostName();
				Cookie cookie = new Cookie("hostName", hostName);
				cookie.setMaxAge(30 * 24 * 3600);
				response.addCookie(cookie);
			}
		} catch (Exception e) {
			hostName = "";
		}
		boolean paramExist = false;
		StringBuffer buffer = new StringBuffer();
		Enumeration parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			paramExist = true;
			String param = (String) parameterNames.nextElement();
			if (!param.equals("mdp") && !param.startsWith("_")) {
				buffer.append(param + "=" + request.getParameter(param) + ", ");
			}
		}
		String parametres = buffer.toString();
		if (paramExist) {
			parametres = buffer.substring(0, buffer.length() - 2);
		}
		String user = "";
		Utilisateur uti = (Utilisateur) request.getSession().getAttribute("utilisateur");
		if (uti != null) {
			user = uti.getLogin();
		}
		if (hostName.equals(request.getRemoteHost())) {
			hostName = "";
		}
		IntranetLog log = new IntranetLog();
		log.setDate(new GregorianCalendar());
		log.setIp(request.getRemoteHost());
		log.setHost(hostName);
		log.setUser(user);
		log.setRequest(request.getRequestURI());
		log.setParameters(parametres);
		log.setMessage(message);

		dao.addLog(log);
	}

	@Override
	public synchronized List getLog(String request) {
		return dao.getLog(request);
	}

	@Override
	public synchronized List getWarnings() {
		return dao.getWarnings();
	}

	@Override
	public StatistiquesAnnuaire getStatistiquesRues() {
		return dao.getStatistiquesRues();
	}

	@Override
	public synchronized StatistiquesAnnuaire getStatistiquesAnnuaire() {
		return dao.getStatistiquesAnnuaire();
	}

	@Override
	public synchronized List getLogFormationUtilisateur() {
		return dao.getLogFormationUtilisateur();
	}

	@Override
	public synchronized StatistiquesAnnuaire getStatistiquesIntranet() {
		return dao.getStatistiquesIntranet();
	}

	@Override
	public synchronized List getLogFormationAdministration() {
		return dao.getLogFormationAdministration();
	}

	public synchronized StatistiquesFormations getStatistiquesFormations() {
		return dao.getStatistiquesFormations();
	}

	@Override
	public synchronized Statistiques getStatistiques() {
		return dao.getStatistiques();
	}

	@Override
	public List getListPole() {
		// TODO Auto-generated method stub
		return this.dao.getListPole();
	}

	@Override
	public Pole getPoleById(long id_pole) {
		// TODO Auto-generated method stub
		DaoPole daoPole = this.dao.getPoleById(id_pole);
		Pole pole = this.daoPoleToPole(daoPole);

		return pole;
	}

	@Override
	public Pole getPoleByName(String nom) {
		// TODO Auto-generated method stub
		DaoPole daoPole = this.dao.getPoleByName(nom);
		return this.daoPoleToPole(daoPole);
	}

	@Override
	public List getListDepartement() {
		// TODO Auto-generated method stub
		return this.dao.getListDepartement();
	}

	@Override
	public Departement getDepartementById(int id_departement) {
		// TODO Auto-generated method stub
		DaoDepartement daoDepartement = this.dao.getDepartementById(id_departement);
		return this.daoDepartementToDepartement(daoDepartement);
	}

	@Override
	public Departement getDepartementByName(String nom) {
		// TODO Auto-generated method stub
		this.dao.getDepartementByName(nom);
		return null;
	}

	@Override
	public List getListDepartementByPole(int id_pole) {
		// TODO Auto-generated method stub
		return this.dao.getListDepartementByPole(id_pole);
	}

	@Override
	public List getListDepartementByPole(String nom) {
		// TODO Auto-generated method stub
		return this.dao.getListDepartementByPole(nom);
	}

	@Override
	public List getListService() {
		// TODO Auto-generated method stub
		return this.dao.getListService();
	}

	@Override
	public List getListServiceByDepartement(int id_departement) {
		// TODO Auto-generated method stub
		return this.dao.getListServiceByDepartement(id_departement);
	}

	@Override
	public List getListServiceByDepartement(String nom) {
		// TODO Auto-generated method stub
		return this.dao.getListServiceByDepartement(nom);
	}

	@Override
	public Pole getPoleOfService(long id_service) {
		// TODO Auto-generated method stub
		Pole pole = null;

		DaoPole daoPole = this.dao.getPoleOfService(id_service);
		if (daoPole != null) {
			pole = this.daoPoleToPole(daoPole);
		}
		return pole;
	}

	@Override
	public Departement getDepartementOfService(long id_service) {
		// TODO Auto-generated method stub

		Departement departement = null;

		DaoDepartement daoDepartement = this.dao.getDepartementOfService(id_service);
		if (daoDepartement != null) {
			departement = this.daoDepartementToDepartement(daoDepartement);
		}

		return departement;
	}

}
