package secsocket;

import java.io.*;
import java.net.*;
import java.rmi.server.*;

import security.Key;

public class SecClientSocketFactory
		implements RMIClientSocketFactory, Serializable
{

	private static final long serialVersionUID = 1L;
	

	public SecClientSocketFactory() {
	}

	public Socket createSocket( String host, int port) throws IOException {
		return new SecSocket( host, port);
	}

	public boolean equals( Object obj) {
		return (getClass() == obj.getClass());
	}

}
