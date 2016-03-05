import java.io.File;
import java.io.Serializable;
import java.rmi.RemoteException;

public class Repository implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final String owner;
	private final String path;
		
	public Repository(String o) throws RemoteException {
		owner = o;
		path = o + "/" + o + "'s repo/";
	}
	
	public String owner() { return owner; }
	
	public String path() { return path; }
}
