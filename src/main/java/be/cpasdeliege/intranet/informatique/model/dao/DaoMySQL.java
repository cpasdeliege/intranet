package be.cpasdeliege.intranet.informatique.model.dao;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.text.StyledEditorKit.ItalicAction;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import pJSQL.JSQL;
import pJSQL.JSQLException;
import be.cpasdeliege.intranet.DemServInfo.model.TypeDemandeDsi;
import be.cpasdeliege.intranet.informatique.controler.formulaire.FormulaireImprimanteOrdinateurAssigner;
import be.cpasdeliege.intranet.informatique.model.Departement;
import be.cpasdeliege.intranet.informatique.model.EmailGenerique;
import be.cpasdeliege.intranet.informatique.model.Imprimante;
import be.cpasdeliege.intranet.informatique.model.IntranetLog;
import be.cpasdeliege.intranet.informatique.model.Log;
import be.cpasdeliege.intranet.informatique.model.Personnel;
import be.cpasdeliege.intranet.informatique.model.Pole;
import be.cpasdeliege.intranet.informatique.model.PrivilegeInformatique;
import be.cpasdeliege.intranet.informatique.model.Stat;
import be.cpasdeliege.intranet.informatique.model.Statistiques;
import be.cpasdeliege.intranet.informatique.model.StatistiquesAnnuaire;
import be.cpasdeliege.intranet.informatique.model.StatistiquesFormations;
import be.cpasdeliege.intranet.informatique.model.Tache;
import be.cpasdeliege.intranet.informatique.model.TacheListe;
import be.cpasdeliege.intranet.informatique.model.TicketItem;
import be.cpasdeliege.intranet.informatique.model.TypeEcran;
import be.cpasdeliege.intranet.informatique.model.TypeFonction;
import be.cpasdeliege.intranet.informatique.model.TypeGraveur;
import be.cpasdeliege.intranet.informatique.model.TypeImprimante;
import be.cpasdeliege.intranet.informatique.model.TypeOS;
import be.cpasdeliege.intranet.informatique.model.TypeOrdinateur;
import be.cpasdeliege.intranet.informatique.model.Utilisateur;
import be.cpasdeliege.intranet.informatique.model.WifiVisiteurs;

import be.cpasdeliege.intranet.utils.Utils;

public class DaoMySQL implements DaoInterface {

	public String driver = null;
	public String aliasDB = null;
	public String user = null;
	public String password = null;

	private JSQL bd = null;

	public DaoMySQL() {

	}

	public void connect() {
		try {
			bd = new JSQL(driver);
			bd.connect("mysql", aliasDB, user, password);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public String getAliasDB() {
		return aliasDB;
	}

	public void setAliasDB(String aliasDB) {
		this.aliasDB = aliasDB;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public void addEmailGenerique(EmailGenerique email) {
		try {
			String requete = "insert into emailgenerique (" + "email, " + "mdp, " + "loginPublilink, "
					+ "mdpPublilink, " + "alias) " + "values(?, ?, ?, ?, ?)";

			Object[] parametres = new Object[] { email.getEmail(), email.getMdp(), email.getLoginPublilink(),
					email.getMdpPublilink(), email.getAlias() };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public List getListeEmailGenerique() {
		try {
			String requete = "select * from emailgenerique";
			return (List) bd.executeQueryDB(requete, new BeanListHandler(EmailGenerique.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public void supprimerEmailGenerique(String email) {
		try {
			String requete = "delete from emailgenerique where email like ?";
			Object[] parametres = new Object[] { email };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public void ajouterFonction(String fonction) {
		try {
			String requete = "insert into type_fonction (" + "fonction) " + "values(?)";

			Object[] parametres = new Object[] { fonction };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public void supprimerFonction(String fonction) {
		try {
			String requete = "delete from type_fonction where fonction like ?";
			Object[] parametres = new Object[] { fonction };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public void addPersonnel(DaoPersonnel employe) {
		try {
			String requete = "insert into personnel (" + "nom, " + "prenom," + "loginWindows," + "loginAS400,"
					+ "loginCPAS2000," + "loginNCC," + "loginGRH," + "loginPublilink," + "mdpPublilink," + "wifi,"
					+ "pathPhoto," + "codeAS," + "email," + "mdpEmail," + "emailAlias) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			Object[] parametres = new Object[] { employe.getNom().trim(), employe.getPrenom().trim(),
					employe.getLoginWindows(), employe.getLoginAS400(), employe.getLoginCPAS2000(),
					employe.getLoginNCC(), employe.getLoginGRH(), employe.getLoginPublilink(),
					employe.getMdpPublilink(), employe.getWifi(), employe.getPathPhoto(), employe.getCodeAS(),
					employe.getEmail(), employe.getMdpEmail(), employe.getEmailAlias() };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public void assignerPersonnel(DaoPersonnelService employe) {
		try {
			String requete = "insert into personnel_service (" + "service, " + "nom, " + "prenom, " + "fonction, "
					+ "rem, " + "extension, " + "telephone) " + "values(?, ?, ?, ?, ?, ?, ?)";

			Object[] parametres = new Object[] { employe.getService(), employe.getNom(), employe.getPrenom(),
					employe.getFonction(), employe.getRem(), employe.getExtension(), employe.getTelephone(), };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public void addOrdinateur(DaoOrdinateur ordinateur) {
		try {
			String requete = "insert into ordinateur (" + "nom, " + "actif, " + "systemeExploitation, "
					+ "numeroPrise, " + "carteMere, " + "processeur, " + "memoireRam, " + "disqueDur, "
					+ "carteReseau, " + "carteGraphique, " + "ecran, " + "graveur, " + "numCpas2000, " + "service) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			Object[] parametres = new Object[] { ordinateur.getNom(), ordinateur.getActif(),
					ordinateur.getSystemeExploitation(), ordinateur.getNumeroPrise(), ordinateur.getCarteMere(),
					ordinateur.getProcesseur(), ordinateur.getMemoireRam(), ordinateur.getDisqueDur(),
					ordinateur.getCarteReseau(), ordinateur.getCarteGraphique(), ordinateur.getEcran(),
					ordinateur.getGraveur(), ordinateur.getNumCpas2000(), ordinateur.getService() };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public void modifierOrdinateur(DaoOrdinateur ordinateur) {
		try {
			String requete = "update ordinateur set\n" + "nom = ?,\n" + "actif = ?,\n" + "systemeExploitation = ?,\n"
					+ "numeroPrise = ?,\n" + "carteMere = ?,\n" + "processeur = ?,\n" + "memoireRam = ?,\n "
					+ "disqueDur = ?,\n" + "carteReseau = ?,\n" + "carteGraphique,v" + "ecran,\n" + "graveur,\n"
					+ "numCpas2000,\n" + "service = ?\n" + "where nom = ?";

			Object[] parametres = new Object[] { ordinateur.getActif(), ordinateur.getSystemeExploitation(),
					ordinateur.getNumeroPrise(), ordinateur.getCarteMere(), ordinateur.getProcesseur(),
					ordinateur.getMemoireRam(), ordinateur.getDisqueDur(), ordinateur.getCarteReseau(),
					ordinateur.getCarteGraphique(), ordinateur.getEcran(), ordinateur.getGraveur(),
					ordinateur.getNumCpas2000(), ordinateur.getService(), ordinateur.getNom() };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List getTypeOS() {
		try {
			String requete = "select * from type_os";
			return (List) bd.executeQueryDB(requete, new BeanListHandler(TypeOS.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public void addService(DaoService service) {
		try {
			String requete = "insert into service (" + "nom, " + "fax, " + "localite, " + "numero, " + "codePostal, "
					+ "remarque, " + "emailService, " + "localisation, " + "adresse) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

			Object[] parametres = new Object[] { service.getNom(), service.getFax(), service.getLocalite(),
					service.getNumero(), service.getCodePostal(), service.getRemarque(), service.getEmailService(),
					service.getLocalisation(), service.getAdresse() };
			// Théo
			System.out.println("\n update service in DaoMysl ==> name :" + service.getNom());
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public void addImprimante(Imprimante imprimante) {
		try {
			String requete = "insert into imprimante (" + "numeroSerie, " + "type, " + "ordinateurLocal) "
					+ "values(?, ?, ?)";

			Object[] parametres = new Object[] { imprimante.getNumeroSerie(), imprimante.getType(),
					imprimante.getOrdinateurLocal(), };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List getListePersonnelService(String service, String nom, String prenom) {
		try {
			String requete = "select * from personnel_service where service like ? and nom like ? and prenom like ?";
			Object[] parametres = new Object[] { service, nom, prenom };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DaoPersonnelService.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List getListePersonnelService(int id_service, String nom, String prenom) {
		try {
			String requete = "select * from personnel_service where id_service = ? and nom like ? and prenom like ?";
			Object[] parametres = new Object[] { id_service, nom, prenom };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DaoPersonnelService.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	// public List getListePersonnelService(int[] id_service, String nom, String
	// prenom) {
	// try {
	// String requete = "select * from personnel_service where id_service in (?) and
	// nom like ? and prenom like ?";
	// Object[] parametres = new Object[] {id_service, nom, prenom};
	// return (List)bd.executeQueryDB(requete, parametres, new
	// BeanListHandler(DaoPersonnelService.class));
	// } catch (JSQLException e) {
	// throw new DaoException(e.getMessage());
	// }
	// }

	public List getListePersonnelDepartement(int id_departement, String nom, String prenom) {
		try {
			String requete = "select nom,prenom,nom as nomInfo,prenom as prenomInfo  from (\n"
					+ "select distinct personnel_service.nom, personnel_service.prenom\n"
					+ "from personnel,personnel_service,service,departement\n"
					+ "where personnel.id_personnel = personnel_service.id_personnel\n"
					+ "and personnel_service.id_service = service.id_service\n"
					+ "and service.id_departement = departement.id_departement\n"
					+ "and departement.id_departement = ?\n" + "and personnel_service.nom like ?\n"
					+ "and personnel_service.prenom like ?\n" + ") as T\n" + "order BY nom, prenom asc;\n";

			Object[] parametres = new Object[] { id_departement, nom, prenom };

			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DaoPersonnelService.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}

	}

	public List getListePersonnelService(String service, String nom, String prenom, String orderBy) {
		try {
			String requete = "select * from personnel_service where service like ? and nom like ? and prenom like ? order by "
					+ orderBy;
			Object[] parametres = new Object[] { service, nom, prenom };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DaoPersonnelService.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List<String> getTypeDemandeGTI() {
		List<String> listeType = new ArrayList<String>();
		try {
			String requete = "select * from type_demandegti order by type";
			ResultSet res = bd.executeQuery(requete);
			while (res.next()) {
				listeType.add(res.getString(1));
			}
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
		return listeType;
	}

//	public List<String> getTypeDemandeDSI() {
//		List<String> listeType = new ArrayList<String>();
//		try {
//			String requete = "select * from type_demandedsi order by type";
//			ResultSet res = bd.executeQuery(requete);
//			while(res.next()) {
//				listeType.add(res.getString(1));
//			}
//		} catch (JSQLException e) {
//			throw new DaoException(e.getMessage());
//		} catch (SQLException e) {
//			throw new DaoException(e.getMessage());
//		}
//		return listeType;
//	}

	public List<TypeDemandeDsi> getTypeDemandeDSI() {
		try {
			String requete = "select * from type_demandedsi order by type";
			return (List<TypeDemandeDsi>) bd.executeQueryDB(requete, new BeanListHandler(TypeDemandeDsi.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List<String> getTypeChoix1DemandeDSI() {
		try {
			String requete = "select * from type_demandedsi order by type";
			List<TypeDemandeDsi> listeTypeDSI = (List<TypeDemandeDsi>) bd.executeQueryDB(requete,
					new BeanListHandler(TypeDemandeDsi.class));
			List<String> listeChoix1 = new ArrayList<String>();
			for (TypeDemandeDsi tmp : listeTypeDSI) {
				if (!listeChoix1.contains(tmp.getTypeChoix1())) {
					listeChoix1.add(tmp.getTypeChoix1());
				}
			}
			return listeChoix1;
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List<String> getTypeChoix2DemandeDSI(String type) {
		try {
			String requete = "select * from type_demandedsi where type like '" + type + "%' order by type";
			List<TypeDemandeDsi> listeTypeDSI = (List<TypeDemandeDsi>) bd.executeQueryDB(requete,
					new BeanListHandler(TypeDemandeDsi.class));
			List<String> listeChoix1 = new ArrayList<String>();
			for (TypeDemandeDsi tmp : listeTypeDSI) {
				if (!listeChoix1.contains(tmp.getTypeChoix2())) {
					listeChoix1.add(tmp.getTypeChoix2());
				}
			}
			return listeChoix1;
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List<String> getTypeChoix3DemandeDSI(String type) {
		try {
			String requete = "select * from type_demandedsi where type like '" + type + "%' order by type";
			List<TypeDemandeDsi> listeTypeDSI = (List<TypeDemandeDsi>) bd.executeQueryDB(requete,
					new BeanListHandler(TypeDemandeDsi.class));
			List<String> listeChoix1 = new ArrayList<String>();
			for (TypeDemandeDsi tmp : listeTypeDSI) {
				if (!listeChoix1.contains(tmp.getTypeChoix3())) {
					listeChoix1.add(tmp.getTypeChoix3());
				}
			}
			return listeChoix1;
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public void updateTypeDemandeDSI(String type, String accordDG) {
		try {
			String requete = "update type_demandedsi set " + "accordDG = ? " + "where type = ?";

			Object[] parametres = new Object[] { accordDG, type };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public void updateCodeAS(Personnel pers) {
		try {
			String requete = "update personnel set " + "codeAS = ? " + "where nom = ? " + "AND prenom = ?";

			Object[] parametres = new Object[] { pers.getCodeAS(), pers.getNom(), pers.getPrenom() };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public void updateWifiVisiteurs(WifiVisiteurs wifiVisiteurs) {
		try {
			String requete = "update wifi_visiteurs set " + "ssid = ?, " + "mdp = ? " + "where idWifiVisiteurs = 1";

			Object[] parametres = new Object[] { wifiVisiteurs.getSsid(), wifiVisiteurs.getMdp() };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public WifiVisiteurs getWifiVisiteurs() {
		try {
			String requete = "select * from wifi_visiteurs where idWifiVisiteurs = 1";
			return (WifiVisiteurs) ((List) bd.executeQueryDB(requete, new BeanListHandler(WifiVisiteurs.class))).get(0);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public boolean isAccordDG_DSI(String type) {
		try {
			String requete = "select * from type_demandedsi where type like ?";
			Object[] parametres = new Object[] { type };
			List<TypeDemandeDsi> listeTypeDemande = (List<TypeDemandeDsi>) bd.executeQueryDB(requete, parametres,
					new BeanListHandler(TypeDemandeDsi.class));
			if (listeTypeDemande.size() > 0) {
				return listeTypeDemande.get(0).isAccordDG();
			} else {
				return true;
			}
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List getListePersonnelService(String service, String nom, String prenom, String fonction, String orderBy) {
		try {
			String requete = "select * from personnel_service where service like ? and nom like ? and prenom like ? and fonction like ? order by "
					+ orderBy;
			Object[] parametres = new Object[] { service, nom, prenom, fonction };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DaoPersonnelService.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List getListeTicketItem(int idPlanning) {
		try {
			String requete = "select * from ticket where idPlanning = ?";
			Object[] parametres = new Object[] { idPlanning };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(TicketItem.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public void addTicketItem(TicketItem ticket) {
		try {
			String requete = "insert into ticket (" + "idPlanning, " + "texte, " + "user, " + "date) "
					+ "values(?, ?, ?, ?)";

			GregorianCalendar now = new GregorianCalendar();
			java.sql.Timestamp date = new java.sql.Timestamp(now.getTimeInMillis());
			Object[] parametres = new Object[] { ticket.getIdPlanning(),
					// new String(ticket.getTexte().getBytes(), "UTF-8"),
					ticket.getTexte(), ticket.getUser(), date };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public int addTache(Tache tache) {
		try {
			String requete = "insert into planning (" + "ordinateur, " + "nom, " + "prenom, " + "nomInfo, "
					+ "prenomInfo, " + "service, " + "echeance, " + "dateFin, " + "dsi, " + "type, " + "incident, "
					+ "heureIncident, " + "minuteIncident, " + "titre) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			String[] tmpEmploye = tache.getPersonnel().split(", ");
			if (tmpEmploye.length != 2) {
				tmpEmploye = new String[2];
				tmpEmploye[0] = "";
				tmpEmploye[1] = "";
			}
			String[] tmpEmployeInfo = tache.getPersonnelInfo().split(", ");
			if (tmpEmployeInfo.length != 2) {
				tmpEmployeInfo = new String[2];
				tmpEmployeInfo[0] = "";
				tmpEmployeInfo[1] = "";
			}
			String[] tmp = tache.getEcheance().split("-");
			if (tmp.length != 3) {
				tmp = new String[3];
				tmp[0] = "9999";
				tmp[1] = "01";
				tmp[2] = "01";
			}
			GregorianCalendar dateGreg = new GregorianCalendar(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]) - 1,
					Integer.parseInt(tmp[2]));
			java.sql.Timestamp date = new java.sql.Timestamp(dateGreg.getTimeInMillis());
			Object[] parametres = new Object[] { tache.getOrdinateur(), tmpEmploye[0], tmpEmploye[1], tmpEmployeInfo[0],
					tmpEmployeInfo[1], tache.getService(), date, null, tache.isDsi(), tache.getType(),
					tache.getIncident(), tache.getHeureIncident(), tache.getMinuteIncident(), tache.getTitre() };
			bd.executeUpdate(requete, parametres);

			requete = "select max(idPlanning) from planning";
			ResultSet res = bd.executeQuery(requete);
			res.next();
			return res.getInt(1);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public void updateTache(Tache tache) {
		try {
			String requete = "update planning set " + "ordinateur = ?, " + "nom = ?, " + "prenom = ?, "
					+ "nomInfo = ?, " + "prenomInfo = ?, " + "service = ?, " + "echeance = ?, " + "dateFin = ?, "
					+ "titre = ?, " + "dsi = ?, " + "type = ?, " + "enAttente = ?, " + "id_coordinateur = ?, "
					+ "id_personnel = ? " + "where idPlanning = ?";

			String[] tmpEmploye = tache.getPersonnel().split(", ");
			if (tmpEmploye.length != 2) {
				tmpEmploye = new String[2];
				tmpEmploye[0] = "";
				tmpEmploye[1] = "";
			}
			String[] tmpEmployeInfo = tache.getPersonnelInfo().split(", ");
			if (tmpEmployeInfo.length != 2) {
				tmpEmployeInfo = new String[2];
				tmpEmployeInfo[0] = "";
				tmpEmployeInfo[1] = "";
			}
			String[] tmp = tache.getEcheance().split("-");
			if (tmp.length != 3) {
				tmp = new String[3];
				tmp[0] = "9999";
				tmp[1] = "01";
				tmp[2] = "01";
			}

			String df = tache.getDateFin();
			GregorianCalendar dateGregDateFin;
			;

			if (df == null || df.trim().equals("")) {
				dateGregDateFin = null;
			} else {
				String[] tmpDateFin = tache.getDateFin().split("-");
				if (tmpDateFin.length != 3) {
					tmpDateFin = new String[3];
					tmpDateFin[0] = "3000";
					tmpDateFin[1] = "01";
					tmpDateFin[2] = "01";
				}
				dateGregDateFin = new GregorianCalendar(Integer.parseInt(tmpDateFin[0]),
						Integer.parseInt(tmpDateFin[1]) - 1, Integer.parseInt(tmpDateFin[2]));
			}

			GregorianCalendar dateGregEcheance = new GregorianCalendar(Integer.parseInt(tmp[0]),
					Integer.parseInt(tmp[1]) - 1, Integer.parseInt(tmp[2]));
			java.sql.Timestamp dateEcheance = new java.sql.Timestamp(dateGregEcheance.getTimeInMillis());
			java.sql.Timestamp dateDateFin;
			if (dateGregDateFin == null) {
				dateDateFin = null;
			} else {
				dateDateFin = new java.sql.Timestamp(dateGregDateFin.getTimeInMillis());
			}

			Object[] parametres = new Object[] { tache.getOrdinateur(), tmpEmploye[0], tmpEmploye[1], tmpEmployeInfo[0],
					tmpEmployeInfo[1], tache.getService(), dateEcheance, dateDateFin, tache.getTitre(), tache.isDsi(),
					tache.getType(), tache.isEnAttente(), tache.getId_coordinateur(), tache.getId_personnel(),
					tache.getIdPlanning()

			};
			bd.executeUpdate(requete, parametres);

		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List getListeTache() {
		try {
			String requete = "select * from planning";
			return (List) bd.executeQueryDB(requete, new BeanListHandler(TacheListe.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public Tache getTache(String idPlanning) {

		try {
			String requete = "select * from planning where idPlanning = ?";
			Object[] parametres = new Object[] { idPlanning };
			List tmp = (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(TacheListe.class));
			if (tmp.size() == 1) {
				TacheListe tListe = (TacheListe) tmp.get(0);
				Tache tache = new Tache();
				tache.setDateFin(tListe.getDateFin());
				tache.setEcheance(tListe.getEcheance());
				tache.setIdPlanning(tListe.getIdPlanning());
				tache.setOrdinateur(tListe.getOrdinateur());
				tache.setPersonnel(tListe.getNom() + ", " + tListe.getPrenom());
				tache.setPersonnelInfo(tListe.getNomInfo() + ", " + tListe.getPrenomInfo());
				tache.setService(tListe.getService());
				tache.setTitre(tListe.getTitre());
				tache.setEnAttente(tListe.isEnAttente());
				tache.setType(tListe.getType());
				tache.setDsi(tListe.isDsi());
				tache.setIncident(tListe.getIncident());
				tache.setHeureIncident(tListe.getHeureIncident());
				tache.setMinuteIncident(tListe.getMinuteIncident());
				return tache;
			} else {
				return null;
			}
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public List getListeTacheEnAttente(String service, String nom, String prenom, String ordinateur, String order) {
		try {
			String requete = "select * from planning where service like ? and nom like ? and prenom like ? and ordinateur like ? and dateFin is null and enAttente = 1 order by "
					+ order;
			Object[] parametres = new Object[] { service, nom, prenom, ordinateur };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(TacheListe.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List getListeTacheEnCours(String service, String nom, String prenom, String ordinateur, String order) {
		try {
			String requete = "select * from planning where service like ? and nom like ? and prenom like ? and ordinateur like ? and dateFin is null and enAttente = 0 order by "
					+ order;
			Object[] parametres = new Object[] { service, nom, prenom, ordinateur };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(TacheListe.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List getListeIncidentsOuverts(String order) {
		try {
			String requete = "select * from planning where incident = 1 and dateFin is null order by " + order;

			return (List) bd.executeQueryDB(requete, new BeanListHandler(TacheListe.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List getListeIncidentsFermes(String order) {
		try {
			String requete = "select * from planning where incident = 1 and dateFin is not null order by " + order;

			return (List) bd.executeQueryDB(requete, new BeanListHandler(TacheListe.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List getListeMesTaches(String service, String nom, String prenom, String ordinateur, String order,
			String myName) {
		try {
			String requete = "select * from planning where nomInfo like ? and dateFin is null and enAttente = 0 order by "
					+ order;
			Object[] parametres = new Object[] { myName };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(TacheListe.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public List getListeTacheFinie(String service, String nom, String prenom, String ordinateur, String order) {
		try {
			String requete;
			if (order.equals("dateFin")) {
				requete = "select * from planning where service like ? and nom like ? and prenom like ? and ordinateur like ? and dateFin is not null order by "
						+ order + " desc";
			} else {
				requete = "select * from planning where service like ? and nom like ? and prenom like ? and ordinateur like ? and dateFin is not null order by "
						+ order;
			}
			Object[] parametres = new Object[] { service, nom, prenom, ordinateur };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(TacheListe.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List getTypeFonction() {
		try {
			String requete = "select * from type_fonction";
			return (List) bd.executeQueryDB(requete, new BeanListHandler(TypeFonction.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List getTypeEcran() {
		try {
			String requete = "select * from type_ecran";
			return (List) bd.executeQueryDB(requete, new BeanListHandler(TypeEcran.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List getTypeImprimante() {
		try {
			String requete = "select type from type_imprimante order by num";
			return (List) bd.executeQueryDB(requete, new BeanListHandler(TypeImprimante.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List getStat() {
		try {
			String requete = "SELECT service, type, count(*) as nbre FROM cpas.planning group by service, type";
			return (List) bd.executeQueryDB(requete, new BeanListHandler(Stat.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List getTypeGraveur() {
		try {
			String requete = "select * from type_graveur";
			return (List) bd.executeQueryDB(requete, new BeanListHandler(TypeGraveur.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public DaoPersonnel getPersonnel(String login) {
		Utilisateur util = (Utilisateur) getUtilisateur(login, "%", "%").get(0);

		return (DaoPersonnel) getListePersonnels(util.getNom(), util.getPrenom()).get(0);
	}

	public List getListePersonnels(String nom, String prenom) {
		System.out.println("Freeeeeeeeeeeeeeeed :5555                                                            ");
		System.out.println(" daoMysql.getListPersonnels*******************");
		System.out.println("nom ==> " + nom + " , Prenom => " + prenom);

		try {
			String requete = "select * from personnel where nom like ? and prenom like ? order by nom ASC";
			// Object[] parametres = new Object[] {new String(nom.getBytes(),"UTF-8"), new
			// String(prenom.getBytes(),"UTF-8")};
			Object[] parametres = new Object[] { nom, prenom };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DaoPersonnel.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List getListeServices(String param) {
		// List lst = new ArrayList<DaoService>();
		try {
			String requete = "select * from service where nom like ? order by nom ASC";
			Object[] parametres = new Object[] { param };
			/************************************/

			List lst = (List<DaoService>) bd.executeQueryDB(requete, parametres, new BeanListHandler(DaoService.class));
			for (int i = 0; i < lst.size(); i++) {
				DaoService ds = (DaoService) lst.get(i);
				System.out.println("\n id service ==>" + ds.getId_service() + "Nom ==>" + ds.getNom());
			}

			/*************************************/
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DaoService.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List getListeOrdinateurs(String ordinateur, String service) {
		try {
			String requete = "select * from ordinateur where nom like ? and service like ? order by nom ASC ";
			Object[] parametres = new Object[] { ordinateur, service };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DaoOrdinateur.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List getListeOrdinateursActifs(String ordinateur, String service) {
		try {
			String requete = "select * from ordinateur where nom like ? and service like ? and service not like '' order by nom ASC";
			Object[] parametres = new Object[] { ordinateur, service };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DaoOrdinateur.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List getListeOrdinateursNonActifs(String ordinateur, String service) {
		try {
			String requete = "select * from ordinateur where nom like ? and service like ? and service like '' order by nom ASC";
			Object[] parametres = new Object[] { ordinateur, service };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DaoOrdinateur.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List getListeOrdinateurs() {
		try {
			String requete = "select * from ordinateur order by nom ASC";
			Object[] parametres = new Object[] {};
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DaoOrdinateur.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List getListePersonnelServiceRequete(String param) {
		try {
			String requete = "select * from personnel_service where service like ? or fonction like ? or nom like ? or prenom like ? order by nom";
			Object[] parametres = new Object[] { param, param, param, param };
			List tmpList = (List) bd.executeQueryDB(requete, parametres,
					new BeanListHandler(DaoPersonnelService.class));

			requete = "select * from personnel where codeAS like ? order by nom";
			parametres = new Object[] { param };
			List<Personnel> tmpListPersCodeAS = (List) bd.executeQueryDB(requete, parametres,
					new BeanListHandler(Personnel.class));

			for (Personnel pers : tmpListPersCodeAS) {
				requete = "select * from personnel_service where nom like ? and prenom like ? order by nom";
				parametres = new Object[] { pers.getNom(), pers.getPrenom() };
				List<Personnel> tmpListPersServiceCodeAS = (List) bd.executeQueryDB(requete, parametres,
						new BeanListHandler(DaoPersonnelService.class));
				tmpList.addAll(tmpListPersServiceCodeAS);
			}

			return tmpList;
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public void supprimerPersonnel(String nom, String prenom) {
		try {
			String requete = "delete from personnel where nom like ? and prenom like ?";
			Object[] parametres = new Object[] { nom, prenom };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public void supprimerService(String service) {
		try {
			String requete = "delete from service where nom like ?";
			Object[] parametres = new Object[] { service };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public void supprimerOrdinateur(String ordinateur) {
		try {
			String requete = "delete from ordinateur where nom like ?";
			Object[] parametres = new Object[] { ordinateur };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public void supprimerImprimante(String numeroSerie) {
		try {
			String requete = "delete from imprimante where numeroSerie like ?";
			Object[] parametres = new Object[] { numeroSerie };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public void desassignerPersonnel(String service, String nom, String prenom) {
		try {
			String requete = "delete from personnel_service where service like ? and nom like ? and prenom like ?";
			Object[] parametres = new Object[] { service, nom, prenom };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List getTypeOrdinateur() {
		try {
			String requete = "select * from type_ordinateur";
			return (List) bd.executeQueryDB(requete, new BeanListHandler(TypeOrdinateur.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public void assignerOrdinateur(DaoOrdinateurPersonnel employeOrdinateur) {
		try {
			String requete = "insert into personnel_ordinateur (" + "nom, " + "prenom, " + "ordinateur) "
					+ "values(?, ?, ?)";

			Object[] parametres = new Object[] { employeOrdinateur.getNom(), employeOrdinateur.getPrenom(),
					employeOrdinateur.getOrdinateur(), };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public void desassignerOrdinateur(String ordinateur, String nom, String prenom) {
		try {
			String requete = "delete from personnel_ordinateur where ordinateur like ? and nom like ? and prenom like ?";
			Object[] parametres = new Object[] { ordinateur, nom, prenom };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List getListeOrdinateurPersonnel(String ordinateur, String nom, String prenom) {
		try {
			String requete = "select * from personnel_ordinateur where ordinateur like ? and nom like ? and prenom like ?";
			Object[] parametres = new Object[] { ordinateur, nom, prenom };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DaoOrdinateurPersonnel.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List getListeImprimante(String numeroSerie, String ordinateur, String orderBy) {
		try {
			String requete = "select * from imprimante where ordinateurLocal like ? and numeroSerie like ? order by ?";
			Object[] parametres = new Object[] { ordinateur, numeroSerie, orderBy };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(Imprimante.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List getListeImprimante(String numeroSerie, String ordinateur) {
		try {
			String requete = "select * from imprimante where ordinateurLocal like ? and numeroSerie like ?";
			Object[] parametres = new Object[] { ordinateur, numeroSerie };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(Imprimante.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List getListeImprimanteService(String service) {
		try {
			String requete = "select * from imprimante, ordinateur where imprimante.ordinateurLocal like ordinateur.nom and ordinateur.service like ?";
			Object[] parametres = new Object[] { service };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(Imprimante.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public void addUtilisateur(Utilisateur utilisateur) {
		try {
			String requete = "insert into utilisateur (" + "login, " + "nom, " + "prenom, " + "mdp, " + "actif) "
					+ "values(?, ?, ?, ?, ?)";

			Object[] parametres = new Object[] { utilisateur.getLogin(), utilisateur.getNom(), utilisateur.getPrenom(),
					utilisateur.getMdp(), utilisateur.isActif() };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List<DaoPersonnel> getPersonnelDsiUser() {
		try {
			String requete = "SELECT * FROM cpas.personnel, cpas.utilisateur, cpas.privilege_informatique "
					+ "where personnel.nom like utilisateur.nom " + "and personnel.prenom like utilisateur.prenom "
					+ "and utilisateur.login like privilege_informatique.login "
					+ "and privilege_informatique.dsiUser = 1;";
			return (List) bd.executeQueryDB(requete, new BeanListHandler(DaoPersonnel.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List<DaoPersonnel> getPersonnelDsichef() {
		try {
			String requete = "SELECT * FROM cpas.personnel, cpas.utilisateur, cpas.privilege_informatique "
					+ "where personnel.nom like utilisateur.nom " + "and personnel.prenom like utilisateur.prenom "
					+ "and utilisateur.login like privilege_informatique.login "
					+ "and privilege_informatique.dsiChef = 1;";
			return (List) bd.executeQueryDB(requete, new BeanListHandler(DaoPersonnel.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List<DaoPersonnel> getPersonnelDsiDirection() {
		try {
			String requete = "SELECT * FROM cpas.personnel, cpas.utilisateur, cpas.privilege_informatique "
					+ "where personnel.nom like utilisateur.nom " + "and personnel.prenom like utilisateur.prenom "
					+ "and utilisateur.login like privilege_informatique.login "
					+ "and privilege_informatique.dsiDirection = 1;";
			return (List) bd.executeQueryDB(requete, new BeanListHandler(DaoPersonnel.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List<DaoPersonnel> getPersonnelDsiInfo() {
		try {
			String requete = "SELECT * FROM cpas.personnel, cpas.utilisateur, cpas.privilege_informatique "
					+ "where personnel.nom like utilisateur.nom " + "and personnel.prenom like utilisateur.prenom "
					+ "and utilisateur.login like privilege_informatique.login "
					+ "and privilege_informatique.dsiInfo = 1;";
			return (List) bd.executeQueryDB(requete, new BeanListHandler(DaoPersonnel.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List getUtilisateur(String login, String nom, String prenom) {
		try {
			String requete = "select * from utilisateur where login like ? and nom like ? and prenom like ?";
			Object[] parametres = new Object[] { login, nom, prenom };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(Utilisateur.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public void supprimerUtilisateur(String nom, String prenom) {
		try {
			String requete = "delete from utilisateur where nom like ? and prenom like ?";
			Object[] parametres = new Object[] { nom, prenom };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public void supprimerUtilisateur(String login) {
		try {
			String requete = "delete from utilisateur where login like ?";
			Object[] parametres = new Object[] { login };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public void addPrivilègeInformatique(PrivilegeInformatique privilege) {
		try {
            String requete = "insert into privilege_informatique (" +
            "login, " +
            "administrateur, " +
            "telephonie, " +
            "formations," +
            "cadastreMP," +
            "dsiUser," +
            "dsiChef," +
            "dsiInfo," +
            "dsiDirection," +
            "admVM," +
            "wifiUser," +
            "wifiAdmin," + 
            "gpi) " +
            "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            Object[] parametres = new Object[] {
            		privilege.getLogin(),
            		privilege.isAdministrateur(),
            		privilege.isTelephonie(),
            		privilege.isFormations(),
            		privilege.isCadastreMP(),
            		privilege.isDsiUser(),
            		privilege.isDsiChef(),
            		privilege.isDsiInfo(),
            		privilege.isDsiDirection(),
            		privilege.isAdmVM(),
            		privilege.isWifiUser(),
            		privilege.isWifiAdmin(),
            		privilege.isGpi()
            };
            bd.executeUpdate(requete, parametres);
        } catch (JSQLException e) {
            throw new DaoException(e.getMessage());
        }
	}

	public PrivilegeInformatique getPrivilegeInformatique(String login) {
		try {
            String requete = "select * from privilege_informatique where login = ?";
            Object[] parametres = new Object[] {login};
            List liste = (List)bd.executeQueryDB(requete, parametres, new BeanListHandler(PrivilegeInformatique.class));
            if(liste.size() != 0) {
            	return (PrivilegeInformatique)liste.get(0);
            } else {
            	return null;
            }
            
        } catch (JSQLException e) {
            throw new DaoException(e.getMessage());
        } 
	}

	public void supprimerPrivilègeInformatique(String login) {
		try {
            String requete = "delete from privilege_informatique where login like ?";
            Object[] parametres = new Object[] {login};
            bd.executeUpdate(requete, parametres);
        } catch (JSQLException e) {
            throw new DaoException(e.getMessage());
        }
	}

	public void assignerImprimante(FormulaireImprimanteOrdinateurAssigner imprimanteReseau) {
		try {
			String requete = "insert into imprimante_ordinateur (" + "numeroSerie, " + "ordinateur) " + "values(?, ?)";

			Object[] parametres = new Object[] { imprimanteReseau.getNumeroSerie(), imprimanteReseau.getOrdinateur(), };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public void renomerOrdinateur(String ordinateur, String nouvNom) {
		try {
			String requete = "update ordinateur set " + "nom = ? " + "where nom like ?";

			Object[] parametres = new Object[] { nouvNom, ordinateur };
			bd.executeUpdate(requete, parametres);

			requete = "update planning set " + "ordinateur = ? " + "where ordinateur like ?";
			bd.executeUpdate(requete, parametres);

			requete = "update personnel_ordinateur set " + "ordinateur = ? " + "where ordinateur like ?";
			bd.executeUpdate(requete, parametres);

		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public void desassignerImprimante(String ordinateur, String imprimante) {
		try {
			String requete = "delete from imprimante_ordinateur where ordinateur like ? and numeroSerie like ?";
			Object[] parametres = new Object[] { ordinateur, imprimante };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List getListeImprimanteOrdinateur(String ordinateur, String imprimante) {
		try {
			String requete = "select * from imprimante_ordinateur where ordinateur like ? and numeroSerie like ?";
			Object[] parametres = new Object[] { ordinateur, imprimante };
			return (List) bd.executeQueryDB(requete, parametres,
					new BeanListHandler(FormulaireImprimanteOrdinateurAssigner.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List getFonctionService(String service) {
		try {
			String requete = "select distinct(fonction) from personnel_service where service like ? order by fonction";
			Object[] parametres = new Object[] { service };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(TypeFonction.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public Statistiques getStatistiques() {
		Statistiques stat = new Statistiques();

		try {
			String requete = "select count(*) from personnel";
			ResultSet res = bd.executeQuery(requete);
			res.next();
			stat.setPersonnels(res.getInt(1));

			requete = "select count(*) from type_fonction";
			res = bd.executeQuery(requete);
			res.next();
			stat.setFonctions(res.getInt(1));

			requete = "select count(*) from ordinateur";
			res = bd.executeQuery(requete);
			res.next();
			stat.setOrdinateurs(res.getInt(1));

			requete = "select count(*) from imprimante";
			res = bd.executeQuery(requete);
			res.next();
			stat.setImprimantes(res.getInt(1));

			requete = "select count(*) from service";
			res = bd.executeQuery(requete);
			res.next();
			stat.setServices(res.getInt(1));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
		return stat;
	}

	@Override
	public void addLog(IntranetLog log) {
		try {
			String requete = "insert into log (" + "date, " + "ip, " + "host, " + "user, " + "request, "
					+ "parameters, " + "message) " + "values(?, ?, ?, ?, ?, ?, ?)";

			java.sql.Timestamp date = new java.sql.Timestamp(log.getDate().getTimeInMillis());
			Object[] parametres = new Object[] { date, log.getIp(), log.getHost(), log.getUser(), log.getRequest(),
					log.getParameters(), log.getMessage(), };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public List getLog(String request) {
		try {
			GregorianCalendar now = new GregorianCalendar();
			GregorianCalendar dateDebut = new GregorianCalendar(now.get(GregorianCalendar.YEAR),
					now.get(GregorianCalendar.MONTH), now.get(GregorianCalendar.DAY_OF_MONTH), 0, 0, 0);
			dateDebut.add(GregorianCalendar.DAY_OF_MONTH, -7);
			Date debut = new Date(dateDebut.getTimeInMillis());
			String requete = "select * from log where date > ? and request like ? and request not like '%rapports%' order by id desc";
			Object[] parametres = new Object[] { debut, request };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(Log.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public List getWarnings() {
		try {
			GregorianCalendar now = new GregorianCalendar();
			GregorianCalendar dateDebut = new GregorianCalendar(now.get(GregorianCalendar.YEAR),
					now.get(GregorianCalendar.MONTH), now.get(GregorianCalendar.DAY_OF_MONTH), 0, 0, 0);
			dateDebut.add(GregorianCalendar.DAY_OF_MONTH, -30);
			Date debut = new Date(dateDebut.getTimeInMillis());
			String requete = "select * from log where date > ? and message not like '' order by id desc";
			Object[] parametres = new Object[] { debut };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(Log.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List getLogFormationUtilisateur() {
		try {
			GregorianCalendar now = new GregorianCalendar();
			GregorianCalendar dateDebut = new GregorianCalendar(now.get(GregorianCalendar.YEAR),
					now.get(GregorianCalendar.MONTH), now.get(GregorianCalendar.DAY_OF_MONTH), 0, 0, 0);
			dateDebut.add(GregorianCalendar.DAY_OF_MONTH, -7);
			Date debut = new Date(dateDebut.getTimeInMillis());
			String requete = "select * from log where date > ? and request like ? and request not like '%rapports%'  and request not like ? and request not like ? and request not like ? and request not like ? and request not like ? and request not like ? and request not like ? order by id desc";
			Object[] parametres = new Object[] { debut, "%.formations", "%indexAdmin.formations%",
					"%gestionFormation.formations%", "%formulaireModifier.formations%",
					"%formulaireAjouter.formations%", "%supprimerFormation.formations%",
					"%ajouterServiceFormation.formations%", "%supprimerServiceFormation.formations%" };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(Log.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public List getLogFormationAdministration() {
		try {
			GregorianCalendar now = new GregorianCalendar();
			GregorianCalendar dateDebut = new GregorianCalendar(now.get(GregorianCalendar.YEAR),
					now.get(GregorianCalendar.MONTH), now.get(GregorianCalendar.DAY_OF_MONTH), 0, 0, 0);
			dateDebut.add(GregorianCalendar.DAY_OF_MONTH, -7);
			Date debut = new Date(dateDebut.getTimeInMillis());
			String requete = "select * from log where date > ? and request like ? and request not like '%rapports%'  and request not like ? and request not like ? and request not like ? and request not like ? order by id desc";
			Object[] parametres = new Object[] { debut, "%.formations", "%index.formations%",
					"%typeFormations.formations%", "%formation.formations%", "%services.formations%" };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(Log.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public StatistiquesFormations getStatistiquesFormations() {
		StatistiquesFormations stats = new StatistiquesFormations();
		try {
			String requete = "select count(*) from log where request like ? and request not like ? and request not like ? and request not like ? and request not like ?";
			Object[] parametres = new Object[] { "%.formations", "%indexAdmin.formations%",
					"%gestionFormation.formations%", "%formulaireModifier.formations%",
					"%formulaireAjouter.formations%" };
			ResultSet res = bd.executeQuery(requete, parametres);
			res.next();
			stats.setPagesTotal(res.getInt(1));

			requete = "select count(distinct host) from log where request like ? and request not like ? and request not like ? and request not like ? and request not like ?";
			parametres = new Object[] { "%.formations", "%indexAdmin.formations%", "%gestionFormation.formations%",
					"%formulaireModifier.formations%", "%formulaireAjouter.formations%" };
			res = bd.executeQuery(requete, parametres);
			res.next();
			stats.setOrdinateursTotal(res.getInt(1));

			GregorianCalendar now = new GregorianCalendar();
			GregorianCalendar dateDebut = new GregorianCalendar(now.get(GregorianCalendar.YEAR),
					now.get(GregorianCalendar.MONTH), now.get(GregorianCalendar.DAY_OF_MONTH), 0, 0, 0);
			GregorianCalendar dateFin = new GregorianCalendar(now.get(GregorianCalendar.YEAR),
					now.get(GregorianCalendar.MONTH), now.get(GregorianCalendar.DAY_OF_MONTH), 0, 0, 0);
			dateFin.add(GregorianCalendar.DAY_OF_MONTH, 1);
			Date debut = new Date(dateDebut.getTimeInMillis());
			Date fin = new Date(dateFin.getTimeInMillis());
			requete = "select count(*) from log where request like ? and date > ? and date < ? and request not like ? and request not like ? and request not like ? and request not like ?";
			parametres = new Object[] { "%.formations", debut, fin, "%indexAdmin.formations%",
					"%gestionFormation.formations%", "%formulaireModifier.formations%",
					"%formulaireAjouter.formations%" };
			res = bd.executeQuery(requete, parametres);
			res.next();
			stats.setPagesAujourdhui(res.getInt(1));
			requete = "select count(distinct host) from log where request like ? and date > ? and date < ? and request not like ? and request not like ? and request not like ? and request not like ?";
			parametres = new Object[] { "%.formations", debut, fin, "%indexAdmin.formations%",
					"%gestionFormation.formations%", "%formulaireModifier.formations%",
					"%formulaireAjouter.formations%" };
			res = bd.executeQuery(requete, parametres);
			res.next();
			stats.setOrdinateursAujourdhui(res.getInt(1));

			dateDebut.add(GregorianCalendar.DAY_OF_MONTH, -1);
			dateFin.add(GregorianCalendar.DAY_OF_MONTH, -1);
			debut = new Date(dateDebut.getTimeInMillis());
			fin = new Date(dateFin.getTimeInMillis());
			requete = "select count(*) from log where request like ? and date > ? and date < ? and request not like ? and request not like ? and request not like ? and request not like ?";
			parametres = new Object[] { "%.formations", debut, fin, "%indexAdmin.formations%",
					"%gestionFormation.formations%", "%formulaireModifier.formations%",
					"%formulaireAjouter.formations%" };
			res = bd.executeQuery(requete, parametres);
			res.next();
			stats.setPagesHier(res.getInt(1));
			requete = "select count(distinct host) from log where request like ? and date > ? and date < ? and request not like ? and request not like ? and request not like ? and request not like ?";
			parametres = new Object[] { "%.formations", debut, fin, "%indexAdmin.formations%",
					"%gestionFormation.formations%", "%formulaireModifier.formations%",
					"%formulaireAjouter.formations%" };
			res = bd.executeQuery(requete, parametres);
			res.next();
			stats.setOrdinateursHier(res.getInt(1));

		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
		return stats;
	}

	@Override
	public StatistiquesAnnuaire getStatistiquesAnnuaire() {
		StatistiquesAnnuaire stats = new StatistiquesAnnuaire();
		try {
			String requete = "select count(*) from log where request like ?";
			Object[] parametres = new Object[] { "%.annuaire" };
			ResultSet res = bd.executeQuery(requete, parametres);
			res.next();
			stats.setPagesTotal(res.getInt(1));

			requete = "select count(distinct host) from log where request like ? ";
			parametres = new Object[] { "%.annuaire" };
			res = bd.executeQuery(requete, parametres);
			res.next();
			stats.setOrdinateursTotal(res.getInt(1));

			GregorianCalendar now = new GregorianCalendar();
			GregorianCalendar dateDebut = new GregorianCalendar(now.get(GregorianCalendar.YEAR),
					now.get(GregorianCalendar.MONTH), now.get(GregorianCalendar.DAY_OF_MONTH), 0, 0, 0);
			GregorianCalendar dateFin = new GregorianCalendar(now.get(GregorianCalendar.YEAR),
					now.get(GregorianCalendar.MONTH), now.get(GregorianCalendar.DAY_OF_MONTH), 0, 0, 0);
			dateFin.add(GregorianCalendar.DAY_OF_MONTH, 1);
			Date debut = new Date(dateDebut.getTimeInMillis());
			Date fin = new Date(dateFin.getTimeInMillis());
			requete = "select count(*) from log where request like ? and date > ? and date < ?";
			parametres = new Object[] { "%.annuaire", debut, fin };
			res = bd.executeQuery(requete, parametres);
			res.next();
			stats.setPagesAujourdhui(res.getInt(1));
			requete = "select count(distinct host) from log where request like ? and date > ? and date < ?";
			parametres = new Object[] { "%.annuaire", debut, fin };
			res = bd.executeQuery(requete, parametres);
			res.next();
			stats.setOrdinateursAujourdhui(res.getInt(1));

			dateDebut.add(GregorianCalendar.DAY_OF_MONTH, -1);
			dateFin.add(GregorianCalendar.DAY_OF_MONTH, -1);
			debut = new Date(dateDebut.getTimeInMillis());
			fin = new Date(dateFin.getTimeInMillis());
			requete = "select count(*) from log where request like ? and date > ? and date < ?";
			parametres = new Object[] { "%.annuaire", debut, fin };
			res = bd.executeQuery(requete, parametres);
			res.next();
			stats.setPagesHier(res.getInt(1));
			requete = "select count(distinct host) from log where request like ? and date > ? and date < ?";
			parametres = new Object[] { "%.annuaire", debut, fin };
			res = bd.executeQuery(requete, parametres);
			res.next();
			stats.setOrdinateursHier(res.getInt(1));

		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
		return stats;
	}

	@Override
	public StatistiquesAnnuaire getStatistiquesRues() {
		StatistiquesAnnuaire stats = new StatistiquesAnnuaire();
		try {
			String requete = "select count(*) from log where request like ?";
			Object[] parametres = new Object[] { "%.rues" };
			ResultSet res = bd.executeQuery(requete, parametres);
			res.next();
			stats.setPagesTotal(res.getInt(1));

			requete = "select count(distinct host) from log where request like ? ";
			parametres = new Object[] { "%.rues" };
			res = bd.executeQuery(requete, parametres);
			res.next();
			stats.setOrdinateursTotal(res.getInt(1));

			GregorianCalendar now = new GregorianCalendar();
			GregorianCalendar dateDebut = new GregorianCalendar(now.get(GregorianCalendar.YEAR),
					now.get(GregorianCalendar.MONTH), now.get(GregorianCalendar.DAY_OF_MONTH), 0, 0, 0);
			GregorianCalendar dateFin = new GregorianCalendar(now.get(GregorianCalendar.YEAR),
					now.get(GregorianCalendar.MONTH), now.get(GregorianCalendar.DAY_OF_MONTH), 0, 0, 0);
			dateFin.add(GregorianCalendar.DAY_OF_MONTH, 1);
			Date debut = new Date(dateDebut.getTimeInMillis());
			Date fin = new Date(dateFin.getTimeInMillis());
			requete = "select count(*) from log where request like ? and date > ? and date < ?";
			parametres = new Object[] { "%.rues", debut, fin };
			res = bd.executeQuery(requete, parametres);
			res.next();
			stats.setPagesAujourdhui(res.getInt(1));
			requete = "select count(distinct host) from log where request like ? and date > ? and date < ?";
			parametres = new Object[] { "%.rues", debut, fin };
			res = bd.executeQuery(requete, parametres);
			res.next();
			stats.setOrdinateursAujourdhui(res.getInt(1));

			dateDebut.add(GregorianCalendar.DAY_OF_MONTH, -1);
			dateFin.add(GregorianCalendar.DAY_OF_MONTH, -1);
			debut = new Date(dateDebut.getTimeInMillis());
			fin = new Date(dateFin.getTimeInMillis());
			requete = "select count(*) from log where request like ? and date > ? and date < ?";
			parametres = new Object[] { "%.rues", debut, fin };
			res = bd.executeQuery(requete, parametres);
			res.next();
			stats.setPagesHier(res.getInt(1));
			requete = "select count(distinct host) from log where request like ? and date > ? and date < ?";
			parametres = new Object[] { "%.rues", debut, fin };
			res = bd.executeQuery(requete, parametres);
			res.next();
			stats.setOrdinateursHier(res.getInt(1));

		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
		return stats;
	}

	public StatistiquesAnnuaire getStatistiquesIntranet() {
		StatistiquesAnnuaire stats = new StatistiquesAnnuaire();
		try {
			String requete = "select count(*) from log";
			Object[] parametres;
			ResultSet res = bd.executeQuery(requete);
			res.next();
			stats.setPagesTotal(res.getInt(1));

			requete = "select count(distinct host) from log";
			res = bd.executeQuery(requete);
			res.next();
			stats.setOrdinateursTotal(res.getInt(1));

			GregorianCalendar now = new GregorianCalendar();
			GregorianCalendar dateDebut = new GregorianCalendar(now.get(GregorianCalendar.YEAR),
					now.get(GregorianCalendar.MONTH), now.get(GregorianCalendar.DAY_OF_MONTH), 0, 0, 0);
			GregorianCalendar dateFin = new GregorianCalendar(now.get(GregorianCalendar.YEAR),
					now.get(GregorianCalendar.MONTH), now.get(GregorianCalendar.DAY_OF_MONTH), 0, 0, 0);
			dateFin.add(GregorianCalendar.DAY_OF_MONTH, 1);
			Date debut = new Date(dateDebut.getTimeInMillis());
			Date fin = new Date(dateFin.getTimeInMillis());
			requete = "select count(*) from log where date > ? and date < ?";
			parametres = new Object[] { debut, fin };
			res = bd.executeQuery(requete, parametres);
			res.next();
			stats.setPagesAujourdhui(res.getInt(1));
			requete = "select count(distinct host) from log where date > ? and date < ?";
			parametres = new Object[] { debut, fin };
			res = bd.executeQuery(requete, parametres);
			res.next();
			stats.setOrdinateursAujourdhui(res.getInt(1));

			dateDebut.add(GregorianCalendar.DAY_OF_MONTH, -1);
			dateFin.add(GregorianCalendar.DAY_OF_MONTH, -1);
			debut = new Date(dateDebut.getTimeInMillis());
			fin = new Date(dateFin.getTimeInMillis());
			requete = "select count(*) from log where date > ? and date < ?";
			parametres = new Object[] { debut, fin };
			res = bd.executeQuery(requete, parametres);
			res.next();
			stats.setPagesHier(res.getInt(1));
			requete = "select count(distinct host) from log where date > ? and date < ?";
			parametres = new Object[] { debut, fin };
			res = bd.executeQuery(requete, parametres);
			res.next();
			stats.setOrdinateursHier(res.getInt(1));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
		return stats;
	}

	@Override
	public List getListPole() {
		try {
			String requete = "select *\n" + "from pole\n" + "order by nom ASC;\n";
			Object[] parametres = new Object[] {};
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DaoPole.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public DaoPole getPoleById(long id_pole) {
		try {
			String requete = "select *\n" + "from pole\n" + "where id_pole = ?\n" + "order by nom ASC;\n";
			Object[] parametres = new Object[] { id_pole };
			List poles = (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DaoPole.class));
			if (poles.size() == 1) {
				return (DaoPole) poles.get(0);
			}
			return null;

		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public DaoPole getPoleByName(String nom) {
		try {
			String requete = "select *\n" + "from pole\n" + "where nom = ?\n" + "order by nom ASC;\n";
			Object[] parametres = new Object[] { nom };

			List poles = (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DaoPole.class));
			if (poles.size() == 1) {
				return (DaoPole) poles.get(0);
			}

			return null;

		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public List getListDepartement() {
		try {
			String requete = "select *\n" + "from departement\n" + "order by nom ASC;\n";
			Object[] parametres = new Object[] {};
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DaoDepartement.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public DaoDepartement getDepartementById(int id_departement) {
		try {
			String requete = "select *\n" + "from departement\n" + "where id_departement = ?\n" + "order by nom ASC;\n";
			Object[] parametres = new Object[] {};
			List departements = (List) bd.executeQueryDB(requete, parametres,
					new BeanListHandler(DaoDepartement.class));
			if (departements.size() == 1) {
				return (DaoDepartement) departements.get(0);
			}

			return null;
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public List getListDepartementByPole(int id_pole) {
		try {
			String requete = "select *\n" + "from departement\n" + "where id_pole = ?\n" + "order by nom ASC;\n";
			Object[] parametres = new Object[] { id_pole };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DaoDepartement.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public List getListService() {
		try {
			String requete = "select *\n" + "from service\n" + "order by nom ASC;\n";
			Object[] parametres = new Object[] {};
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DaoService.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public List getListServiceByDepartement(int id_departement) {
		try {
			String requete = "select *\n" + "from service\n" + "where id_departement = ?\n" + "order by nom ASC;\n";
			Object[] parametres = new Object[] { id_departement };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DaoService.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public DaoDepartement getDepartementByName(String nom) {
		try {
			String requete = "select *\n" + "from departement\n" + "where nom = ?\n" + "order by nom ASC;\n";
			Object[] parametres = new Object[] { nom };
			List departements = (List) bd.executeQueryDB(requete, parametres,
					new BeanListHandler(DaoDepartement.class));
			if (departements.size() == 1) {
				return (DaoDepartement) departements.get(0);
			}
			return null;
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public List getListDepartementByPole(String nom) {
		try {
			String requete = "select *\n" + "from departement\n" + "where pole = ?\n" + "order by nom ASC;\n";
			Object[] parametres = new Object[] { nom };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DaoDepartement.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public List getListServiceByDepartement(String nom) {
		try {
			String requete = "select *\n" + "from service\n" + "where departement = ?\n" + "order by nom ASC;\n";
			Object[] parametres = new Object[] { nom };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DaoService.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public DaoPole getPoleOfService(long id_service) {
		try {
			String requete = "select pole.*\n" + "from pole, departement, service\n"
					+ "where pole.id_pole = departement.id_pole\n"
					+ "and departement.id_departement = service.id_departement\n" + "and service.id_service = ?;\n";
			Object[] parametres = new Object[] { id_service };

			List poles = (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DaoPole.class));
			if (poles.size() == 1) {
				return (DaoPole) poles.get(0);
			}

		} catch (JSQLException e) {
			return null;
		}
		return null;
	}

	@Override
	public DaoDepartement getDepartementOfService(long id_service) {
		try {
			String requete = "select departement.*\n" + "from pole, departement, service\n"
					+ "where pole.id_pole = departement.id_pole\n"
					+ "and departement.id_departement = service.id_departement\n" + "and service.id_service = ?;\n";
			Object[] parametres = new Object[] { id_service };

			List departements = (List) bd.executeQueryDB(requete, parametres,
					new BeanListHandler(DaoDepartement.class));
			if (departements.size() == 1) {
				return (DaoDepartement) departements.get(0);
			}

		} catch (JSQLException e) {
			return null;
		}
		return null;
	}

	// *****************Théo***********************************
	@Override
	public void updateService(DaoService service) {
		try {
			String requete = "update service set " +

					"fax =?, " + "localite=?, " + "numero =?, " + "codePostal=?, " + "remarque =?, "
					+ "emailService =?, " + "localisation =?, " + "adresse =? " + "where nom = ?";

			Object[] parametres = new Object[] {

					service.getFax(), service.getLocalite(), service.getNumero(), service.getCodePostal(),
					service.getRemarque(), service.getEmailService(), service.getLocalisation(), service.getAdresse(),
					service.getNom() };

			// System.out.println("\n update service in DaoMysl ==> name :" +
			// service.getNom());
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}

	}
	// ********************************************************

	// *****************Fred***********************************
	@Override
	public void updatePersonnel(DaoPersonnel personnel) {
		try {
			String requete = "update personnel set " + "nom =?, " + "prenom=?, " + "loginWindows =?, "
					+ "loginAS400=?, " + "loginCPAS2000 =?, " + "loginNCC =?, " + "loginGRH =?, "
					+ "loginPublilink =?, " + "mdpPublilink =?, " + "email =?, " + "mdpEmail =?, " + "emailAlias =?, "
					+ "wifi =?, " + "PathPhoto =?, " + "codeAS =? " + "where id_personnel = ?";
			Object[] parametres = new Object[] { personnel.getNom(), personnel.getPrenom(), personnel.getLoginWindows(),
					personnel.getLoginAS400(), personnel.getLoginCPAS2000(), personnel.getLoginNCC(),
					personnel.getLoginGRH(), personnel.getLoginPublilink(), personnel.getMdpPublilink(),
					personnel.getEmail(), personnel.getMdpEmail(), personnel.getEmailAlias(), personnel.getWifi(),
					personnel.getPathPhoto(), personnel.getCodeAS(), personnel.getId_personnel() };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}
	// ********************************************************
}
