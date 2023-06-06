package be.cpasdeliege.intranet.DemServInfo.dao;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mysql.jdbc.ResultSet;

import pJSQL.JSQL;
import pJSQL.JSQLException;
import be.cpasdeliege.intranet.DemServInfo.model.Chef;
import be.cpasdeliege.intranet.DemServInfo.model.Config;
import be.cpasdeliege.intranet.DemServInfo.model.DemServInf;
import be.cpasdeliege.intranet.DemServInfo.model.Dsigti;
import be.cpasdeliege.intranet.DemServInfo.model.Remarque;
import be.cpasdeliege.intranet.DemServInfo.model.StatDem;
import be.cpasdeliege.intranet.informatique.model.dao.DaoException;
import be.cpasdeliege.intranet.utils.Utils;

public class DaoDemServInf {

	public String driver = null;
	public String aliasDB = null;
	public String user = null;
	public String password = null;
	public String start = null;

	private JSQL bd = null;

	public DaoDemServInf() {

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

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
		connect();
	}

	public synchronized void testBD() {
		try {
			getRemarques("1");
		} catch (Exception e) {
		}
	}

	public synchronized void addConfig(Config config) {
		try {
			String requete = "insert into config (" + "login, " + "nom, " + "prenom) " + "values(?, ?, ?)";

			Object[] parametres = new Object[] { config.getLogin(), config.getNom(), config.getPrenom() };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			System.out.println("message = " + e.getMessage());
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized void addDsigti(Dsigti dsigti) {
		try {
			String requete = "insert into dsigti (" + "idDemande, " + "idPlanning) " + "values(?, ?)";

			Object[] parametres = new Object[] { dsigti.getIdDemande(), dsigti.getIdPlanning() };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			System.out.println("message = " + e.getMessage());
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized void deleteDsigti(String idDemande, String idPlanning) {
		try {
			String requete = "delete from dsigti where idDemande = ? and idPlanning = ?";
			Object[] parametres = new Object[] { idDemande, idPlanning };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			System.out.println("message = " + e.getMessage());
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized List<Dsigti> getDsigti(String idDemande) {
		try {
			String requete = "select * from dsigti where idDemande = " + idDemande;
			return (List) bd.executeQueryDB(requete, new BeanListHandler(Dsigti.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized List<Dsigti> getDsigti() {
		try {
			String requete = "select * from dsigti";
			return (List) bd.executeQueryDB(requete, new BeanListHandler(Dsigti.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized void updateDsigti(Dsigti dsigti) {
		try {
			String requete = "update dsigti set " + "fini = ? " + "where idPlanning = ? " + "and idDemande = ?";

			Object[] parametres = new Object[] { dsigti.getFini(), dsigti.getIdPlanning(), dsigti.getIdDemande() };
			bd.executeUpdate(requete, parametres);

		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized void updateConfig(Config config) {
		try {
			String requete = "update config set " + "nom = ?, " + "prenom = ? " + "where login = ?";

			Object[] parametres = new Object[] { config.getNom(), config.getPrenom(), config.getLogin() };
			bd.executeUpdate(requete, parametres);

		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized void deleteConfig(String login) {
		try {
			String requete = "delete from config where login like ?";
			Object[] parametres = new Object[] { login };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized Config getconfig(String login) {
		try {
			String requete = "select * from config where login like ?";
			Object[] parametres = new Object[] { login };
			List tmp = (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(Config.class));
			if (tmp.size() == 0) {
				Config config = new Config(login);
				addConfig(config);
				return config;
			} else {
				return (Config) ((List) bd.executeQueryDB(requete, parametres, new BeanListHandler(Config.class)))
						.get(0);
			}
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized int addDemande(DemServInf demande) {
		try {
			String requete = "insert into demandes (" + "typeDemande, " + "description, " + "dateDemande, "
					+ "dateExecSouhaitee, " + "dateExecPrevue, " + "dateExecSouhaiteeSecr, "
					+ "dateExecSouhaiteeSecrConf, " + "dateExecEffective, " + "validationSecr, " + "validationInfor, "
					+ "validationSecrConf, " + "validationChef, " + "dateExecSouhaiteeChef, " + "nomChef, "
					+ "prenomChef," + "service, " + "idPlanning, " + "nom, " + "prenom," + "remarqueEcheance,"
					+ "enAttente," + "titre) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			Object[] parametres = new Object[] { demande.getTypeDemande(), demande.getDescription(),
					demande.getDateDemande(), demande.getDateExecSouhaitee(), demande.getDateExecPrevue(),
					demande.getDateExecSouhaiteeSecr(), demande.getDateExecSouhaiteeSecrConf(),
					demande.getDateExecEffective(), demande.getValidationSecr(), demande.getValidationInfor(),
					demande.getValidationSecrConf(), demande.getValidationChef(), demande.getDateExecSouhaiteeChef(),
					demande.getNomChef(), demande.getPrenomChef(), demande.getService(), demande.getIdPlanning(),
					demande.getNom(), demande.getPrenom(), demande.getRemarqueEcheance(), demande.getEnAttente(),
					demande.getTitre() };
			bd.executeUpdate(requete, parametres);
			return bd.getLastInsertId("demandes", "idDemandes");
		} catch (JSQLException e) {
			System.out.println("message = " + e.getMessage());
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized void updateDemande(DemServInf demande) {
		try {
			String requete = "update demandes set " + "typeDemande = ?, " + "description = ?, " + "dateDemande = ?, "
					+ "dateExecSouhaitee = ?, " + "dateExecPrevue = ?, " + "dateExecSouhaiteeSecr = ?, "
					+ "dateExecSouhaiteeSecrConf = ?, " + "dateExecEffective = ?, " + "validationSecr = ?, "
					+ "validationInfor = ?, " + "validationSecrConf = ?, " + "validationChef = ?, " + "nomChef = ?, "
					+ "prenomChef = ?, " + "service = ?, " + "dateExecSouhaiteeChef = ?, " + "nom = ?, "
					+ "prenom = ?, " + "remarqueEcheance = ?, " + "idPlanning = ?, " + "enAttente = ?, " + "titre = ? "
					+ "where idDemandes = ?";

			Object[] parametres = new Object[] { demande.getTypeDemande(), demande.getDescription(),
					demande.getDateDemande(), demande.getDateExecSouhaitee(), demande.getDateExecPrevue(),
					demande.getDateExecSouhaiteeSecr(), demande.getDateExecSouhaiteeSecrConf(),
					demande.getDateExecEffective(), demande.getValidationSecr(), demande.getValidationInfor(),
					demande.getValidationSecrConf(), demande.getValidationChef(), demande.getNomChef(),
					demande.getPrenomChef(), demande.getService(), demande.getDateExecSouhaiteeChef(), demande.getNom(),
					demande.getPrenom(), demande.getRemarqueEcheance(), demande.getIdPlanning(), demande.getEnAttente(),
					demande.getTitre(), demande.getIdDemandes() };
			bd.executeUpdate(requete, parametres);

		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized void deleteDemande(String idDemande) {
		try {
			String requete = "delete from demandes where idDemandes like ?";
			Object[] parametres = new Object[] { idDemande };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized List<DemServInf> getDemandes() {
		try {
			String requete = "select * from demandes";
			return (List) bd.executeQueryDB(requete, new BeanListHandler(DemServInf.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized List<Chef> getListeChefs() {
		try {
			String requete = "SELECT utilisateur.nom, utilisateur.prenom FROM cpas.utilisateur, cpas.privilege_informatique WHERE utilisateur.login LIKE privilege_informatique.login and privilege_informatique.dsiChef = 1";
			return (List) bd.executeQueryDB(requete, new BeanListHandler(Chef.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	/*
	 * USER
	 */

//	public synchronized List<DemServInf> getDemandesNouvellesUser(String nom, String prenom, String orderby) {
//		try {
//            String requete = "select * from demandes where validationChef like 'none' and nom like '" + nom + "' and prenom like '" + prenom + "' order by " + orderby;
//            return (List)bd.executeQueryDB(requete, new BeanListHandler(DemServInf.class));
//        } catch (JSQLException e) {
//            throw new DaoException(e.getMessage());
//        } 
//	}

	public synchronized List<DemServInf> getDemandesNouvellesUser(String nom, String prenom, String orderby) {
		try {
			String requete = "select * from demandes where validationChef like 'none' and nom like ? and prenom like ? order by ?";
			Object[] parametres = new Object[] { nom, prenom, orderby };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DemServInf.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

//	public synchronized List<DemServInf> getDemandesValidationUser(String nom, String prenom, String orderby) {
//		try {
//            String requete = "select * from demandes where (validationChef like 'oui') and (validationSecrConf like 'none') and nom like '" + nom + "' and prenom like '" + prenom + "' order by " + orderby;
//            return (List)bd.executeQueryDB(requete, new BeanListHandler(DemServInf.class));
//        } catch (JSQLException e) {
//            throw new DaoException(e.getMessage());
//        } 
//	}
//	
	public synchronized List<DemServInf> getDemandesValidationUser(String nom, String prenom, String orderby) {
		try {
			String requete = "select * from demandes where (validationChef like 'oui') and (validationSecrConf like 'none') and nom like ? and prenom like ? and enAttente = 0 order by ?";
			Object[] parametres = new Object[] { nom, prenom, orderby };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DemServInf.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized List<DemServInf> getDemandesValidationUserEnAttente(String nom, String prenom, String orderby) {
		try {
			String requete = "select * from demandes where (validationChef like 'oui') and (validationSecrConf like 'none') and nom like ? and prenom like ? and enAttente = 1 order by ?";
			Object[] parametres = new Object[] { nom, prenom, orderby };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DemServInf.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

//	public synchronized List<DemServInf> getDemandesAccepteesUser(String nom, String prenom, String orderby) {
//		try {
//            String requete = "select * from demandes where validationSecrConf like 'oui' and dateExecEffective is null and nom like '" + nom + "' and prenom like '" + prenom + "' order by " + orderby;
//            return (List)bd.executeQueryDB(requete, new BeanListHandler(DemServInf.class));
//        } catch (JSQLException e) {
//            throw new DaoException(e.getMessage());
//        } 
//	}

	public synchronized List<DemServInf> getDemandesAccepteesUser(String nom, String prenom, String orderby) {
		try {
			String requete = "select * from demandes where validationSecrConf like 'oui' and dateExecEffective is null and nom like ? and prenom like ? order by ?";
			Object[] parametres = new Object[] { nom, prenom, orderby };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DemServInf.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

//	public synchronized List<DemServInf> getDemandesExecuteesUser(String nom, String prenom, String orderby) {
//		try {
//            String requete = "select * from demandes where dateExecEffective is not null and nom like '" + nom + "' and prenom like '" + prenom + "' order by " + orderby;
//            return (List)bd.executeQueryDB(requete, new BeanListHandler(DemServInf.class));
//        } catch (JSQLException e) {
//            throw new DaoException(e.getMessage());
//        } 
//	}

	public synchronized List<DemServInf> getDemandesExecuteesUser(String nom, String prenom, String orderby) {
		try {
			String requete = "select * from demandes where dateExecEffective is not null and nom like ? and prenom like ? order by ?";
			Object[] parametres = new Object[] { nom, prenom, orderby };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DemServInf.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

//	public synchronized List<DemServInf> getDemandesRefuseesUser(String nom, String prenom, String orderby) {
//		try {
//            String requete = "select * from demandes where (validationSecrConf like 'non' or validationChef like 'non') and nom like '" + nom + "' and prenom like '" + prenom + "' order by " + orderby;
//            return (List)bd.executeQueryDB(requete, new BeanListHandler(DemServInf.class));
//        } catch (JSQLException e) {
//            throw new DaoException(e.getMessage());
//        } 
//	}

	public synchronized List<DemServInf> getDemandesRefuseesUser(String nom, String prenom, String orderby) {
		try {
			String requete = "select * from demandes where (validationSecrConf like 'non' or validationChef like 'non') and nom like ? and prenom like ? order by ?";
			Object[] parametres = new Object[] { nom, prenom, orderby };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DemServInf.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	/*
	 * CHEF
	 */

//	public synchronized List<DemServInf> getDemandesNouvellesChef(String nom, String prenom, String orderby) {
//		try {
//            String requete = "select * from demandes where validationChef like 'none' and nomChef like '" + nom + "' and prenomChef like '" + prenom + "' order by " + orderby;
//            return (List)bd.executeQueryDB(requete, new BeanListHandler(DemServInf.class));
//        } catch (JSQLException e) {
//            throw new DaoException(e.getMessage());
//        } 
//	}

	public synchronized List<DemServInf> getDemandesNouvellesChef(String nom, String prenom, String orderby) {
		try {
			String requete = "select * from demandes where validationChef like 'none' and nomChef like ? and prenomChef like ? order by ?";
			Object[] parametres = new Object[] { nom, prenom, orderby };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DemServInf.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

//	public synchronized List<DemServInf> getDemandeValidationChef(String nom, String prenom, String orderby) {
//		try {
//            String requete = "select * from demandes where (validationChef like 'oui') and (validationSecrConf like 'none') and nomChef like '" + nom + "' and prenomChef like '" + prenom + "' order by " + orderby;
//            return (List)bd.executeQueryDB(requete, new BeanListHandler(DemServInf.class));
//        } catch (JSQLException e) {
//            throw new DaoException(e.getMessage());
//        } 
//	}

	public synchronized List<DemServInf> getDemandeValidationChef(String nom, String prenom, String orderby) {
		try {
			String requete = "select * from demandes where (validationChef like 'oui') and (validationSecrConf like 'none') and nomChef like ? and prenomChef like ? and enAttente = 0 order by ?";
			Object[] parametres = new Object[] { nom, prenom, orderby };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DemServInf.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized List<DemServInf> getDemandesValidationChefEnAttente(String nom, String prenom, String orderby) {
		try {
			String requete = "select * from demandes where (validationChef like 'oui') and (validationSecrConf like 'none') and nomChef like ? and prenomChef like ? and enAttente = 1 order by ?";
			Object[] parametres = new Object[] { nom, prenom, orderby };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DemServInf.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

//	public synchronized List<DemServInf> getDemandesAccepteesChef(String nom, String prenom, String orderby) {
//		try {
//            String requete = "select * from demandes where validationSecrConf like 'oui' and dateExecEffective is null and nomChef like '" + nom + "' and prenomChef like '" + prenom + "' order by " + orderby;
//            return (List)bd.executeQueryDB(requete, new BeanListHandler(DemServInf.class));
//        } catch (JSQLException e) {
//            throw new DaoException(e.getMessage());
//        } 
//	}

	public synchronized List<DemServInf> getDemandesAccepteesChef(String nom, String prenom, String orderby) {
		try {
			String requete = "select * from demandes where validationSecrConf like 'oui' and dateExecEffective is null and nomChef like ? and prenomChef like ? order by ?";
			System.out.println("Fred : Chef Nom : " + nom + " Prenom : " + prenom + " orderby : " + orderby);
			Object[] parametres = new Object[] { nom, prenom, orderby };
			System.out.println(
					"********************************************************************************************");
			ResultSet rs = (ResultSet) bd.executeQuery(
					"select * from demandes where validationSecrConf like 'oui' and dateExecEffective is null and nomChef like '"
							+ Utils.escapeCharacters(nom) + "' and prenomChef like '" + Utils.escapeCharacters(prenom) + "' order by " + orderby);
			try {
				while (rs.next()) {
					System.out.println(rs.getInt("iddemandes"));
				}
			} catch (Exception e) {
				System.out.println("Message : " + e.getMessage());
				System.out.println("Cause : " + e.getCause());

			}
			System.out.println(
					"********************************************************************************************");
			List<DemServInf> tt = (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DemServInf.class));
			System.out.println(tt.size());
			for (int i = 0; i < tt.size(); i++) {
				System.out.println(tt.get(i).getIdDemandes());
			}
			/*
			 * for(DemServInf dsi : tt) { System.out.println(dsi.getIdDemandes()); }
			 */

			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DemServInf.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

//	public synchronized List<DemServInf> getDemandesExecuteesChef(String nom, String prenom, String orderby) {
//		try {
//            String requete = "select * from demandes where dateExecEffective is not null and nomChef like '" + nom + "' and prenomChef like '" + prenom + "' order by " + orderby;
//            return (List)bd.executeQueryDB(requete, new BeanListHandler(DemServInf.class));
//        } catch (JSQLException e) {
//            throw new DaoException(e.getMessage());
//        } 
//	}

	public synchronized List<DemServInf> getDemandesExecuteesChef(String nom, String prenom, String orderby) {
		try {
			String requete = "select * from demandes where dateExecEffective is not null and nomChef like ? and prenomChef like ? order by ?";
			Object[] parametres = new Object[] { nom, prenom, orderby };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DemServInf.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

//	public synchronized List<DemServInf> getDemandesRefuseesChef(String nom, String prenom, String orderby) {
//		try {
//            String requete = "select * from demandes where (validationSecrConf like 'non' or validationChef like 'non') and nomChef like '" + nom + "' and prenomChef like '" + prenom + "' order by " + orderby;
//            return (List)bd.executeQueryDB(requete, new BeanListHandler(DemServInf.class));
//        } catch (JSQLException e) {
//            throw new DaoException(e.getMessage());
//        } 
//	}

	public synchronized List<DemServInf> getDemandesRefuseesChef(String nom, String prenom, String orderby) {
		try {
			String requete = "select * from demandes where (validationSecrConf like 'non' or validationChef like 'non') and nomChef like ? and prenomChef like ? order by ?";
			Object[] parametres = new Object[] { nom, prenom, orderby };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DemServInf.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	/*
	 * Statistiques
	 */

	public synchronized StatDem getStat() {

		int n = getDemandesNouvelles("%", "%", "idDemandes").size();
		int vi = getDemandesValidInfo("%", "%", "idDemandes").size();
		int conf = getDemandesConf("%", "%", "idDemandes").size();
		int acc = getDemandesAcceptees("%", "%", "idDemandes").size();
		int vu = getDemandesValidUser("%", "%", "idDemandes").size();
		int r = getDemandesChefNonValdidee("idDemandes").size();

		int year = Calendar.getInstance().get(Calendar.YEAR);
		int nreDemandeAnnee = getDemandesAnnee("" + year).size();

		StatDem stat = new StatDem();
		stat.setNbrAttenteDG("" + (n + conf + r));
		stat.setNbrAttenteInfo("" + vi);
		stat.setNbrAcceptee("" + acc);
		stat.setNbrAnneeEnCours("" + nreDemandeAnnee);
		stat.setAnnee("" + year);
		return stat;
	}

	public synchronized List<DemServInf> getDemandesChefNonValdidee(String orderby) {
		try {
			String requete = "select * from demandes where validationChef like 'none' order by " + orderby;
			return (List) bd.executeQueryDB(requete, new BeanListHandler(DemServInf.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized List<DemServInf> getDemandesAnnee(String annee) {
		try {
			String requete = "SELECT * FROM demservinfo.demandes where dateDemande > '" + annee + "-01-01'";
			return (List) bd.executeQueryDB(requete, new BeanListHandler(DemServInf.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	/*
	 * DIRECTION / INFORMATIQUE
	 */

//	public synchronized List<DemServInf> getDemandesNouvelles(String nom, String prenom, String orderby) {
//		try {
//            String requete = "select * from demandes where(validationChef like 'oui') and validationSecr like 'none' and nom like '" + nom + "' and prenom like '" + prenom + "' order by " + orderby;
//            return (List)bd.executeQueryDB(requete, new BeanListHandler(DemServInf.class));
//        } catch (JSQLException e) {
//            throw new DaoException(e.getMessage());
//        } 
//	}

	public synchronized List<DemServInf> getDemandesNouvelles(String nom, String prenom, String orderby) {
		try {
			String requete = "select * from demandes where(validationChef like 'oui') and validationSecr like 'none' and nom like ? and prenom like ? order by ?";
			Object[] parametres = new Object[] { nom, prenom, orderby };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DemServInf.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

//	public synchronized List<DemServInf> getDemandesValidInfo(String nom, String prenom, String orderby) {
//		try {
//            String requete = "select * from demandes where (validationSecr not like 'none') and (validationInfor like 'none') and nom like '" + nom + "' and prenom like '" + prenom + "' order by " + orderby;
//            return (List)bd.executeQueryDB(requete, new BeanListHandler(DemServInf.class));
//        } catch (JSQLException e) {
//            throw new DaoException(e.getMessage());
//        } 
//	}

	public synchronized List<DemServInf> getDemandesValidInfo(String nom, String prenom, String orderby) {
		try {
			String requete = "select * from demandes where (validationSecr not like 'none') and (validationInfor like 'none') and nom like ? and prenom like ? and enAttente = 0 order by ?";
			Object[] parametres = new Object[] { nom, prenom, orderby };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DemServInf.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized List<DemServInf> getDemandesValidInfoEnAttente(String nom, String prenom, String orderby) {
		try {
			String requete = "select * from demandes where (validationSecr not like 'none') and (validationInfor like 'none') and nom like ? and prenom like ? and enAttente = 1 order by ?";
			Object[] parametres = new Object[] { nom, prenom, orderby };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DemServInf.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

//	public synchronized List<DemServInf> getDemandesValidUser(String nom, String prenom, String orderby) {
//		try {
//            String requete = "select * from demandes where (validationSecr not like 'none') and (validationSecrConf like 'none') and nom like '" + nom + "' and prenom like '" + prenom + "' order by " + orderby;
//            return (List)bd.executeQueryDB(requete, new BeanListHandler(DemServInf.class));
//        } catch (JSQLException e) {
//            throw new DaoException(e.getMessage());
//        } 
//	}

	public synchronized List<DemServInf> getDemandesValidUser(String nom, String prenom, String orderby) {
		try {
			String requete = "select * from demandes where (validationSecr not like 'none') and (validationSecrConf like 'none') and nom like ? and prenom like ? order by ?";
			Object[] parametres = new Object[] { nom, prenom, orderby };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DemServInf.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

//	public synchronized List<DemServInf> getDemandesConf(String nom, String prenom, String orderby) {
//		try {
//            String requete = "select * from demandes where (validationSecr not like 'none' and validationInfor not like 'none') and (validationSecrConf like 'none') and nom like '" + nom + "' and prenom like '" + prenom + "' order by " + orderby;
//            return (List)bd.executeQueryDB(requete, new BeanListHandler(DemServInf.class));
//        } catch (JSQLException e) {
//            throw new DaoException(e.getMessage());
//        } 
//	}

	public synchronized List<DemServInf> getDemandesConf(String nom, String prenom, String orderby) {
		try {
			String requete = "select * from demandes where (validationSecr not like 'none' and validationInfor not like 'none') and (validationSecrConf like 'none') and nom like ? and prenom like ? order by ?";
			Object[] parametres = new Object[] { nom, prenom, orderby };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DemServInf.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

//	public synchronized List<DemServInf> getDemandesAcceptees(String nom, String prenom, String orderby) {
//		try {
//            String requete = "select * from demandes where validationSecrConf like 'oui' and dateExecEffective is null and nom like '" + nom + "' and prenom like '" + prenom + "' order by " + orderby;
//            return (List)bd.executeQueryDB(requete, new BeanListHandler(DemServInf.class));
//        } catch (JSQLException e) {
//            throw new DaoException(e.getMessage());
//        } 
//	}

	public synchronized List<DemServInf> getDemandesAcceptees(String nom, String prenom, String orderby) {
		try {
			String requete = "select * from demandes where validationSecrConf like 'oui' and dateExecEffective is null and nom like ? and prenom like ? order by ?";
			Object[] parametres = new Object[] { nom, prenom, orderby };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DemServInf.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

//	public synchronized List<DemServInf> getDemandesExecutees(String nom, String prenom, String orderby) {
//		try {
//            String requete = "select * from demandes where dateExecEffective is not null and nom like '" + nom + "' and prenom like '" + prenom + "' order by " + orderby;
//            return (List)bd.executeQueryDB(requete, new BeanListHandler(DemServInf.class));
//        } catch (JSQLException e) {
//            throw new DaoException(e.getMessage());
//        } 
//	}

	public synchronized List<DemServInf> getDemandesExecutees(String nom, String prenom, String orderby) {
		try {
			String requete = "select * from demandes where dateExecEffective is not null and nom like ? and prenom like ? order by ?";
			Object[] parametres = new Object[] { nom, prenom, orderby };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DemServInf.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}
//	public synchronized List<DemServInf> getDemandesAttente(String nom, String prenom, String orderby) {
//		try {
//            String requete = "select * from demandes where validationSecrConf like 'attente' and nom like '" + nom + "' and prenom like '" + prenom + "' order by " + orderby;
//            return (List)bd.executeQueryDB(requete, new BeanListHandler(DemServInf.class));
//        } catch (JSQLException e) {
//            throw new DaoException(e.getMessage());
//        } 
//	}

	public synchronized List<DemServInf> getDemandesAttente(String nom, String prenom, String orderby) {
		try {
			String requete = "select * from demandes where validationSecrConf like 'attente' and nom like ? and prenom like ? order by ?";
			Object[] parametres = new Object[] { nom, prenom, orderby };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DemServInf.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

//	public synchronized List<DemServInf> getDemandesRefusees(String nom, String prenom, String orderby) {
//		try {
//            String requete = "select * from demandes where validationSecrConf like 'non' and nom like '" + nom + "' and prenom like '" + prenom + "' order by " + orderby;
//            return (List)bd.executeQueryDB(requete, new BeanListHandler(DemServInf.class));
//        } catch (JSQLException e) {
//            throw new DaoException(e.getMessage());
//        } 
//	}

	public synchronized List<DemServInf> getDemandesRefusees(String nom, String prenom, String orderby) {
		try {
			String requete = "select * from demandes where validationSecrConf like 'non' and nom like ? and prenom like ? order by ?";
			Object[] parametres = new Object[] { nom, prenom, orderby };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DemServInf.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

//	public synchronized List<DemServInf> getDemandesSupprimees(String nom, String prenom, String orderby) {
//		try {
//            String requete = "select * from demandes where validationSecrConf like 'supp' and nom like '" + nom + "' and prenom like '" + prenom + "' order by " + orderby;
//            return (List)bd.executeQueryDB(requete, new BeanListHandler(DemServInf.class));
//        } catch (JSQLException e) {
//            throw new DaoException(e.getMessage());
//        } 
//	}

	public synchronized List<DemServInf> getDemandesSupprimees(String nom, String prenom, String orderby) {
		try {
			String requete = "select * from demandes where validationSecrConf like 'supp' and nom like ? and prenom like ? order by ?";
			Object[] parametres = new Object[] { nom, prenom, orderby };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(DemServInf.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized DemServInf getDemande(String idDemandes) {
		return getDemande(Integer.parseInt(idDemandes));
	}

	public synchronized DemServInf getDemande(int idDemandes) {
		try {
			String requete = "select * from demandes where idDemandes = " + idDemandes;
			return (DemServInf) ((List) bd.executeQueryDB(requete, new BeanListHandler(DemServInf.class))).get(0);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized void addRemarque(Remarque remarque) {
		if (!remarque.getTexte().equals("")) {
			try {
				String requete = "insert into remarques (" + "idDemandes, " + "texte, " + "user, " + "date) "
						+ "values(?, ?, ?, ?)";

				Object[] parametres = new Object[] { remarque.getIdDemande(), remarque.getTexte(), remarque.getUser(),
						remarque.getDate() };
				bd.executeUpdate(requete, parametres);
			} catch (JSQLException e) {
				System.out.println("message = " + e.getMessage());
				throw new DaoException(e.getMessage());
			}
		}
	}

	public synchronized List<Remarque> getRemarques(String idDemandes) {
		try {
			String requete = "select * from remarques where idDemandes = " + idDemandes;
			return (List) bd.executeQueryDB(requete, new BeanListHandler(Remarque.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}
}
