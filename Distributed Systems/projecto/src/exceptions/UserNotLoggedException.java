package exceptions;

import javax.jws.WebService;

@WebService
public class UserNotLoggedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UserNotLoggedException(){
		super();
	}

	public UserNotLoggedException( String msg ){
		super( msg );
	}
}
