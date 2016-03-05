package filetransfer;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

import security.CipherClientSocketFactory;
import security.CipherServerSocketFactory;

public class FileServerClass  extends UnicastRemoteObject implements FileServer {

	public static String SERVICENAME = "FileServer";
	private static final long serialVersionUID = 1L;

	public FileServerClass() throws RemoteException {
		//super();
	}

	public InputStream getInputStream(String filepath) throws RemoteException, IOException {
		System.err.println("getInputStream " + System.getProperty("user.dir") + filepath);
		return new RMIInputStreamImpl(new RMIInputStreamClass(new
				FileInputStream( new File(System.getProperty("user.dir") + filepath))));
	}
	
	public void start() throws Exception {
		try 
		{ // start rmiregistry
			System.getProperties().put( "java.security.policy", "policy.all" );
			if (System.getSecurityManager() == null)
				System.setSecurityManager( new RMISecurityManager());
			
			try { LocateRegistry.createRegistry(1099); }
			catch (RemoteException e) {}
			
			RMIClientSocketFactory csf = new CipherClientSocketFactory();
	        RMIServerSocketFactory ssf = new CipherServerSocketFactory();
	        
	        UnicastRemoteObject.unexportObject(this,true);
	        FileServer fileserver = (FileServer) UnicastRemoteObject.exportObject(this, 0, csf, ssf);
			Naming.rebind( SERVICENAME, fileserver);
		}
		catch (Exception e){e.printStackTrace();}
		catch (Throwable th){th.printStackTrace();}
		System.err.println("Servidor de ficheiros lancado");
	}


}