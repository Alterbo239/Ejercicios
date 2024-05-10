package tema8;

import java.sql.*;

/**
 *
 * @author lliurex
 */
public class BaseCuentas {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuentas", "root", "0239");
            System.out.println("Conexion completada!");
            //crearTabla(con);
            transaccion(con, "cuentas", 3, 4, 400); //Cantidad si es negativa furula igual.
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
    /**
     * Crear Tabla.
     * @param con Conexion con la BD
     * @throws SQLException Si falla algo.
     */
    public static void crearTabla(Connection con) throws SQLException {
        String crear = "CREATE TABLE cuenta (id INT, saldo INT NOT NULL, CONSTRAINT PK_id PRIMARY KEY (id))";
        Statement st = null;
        try {
            st = con.createStatement();
            st.executeUpdate(crear);
        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            st.close();
        }
    }
    /**
     * Realizar transaccion
     * @param con Conexion con la BD
     * @param nomBD Nombre de la BD.
     * @param cuentaA Cuenta de la que se saca el dinero.
     * @param cuentaB Cuenta a la que se le ingresa el dinero.
     * @param cantidad Cantidad del dinero.
     * @throws SQLException Si falla algo.
     */
    public static void transaccion(Connection con, String nomBD, int cuentaA, int cuentaB, int cantidad) throws SQLException {
        String actualizaA = "update " + nomBD + ".cuenta set SALDO = SALDO - " + cantidad + " WHERE id = " + cuentaA;
        String actualizaB = "update " + nomBD + ".cuenta set SALDO = SALDO + " + cantidad + " WHERE id = " + cuentaB;
        Statement st = null;
        try {
            con.setAutoCommit(false);
            st = con.createStatement();
            //Si el saldo de la cuenta A se queda en 0.
            String comprobar = "SELECT saldo FROM cuenta WHERE id = " + cuentaA;
            ResultSet rs = st.executeQuery(comprobar);
            rs.next();
            int resultado = rs.getInt("saldo");
            if (resultado < cantidad) {
                throw new SQLException();
            }
            
            //Si la cuenta esta vacia.
            if (st.executeUpdate(actualizaA) == 0) {
                throw new SQLException();
            }
            if (st.executeUpdate(actualizaB) == 0) {
                throw new SQLException();
            }
            con.commit();
            //Excepcion:
        } catch (SQLException s) {
            s.printStackTrace();
            System.out.println("En el catch");
            if (con != null) { //Si esta conectado pero falla por otra cosa, hace un rollback.
                try {
                    System.out.println("Rollback de la transaccion.");
                    con.rollback();
                } catch (SQLException ex) {
                    printSQLException(ex);
                }
            }
        } finally {
            try {
                st.close();
                con.setAutoCommit(true);
            } catch (java.sql.SQLException j) {
                printSQLException(j);
            }
        }
    }
}