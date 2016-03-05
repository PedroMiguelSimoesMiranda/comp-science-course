package security;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.rmi.server.RMIClientSocketFactory;

public class CipherClientSocketFactory implements RMIClientSocketFactory, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Socket createSocket(String host, int port) throws IOException {
		return new CipherSocket(host, port);
	}
	
	public boolean equals( Object obj) {
		return true;
	}
}
