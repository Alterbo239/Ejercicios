package Ficheros;
/**
 *
 * @author Alberto Zamora Landete
 */
import java.io.*;

public class FicheroObjeto {
    public static void main(String[] args) {
        String amigos[] = {"Maria", "Juan", "Luis"};
        long tlf[] = {123, 456, 789};
        //Escribir fichero.
        try {
            FileOutputStream fs = new FileOutputStream("amigos.txt");
            MiObjectOutputStream oos = new MiObjectOutputStream(fs);
            
            for (int i = 0; i < amigos.length; i++) {
                Amigo a = new Amigo(amigos[i], tlf[i]);
                oos.writeObject(a);
            }
            if (oos != null) {
                oos.close();
                fs.close();
            }
        } catch (IOException i) {
            i.printStackTrace();
        }
        //Leer fichero.
        try {
            File f = null;
            FileInputStream fe = null;
            ObjectInputStream ois = null;
            try {
                f = new File("amigos.txt");
                if (f.exists()) {
                    fe = new FileInputStream(f);
                    ois = new ObjectInputStream(fe);
                    
                    while(true) {
                        Amigo a = null;
                        a = (Amigo) ois.readObject();
                        a.print();
                    }
                }
            } catch (EOFException end) {
                System.out.println("----------");
            } catch (FileNotFoundException fnf) {
                System.out.println("Fichero no encontrado " + fnf);
            } catch (Throwable t) {
                System.out.println("Error de programa " + t);
                t.printStackTrace();
            } finally {
                if (ois != null) {
                    ois.close();
                    fe.close();
                }
            }
        } catch (IOException i) {
            i.printStackTrace();
        }
    } //FinMain.
}
