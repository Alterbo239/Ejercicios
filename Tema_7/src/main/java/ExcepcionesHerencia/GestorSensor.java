package ExcepcionesHerencia;
/**
 *
 * @author lliurex
 */
public class GestorSensor {
    private Sensor s;
    
    public GestorSensor(Sensor s) {
        this.s = s;
    }
    
    public void enviarDato(double dato) throws NoDisponible {
        if (!s.enviarDato(dato)) {
            throw new NoDisponible(dato);
        }
    }
}