package secsocket;

import java.io.*;
import java.net.*;
import java.rmi.server.*;

import security.*;

public class SecServerSocketFactory
		implements RMIServerSocketFactory
{
	private Key cipher = null;
	private PublicKey cpubk = null;
	
	public SecServerSocketFactory() {
	}

	public SecServerSocketFactory(Key cipher) {
		this.cipher = cipher;
	}
	
	public SecServerSocketFactory(Key c, PublicKey ck) {
		cipher = c;
		cpubk = ck;
	}
	
	public ServerSocket createServerSocket(int port) throws IOException {
		if(cipher == null)
			return new SecServerSocket(port);
		else 
			return new SecServerSocket(port, cipher, cpubk);
	}

	public boolean equals(Object obj) {
	    return (getClass() == obj.getClass() 
			&&  cipher == ((SecServerSocketFactory)obj).cipher);
	}

}
