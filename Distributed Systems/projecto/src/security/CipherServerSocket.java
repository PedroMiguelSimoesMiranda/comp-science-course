package security;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CipherServerSocket extends ServerSocket {

	public CipherServerSocket(int port) throws IOException {
		super(port);
	}
	
	public Socket accept() throws IOException {
		Socket s = new CipherSocket();
		super.implAccept(s);
        return s;
	}


}
