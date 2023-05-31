package be.cpasdeliege.intranet.informatique.model.domain;

public class DomainException extends RuntimeException {

	static final long serialVersionUID = 1;

	public DomainException() {
		super();
	}

	public DomainException(String message) {
		super(message);
	}

	public DomainException(String message, Throwable cause) {
		super(message, cause);
	}

	public DomainException(Throwable cause) {
		super(cause);
	}
}
