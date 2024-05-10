package ExcepcionesHerencia;
/**
 * Clase que determina si el valor es valido o no.
 * @author lliurex
 */
public class NoDisponible extends Exception {
    private double dato;
    public NoDisponible(double x) {
        super("El sensor no esta disponible.");
        this.dato = x;
    }
    
    public double valor() {
        return dato;
    }
}
