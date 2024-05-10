package Ejercicios_T7;

import java.io.*;
import java.util.*;

/**
 * Programa que lee cuantas palabras tiene un fichero.
 * @author Alberto Zamora Landete
 * @version 1
 * @since 29/04/2024
 */
public class Cuenta {
    public static void main(String[] args) {
        Scanner esc = new Scanner(System.in);
        String cadena = esc.nextLine();
        crearFichero(cadena);
        System.out.println("Esa sentencia tiene: " + cuentaPalabras("Palabras.txt") + " palabras.");
    }
    /**
     * Funcion que crea un fichero con el texto que introduces.
     * @param cadena Texto a guardar en el fichero.
     */
    public static void crearFichero(String cadena) {
        try {
            File f = new File("Palabras.txt");
            FileWriter fw = new FileWriter(f, false);
            char caracter = 0;
            for (int i = 0; i < cadena.length(); i++) {
                caracter = cadena.charAt(i);
                fw.write(caracter);
            }
            if (fw != null) {
                fw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Funcion que cuenta cuantas palabras tiene un fichero.
     * @param fichero Nombre del fichero a introducir.
     * @return Cantidad de palabras que tiene.
     */
    public static int cuentaPalabras(String fichero) {
        int palabras = 0;
        File f = new File("Palabras.txt");
        String cadena;
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            while ((cadena = br.readLine()) != null) {
                for (int i = 0; i < cadena.length(); i++) {
                    if (cadena.charAt(i) == ' ') {
                        palabras ++;
                    }
                }
                palabras ++;
            }
            if (fr != null) {
                fr.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return palabras;
    }
}