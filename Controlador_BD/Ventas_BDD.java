/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador_BD;

import Modelado.Ventas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Ventas_BDD {

    ConexionSql conec = new ConexionSql();

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public boolean registrar_venta(Ventas ve) {
        String sql = "INSERT INTO ventas(codigoProducto,cliente,precio)VALUES (?,?,?)";
        try {
            con = conec.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, ve.getCodigoProducto());
            ps.setString(2, ve.getCliente());
            ps.setDouble(3, ve.getTotal());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public List Listar() {
        List<Ventas> listaDeVentas = new ArrayList();
        String sql = "SELECT * FROM ventas";
        try {
            con = conec.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ventas ve = new Ventas();
                ve.setCodigoProducto(rs.getString("codigoProducto"));
                ve.setCliente(rs.getString("cliente"));
                ve.setTotal(rs.getDouble("precio"));
                listaDeVentas.add(ve);

            }
        } catch (Exception e) {
            System.out.println(e.toString());

        }
        return listaDeVentas;
    }

    public boolean eliminar_Venta(String codigoPro) {
        String sql = "DELETE FROM ventas WHERE codigoProducto = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, codigoPro);
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

    public boolean modificar_ventas(Ventas ve) {
        String sql = "UPDATE ventas SET codigoProducto = ?, cliente = ?, precio = ? WHERE codigoProducto = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, ve.getCodigoProducto());
            ps.setString(2, ve.getCliente());
            ps.setDouble(3, ve.getTotal());            
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
    public boolean actualizarStock(int canti, String codigo){
        String sql = "UPDATE productos SET stock = ? WHERE codigo = ?";
        try{
            con = conec.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, canti);
            ps.setString(2, codigo);
            ps.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }
    }

}
