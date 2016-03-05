import java.io.File;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IServer extends Remote{

	public UserInfo add_user(String name, String pass) throws RemoteException, UserAlreadyExistsException;
	
	public void rem_user(String name) throws RemoteException, UserNotFoundException;
	
	public UserInfo login(String username, String hashpass) throws RemoteException, WrongLoginException ;
	
	public File sync(String user, File newf) throws RemoteException, IOException;
	
	public File get_files(String path) throws RemoteException;
}
