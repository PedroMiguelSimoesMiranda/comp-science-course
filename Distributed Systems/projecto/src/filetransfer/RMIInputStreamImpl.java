package filetransfer;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

public class RMIInputStreamImpl extends InputStream implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	RMIInputStream in;
    
    public RMIInputStreamImpl(RMIInputStream in) {
        this.in = in;
    }
    
    public int read() throws IOException {
        return in.read();
    }
    public int read(byte[] b, int off, int length) throws IOException {
        byte[] b2 = in.readBytes(length);
        if (b2 == null) return -1;
        int i = b2.length;
        System.arraycopy(b2, 0, b, off, i);
        return i;
    }
    
    public void close() throws IOException {
        super.close();
    }
    
}