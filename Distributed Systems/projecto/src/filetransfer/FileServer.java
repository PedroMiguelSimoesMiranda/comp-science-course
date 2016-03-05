package filetransfer;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface FileServer extends Remote{	
	
	void start() throws Exception;
	InputStream getInputStream(String src) throws RemoteException, IOException;
	
}
