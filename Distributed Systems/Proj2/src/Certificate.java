import java.io.Serializable;
import java.util.Arrays;

import security.PrivateKey;
import security.PublicKey;


public class Certificate implements Serializable {

	private static final long serialVersionUID = 1L;

	private byte[] cert;

	public Certificate(byte[] pubkey, PrivateKey cipher) {
		cert = new byte[0];
		byte[] block;
		int limit = 0, oldlen = 0;
		for(int i = 0; i < pubkey.length; i += 117) {
			limit = Math.min(i + 117, pubkey.length); 
			block = cipher.encrypt(pubkey, i, limit-i);
			oldlen = cert.length;
			cert = Arrays.copyOf(cert, cert.length + block.length);
			System.arraycopy(block, 0, cert, oldlen, block.length);
		}
	}

	public boolean check(byte[] spub, PublicKey cipher) {
		byte[] pubkey = new byte[0];
		byte[] block;
		int limit = 0, oldlen = 0;
		for(int i = 0; i < cert.length; i += 128) {
			limit = Math.min(i + 128, cert.length);
			block = cipher.decrypt(cert, i, limit-i);
			oldlen = pubkey.length;
			pubkey = Arrays.copyOf(pubkey, pubkey.length + block.length);
			System.arraycopy(block, 0, pubkey, oldlen, block.length);
		}
		return equals(pubkey, spub);
	}
	
	private boolean equals(byte[] a, byte[] b) {
		if(a.length != b.length)
			return false;
		for(int i = 0; i < a.length; i++)
			if(a[i] != b[i])
				return false;
		return true;
	}
}
