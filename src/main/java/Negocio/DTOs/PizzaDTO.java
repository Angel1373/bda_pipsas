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
    private String nombre;
    private String tamano;
    private String descripcion;
    private double precio;
    private boolean disponible;

    public PizzaDTO(String nombre, String tamano, String descripcion, double precio, boolean disponible) {
        this.nombre = nombre;
        this.tamano = tamano;
        this.descripcion = descripcion;
        this.precio = precio;
        this.disponible = disponible;
    }

    public PizzaDTO() {
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
        return "PizzaDTO{" + "nombre=" + nombre + ", tamano=" + tamano + ", descripcion=" + descripcion + ", precio=" + precio + ", disponible=" + disponible + '}';
    }
    
    
    
}
