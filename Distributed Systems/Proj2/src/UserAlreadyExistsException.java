

public class UserAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserAlreadyExistsException() {
		super("Wrong Login");
	}

	public UserAlreadyExistsException(String message) {
		super(message);
	}
}
