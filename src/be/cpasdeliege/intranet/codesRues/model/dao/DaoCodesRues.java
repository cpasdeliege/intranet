package be.cpasdeliege.intranet.codesRues.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pJSQL.JSQL;
import pJSQL.JSQLException;

public class DaoCodesRues {

	public String driver = null;
	public String aliasDB = null;
	public String user = null;
	public String password = null;
	public String start = null;

	private JSQL bd = null;

	public DaoCodesRues() {

	}

	public void connect() {
		try {
			bd = new JSQL(driver);
			bd.connect("relique:csv", aliasDB, user, password);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public void start(boolean start) {
		connect();
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

	public synchronized List<Addenda> getAddenda() {
		try {
			String requete = "select * from addenda";
			ResultSet res = bd.executeQuery(requete);
			List<Addenda> listeRue = new ArrayList<Addenda>();
			while (res.next()) {
				Addenda rue = new Addenda();
				rue.setAbrevRue(res.getString(1));
				rue.setTypeRue(res.getString(2));
				rue.setNomRue(res.getString(4));
				rue.setAntenne(res.getString(5));
				listeRue.add(rue);
			}
			return listeRue;
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized List<Rue> getListeRues() {
		try {
			String requete = "select * from codesRues";
			ResultSet res = bd.executeQuery(requete);
			List<Rue> listeRue = new ArrayList<Rue>();
			while (res.next()) {
				Rue rue = new Rue();
				rue.setCodeRue(res.getString(1));
				rue.setNomRue(res.getString(2));
				rue.setTypeRue(res.getString(3));
				rue.setCodePostal(res.getString(4));
				rue.setAntenne(res.getString(5));
				rue.setCodeAntenne(res.getString(6));
				rue.setRemarque(res.getString(7));
				listeRue.add(rue);
			}
			return listeRue;
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized List<Rue> getListeRues(String codeAntenne) {
		List<Rue> temp = getListeRues();
		List<Rue> listeRue = new ArrayList<Rue>();
		for (Rue rue : temp) {
			if (rue.getCodeAntenne().equals(codeAntenne)) {
				listeRue.add(rue);
			}
		}
		return listeRue;
	}

	public synchronized void testBD() {
		try {
			// getListeRues();
		} catch (Exception e) {
		}
	}
}
