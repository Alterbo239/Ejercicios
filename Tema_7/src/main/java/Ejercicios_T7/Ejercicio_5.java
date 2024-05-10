package Ejercicios_T7;
/**
 * Programa que calcula la media de los numeros introducidos en un fichero.
 * @author Alberto Zamora Landete
 * @version 1
 * @since 2024/04/29
 */
import java.util.*;
import java.io.*;

public class Ejercicio_5 {
    public static void main(String[] args) {
        Scanner esc = new Scanner(System.in);
        int centinela;
        
        do {
            System.out.println("Que queires hacer?");
            System.out.println("1) Escribir Numero. 2) Mostrar Fichero. 3) Mostrar Media. 4) Eliminar Numero. -1) Salir.");
            centinela = esc.nextInt();
            switch(centinela) {
                case 1:
                    int numero = esc.nextInt();
                    escribeFichero(numero);
                    break;
                case 2:
                    leerFichero("numeros.dat");
                    break;
                case 3:
                    System.out.println(mostrarMedia("numeros.dat"));
                    break;
                case 4:
                    int eliminar = esc.nextInt();
                    eliminar("numeros.dat", eliminar);
                    break;
                case -1:
                    break;
            }
        } while (centinela != -1);
    }
    /**
     * Funcion que escribe en el fichero.
     * @param num Numero a almacenar.
     */
    public static void escribeFichero(int num) {
        try {
            FileOutputStream fs = new FileOutputStream("numeros.dat", true);
            DataOutputStream d = new DataOutputStream(fs);
            d.writeInt(num);
            if (d != null) {
                d.close();
                fs.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Funcion que lee el fichero entero.
     * @param fichero Nombre del fichero a leer.
     */
    public static void leerFichero(String fichero) {
        try {
            File f = null;
            FileInputStream fe = null;
            DataInputStream d = null;
            try {
                f = new File (fichero);
                if (f.exists()) {
                    fe = new FileInputStream(f);
                    d = new DataInputStream(fe);
                    int num;
                    while (true) {
                        num = d.readInt();
                        System.out.println(num + " ");
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
    /**
     * Funcion que muestra la media de los numeros del fichero.
     * @param fichero Nombre del fichero.
     * @return Media de los numeros dentro del fichero.
     */
    public static float mostrarMedia(String fichero) {
        float media = 0;
        int contador = 0;
        try {
            File f = null;
            FileInputStream fe = null;
            DataInputStream d = null;
            try {
                f = new File (fichero);
                if (f.exists()) {
                    fe = new FileInputStream(f);
                    d = new DataInputStream(fe);
                    int num;
                    while (true) {
                        num = d.readInt();
                        media += num;
                        contador++;
                    }
                }
            } catch (EOFException eof) {
                System.out.print("");
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
        return media/contador;
    }
    
    public static void eliminar(String fichero, int num) {
        try {
            File f = null;
            FileInputStream fe = null;
            DataInputStream d = null;
            try {
                f = new File (fichero);
                if (f.exists()) {
                    fe = new FileInputStream(f);
                    d = new DataInputStream(fe);
                    int numero;
                    while (true) {
                        numero = d.readInt();
                        if (num != numero) {
                            try {
                            FileOutputStream fs = new FileOutputStream("numeros2.dat", false);
                            DataOutputStream da = new DataOutputStream(fs);
                            da.writeInt(numero);
                            if (da != null) {
                                da.close();
                                fs.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            
                            
                        }
                        }
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