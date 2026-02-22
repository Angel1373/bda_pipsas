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
    private Integer id_telefono;
    private String numeroTelefono;
    private String etiqueta;
    private Integer id_cliente;

    public Telefono(Integer id_telefono, String numeroTelefono, String etiqueta, Integer id_cliente) {
        this.id_telefono = id_telefono;
        this.numeroTelefono = numeroTelefono;
        this.etiqueta = etiqueta;
        this.id_cliente = id_cliente;
    }

    public Telefono(String numeroTelefono, String etiqueta, Integer id_cliente) {
        this.numeroTelefono = numeroTelefono;
        this.etiqueta = etiqueta;
        this.id_cliente = id_cliente;
    }

    public Telefono() {
    }

    public Integer getId_telefono() {
        return id_telefono;
    }

    public void setId_telefono(Integer id_telefono) {
        this.id_telefono = id_telefono;
    }
    
    
    

  
    public String getEtiqueta() {
        return etiqueta;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

   

  

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

  

    @Override
    public String toString() {
        return "Telefono{" + "id_telefono=" + id_telefono + ", numeroTelefono=" + numeroTelefono + ", etiqueta=" + etiqueta + ", id_cliente=" + id_cliente + '}';
    }
    
    
    
    
}
