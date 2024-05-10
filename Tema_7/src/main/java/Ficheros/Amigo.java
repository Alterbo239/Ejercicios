package Ficheros;
/**
 *
 * @author lliurex
 */
import java.io.*;

public class Amigo implements Serializable {
    protected String nombre;
    protected long tlf;
    
    public Amigo(String nom, long tlf) {
        this.nombre = nom;
        this.tlf = tlf;
    }
    
    public void print() {
        System.out.println(nombre + " -> " + tlf);
    }
}