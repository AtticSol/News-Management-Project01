package by.itac.project01.service.exception;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
	}

	public ServiceException(String e) {
		super(e);
	}

	public ServiceException(Exception e) {
		super(e);
	}

	public ServiceException(String e, Exception ex) {
		super(e, ex);
	}
}
