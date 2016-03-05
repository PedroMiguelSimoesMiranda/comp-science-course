package secsocket ;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

import security.Key;
import security.PrivateKey;
import security.PublicKey;
import security.SecureRandom;
import security.SymetricKey;

class SecSocket extends Socket {

	private Key cipher = null;
	
	private PublicKey your_pub;
	private PrivateKey mine_prv;
	
	private boolean isServer;
	
	private InputStream in = null;
	private OutputStream out = null;

	public SecSocket(Key k, PublicKey clientkey) throws IOException {
		super();
		cipher = k;
		your_pub = clientkey;
		isServer = true;
	}
	public SecSocket() throws IOException {
		super();
		this.cipher = null;
	}
	
	public SecSocket(String host, int port, Key cipher) throws IOException {
		super(host, port);
		this.cipher = cipher;
	}

	public SecSocket(String host, int port) throws IOException {
		super(host, port);
		this.cipher = null;
		isServer = false;
	}

	public void setKey( Key cipher ) throws IOException {
		if (in == null && out == null )
			this.cipher = cipher;
		else throw new IOException("setKey: can't change cipher key, streams already open.");
	}

    public synchronized InputStream getInputStream() throws IOException {
        if (in == null) {
        	if (cipher== null) {
        		read_keys();
        		if(isServer)
        			server_negotiate_key();
        		else
        			client_negotiate_key();
        	}

            in = new SecInputStream(super.getInputStream(), cipher);
        }
        return in;
    }
  
    public synchronized OutputStream getOutputStream() throws IOException {
        if (out == null) {
        	if (cipher== null) {
        		read_keys();
        		if(isServer)
        			server_negotiate_key();
        		else
        			client_negotiate_key();
        	}

            out = new SecOutputStream(super.getOutputStream(), cipher);
        }
        return out;
    }
    
    public void client_negotiate_key() throws IOException {
    	byte[] offer = new byte[128];
    	super.getInputStream().read(offer, 0, 128);
    	// decipher with client's private key
    	byte[] rd = mine_prv.decrypt(offer);

    	// cipher with server's public key
    	super.getOutputStream().write(your_pub.encrypt(rd));
    	
    	byte[] reply = new byte[128];
    	super.getInputStream().read(reply, 0, 128);
    	byte[] symkey = mine_prv.decrypt(reply);
    	
    	try {
			cipher = SymetricKey.createKey(symkey);
		} catch (Exception e) {}
    }
    
    public void server_negotiate_key() throws IOException {
    	SecureRandom rand = new SecureRandom();
    	byte[] rb = rand.randomBytes(16);
    	// cipher with client's public key
    	super.getOutputStream().write(your_pub.encrypt(rb));
    	
    	
    	byte[] reply = new byte[128];
    	super.getInputStream().read(reply,0,128);
    	// decipher with server's private key
    	byte[] rdm_reply = mine_prv.decrypt(reply);
    	// agree
    	if(SecServerSocket.equals(rb, rdm_reply)) {
    		SymetricKey sk = null;
    		try {
				sk = SymetricKey.createKey();
			} catch (Exception e) {}
			
			super.getOutputStream().write(your_pub.encrypt(sk.exportKey()));
			cipher = sk;
    	} else {
    		super.close();
    		return;
    	}
    }
    
    public void read_keys() {
    	if(isServer) {
    		// read server's private key
    		try {
    			ObjectInputStream file = new ObjectInputStream(new FileInputStream("files/keys/sprv") );
    			mine_prv =  (PrivateKey) file.readObject();
    			file.close();
    		} catch (Exception e) {}
    	}
    	else {
    		// read client's private key
    		// read server's public key
    		try {
    			ObjectInputStream file = new ObjectInputStream(new FileInputStream("files/keys/cprv") );
    			mine_prv =   (PrivateKey) file.readObject();
    			file.close();
    		
    			file = new ObjectInputStream(new FileInputStream("files/keys/spub"));
    			your_pub =  (PublicKey) file.readObject();
    			file.close();
    		} catch (Exception e) {}
    	}
    }
    
}
