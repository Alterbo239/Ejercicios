package ExcepcionesHerencia;
/**
 * Sensor que envia datos mayores a "0".
 * @author lliurex
 */
public class Sensor {
    public boolean enviarDato(double dato) {
        boolean envio;
        if (dato > 0) {
            envio = true;
        } else {
            envio = false;
        }
        return envio;
    }
}
