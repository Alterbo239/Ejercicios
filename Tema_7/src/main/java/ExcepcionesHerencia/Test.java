package ExcepcionesHerencia;
/**
 *
 * @author lliurex
 */
import java.util.*;

public class Test {
    public static void main(String[] args) {
        Scanner esc = new Scanner(System.in);
        Sensor s = new Sensor();
        GestorSensor gs = new GestorSensor(s);
        double dato;
        
        try {
            dato = esc.nextDouble();
            gs.enviarDato(dato);
        } catch(NoDisponible ex) {
            System.out.println(ex.toString() + " Error: " + ex.valor());
            System.out.println("El dato tiene que ser mayor a '0'.");
        }
    }
}
