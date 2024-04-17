/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejemploproyecto;

//clases de conexion a mysql y los drivers de sql
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionMySQL2 {

    // conexion a Mysql
    public static Connection conn;

    // nombramiento metodo ejecutadorConsulta
    public static void ejecutadorConsulta(){
        // Datos de conexion a la bd - nombramiento de variables
        String driver = "com.mysql.cj.jdbc.Driver";
        String username = "root";
        String password = "123456";
        String port = "3306";
        String hostname = "localhost";
        String database = "hablaluisafernanda";
        String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?autoReconnect=true&useSSL=false";
        PreparedStatement statement;
        ResultSet rs;

        // query insert
        String insertQuery = "INSERT INTO usuario(nombre, edad, telefono, correo, idioma) "
                + "VALUES (?,?,?,?,?)";
        // query select
        String selectQuery = "SELECT * FROM usuario";

        try {
            // nombramiento de la clase
            Class.forName(driver);
            try {
                // credenciales para ingresar a la base de datos
                conn = DriverManager.getConnection(url, username, password);
                // creacion de paciente
                statement = conn.prepareStatement(insertQuery);
                // mostrar datos paciente
                rs = statement.executeQuery(selectQuery);
                // generar que automaticamente pase a la siguiente casilla para generar los datos
                rs.next();
                // para verificacion de insercion 
                statement.setString(1, "Fernando");
                statement.setInt(2, 25);
                statement.setString(3, "123456");
                statement.setString(4, "javier@gmail.com");
                statement.setString(5, "es");
                //Ejecutar la inserción
                int filasAfectadas = statement.executeUpdate();
                // realizamos do-while para imprimir por pantalla los datos guardados en la bd
                try {
                    rs = conn.createStatement().executeQuery(selectQuery);
                    // Imprimir los datos recuperados
                    while (rs.next()) {
                        System.out.println(rs.getInt("id") + ": "
                                + rs.getString("nombre") + "\t"
                                + rs.getInt("edad") + "\t"
                                + rs.getString("telefono") + "\t"
                                + rs.getString("correo") + "\t"
                                + rs.getString("idioma"));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                // verificar si el usuario fue creado o no por medio de la verificacion de insercion
                if (filasAfectadas > 0) {
                    System.out.println("Usuario creado con éxito");
                } else {
                    System.out.println("Error al crear el usuario");
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Manejo de errores
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionMySQL2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) {
        ejecutadorConsulta();
    }

}
