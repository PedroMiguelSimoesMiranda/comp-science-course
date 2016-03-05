import java.rmi.Remote;
import java.rmi.RemoteException;

import security.PublicKey;


public interface ICertAuthority extends Remote {

	public PublicKey get_pubkey() throws RemoteException;
	
	public Certificate make_certificate(byte[] pubkey) throws RemoteException;
}