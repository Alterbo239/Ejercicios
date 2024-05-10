package com.mycompany.tema_7;
/**
 * @author lliurex
 */
import java.util.*;

public class Tema_7 {
    public static void main(String[] args) {
        Scanner esc = new Scanner(System.in);
        System.out.println("N1");
        int num = esc.nextInt();
        System.out.println("N2");
        int num2 = esc.nextInt();
        
        try { //Intenta hacer el metodo.
            System.out.println(dividir(num, num2));
        } catch (Exception e) { //Si hay algun tipo de error, se muestra.
            System.out.println(e.toString());
        } finally { //Se muestra haya o no una excepcion.
            System.out.println("Gracias por jugar!");
        }
    }
    /**
     * Metodo para dividir.
     * @param num Dividendo.
     * @param num2 Divisor.
     * @return Resultado.
     * @throws Exception dividir entre 0.
     */
    public static int dividir(int num, int num2) throws Exception {
        if (num2 == 0) {
            throw new Exception("No se puede dividir entre '0'."); //Si el divisor es "0", manda la precaucion del error.
        }
        return num/num2;
    }
}
