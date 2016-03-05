package filetransfer;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInputStream extends Remote {

	
	public int read() throws IOException, RemoteException;
	
	public byte[] readBytes(int len) throws IOException, RemoteException;
	
	public void close() throws IOException, RemoteException;
}