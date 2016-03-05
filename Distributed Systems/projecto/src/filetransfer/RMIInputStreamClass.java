package filetransfer;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIInputStreamClass implements RMIInputStream {
	
    private InputStream in;
    private byte[] readByte;
    
    public RMIInputStreamClass(InputStream in) throws IOException {
        this.in = in;
        UnicastRemoteObject.exportObject(this, 1099);
    }
    
    
    public byte[] readBytes(int length) throws IOException, 
            RemoteException {
        if (readByte == null || readByte.length != length)
            readByte = new byte[length];
            
        int len2 = in.read(readByte);
        if (len2 <= 0)
            return null; 
        
        if (len2 != length) {
            byte[] b2 = new byte[len2];
            System.arraycopy(readByte, 0, b2, 0, len2);
            return b2;
        }
        else
            return readByte;
    }
    
    
    public int read() throws IOException, RemoteException {
        return in.read();
    }
    
    public void close() throws IOException, RemoteException {
        in.close();
    }
}