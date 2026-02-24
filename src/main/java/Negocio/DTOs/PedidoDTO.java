package Negocio.DTOs;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gael Galaviz
 */
public class PedidoDTO {
    
    private int idPedido;
    private String estado;
    private String notas ;
    private double costo;

    public PedidoDTO(int idPedido, String estado, String notas, double costo) {
        this.idPedido = idPedido;
        this.estado = estado;
        this.notas = notas;
        this.costo = costo;
    }

    public PedidoDTO() {
    }
    
    public int getIdPedido() {
        return idPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
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

    @Override
    public String toString() {
        return "PedidoDTO{" + "idPedido=" + idPedido + "estado=" + estado + ", notas=" + notas + ", costo=" + costo + '}';
    }
    
    
    
}
