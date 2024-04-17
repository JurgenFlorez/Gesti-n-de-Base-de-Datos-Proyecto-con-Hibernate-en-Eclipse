/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejemploproyecto;

//clases de conexion a mysql y los drivers de sql
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionMySQL {
    
    // conexion a Mysql
    public static Connection conn;

    public static void main(String[] args) {
        // Datos de conexion a la bd
        String driver = "com.mysql.cj.jdbc.Driver";
        String username = "root";
        String password = "123456";
        String port = "3306";
        String hostname = "localhost";
        String database = "hablaluisafernanda";
        String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?autoReconnect=true&useSSL=false";
        Statement statement;
        ResultSet rs;
        // query insert
        String insertQuery = "INSERT INTO usuario(nombre, edad, telefono, correo, idioma) "
                + "VALUES ('javier2',28,'320945','javier2@gmail.com','ingles')";
        // query select
        String selectQuery = "SELECT * FROM usuario";

        try {
            Class.forName(driver);
            try {
                // credenciales para ingresar a la base de datos
                conn = DriverManager.getConnection(url, username, password);
                statement = conn.createStatement();
                // creacion de paciente
                statement.executeUpdate(insertQuery);
                // mostrar datos paciente
                rs = statement.executeQuery(selectQuery);
                // generar que automaticamente pase a la siguiente casilla para generar los datos
                rs.next();
                // realizamos do-while para imprimir por pantalla los datos guardados en la bd
                do {
                    System.out.println(rs.getInt("id") + ": "
                            + rs.getString("nombre") + "\t"
                            + rs.getInt("edad") + "\t"
                            + rs.getString("telefono") + "\t"
                            + rs.getString("correo") + "\t"
                            + rs.getString("idioma"));
                } while (rs.next());

            } catch (SQLException e) {
                e.printStackTrace(); // Manejo de errores
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
