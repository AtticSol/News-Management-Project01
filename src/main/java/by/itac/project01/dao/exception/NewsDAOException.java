package by.itac.project01.dao.exception;

public class NewsDAOException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public NewsDAOException() {
		super();
	}
	
	public NewsDAOException(Exception exception) {
		super(exception);
	}
	
	public NewsDAOException(String message) {
		super(message);
	}
	
	public NewsDAOException(String message, Exception exception) {
		super(message, exception);
	}
	

}
