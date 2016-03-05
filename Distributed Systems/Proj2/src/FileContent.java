
import java.io.File;
import java.io.Serializable;
import java.rmi.RemoteException;


public class FileContent implements Serializable {

	private static final long serialVersionUID = 1L;

	private File file;
	private byte[] data;
	
	public FileContent(File f, byte[] d) throws RemoteException {
		file = f;
		data = d;
	}
	
	public File file() {
		return file;
	}
	
	public byte[] data() {
		return data;
	}
}
