/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.DTOs;

/**
 *
 * @author Gael Galaviz
 */
public class TelefonoDTO {
    private String numero;
    private String etiqueta;

    public TelefonoDTO(String numero, String etiqueta) {
        this.numero = numero;
        this.etiqueta = etiqueta;
    }

    public TelefonoDTO() {
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    @Override
    public String toString() {
        return "TelefonoDTO{" + "numero=" + numero + ", etiqueta=" + etiqueta + '}';
    }
    
    
}
