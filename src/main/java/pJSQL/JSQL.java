/*
 * Créé le 25 janv. 2005
 * 
 */
package pJSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/**
 * Gestion des transactions sql.
 * 
 * @version 1.0
 * @author Frédéric Delrée - frederic@delree.be.tf
 */
public class JSQL {

	private Connection conDB = null;

	/**
	 * Charge le pilote du driver spécifié.
	 * 
	 * @param driver la classe du driver
	 * @throws JSQLException si la classe du driver n'est pas dans le classpath de
	 *                       l'application.
	 */
	public JSQL(String driver) throws JSQLException {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new JSQLException("ClassNotFoundException dans " + "JSQL(String driver) : \n\nMessage : le driver \""
					+ driver + "\" est introuvable.");
		}
	}

	/**
	 * Effectue la connexion avec la base.
	 * 
	 * @param protocole le nom du protocole à utiliser avec le driver.
	 * @param aliasDB   le chemin de la base de donnée.
	 * @param user      utilisateur de la base de donnée.
	 * @param pwd       mot de passe utilisateur.
	 * @throws JSQLException
	 */
	public void connect(String protocole, String aliasDB, String user, String pwd) throws JSQLException {
		try {
			Properties props = new Properties();
			props.setProperty("user", user);
			props.setProperty("password", pwd);
			props.setProperty("autoReconnect", "true");
			conDB = DriverManager.getConnection("jdbc:" + protocole + ":" + aliasDB, props);
		} catch (SQLException e) {
			throw new JSQLException(
					"SQLException dans connect(" + "String protocole, String aliasDB, String user, " + "String pwd)",
					e);
		} catch (ExceptionInInitializerError e) {
			throw new JSQLException("ExceptionInInitializerError dans "
					+ "connect(String protocole, String aliasDB, String user, "
					+ "String pwd) :\n\nMessage : vérifiez les arguments " + "de la connection à la base de données.");
		}
	}

	/**
	 * Effectue la connexion avec la base CSV.
	 * 
	 * @param protocole  le nom du protocole à utiliser avec le driver.
	 * @param aliasDB    le chemin de la base de donnée.
	 * @param properties les propriétés du fichier csv.
	 * @throws JSQLException
	 */
	public void connect(String protocole, String aliasDB, Properties properties) throws JSQLException {
		try {
			conDB = DriverManager.getConnection("jdbc:" + protocole + ":" + aliasDB, properties);
		} catch (SQLException e) {
			throw new JSQLException(
					"SQLException dans connect(" + "String protocole, String aliasDB, String user, " + "String pwd)",
					e);
		} catch (ExceptionInInitializerError e) {
			throw new JSQLException("ExceptionInInitializerError dans "
					+ "connect(String protocole, String aliasDB, String user, "
					+ "String pwd) :\n\nMessage : vérifiez les arguments " + "de la connection à la base de données.");
		}
	}

	public int getLastInsertId(String table, String column) throws JSQLException {
		try {
			ResultSet res = executeQuery("SELECT MAX(" + column + ") as id FROM " + table);
			res.next();
			return res.getInt("id");
		} catch (SQLException e) {
			throw new JSQLException();
		}
	}

	/**
	 * Ferme la connexion à la base.
	 * 
	 * @throws JSQLException
	 */
	public void close() throws JSQLException {
		try {
			conDB.close();
		} catch (SQLException e) {
			throw new JSQLException("SQLException dans close()", e);
		}
	}

	/**
	 * Exécute la requete spécifiée.
	 * 
	 * @param query la requête sql.
	 * @return Le résultat sous forme d'un JSQLResultset.
	 * @throws JSQLException
	 */
	public ResultSet executeQuery(String query) throws JSQLException {
		try {
			return conDB.createStatement().executeQuery(query);
		} catch (SQLException e) {
			throw new JSQLException("SQLException dans executQuery(" + "String query)", e);
		} catch (ExceptionInInitializerError e) {
			throw new JSQLException("ExceptionInInitializerError dans " + "executQuery(String query)\n\nquery   : "
					+ query + "\nMessage : Erreur de syntaxe " + "dans la requete.\n");
		}
	}

	/**
	 * Exécute la requête formatée spécifiée.<br>
	 * <br>
	 * Exemple : "Select * from userData" pourrait donner : executeQuery ("Select *
	 * from ?", new Objet[]{"userData"}).
	 * 
	 * @param query la requête sql à formater.
	 * @param args  les éléments pour formater la requête.
	 * @return Le résultat sous forme d'un JSQLResultset.
	 * @throws JSQLException
	 */
	public ResultSet executeQuery(String query, Object[] args) throws JSQLException {
		try {
			PreparedStatement pStatement = conDB.prepareStatement(query);
			for (int i = 0; i < args.length; i++) {
				pStatement.setObject(i + 1, args[i]);
			}
			return pStatement.executeQuery();
		} catch (SQLException e) {
			throw new JSQLException("SQLException dans executQuery(" + "String query, String[] args)", e);
		} catch (ExceptionInInitializerError e) {
			StringBuffer temp = new StringBuffer();
			for (int j = 0; j < args.length; j++) {
				temp.append("(" + args[j] + ") ");
			}
			throw new JSQLException(
					"ExceptionInInitializerError dans " + "executQuery(String query, String[] args)\n\nquery   : \""
							+ query + "\" -> " + temp + "\nMessage : Erreur de syntaxe " + "dans la requete.\n");
		}
	}

	/**
	 * 
	 * @param query
	 * @param beanListHandler
	 * @return
	 * @throws JSQLException
	 */
	public Object executeQueryDB(String query, BeanListHandler beanListHandler) throws JSQLException {
		try {
			QueryRunner qRunner = new QueryRunner();
			return (Object) qRunner.query(conDB, query, beanListHandler);
		} catch (SQLException e) {
			throw new JSQLException("SQLException dans executQuery(" + "String query)", e);
		} catch (ExceptionInInitializerError e) {
			throw new JSQLException("ExceptionInInitializerError dans " + "executQuery(String query)\n\nquery   : "
					+ query + "\nMessage : Erreur de syntaxe " + "dans la requete.\n");
		}
	}

	/**
	 * 
	 * @param query
	 * @param args
	 * @param beanListHandler
	 * @return
	 * @throws JSQLException
	 */
	public Object executeQueryDB(String query, Object[] args, BeanListHandler beanListHandler) throws JSQLException {
		try {
			QueryRunner qRunner = new QueryRunner();
			return qRunner.query(conDB, query, args, beanListHandler);
		} catch (SQLException e) {
			throw new JSQLException("SQLException dans executQuery(" + "String query)", e);
		} catch (ExceptionInInitializerError e) {
			throw new JSQLException("ExceptionInInitializerError dans " + "executQuery(String query)\n\nquery   : "
					+ query + "\nMessage : Erreur de syntaxe " + "dans la requete.\n");
		}
	}

	/**
	 * Exécute la requête spécifiée.
	 * 
	 * @param query la requête sql.
	 * @return int nombre de tuple mis à jour.
	 * @throws JSQLException
	 */
	public int executeUpdate(String query) throws JSQLException {
		try {
			return conDB.createStatement().executeUpdate(query);
		} catch (SQLException e) {
			throw new JSQLException("SQLException dans executUpdate(" + "String query)", e);
		} catch (ExceptionInInitializerError e) {
			throw new JSQLException("ExceptionInInitializerError dans " + "executeUpdate(String query)\n\nquery   : "
					+ query + "\nMessage : Erreur de syntaxe " + "dans la requete.\n");
		}
	}

	/**
	 * Exécute la requête formatée spécifiée.
	 * 
	 * @param query la requête sql à formater.
	 * @param args  les éléments pour formater la requête.
	 * @return int nombre de tuple mis à jour.
	 * @throws JSQLException
	 */
	public int executeUpdate(String query, Object[] args) throws JSQLException {
		try {
			PreparedStatement pStatement = conDB.prepareStatement(query);
			for (int i = 0; i < args.length; i++) {
//            	System.out.println("message = " + i + "  --> " + args[i].toString());
				pStatement.setObject(i + 1, args[i]);
			}
			return pStatement.executeUpdate();
		} catch (SQLException e) {
			throw new JSQLException("SQLException dans executUpdate(" + "String query, String[] args)" + query, e);
		} catch (ExceptionInInitializerError e) {
			StringBuffer temp = new StringBuffer();
			for (int j = 0; j < args.length; j++) {
				temp.append("(" + args[j] + ") ");
			}
			throw new JSQLException(
					"ExceptionInInitializerError dans " + "executeUpdate(String query, String[] args)\n\nquery   : "
							+ query + " - " + temp + "\nMessage : Erreur de syntaxe " + "dans la requete.\n");
		}
	}

	/**
	 * Regarde si la connexion est toujours active.
	 * 
	 * @return true si elle l'est.
	 * @throws JSQLException
	 */
	public boolean isClose() throws JSQLException {
		try {
			return conDB.isClosed();
		} catch (SQLException e) {
			throw new JSQLException("SQLException dans isClose()", e);
		}
	}
}
