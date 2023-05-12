package be.cpasdeliege.intranet.formations.model.dao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import pJSQL.JSQL;
import pJSQL.JSQLException;
import be.cpasdeliege.intranet.formations.model.Formation;
import be.cpasdeliege.intranet.formations.model.FormationService;
import be.cpasdeliege.intranet.formations.model.TypeFormation;
import be.cpasdeliege.intranet.informatique.model.dao.DaoException;

public class DaoFormations {

	public String driver = null;
	public String aliasDB = null;
	public String user = null;
	public String password = null;
	public String start = null;

	private JSQL bd = null;

	public DaoFormations() {

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

	public synchronized void addFormation(Formation formation) {
		try {
			String requete = "insert into formation (" + "intitule, " + "objectif, " + "synthese, " + "methodologie, "
					+ "operateur, " + "personneVisee, " + "rapportFormation, " + "typologie, " + "departement, "
					+ "service, " + "duree, " + "lieu, " + "annee, " + "rapport) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			Object[] parametres = new Object[] { formation.getIntitule(), formation.getObjectif(),
					formation.getSynthese(), formation.getMethodologie(), formation.getOperateur(),
					formation.getPersonneVisee(), formation.getRapportFormation(), formation.getTypologie(),
					formation.getDepartement(), formation.getService(), formation.getDuree(), formation.getLieu(),
					formation.getAnnee(), formation.getRapport() };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized void addFormationService(int idFormation, String service) {
		try {
			String requete = "insert into formationservice (" + "idFormation, " + "service) " + "values(?, ?)";

			Object[] parametres = new Object[] { idFormation, service };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized void addFormationService(String idFormation, String service) {
		addFormationService(Integer.parseInt(idFormation), service);
	}

	public synchronized List<Formation> getFormations() {
		try {
			String requete = "select * from formation order by intitule";
			return (List) bd.executeQueryDB(requete, new BeanListHandler(Formation.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized List<FormationService> getServicesFormation(int idFormation) {
		try {
			String requete = "select * from formationservice where idFormation = ?";
			Object[] parametres = new Object[] { idFormation, };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(FormationService.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized List<FormationService> getServicesFormation(String idFormation) {
		return getServicesFormation(Integer.parseInt(idFormation));
	}

	public synchronized List<FormationService> getFormationService(String service) {
		try {
			String requete = "select fs.idFormation, fs.service from formationservice fs, formation f where f.idFormation = fs.idFormation and fs.service like ? order by f.intitule";
			Object[] parametres = new Object[] { service, };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(FormationService.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized Formation getFormation(int idFormation) {
		try {
			String requete = "select * from formation where idFormation = ?";
			Object[] parametres = new Object[] { idFormation, };
			List tmp = (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(Formation.class));
			return (tmp.size() > 0) ? (Formation) tmp.get(0) : null;
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized Formation getFormation(String idFormation) {
		return getFormation(Integer.parseInt(idFormation));
	}

	public synchronized List<Formation> getFormationParType(String typeFormation) {
		try {
			String requete = "select * from formation where typologie like ?";
			Object[] parametres = new Object[] { typeFormation, };
			return (List) bd.executeQueryDB(requete, parametres, new BeanListHandler(Formation.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized List<TypeFormation> getTypeFormation() {
		try {
			String requete = "select * from type";
			return (List) bd.executeQueryDB(requete, new BeanListHandler(TypeFormation.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized void updateFormation(Formation formation) {
		try {
			String requete = "update formation set " + "intitule = ?, " + "objectif = ?, " + "synthese = ?, "
					+ "methodologie = ?, " + "operateur = ?, " + "personneVisee = ?, " + "rapportFormation = ?, "
					+ "typologie = ?, " + "departement = ?, " + "service = ?, " + "duree = ?, " + "lieu = ?, "
					+ "annee = ?, " + "rapport = ? where idFormation = ? ";

			Object[] parametres = new Object[] { formation.getIntitule(), formation.getObjectif(),
					formation.getSynthese(), formation.getMethodologie(), formation.getOperateur(),
					formation.getPersonneVisee(), formation.getRapportFormation(), formation.getTypologie(),
					formation.getDepartement(), formation.getService(), formation.getDuree(), formation.getLieu(),
					formation.getAnnee(), formation.getRapport(), formation.getIdFormation() };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized void supprimerFormationService(int idFormation, String service) {
		try {
			String requete = "delete from formationservice where idFormation = ? and service like ?";
			Object[] parametres = new Object[] { idFormation, service };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized void supprimerFormationService(int idFormation) {
		try {
			String requete = "delete from formationservice where idFormation = ?";
			Object[] parametres = new Object[] { idFormation };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized void supprimerFormationService(String idFormation, String service) {
		supprimerFormationService(Integer.parseInt(idFormation), service);
	}

	public synchronized void supprimerFormation(int idFormation) {
		try {
			String requete = "delete from formation where idFormation = ?";
			Object[] parametres = new Object[] { idFormation };
			supprimerFormationService(idFormation);
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized void supprimerFormation(String idFormation) {
		supprimerFormation(Integer.parseInt(idFormation));
	}

	public synchronized void testBD() {
		try {
			getTypeFormation();
		} catch (Exception e) {
		}
	}
}
