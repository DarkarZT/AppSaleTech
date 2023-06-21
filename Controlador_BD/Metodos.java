/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador_BD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Milto
 */
public class Metodos {
    public static ConexionSql conexion = new ConexionSql();
    
    
    public static PreparedStatement Ps;
    public static ResultSet Rs;
    public static String sql;
    public static int num = 0;
    
    
    public int guardar(String Nombre, String Apellido, String Correo, String Contrasena){
        
        int resul =  0;
        Connection conexion = null;
        
        String sentencia = ("INSERT INTO usuarios (Nombre,Apellido,Correo,Contraseña) VALUES (?,?,?,?)");
       try{
           conexion = ConexionSql.conectar();
           Ps = conexion.prepareStatement(sentencia);
           Ps.setString(1, Nombre);
           Ps.setString(2, Apellido);
           Ps.setString(3, Correo);
           Ps.setString(4, Contrasena);
           
           resul = Ps.executeUpdate();
           
           conexion.close();
       }catch (Exception e){
           
           System.out.println(e);
           
       }
       
       return resul;
        
    }
    public String buscarNombre(String Correo){
        
        String busca_nombre = "";
        Connection conexion = null;
        try{
            conexion = ConexionSql.conectar();
            String sent_bu  = ("SELECT Nombre,Apellido,Contraseña FROM usuarios WHERE Correo = '" + Correo + "'");
            Ps = conexion.prepareStatement(sent_bu);
            Rs = Ps.executeQuery();
            if(Rs.next()){
                    
                String Nombre  = Rs.getString("Nombre");
                String Apellido  = Rs.getString("Apellido");
                String contra = Rs.getString("Contraseña");
                busca_nombre = (Nombre +" "+ Apellido + " Tu contraseña es: " + contra);
                return busca_nombre;
            }
            conexion.close();
        }catch(SQLException e){
            
            System.out.println(e);
            
        }
        return busca_nombre;
    }
    public String Buscarusuario(String correo, String Contra){
        
        String busqueda =  null;
        Connection conexion  = null;
        try{
            conexion = ConexionSql.conectar();
            String sent_Usuario = ("SELECT Nombre, Correo, Contraseña FROM usuarios WHERE Correo = '" + correo +"' && Contraseña = '"+ Contra+ "'");
            Ps = conexion.prepareStatement(sent_Usuario);
            Rs = Ps.executeQuery();
            if(Rs.next()){
                busqueda = "Usuario registrado";
              
            }else{
                busqueda = "Usuario no Registrado";
            }
            conexion.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return busqueda;
    }
    public String buscarInicio(String Correo){
        
        String busca_nombre = "";
        Connection conexion = null;
        try{
            conexion = ConexionSql.conectar();
            String sent_bu  = ("SELECT Nombre,Apellido,Contraseña FROM usuarios WHERE Correo = '" + Correo + "'");
            Ps = conexion.prepareStatement(sent_bu);
            Rs = Ps.executeQuery();
            if(Rs.next()){
                    
                String Nombre  = Rs.getString("Nombre");
                String Apellido  = Rs.getString("Apellido");
                String contra = Rs.getString("Contraseña");
                busca_nombre = (Nombre +" "+ Apellido );
                return busca_nombre;
            }
            conexion.close();
        }catch(SQLException e){
            
            System.out.println(e);
            
        }
        return busca_nombre;
    }
}
