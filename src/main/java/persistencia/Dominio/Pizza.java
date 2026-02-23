/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.Dominio;

/**
 *
 * @author Usuario
 */
public class Pizza {
    
    private int idPizza;
    private String nombre;
    private String tamano;
    private String descripcion;
    private double precio;
    private boolean disponible;

    public Pizza(int idPizza, String nombre, String tamano, String descripcion, double precio, boolean disponible) {
        this.idPizza = idPizza;
        this.nombre = nombre;
        this.tamano = tamano;
        this.descripcion = descripcion;
        this.precio = precio;
        this.disponible = disponible;
    }

    public Pizza(String nombre, String tamano, String descripcion, double precio, boolean disponible) {
        this.nombre = nombre;
        this.tamano = tamano;
        this.descripcion = descripcion;
        this.precio = precio;
        this.disponible = disponible;
    }

    public Pizza(double precio, boolean disponible) {
        this.precio = precio;
        this.disponible = disponible;
    }
    
    

    public Pizza() {
    }

    public int getIdPizza() {
        return idPizza;
    }

    public void setIdPizza(int idPizza) {
        this.idPizza = idPizza;
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
        return "Pizza{" + "idPizza=" + idPizza + ", nombre=" + nombre + ", tamano=" + tamano + ", descripcion=" + descripcion + ", precio=" + precio + ", disponible=" + disponible + '}';
    }
    
}
