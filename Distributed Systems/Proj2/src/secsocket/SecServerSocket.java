package secsocket ;

import java.io.*;
import java.net.*;

import security.*;

class SecServerSocket extends ServerSocket {
  
	private Key cipher = null;
	private PublicKey pubclient = null;
	
    public SecServerSocket(int port) throws IOException {
        super(port);
    }
    
    public SecServerSocket(int port, Key cipher) throws IOException {
        super(port);
        this.cipher = cipher;
    }

    public SecServerSocket(int port, Key k, PublicKey c) throws IOException {
        super(port);
        cipher = k;
        pubclient = c;
    }
    
    public static boolean equals(byte[] arr1, byte[] arr2) {
    	if(arr1.length != arr2.length)
    		return false;
    	
    	for(int i = 0; i < arr1.length; i++) 
    		if(arr1[i] != arr2[i])
    			return false;
    	
    	return true;
    }
    
    public Socket accept() throws IOException {
        Socket s = new SecSocket(null, pubclient);
        super.implAccept(s);
        return s;
    }
}