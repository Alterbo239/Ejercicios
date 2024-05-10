package Ejercicios;
/**
 *
 * @author lliurex
 */
import java.sql.*;

public class Ejercicio_01 {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ejercicios_prg", "root", "0239");
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
    
    public static void crearTabla(Connection con) {
        Statement st = null;
        String crear = "CREATE TABLE concesionario";
        try {
            st = con.createStatement();
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }
}
