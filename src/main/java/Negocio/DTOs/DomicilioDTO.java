/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.DTOs;

/**
 *
 * @author Gael Galaviz
 */
public class DomicilioDTO {
    private String calle;
    private String colonia;
    private String numero;

    public DomicilioDTO(String calle, String colonia, String numero) {
        this.calle = calle;
        this.colonia = colonia;
        this.numero = numero;
    }

    public DomicilioDTO() {
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "DomicilioDTO{" + "calle=" + calle + ", colonia=" + colonia + ", numero=" + numero + '}';
    }
    
}
