/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.DTOs;

/**
 *
 * @author Gael Galaviz
 */
public class PizzaDTO {
    private Integer id_pizza;
    private String nombre;
    private String tamano;
    private String descripcion;
    private double precio;
    private boolean disponible;

    public PizzaDTO(Integer id_pizza, String nombre, String tamano, String descripcion, double precio, boolean disponible) {
        this.id_pizza = id_pizza;
        this.nombre = nombre;
        this.tamano = tamano;
        this.descripcion = descripcion;
        this.precio = precio;
        this.disponible = disponible;
    }

    public PizzaDTO(double precio, boolean disponible) {
        this.precio = precio;
        this.disponible = disponible;
    }

    public PizzaDTO(int id_pizza, double precio, boolean disponible) {
        this.id_pizza = id_pizza;
        this.precio = precio;
        this.disponible = disponible;
    }

    
    public PizzaDTO() {
    }

    public Integer getId_pizza() {
        return id_pizza;
    }

    public void setId_pizza(Integer id_pizza) {
        this.id_pizza = id_pizza;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "PizzaDTO{" + "id_pizza=" + id_pizza + ", nombre=" + nombre + ", tamano=" + tamano + ", descripcion=" + descripcion + ", precio=" + precio + ", disponible=" + disponible + '}';
    }

   
    
    
}
