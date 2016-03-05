package security;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import sd1.security.*;

public class Certificate implements Serializable {

	private static final long serialVersionUID = 1L;
	private static String CERTIFICATEDIR = "/certificados";

	public byte[] name;

	public byte[] publicKey;

	public byte[] digest;

	public Certificate(PrivateKey certAutPrivateKey, String entName, PublicKey entPublicKey){
		
		byte[] entBytes = entName.getBytes();
		byte[] entPK = entPublicKey.exportKey();

		this.name = certAutPrivateKey.encrypt(entBytes);
		
		publicKey = new byte[0];
		byte[] block;
		
		int limit = 0, oldlen = 0;
		for(int i = 0; i < entPK.length; i += 117) {
			limit = Math.min(i + 117, entPK.length); 
			block = certAutPrivateKey.encrypt(entPK, i, limit-i);
			oldlen = publicKey.length;
			publicKey = Arrays.copyOf(publicKey, publicKey.length + block.length);
			System.arraycopy(block, 0, publicKey, oldlen, block.length);
		}
		
		byte[] toDig = new byte[entBytes.length + entPK.length];
		
		System.arraycopy(entBytes, 0, toDig, 0, entBytes.length);
		System.arraycopy(entPK, 0, toDig, entBytes.length, entPK.length);
		
		try 
		{
			this.digest = Digest.getDigest(toDig);
		} 
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void exportCert(Certificate x, String name) {
		try {
			(new File( System.getProperty("user.dir") + CERTIFICATEDIR)).mkdirs();
			File f = new File( System.getProperty("user.dir") + CERTIFICATEDIR + "/" + name + ".cert");
			
			ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(f));
			file.writeObject(x);
			file.flush(); 
			file.close(); 
		} 
		catch (IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	public static PublicKey getPublicKey(Certificate x, PublicKey ca) throws Exception{
		
		byte[] name = ca.decrypt(x.name);
		byte[] pk = new byte[0];
		byte[] block;
		byte[] dig;
		
		int limit = 0, oldlen = 0;
		for(int i = 0; i < x.publicKey.length; i += 128) {
			limit = Math.min(i + 128, x.publicKey.length); 
			block = ca.decrypt(x.publicKey, i, limit-i);
			oldlen = pk.length;
			pk = Arrays.copyOf(pk, pk.length + block.length);
			System.arraycopy(block, 0, pk, oldlen, block.length);
		}
		
		byte[] todig = new byte[name.length + pk.length];
		
		System.arraycopy(name, 0, todig, 0, name.length);
		System.arraycopy(pk, 0, todig, name.length, pk.length);
		
		dig = Digest.getDigest(todig);
		
		if (Arrays.equals(x.digest, dig))
			return PublicKey.createKey(pk);
		else
			throw new Exception();	// CERTIFICADO VIOLADO
	}
}
