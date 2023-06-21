/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador_BD;

import Modelado.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Milto
 */
public class Productos_BDD {

    Connection con;
    ConexionSql cn = new ConexionSql();
    PreparedStatement ps;
    ResultSet rs;

    public boolean Registrar_productos(Producto pr) {
        String sql = "INSERT INTO productos(codigo,nombre,stock,precio)VALUES (?,?,?,?)";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, pr.getCodigo());
            ps.setString(2, pr.getNombre());
            ps.setInt(3, pr.getStock());
            ps.setDouble(4, pr.getPrecio());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public List ListarProd() {
        List<Producto> listaDeProductos = new ArrayList();
        String sql = "SELECT * FROM productos";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto pr = new Producto();
                pr.setId(rs.getInt("id"));
                pr.setCodigo(rs.getString("codigo"));
                pr.setNombre(rs.getString("nombre"));
                pr.setStock(rs.getInt("stock"));
                pr.setPrecio(rs.getDouble("precio"));
                listaDeProductos.add(pr);

            }
        } catch (Exception e) {
            System.out.println(e.toString());

        }
        return listaDeProductos;
    }

    public boolean Eliminar_Producto(String code) {
        String sql = "DELETE FROM productos WHERE codigo = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, code);
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

    public boolean modificarProduc(Producto pr) {
        String sql = "UPDATE productos SET nombre = ?, stock = ?, precio = ? WHERE codigo = ? ";
        try {
            ps = con.prepareStatement(sql);

            ps.setString(1, pr.getNombre());
            ps.setInt(2, pr.getStock());
            ps.setDouble(3, pr.getPrecio());
            ps.setString(4, pr.getCodigo());
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

    public Producto BuscarProducto(String cod) {
        Producto produc = new Producto();
        String sql = "SELECT * FROM productos WHERE codigo =?";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, cod);
            rs = ps.executeQuery();
            if (rs.next()) {
                produc.setNombre(rs.getString("nombre"));
                produc.setPrecio(rs.getDouble("precio"));
                produc.setStock(rs.getInt("stock"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return produc;
    }

}
