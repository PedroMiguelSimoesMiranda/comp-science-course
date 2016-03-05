package security;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import sd1.security.KeyPair;

public class Keygen {
	
	public static void createKeyPair(String entity) throws IOException {
		
		new File(System.getProperty("user.dir") + "/Keys/"+entity ).mkdirs();
		
		FileOutputStream ospub=new FileOutputStream(new File(System.getProperty("user.dir")+"/Keys/"+entity+"/Public.pbk"));
		FileOutputStream ospriv=new FileOutputStream(new File(System.getProperty("user.dir")+"/Keys/"+entity+"/Private.prk"));
		
		KeyPair kp= sd1.security.KeyPair.createKeyPair();
		
		ospub.write(kp.getPublic().exportKey());
		ospriv.write(kp.getPrivate().exportKey());
		ospub.close();
		ospriv.close();
	}	
	
	public static void main(String[] args) throws IOException{
		if (args.length != 1)
		{
			System.err.println("Usage: Keygen entity");
			System.exit(0);
		}
		
		Keygen.createKeyPair(args[0]);		
	}
}
