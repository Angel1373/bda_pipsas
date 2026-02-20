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
    private String estado;
    private String notas ;
    private double costo;

    public PedidoDTO(String estado, String notas, double costo) {
        this.estado = estado;
        this.notas = notas;
        this.costo = costo;
    }

    public PedidoDTO() {
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
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

    @Override
    public String toString() {
        return "PedidoDTO{" + "estado=" + estado + ", notas=" + notas + ", costo=" + costo + '}';
    }
    
    
    
}
