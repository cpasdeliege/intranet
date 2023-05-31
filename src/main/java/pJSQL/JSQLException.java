/*
 * Créé le 25 janv. 2005
 *
 */
package pJSQL;

import java.sql.SQLException;

/**
 * 
 * @version 1.0
 * @author Frédéric Delrée - frederic@delree.be.tf
 *
 * 
 */
public class JSQLException extends Exception {
	static final long serialVersionUID = 1;
	private String toPrint;

	public JSQLException() {
		toPrint = new String("JSQLException de type non précisé");
	}

	public JSQLException(String msg) {
		toPrint = new String(msg);
	}

	public JSQLException(String msg, Exception excp) {
		toPrint = new String(msg + " :\n\nDescription : " + excp.getMessage());
	}

	public JSQLException(String msg, SQLException excp) {
		toPrint = new String(msg + " :\n\nDescription : " + excp.getMessage() + "\n   SQLState : " + excp.getSQLState()
				+ "\ncode erreur : " + excp.getErrorCode());
	}

	public String getMessage() {
		return toPrint;
	}
}
