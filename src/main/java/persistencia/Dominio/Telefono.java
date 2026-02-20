/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.Dominio;

/**
 *
 * @author Usuario
 */
public class Telefono {
    private int id_telefono;
    private String numero;
    private String etiqueta;
    private int id_cliente;

    public Telefono(int id_telefono, String numero, String etiqueta, int id_cliente) {
        this.id_telefono = id_telefono;
        this.numero = numero;
        this.etiqueta = etiqueta;
        this.id_cliente = id_cliente;
    }

    public Telefono(String numero, String etiqueta, int id_cliente) {
        this.numero = numero;
        this.etiqueta = etiqueta;
        this.id_cliente = id_cliente;
    }

    public int getId_telefono() {
        return id_telefono;
    }

    public String getNumero() {
        return numero;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_telefono(int id_telefono) {
        this.id_telefono = id_telefono;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
    
    
    
}
