package Ficheros;

import java.io.*;
/**
 * Programa que crea un fichero y lo lee.
 * @author Alberto Zamora Landete.
 * @version 2
 * @since 2024/04/23
 */
public class ProbandoFicheros {
    public static void main(String[] args) {
        FileOutputStream f = null;
        String cadena = "Mi gato se llama Oliver, el de mi hermana Boo.";
        escribe(cadena, "cadena.dat");
        System.out.println(lee("cadena.dat"));
    }
    /**
     * Funcion que crea un fichero.
     * @param cadena Texto que vamos a guardar en el fichero.
     * @param fichero Nombre del fichero.
     */
    public static void escribe(String cadena, String fichero) {
        FileOutputStream f = null; //Instancia nulo.
        char caracter = 0;
        try {
            f = new FileOutputStream(fichero); //Fichero a crear.
            for (int i = caracter; i < cadena.length(); i++) {
                caracter = cadena.charAt(i); //Texto a guardar (Caracter a caracter).
                f.write((byte) caracter); //Introduce el texto en el fichero.
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (f != null) {
                    f.close();
                    System.out.println("Fichero creado con exito."); //Si se ha creado.
                } else {
                    System.out.println("No se pudo crear el fichero, sentimos las molestias."); //Si no se ha creado.
                }
            } catch (IOException e) {
                    e.printStackTrace();
            }
        }
    }
    /**
     * Programa que lee un fichero.
     * @param fichero Nombre del fichero.
     * @return Cadena guardada en el fichero.
     */
    public static String lee(String fichero) {
        FileInputStream f = null; //Instancia nulo.
        String cadena = "";
        char caracter;
        try {
            f = new FileInputStream(fichero); //Fichero a leer.
            int size = f.available(); //Tamanyo del fichero.
            for (int i = 0; i < size; i++) {
                caracter = (char) f.read(); //Caracter a leer (uno a uno).
                cadena += caracter; //Guardamos en la cadena el caracter leido.
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (f  != null) {
                    f.close();
                    System.out.println("Fichero leido con exito :D"); //Si se ha leido.
                } else {
                    System.out.println("Fichero no leido con exito UnU"); //Si no se ha leido.
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return cadena;
    }
}