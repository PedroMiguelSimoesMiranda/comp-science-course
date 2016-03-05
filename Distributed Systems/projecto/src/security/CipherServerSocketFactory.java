package security;

import java.io.IOException;
import java.net.ServerSocket;
import java.rmi.server.RMIServerSocketFactory;

public class CipherServerSocketFactory implements RMIServerSocketFactory {

	public CipherServerSocketFactory() {
	}
	
	
	public ServerSocket createServerSocket(int port) throws IOException {
		return new CipherServerSocket(port);
	}
	
	public boolean equals( Object obj) {
		return true;
	}

}
