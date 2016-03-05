import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import security.KeyPair;
import security.PrivateKey;
import security.PublicKey;


public class CertAuthority implements ICertAuthority {

	private KeyPair keys;
	
	public CertAuthority() throws IOException, ClassNotFoundException {
		try {
			ObjectInputStream file = new ObjectInputStream(new FileInputStream("files/keys/certpub"));
			PublicKey pub = (PublicKey) file.readObject();
			file = new ObjectInputStream(new FileInputStream("files/keys/certprv"));
			PrivateKey prv = (PrivateKey) file.readObject();
			keys = new KeyPair(pub, prv);
			file.close();
		} catch(FileNotFoundException e) {
			keys = KeyPair.createKeyPair();
			ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream("files/keys/certpub"));
			file.writeObject(keys.getPublic());
			file.flush();
			file = new ObjectOutputStream(new FileOutputStream("files/keys/certprv"));
			file.writeObject(keys.getPrivate());
			file.flush();
			file.close();
		}
	}
	
	public PublicKey get_pubkey() throws RemoteException {
		return keys.getPublic();
	}

	public Certificate make_certificate(byte[] pubkey) throws RemoteException {
		return new Certificate(pubkey, keys.getPrivate());
	}
	
	public static void main(String[] args) {
		try { 
			CertAuthority verizign = new CertAuthority();
			ICertAuthority server = (ICertAuthority) UnicastRemoteObject.exportObject(verizign,0);
			Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);  
			registry.bind("Verizign", server ); 
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		System.out.println("Verizign bound in registry");
	}

}
