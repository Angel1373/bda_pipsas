/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.Dominio;

/**
 *
 * @author Usuario
 */
public class Pedido {
    
    private int idPedido;
    private estadoPedido estado;
    private String notas;
    private double costo;

    public Pedido(int idPedido, estadoPedido estado, String notas, double costo) {
        this.idPedido = idPedido;
        this.estado = estado;
        this.notas = notas;
        this.costo = costo;
    }

    public Pedido(estadoPedido estado, String notas, double costo) {
        this.estado = estado;
        this.notas = notas;
        this.costo = costo;
    }

    public Pedido() {
    } 
    
    //metodo para los estados de sql cuando lo llamemos por ejemplo estadoPedido.PENDIENTE se pondra pendiente el pedido
    //Le damos un valor y lo metemos al constructor y lo devolvemos, el constructor es por que cada valor del enum es un objeto
    /*
    por ejemplo estadoPedido.PENDIENTE es un objeto y con el get valor obtenemos el pendientepe
    y hace new EstadoPedido("pendiente") y eso entra al constructor y el valor toma el "pendiente"
    */
    public enum estadoPedido {

        PENDIENTE("pendiente"),
        LISTO("listo"),
        ENTREGADO("entregado"),
        CANCELADO("cancelado"),
        NO_RECLAMADO("no reclamado");

        private final String valor;

        estadoPedido(String valor) {
            this.valor = valor;
        }

        public String getValor() {
            return valor;
        }
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public estadoPedido getEstado() {
        return estado;
    }

    public void setEstado(estadoPedido estado) {
        this.estado = estado;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
    
    
}
