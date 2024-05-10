package Ficheros;

import java.io.*;

/**
 *
 * @author Alberto Zamora Landete.
 */
public class Escribe {
    public static void main(String[] args) {
        String amics[] = {"Andrea", "Kiara", "Pablo", "Sergio", "Elena"};
        escribirFichero(amics, "amigos.txt");
        leerFichero("amigos.txt");
    }
    
    public static void escribirFichero(String amics[], String fichero) {
        try {
            File fs = new File("..'\'" + fichero);
            FileWriter fw = new FileWriter(fs, false); //True para que anyada al fichero en lugar de reemplazar.
            int i = 0;
            for (String s: amics) {
                s = i + " " + s;
                fw.write(s, 0, s.length());
                fw.write("\r\n");
                i++;
            }
            if (fw != null) {
                fw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void leerFichero(String fichero) {
        File fe = new File("..'\'" + fichero);
        if (fe.exists()) {
            try {
                FileReader fr = new FileReader(fe);
                BufferedReader br = new BufferedReader(fr);
                String cadena;
                while ((cadena = br.readLine()) != null) {
                    System.out.println(cadena);
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}