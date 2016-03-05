
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import security.PublicKey;


public interface IServer extends Remote {

	public UserInfo add_user(String name, String pass) throws RemoteException, UserAlreadyExistsException;
	
	public void rem_user(String name) throws RemoteException, UserNotFoundException;
	
	public UserInfo login(String username, String hashpass) throws RemoteException, WrongLoginException, UserNotFoundException, BannedException ;
	
	public void report(String reported, String user) throws RemoteException, IOException;
	
	public String[] server_list(String username) throws RemoteException;
	
	public long last_update(String user, String filename) throws RemoteException;
	
	public void upload(String user, FileContent fc, long lastmod) throws RemoteException, IOException;
	
	public FileContent download(String user, String filename) throws RemoteException, IOException;
	
	public void give_read(String to_user, UserInfo from, String file) throws RemoteException, IOException, UserNotFoundException;

	public void give_write(String to_user, UserInfo from, String file) throws RemoteException, IOException, UserNotFoundException;
	
	public void take_read(String to_user, String file) throws RemoteException, IOException, UserNotFoundException;
	
	public void take_write(String to_user, String file) throws RemoteException, IOException, UserNotFoundException;

	public Certificate get_cert() throws RemoteException;

	public byte[] get_pubkey() throws RemoteException, IOException;
}
