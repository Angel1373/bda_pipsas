/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.DTOs;

/**
 *
 * @author luiscarlosbeltran
 */

// es diferente a PizzasEnPedidosDTO
//imaginen como un carrito de compras, al que le metes los productos para despues pagarlo todo
//pues esta clase representa como si fuera ese producto
public class DetallePedidoDTO {
    private PizzaDTO pizza;
    private int cantidad;
    private String Notas;
    
    public double getSubtotal(){
        return pizza.getPrecio() * cantidad;
    }

    public PizzaDTO getPizza() {
        return pizza;
    }

    public void setPizza(PizzaDTO pizza) {
        this.pizza = pizza;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNotas() {
        return Notas;
    }

    public void setNotas(String Notas) {
        this.Notas = Notas;
    }
    
    
    
}
