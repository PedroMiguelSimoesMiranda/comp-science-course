package security;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import sd1.security.PrivateKey;
import sd1.security.PublicKey;

public class CertificateGen {
	
	public static Certificate generateCertificate(String pathCAPrivK, String entName, String pathEntPubK) throws Exception{
		
		File fca = new File(pathCAPrivK);
		InputStream isca = new FileInputStream(fca);
		
		byte[] privKeyCA = new byte [(int)fca.length()];
		isca.read(privKeyCA);
		
		File fen = new File(pathEntPubK);
		InputStream isen = new FileInputStream(fen);
		byte[] pubKeyEn = new byte [(int)fen.length()];
		isen.read(pubKeyEn);
		
		Certificate cert = new Certificate(PrivateKey.createKey(privKeyCA), entName, PublicKey.createKey(pubKeyEn));
		
		Certificate.exportCert(cert, entName);
		
		return cert;
	}
	
	public static void main(String[] args) throws Exception{
		if (args.length != 3)
		{
			System.err.println("Usage: CertificateGen path_to_CA_privateKey entity path_to_entity_publicKey");
			System.exit(0);
		}
		generateCertificate(args[0], args[1], args[2]);
	}
	

}
