/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador_BD;

import Modelado.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 *
 * @author Milto
 */
public class Cliente_BDD {

    ConexionSql conec = new ConexionSql();

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public boolean RegistrarCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (dni,nombre,telefono,direccion) VALUES (?,?,?,?)";
        try {
            con = conec.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getDni());
            ps.setString(2, cliente.getNombre());
            ps.setInt(3, cliente.getTelefono());
            ps.setString(4, cliente.getDireccion());
            ps.execute();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {

                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    public List Listar() {
        List<Cliente> listaDeClientes = new ArrayList();
        String sql = "SELECT * FROM clientes";
        try {
            con = conec.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cl = new Cliente();
                cl.setId(rs.getInt("id"));
                cl.setDni(rs.getString("dni"));
                cl.setNombre(rs.getString("nombre"));
                cl.setTelefono(rs.getInt("telefono"));
                cl.setDireccion(rs.getString("direccion"));
                listaDeClientes.add(cl);

            }
        } catch (Exception e) {
            System.out.println(e.toString());

        }
        return listaDeClientes;
    }

    public boolean Eliminar_Cliente(String id) {
        String sql = "DELETE FROM clientes WHERE dni = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }

        }
    }

    public boolean ModificarClient(Cliente cl) {
        String sql = "UPDATE clientes SET nombre = ?, telefono = ?, direccion = ? WHERE dni = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getNombre());
            ps.setInt(2, cl.getTelefono());
            ps.setString(3, cl.getDireccion());
            ps.setString(4, cl.getDni());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }
    public Cliente Buscarcliente(String dni){
        Cliente cl = new Cliente();
        String sql = "SELECT * FROM clientes WHERE dni = ?";
        try{
            con = conec.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, dni);
            rs = ps.executeQuery();
            if(rs.next()){
                cl.setNombre(rs.getString("nombre"));
                cl.setTelefono(rs.getInt("telefono"));
                cl.setDireccion(rs.getString("direccion"));
               
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return cl;
    }

}
