package Ficheros;
/**
 *
 * @author lliurex
 */
import java.io.*;

public class MiObjectOutputStream extends ObjectOutputStream {
    MiObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }
    MiObjectOutputStream() throws IOException, SecurityException {
        super();
    }
    
    protected void writeStreamHeader() throws IOException {
        
    }
}
