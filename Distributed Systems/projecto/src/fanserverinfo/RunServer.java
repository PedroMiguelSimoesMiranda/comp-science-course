package fanserverinfo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import javax.xml.ws.Endpoint;

import security.CipherClientSocketFactory;
import security.CipherServerSocketFactory;

public abstract class RunServer {

	private static String THIS_SERVER_IP = "localhost" ;
	private static FanServerRemote server = null;
	private static FanServerWS serverws = null;
	private static FanServerClass fanserver = null;

	private static void launchServer(){
		try 
		{
			System.getProperties().put( "java.security.policy", "policy.all" );

			if( System.getSecurityManager() == null)
				System.setSecurityManager( new RMISecurityManager() );

			try { // start rmiregistry
				LocateRegistry.createRegistry( 1099 );
			} catch( RemoteException e ) {}

			readFromFile();

			server = new FanServerRMI( fanserver );
			serverws = new FanServerWS( fanserver );

			RMIClientSocketFactory csf = new CipherClientSocketFactory();
			RMIServerSocketFactory ssf = new CipherServerSocketFactory();

			FanServerRemote exportServer = (FanServerRemote) UnicastRemoteObject.exportObject(server, 0, csf, ssf);
			Naming.rebind( FanServerRMI.SERVICENAME, exportServer );

			System.out.println( "FanZonServer bound in registry" );

			Endpoint.publish("http://" + THIS_SERVER_IP + ":7070" + FanServerWS.SERVICENAME, serverws);
		}

		catch( Throwable th ) { th.printStackTrace();}
	}

	private static void readFromFile() throws Exception, ClassNotFoundException{
		try {
			ObjectInputStream file = new ObjectInputStream(new FileInputStream(FanServerClass.FILENAME)); 
			fanserver = (FanServerClass) file.readObject();
			file.close();
		}
		catch ( ClassNotFoundException e ) { 
			System.out.println(e.getMessage());
		}
		catch ( IOException e ){
			fanserver = new FanServerClass();
		} 
	}


	public static void writeInFile(){
		try 
		{
			ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(FanServerClass.FILENAME));
			file.writeObject(fanserver);
			file.flush(); 
			file.close(); 
		} 
		catch (IOException e){
			System.out.println(e.getMessage());
		}
	}

	public static void main( String args[] ) throws Exception {

		if( args.length!= 1)
		{
			System.err.println("Usage: RunServer ip");
			System.exit(0);
		}
		THIS_SERVER_IP = args[0];
		launchServer();
	}
}
