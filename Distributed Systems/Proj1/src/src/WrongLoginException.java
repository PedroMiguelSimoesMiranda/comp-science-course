
public class WrongLoginException extends Exception {

	private static final long serialVersionUID = 1L;

	public WrongLoginException() {
		super("Wrong Login");
	}

	public WrongLoginException(String message) {
		super(message);
	}
}

