package security;

import sd1.security.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

public class CipherSocket extends Socket {

	private static final String CERT_PATH = "/certificados/user.cert";
	private static final String CA_PUBLIC_KEY_PATH = "/Keys/CA/Public.pbk";
	private static final String USER_PRIVATE_KEY_PATH = "/Keys/user/Private.prk";

	private OutputStream cos = null;
	private InputStream cis = null;

	public CipherSocket(String host, int port) throws UnknownHostException, IOException {
		super(host, port);
	}

	public CipherSocket() {
		super();
	}

	private void negotiateKeys() {
		try {
			
			PublicKey ca_pb = getCA_PublicKey();
			ObjectOutputStream oos = new ObjectOutputStream(super.getOutputStream());			
			ObjectInputStream ois = new ObjectInputStream(super.getInputStream());

			Certificate x = getCertificate(); // NOSSO CERTIFICADO

			oos.writeObject(x);
			oos.flush();

			Certificate otherCertificate = (Certificate) ois.readObject();
			PublicKey otherPublic = Certificate.getPublicKey(otherCertificate, ca_pb);
			PrivateKey myPrivate = getMyPrivateKey();

			// CONTINUA AQUI....

			// Envia nounce
			SecureRandom generator = new SecureRandom();
			byte[] myNouce1 = generator.randomBytes(10); // gera nounce
			byte[] msgNouce1 = otherPublic.encrypt(myNouce1); // cifra o nouce
			oos.writeShort(msgNouce1.length);
			oos.write(msgNouce1);
			oos.flush();

			// Recebe nounce
			int length = ois.readShort();
			byte[] oMsgNouce1 = new byte[length];
			ois.read(oMsgNouce1);
			byte[] otherNouce1 = myPrivate.decrypt(oMsgNouce1);

			// Cria chave simetrica
			SymetricKey inputKey = SymetricKey.createKey();
			byte[] inputSymetricKey = inputKey.exportKey();

			// Junta chave simetrica ao nouce recebido
			byte[] nouceAndSymetric = new byte[otherNouce1.length + inputSymetricKey.length];
			System.arraycopy(otherNouce1, 0, nouceAndSymetric, 0, otherNouce1.length);
			System.arraycopy(inputSymetricKey, 0, nouceAndSymetric, otherNouce1.length, inputSymetricKey.length);

			// Cifra com a chave publica do parceiro de comunicacao
			byte[] ciphNouceAndSymetric = otherPublic.encrypt(nouceAndSymetric);
			oos.writeObject(ciphNouceAndSymetric);
			oos.flush();

			// Leitura
			byte[] myNouceAndOutSymetric = (byte[]) ois.readObject();
			byte[] recNouceAndOutSmyteric = myPrivate.decrypt(myNouceAndOutSymetric);
			byte[] myNouce = new byte[myNouce1.length];
			int symLen = recNouceAndOutSmyteric.length - myNouce1.length;
			byte[] outSymetricKey = new byte[symLen];
			System.arraycopy(recNouceAndOutSmyteric, 0, myNouce, 0, myNouce1.length);
			System.arraycopy(recNouceAndOutSmyteric, myNouce1.length, outSymetricKey, 0, symLen );

			SymetricKey outPutKey = null;

			if ( Arrays.equals(myNouce, myNouce1) )
			{
				outPutKey = SymetricKey.createKey(outSymetricKey);

				this.cis = new CipherInputStream(ois, inputKey);
				this.cos = new CipherOutputStream(oos, outPutKey);
			}
			else 
				this.close();

		} 
		catch (IOException e){ e.printStackTrace();} 
		catch (ClassNotFoundException e) { e.printStackTrace();} 
		catch (Exception e) { e.printStackTrace();}


	}


	private static PrivateKey getMyPrivateKey() {
		try 
		{			
			File fca = new File(System.getProperty("user.dir") + USER_PRIVATE_KEY_PATH);
			InputStream isca = new FileInputStream(fca);

			byte[] myPrivate = new byte [(int)fca.length()];
			isca.read(myPrivate);
			return PrivateKey.createKey(myPrivate);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public synchronized OutputStream getOutputStream() {
		if( cos == null)
			this.negotiateKeys();
		return cos;
	}



	public synchronized InputStream getInputStream() {
		if( cis == null)
			this.negotiateKeys();
		return cis;
	}

	private static PublicKey getCA_PublicKey(){
		try 
		{			
			File fca = new File(System.getProperty("user.dir") + CA_PUBLIC_KEY_PATH);
			InputStream isca = new FileInputStream(fca);

			byte[] publicKeyCA = new byte [(int)fca.length()];
			isca.read(publicKeyCA);
			return PublicKey.createKey(publicKeyCA);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	private static Certificate getCertificate() {
		try
		{
			ObjectInputStream file = new ObjectInputStream(
					new FileInputStream(System.getProperty("user.dir")+ CERT_PATH)); 
			Certificate x = (Certificate) file.readObject();
			file.close();
			return x;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
