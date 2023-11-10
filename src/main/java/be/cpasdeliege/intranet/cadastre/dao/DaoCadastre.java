package be.cpasdeliege.intranet.cadastre.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import pJSQL.JSQL;
import pJSQL.JSQLException;
import be.cpasdeliege.intranet.cadastre.model.AdresseMail;
import be.cpasdeliege.intranet.cadastre.model.Annexe;
import be.cpasdeliege.intranet.cadastre.model.DenominationReference;
import be.cpasdeliege.intranet.cadastre.model.Formulaire;
import be.cpasdeliege.intranet.cadastre.model.Lien;
import be.cpasdeliege.intranet.cadastre.model.Marche;
import be.cpasdeliege.intranet.cadastre.model.TypeMarche;
import be.cpasdeliege.intranet.cadastre.model.Util;
import be.cpasdeliege.intranet.informatique.model.dao.DaoException;

public class DaoCadastre {

	public String driver = null;
	public String aliasDB = null;
	public String user = null;
	public String password = null;
	public String start = null;

	private JSQL bd = null;

	public DaoCadastre() {

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

	public synchronized int addMarche(Marche marche) {
		try {
			String requete = "insert into marche_public (" + "id_type_marche, " + "intitule, " + "reference, "
					+ "adjudicataire, " + "adjudicateur, " + "adresse, " + "date_debut, " + "date_fin, " + "date_BP, "
					+ "description) " + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			Object[] parametres = new Object[] { marche.getId_type_marche(), marche.getIntitule(),
					marche.getReference(), marche.getAdjudicataire(), marche.getAdjudicateur(), marche.getAdresse(),
					marche.getDate_debut(), marche.getDate_fin(), marche.getDate_BP(), marche.getDescription() };
			bd.executeUpdate(requete, parametres);

			requete = "select max(id_marche_public) from marche_public";
			ResultSet res = bd.executeQuery(requete);
			res.next();
			return res.getInt(1);

		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized void addAnnexe(Annexe annexe) {
		try {
			String requete = "insert into annexes (" + "id_marche_public, " + "nom, " + "path) " + "values(?, ?, ?)";

			Object[] parametres = new Object[] { annexe.getId_marche_public(), annexe.getNom(), annexe.getPath() };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized void addLien(Lien lien) {
		try {
			String requete = "insert into liens (" + "id_marche_public, " + "nom, " + "lien) " + "values(?, ?, ?)";

			Object[] parametres = new Object[] { lien.getId_marche_public(), lien.getNom(), lien.getLien() };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized void addAdresseMail(AdresseMail adresseMail) {
		try {
			String requete = "insert into mail (" + "adresse) " + "values(?)";

			Object[] parametres = new Object[] { adresseMail.getAdresse() };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized void addFormulaire(Formulaire formulaire) {
		try {
			String requete = "insert into formulaires (" + "nom, " + "path) " + "values(?, ?)";

			Object[] parametres = new Object[] { formulaire.getNom(), formulaire.getPath() };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized void addTypeMarche(TypeMarche typeMarche) {
		try {
			String requete = "insert into type_marche (" + "type_marche, " + "pathImage) " + "values(?, ?)";

			Object[] parametres = new Object[] { typeMarche.getType_marche(), typeMarche.getPathImage() };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized void addDenomitationReference(DenominationReference denomitationReference) {
		try {
			String requete = "insert into denomination_reference (" + "denomination) " + "values(?)";

			Object[] parametres = new Object[] { denomitationReference.getDenomination() };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized List<DenominationReference> getListeDenominationReference() {
		try {
			String requete = "select * from denomination_reference";
			return (List) bd.executeQueryDB(requete, new BeanListHandler(DenominationReference.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized DenominationReference getDenominationReference(String id_denomination_reference) {
		try {
			String requete = "select * from denomination_reference where id_denomination_reference = "
					+ id_denomination_reference;
			return (DenominationReference) ((List) bd.executeQueryDB(requete, new BeanListHandler(Annexe.class)))
					.get(0);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public void supprimerDenominationReference(String id_denomination_reference) {
		try {
			String requete = "delete from denomination_reference where id_denomination_reference = ?";
			Object[] parametres = new Object[] { id_denomination_reference };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public void modifierDenominationReference(DenominationReference denomitationReference) {
		try {
			String requete = "update denomination_reference set denomination = ? where id_denomination_reference = ?";
			Object[] parametres = new Object[] { denomitationReference.getDenomination(),
					denomitationReference.getId_denomination_reference() };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized List<Annexe> getListeAnnexe(String id_marche_public) {
		try {
			String requete = "select * from annexes where id_marche_public = " + id_marche_public;
			return (List) bd.executeQueryDB(requete, new BeanListHandler(Annexe.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized List<Lien> getListeLien(String id_marche_public) {
		try {
			String requete = "select * from liens where id_marche_public = " + id_marche_public;
			return (List) bd.executeQueryDB(requete, new BeanListHandler(Lien.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized List<AdresseMail> getListeAdresseMail() {
		try {
			String requete = "select * from mail";
			return (List) bd.executeQueryDB(requete, new BeanListHandler(AdresseMail.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized List<Marche> getListeMarchesFinProche() {
		List<TypeMarche> listeTypeMarche = getListeTypeMarche();
		try {
			String requete = "select * from marche_public where flag = 0 AND " + "DATEDIFF(date_fin, now()) < 180";
			List<Marche> listeMarche = (List) bd.executeQueryDB(requete, new BeanListHandler(Marche.class));
			for (Marche marche : listeMarche) {
				marche.setTypeMarche(Util.getTypeMarche(listeTypeMarche, marche.getId_type_marche()));
			}
			return listeMarche;
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized List<Formulaire> getListeFormulaire() {
		try {
			String requete = "select * from formulaires";
			return (List) bd.executeQueryDB(requete, new BeanListHandler(Formulaire.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public void supprimerAnnexe(String id_annexe) {
		try {
			String requete = "delete from annexes where id_annexe = ?";
			Object[] parametres = new Object[] { id_annexe };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public void supprimerLien(String id_annexe) {
		try {
			String requete = "delete from liens where id_lien = ?";
			Object[] parametres = new Object[] { id_annexe };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public void supprimerAdresseMail(String id_mail) {
		try {
			String requete = "delete from mail where id_mail = ?";
			Object[] parametres = new Object[] { id_mail };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public void supprimerFormulaire(String id_formulaire) {
		try {
			String requete = "delete from formulaires where id_formulaire = ?";
			Object[] parametres = new Object[] { id_formulaire };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public void modifierType(TypeMarche typeMarche) {
		try {
			String requete = "update type_marche set type_marche = ?, pathImage = ? where id_type_marche = ?";
			Object[] parametres = new Object[] { typeMarche.getType_marche(), typeMarche.getPathImage(),
					typeMarche.getId_type_marche() };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public void modifierMarche(Marche marche) {
		try {
			String requete = "update marche_public set " + "id_type_marche = ?," + "intitule = ?," + "reference = ?,"
					+ "adjudicataire = ?," + "adresse = ?," + "description = ?," + "adjudicateur = ?," + "flag = ?,"
					+ "date_BP = ?," + "date_debut = ?," + "date_fin = ? " + "where id_marche_public = ?";
			Object[] parametres = new Object[] { marche.getId_type_marche(), marche.getIntitule(),
					marche.getReference(), marche.getAdjudicataire(), marche.getAdresse(), marche.getDescription(),
					marche.getAdjudicateur(), marche.getFlag(), marche.getDate_BP(), marche.getDate_debut(),
					marche.getDate_fin(), marche.getId_marche_public() };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public void supprimerType(String id_type_marche) {
		try {
			String requete = "delete from type_marche where id_type_marche = ?";
			Object[] parametres = new Object[] { id_type_marche };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized Annexe getAnnexe(String id_annexe) {
		try {
			String requete = "select * from annexes where id_annexe = " + id_annexe;
			return (Annexe) ((List) bd.executeQueryDB(requete, new BeanListHandler(Annexe.class))).get(0);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized Lien getLien(String id_lien) {
		try {
			String requete = "select * from liens where id_lien = " + id_lien;
			return (Lien) ((List) bd.executeQueryDB(requete, new BeanListHandler(Lien.class))).get(0);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized Formulaire getFormulaire(String id_formulaire) {
		try {
			String requete = "select * from formulaires where id_formulaire = " + id_formulaire;
			return (Formulaire) ((List) bd.executeQueryDB(requete, new BeanListHandler(Formulaire.class))).get(0);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized void supprimerMarche(String id_marche_public) {
		try {
			String requete = "update marche_public set " + "flag = ? " + "where id_marche_public = ?";
			Object[] parametres = new Object[] { 1, id_marche_public };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized void deflagMarche(String id_marche_public) {
		try {
			String requete = "update marche_public set " + "flag = ? " + "where id_marche_public = ?";
			Object[] parametres = new Object[] { 0, id_marche_public };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized void deleteMarche(String id_marche_public) {
		try {
			String requete = "update marche_public set " + "flag = ? " + "where id_marche_public = ?";
			Object[] parametres = new Object[] { 2, id_marche_public };
			bd.executeUpdate(requete, parametres);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
//		List<Annexe> listeAnnexe = getListeAnnexe(id_marche_public);
//		for (Annexe annexe : listeAnnexe) {
//			supprimerAnnexe(annexe.getId_annexe());
//		}
//		try {
//            String requete = "delete from marche_public where id_marche_public = ?";
//            Object[] parametres = new Object[] {id_marche_public};
//            bd.executeUpdate(requete, parametres);
//        } catch (JSQLException e) {
//            throw new DaoException(e.getMessage());
//        }
	}

	public synchronized List<Marche> getListeMarchesByType(String id_type_marche, String orderBy, String sensOrderBy) {
		if (orderBy == null) {
			orderBy = "id_marche_public";
		}
		if (sensOrderBy == null) {
			sensOrderBy = "asc";
		}
		List<TypeMarche> listeTypeMarche = getListeTypeMarche();
		try {
			String requete = "select * from marche_public where flag = 0 and id_type_marche = " + id_type_marche
					+ " order by " + orderBy + " " + sensOrderBy;
			List<Marche> listeMarche = (List) bd.executeQueryDB(requete, new BeanListHandler(Marche.class));
			for (Marche marche : listeMarche) {
				marche.setTypeMarche(Util.getTypeMarche(listeTypeMarche, marche.getId_type_marche()));
			}
			return listeMarche;
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized Marche getMarche(String id_marche_public) {
		List<TypeMarche> listeTypeMarche = getListeTypeMarche();
		try {
			String requete = "select * from marche_public where id_marche_public = " + id_marche_public;
			List<Marche> listeMarche = (List) bd.executeQueryDB(requete, new BeanListHandler(Marche.class));
			for (Marche marche : listeMarche) {
				marche.setTypeMarche(Util.getTypeMarche(listeTypeMarche, marche.getId_type_marche()));
			}
			return listeMarche.get(0);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized int getIdReference(String denomination) {
		try {
			String requete = "select count(*) from marche_public where reference like \"" + denomination + "-%\"";
			ResultSet res = bd.executeQuery(requete);
			res.next();
			return res.getInt(1);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized List<Marche> getListeMarches(String orderBy, String sensOrderBy) {
		if (orderBy == null) {
			orderBy = "id_marche_public";
		}
		if (sensOrderBy == null) {
			sensOrderBy = "asc";
		}
		List<TypeMarche> listeTypeMarche = getListeTypeMarche();
		try {
			String requete = "select * from marche_public where flag = 0 order by " + orderBy + " " + sensOrderBy;
			List<Marche> listeMarche = (List) bd.executeQueryDB(requete, new BeanListHandler(Marche.class));
			for (Marche marche : listeMarche) {
				marche.setTypeMarche(Util.getTypeMarche(listeTypeMarche, marche.getId_type_marche()));
			}
			return listeMarche;
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized List<Marche> getListeMarchesSupp(String orderBy, String sensOrderBy) {
		if (orderBy == null) {
			orderBy = "id_marche_public";
		}
		if (sensOrderBy == null) {
			sensOrderBy = "asc";
		}
		List<TypeMarche> listeTypeMarche = getListeTypeMarche();
		try {
			String requete = "select * from marche_public where flag = 1 order by " + orderBy + " " + sensOrderBy;
			List<Marche> listeMarche = (List) bd.executeQueryDB(requete, new BeanListHandler(Marche.class));
			for (Marche marche : listeMarche) {
				marche.setTypeMarche(Util.getTypeMarche(listeTypeMarche, marche.getId_type_marche()));
			}
			return listeMarche;
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized List<Marche> getListeMarchesRecherche(String motcle, String orderBy, String sensOrderBy) {
		if (orderBy == null) {
			orderBy = "id_marche_public";
		}
		if (sensOrderBy == null) {
			sensOrderBy = "asc";
		}
		List<TypeMarche> listeTypeMarche = getListeTypeMarche();
		String[] tmp = motcle.split(" ");
		try {
			StringBuffer sbRequete = new StringBuffer();
			sbRequete.append("SELECT * FROM marche_public WHERE ");
			for (int i = 0; i < tmp.length; i++) {
				sbRequete.append("(");
				sbRequete.append("intitule LIKE \"%" + tmp[i] + "%\" ");
				sbRequete.append("OR description LIKE \"%" + tmp[i] + "%\" ");
				sbRequete.append("OR reference LIKE \"%" + tmp[i] + "%\" ");
				sbRequete.append(") ");
				if (i != tmp.length - 1) {
					sbRequete.append("AND ");
				}
			}
			sbRequete.append("AND flag = 0 ");
			sbRequete.append("ORDER BY " + orderBy + " " + sensOrderBy);
			String requete = sbRequete.toString();
//            String requete = "SELECT * FROM marche_public WHERE " +
//            		"intitule LIKE \"%" + motcle + "%\" " +
//            		"OR description LIKE \"%" + motcle + "%\" " +
//            		"OR reference LIKE \"%" + motcle + "%\" " +
//            		"AND flag = 0 " +
//            		"ORDER BY " + orderBy + " " + sensOrderBy;
			List<Marche> listeMarche = (List) bd.executeQueryDB(requete, new BeanListHandler(Marche.class));
			for (Marche marche : listeMarche) {
				marche.setTypeMarche(Util.getTypeMarche(listeTypeMarche, marche.getId_type_marche()));
			}
			return listeMarche;
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized List<TypeMarche> getListeTypeMarche() {
		try {
			String requete = "select * from type_marche order by type_marche";
			return (List) bd.executeQueryDB(requete, new BeanListHandler(TypeMarche.class));
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public synchronized TypeMarche getTypeMarche(String idTypeMarche) {
		try {
			String requete = "select * from type_marche where id_type_marche = " + idTypeMarche;
			return ((List<TypeMarche>) bd.executeQueryDB(requete, new BeanListHandler(TypeMarche.class))).get(0);
		} catch (JSQLException e) {
			throw new DaoException(e.getMessage());
		}
	}
}
