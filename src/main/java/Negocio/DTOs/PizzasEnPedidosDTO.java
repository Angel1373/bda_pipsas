/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.DTOs;

/**
 *
 * @author Gael Galaviz
 */
public class PizzasEnPedidosDTO {
    private int idPedido;
    private int idPizza;
    private int cantidadPizza;
    private String notas;

    public PizzasEnPedidosDTO(int idPedido, int idPizza, int cantidadPizza, String notas) {
        this.idPedido = idPedido;
        this.idPizza = idPizza;
        this.cantidadPizza = cantidadPizza;
        this.notas = notas;
    }

    public PizzasEnPedidosDTO() {
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdPizza() {
        return idPizza;
    }

    public void setIdPizza(int idPizza) {
        this.idPizza = idPizza;
    }

    public int getCantidadPizza() {
        return cantidadPizza;
    }

    public void setCantidadPizza(int cantidadPizza) {
        this.cantidadPizza = cantidadPizza;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return "PizzasEnPedidosDTO{" + "idPedido=" + idPedido + ", idPizza=" + idPizza + ", cantidadPizza=" + cantidadPizza + ", notas=" + notas + '}';
    }
    
    
}
