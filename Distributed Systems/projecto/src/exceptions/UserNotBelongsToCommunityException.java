package exceptions;

import javax.jws.WebService;

@WebService
public class UserNotBelongsToCommunityException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	
	public UserNotBelongsToCommunityException(){
		super();
	}
	
	
	public UserNotBelongsToCommunityException( String msg ){
		super( msg );
	}

}
