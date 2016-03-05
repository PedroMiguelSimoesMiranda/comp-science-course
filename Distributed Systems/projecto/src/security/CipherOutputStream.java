package security;
import java.io.* ;
import java.nio.* ;
import sd1.security.* ;

public class CipherOutputStream extends OutputStream {
    
    private Key cipher ;
    private DataOutputStream dos ;
    private ByteBuffer block = ByteBuffer.allocate( 992 ) ;
    
    public CipherOutputStream( OutputStream os, Key cipher) {
        super() ;
        this.cipher = cipher ;
        this.dos = new DataOutputStream( os ) ;
    }
    
    public void write( int b ) throws IOException {
        block.put( (byte)b ) ;
        if( block.remaining() == 0 ) flush() ;
    }
    
    public void flush() throws IOException {
        if( block.position() > 0 ) {
            byte[] data = cipher.encrypt( block.array(), 0, block.position() ) ;
            dos.writeShort( data.length ) ;
            dos.write( data ) ;
            dos.flush() ;
            block.rewind() ;
        }
    }
    
    public void close() throws IOException {
        this.flush() ;
        dos.close() ;
    }
}
