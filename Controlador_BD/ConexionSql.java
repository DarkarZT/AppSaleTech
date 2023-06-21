/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador_BD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Milto
 */
public class ConexionSql {
    
    public static String url = "jdbc:mysql://localhost:3306/registros";
    public static String usuario = "root";
    public static String pass = "miltonxdxd11";
    public static String driver = "com.mysql.jdbc.Driver";
    
    public static Connection conectar(){
        Connection conex = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conex = (Connection)DriverManager.getConnection(url,usuario,pass);
            System.out.println("Conexion realizada");
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
        return conex;
    }
}
