package tema8;
/**
 *
 * @author lliurex
 */
import java.sql.*;

public class Tema8 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jugadores", "root", "0239");
            System.out.println("Conncetion succed OK");
            //createTableEquipo(con);
            //createTableJugador(con);
            //cargaEquipo(con);
            //cargaJugador(con);
            //modificaEquipo(con);
            //modificarEdadJugador(con, 1);
            //insertaJugador(con, 10, 2, "Federico", 4, 35);
            verEquipo(con);
            System.out.println();
            verJugador(con);
            cuentaJugadores(con);
            cuentaJugadoresConParametros(con);
        } catch (SQLException s) {
            printSQLException(s);
        }
    }
    /**
     * Print del error SQL.
     * @param ex Nombre del error.
     */
    public static void printSQLException(SQLException ex){
        ex.printStackTrace(System.err);
        System.err.println("SQLState: "+ex.getSQLState());
        System.err.println("Error Code: "+ex.getErrorCode());
        System.err.println("Message: "+ex.getMessage());
        Throwable t= ex.getCause();
        while (t!=null){
            System.out.println("Cause: "+t);
            t=t.getCause();
        }        
    }
    
    //Equipo:
    /**
     * Crear Tabla Equipo.
     * @param con Conexion con la Base de Datos.
     * @throws SQLException Si hubiese algun fallo a la hora de crear la tabla.
     */
    public static void createTableEquipo(Connection con) throws SQLException {
        String crearEquipo = "CREATE TABLE equipo (team_id INT (4) PRIMARY KEY, eq_nom VARCHAR(40) NOT NULL, estadio VARCHAR(40) NOT NULL, poblacion VARCHAR(20) NOT NULL, provincia VARCHAR(20) NOT NULL, cod_postal CHAR(5) NOT NULL)";
        Statement st = null;
        
        try {
            st = con.createStatement();
            st.executeUpdate(crearEquipo);
        } catch (SQLException s) {
            printSQLException(s);
        } finally {
            st.close();
        }
    }
    /**
     * Datos para cargar el equipo.
     * @param con Conexion con la Base de Datos.
     * @throws SQLException Si hubiese algun fallo a la hora de cargar la tabla.
     */
    public static void cargaEquipo(Connection con) throws SQLException {
        String insert = "INSERT INTO equipo VALUES (1, 'Estepona', 'Monterroso', 'Estepona', 'Malaga', '29680'), " +
                "(2, 'Alcorcon', 'Santo Domingo', 'Alcorcon', 'Madrid', '28924'), " + 
                "(3, 'Porcuna', 'San Cristobal', 'Porcuna', 'Jaen', '23790')";
        Statement st = null;
        
        try {
            st = con.createStatement();
            st.executeUpdate(insert);
        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            st.close();
        }
    }
    /**
     * Funcion para ver los equipos.
     * @param con Conexion con la Base de Datos.
     * @throws SQLException Si hubiese algun fallo a la hora de mostrar la tabla.
     */
    public static void verEquipo(Connection con) throws SQLException {
        Statement st = null;
        String select = "SELECT eq_nom, estadio, poblacion, provincia FROM equipo";
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                System.out.println("Equipo: " + rs.getString(1) + ". Estadio: " + rs.getString(2) + ". Poblacion: " + rs.getString(3) + ". Provincia: " + rs.getString(4) + ".");
                System.out.println("**********************************************************************************");
            }
        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            st.close();
        }
    }
    /**
     * Modificar Tabla Equipo.
     * @param con Conexion con la Base de Datos.
     * @throws SQLException Si hubiese algun fallo a la hora de modificar la tabla. 
     */
    public static void modificaEquipo(Connection con) throws SQLException {
        Statement st = null;
        try {
            st = con.createStatement();
            String update = "UPDATE equipo SET estadio = 'Alboran' WHERE team_id = 1";
            st.executeUpdate(update);
        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            st.close();
        }
    }
    
    // Jugadores:
    /**
     * Crear Tabla Jugador.
     * @param con Conexion con la Base de Datos.
     * @throws SQLException Si hubiese algun fallo a la hora de crear la tabla. 
     */
    public static void createTableJugador(Connection con) throws SQLException {
        String crearJugador = "CREATE TABLE jugador (player_id INT (4) PRIMARY KEY, nombre VARCHAR(40) NOT NULL, dorsal INT(2) NOT NULL, edad INT(2) NOT NULL, team_id INT(4) NOT NULL REFERENCES equipo(team_id))";
        Statement st = null;
        
        try {
            st = con.createStatement();
            st.executeUpdate(crearJugador);
        } catch (SQLException s) {
            printSQLException(s);
        } finally {
            st.close();
        }
    }
    /**
     * Datos para cargar el jugador.
     * @param con Conexion con la Base de Datos.
     * @throws SQLException Si hubiese algun fallo a la hora de cargar la tabla.
     */
    public static void cargaJugador (Connection con) throws SQLException {
        Statement st = null;
        try {
            st = con.createStatement();
            String insert = "INSERT INTO jugador (player_id, team_id, nombre, dorsal, edad) VALUES (1, 1, 'Jose Antonio', 1, 42), " +
                "(2, 1, 'Ignacio', 2, 62), " + 
                "(3, 1, 'Diego', 3, 20)";
            st.executeUpdate(insert);
            insert = "INSERT INTO jugador (player_id, team_id, nombre, dorsal, edad) VALUES (4, 2, 'Turrion', 1, 37), " +
                "(5, 2, 'Luis Angel', 2, 37), " + 
                "(6, 2, 'Isaac', 3, 40)";
            st.executeUpdate(insert);
            insert = "INSERT INTO jugador (player_id, team_id, nombre, dorsal, edad) VALUES (7, 3, 'Juan Francisco', 1, 33), " +
                "(8, 3, 'Parra', 2, 37), " + 
                "(9, 3, 'Raul', 3, 19)";
            st.executeUpdate(insert);
        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            st.close();
        }
    }
    /**
     * Funcion para ver los jugadores.
     * @param con Conexion con la Base de Datos.
     * @throws SQLException Si hubiese algun fallo a la hora de mostrar la tabla.
     */
    public static void verJugador(Connection con) throws SQLException {
        Statement st = null;
        String select = "SELECT nombre, dorsal, edad, team_id FROM jugador";
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                System.out.println("Nombre: " + rs.getString(1) + ". Dorsal: " + rs.getString(2) + ". Edad: " + rs.getString(3) + ". Equipo: " + rs.getString(4) + ".");
                System.out.println("*******************************************************");
            }
        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            st.close();
        }
    }
    /**
     * Funcion para sumar la edad de los jugadores.
     * @param con Conexion con la Base de Datos.
     * @param cuantosMas Numero a incrementar.
     * @throws SQLException Si hubiese algun fallo a la hora de mostrar la tabla.
     */
    public static void modificarEdadJugador(Connection con, int cuantosMas) throws SQLException {
        Statement st = null;
        try {
            st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String select = "SELECT * FROM jugador";
            ResultSet rs = st.executeQuery(select);
            
            while (rs.next()) {
                int i = rs.getInt("edad");
                rs.updateInt("edad", (i+cuantosMas));
                rs.updateRow();
            }
        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            st.close();
        }
    }
    /**
     * Funcion para insertar a un nuevo jugador por teclado.
     * @param con Conexion con la Base de Datos.
     * @param player_id ID del jugador.
     * @param team_id ID del equipo al que pertenece.
     * @param nom Nombre del jugador.
     * @param dorsal Dorsal del jugador.
     * @param edad Edad del jugador.
     * @throws SQLException Si hubiese algun fallo a la hora de mostrar la tabla.
     */
    public static void insertaJugador(Connection con, int player_id, int team_id, String nom, int dorsal, int edad) throws SQLException {
        Statement st = null;
        try {
            st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String select = "SELECT * FROM jugador";
            ResultSet rs = st.executeQuery(select);
            
            rs.moveToInsertRow();
            rs.updateInt("player_id", player_id);
            rs.updateInt("team_id", team_id);
            rs.updateString("nombre", nom);
            rs.updateInt("dorsal", dorsal);
            rs.updateInt("edad", edad);
            rs.insertRow(); //Inserta dato en el ResultSet y en la Base de Datos.
            rs.beforeFirst(); //Coloca el dato antes de la primera fila.
        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            st.close();
        }
    }
    /**
     * Funcion cuenta jugadores.
     * @param con Conexion con la Base de Datos.
     * @throws SQLException Si hubiese algun fallo a la hora de mostrar la tabla.
     */
    public static void cuentaJugadores(Connection con) throws SQLException {
        Statement st = null;
        String contador = "SELECT cuentaJugadores()";
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(contador);
            String cuantos;
            while (rs.next()) {
                cuantos = rs.getString(1);
                System.out.println("Existen " + cuantos + " jugadores.");
            }
            System.out.println("******************************");
        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            st.close();
        }
    }
    /**
     * Funcion cuenta jugadores.
     * @param con Conexion con la Base de Datos.
     * @throws SQLException Si hubiese algun fallo a la hora de mostrar la tabla.
     */
    public static void cuentaJugadoresConParametros(Connection con) throws SQLException {
        CallableStatement cs = null;
        try {
            cs = con.prepareCall("{call cuentaJugadores2 (?)}");
            cs.registerOutParameter(1, Types.INTEGER);
            cs.execute();
            int cuantos = cs.getInt(1);
            System.out.println("Existen " + cuantos + " jugadores.");
            System.out.println("******************************");
        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            cs.close();
        }
    }
}