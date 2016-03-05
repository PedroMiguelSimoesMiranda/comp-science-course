

public class BannedException extends Exception {

	private static final long serialVersionUID = 1L;

	public BannedException() {
		super("User Banned");
	}

	public BannedException(String message) {
		super(message);
	}
}
