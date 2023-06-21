
package Modelado;


public class Ventas {
    private String codigoProducto;
    private String cliente;
    private double total;

    public Ventas(String codigoProducto, String cliente, double total) {
        this.codigoProducto = codigoProducto;
        this.cliente = cliente;
        this.total = total;
    }

    public Ventas() {
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    
            
}
