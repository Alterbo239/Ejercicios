package Ficheros;

import java.io.*;

/**
 *
 * @author lliurex
 */
public class FicherosBinario {
    public static void main(String[] args) {
        String amigos[] = {"Juan", "Pepe", "Pepa"};
        long tlf[] = {123, 456, 789};
        
        try {
            FileOutputStream fs = new FileOutputStream("amigos.dat", true);
            DataOutputStream d = new DataOutputStream(fs);
            for (int i = 0; i < amigos.length; i++) {
                d.writeUTF(amigos[i]);
                d.writeLong(tlf[i]);
            }
            if (d != null) {
                d.close();
                fs.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            File f = null;
            FileInputStream fe = null;
            DataInputStream d = null;
            try {
                f = new File ("amigos.dat");
                if (f.exists()) {
                    fe = new FileInputStream(f);
                    d = new DataInputStream(fe);
                    String s;
                    long l;
                    while (true) {
                        s = d.readUTF();
                        l = d.readLong();
                        System.out.println(s + " -> " + l);
                    }
                }
            } catch (EOFException eof) {
                System.out.println("-----");
            } catch (FileNotFoundException fnf) {
                System.out.println("fichero no encontrado.");
            } catch (Throwable t) {
                System.out.println("Error del programa " + t);
            } finally {
                if (d != null) {
                    d.close();
                    fe.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}