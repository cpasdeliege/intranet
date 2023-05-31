package be.cpasdeliege.intranet.formations.model.dao;

public class DaoException extends RuntimeException {

	static final long serialVersionUID = 1;

	public DaoException() {
		super();
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}

}