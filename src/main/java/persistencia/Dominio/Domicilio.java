/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.Dominio;

/**
 *
 * @author Usuario
 */
public class Domicilio {
    
    private int id_domicilio ;
    private String calle;
    private String colonia;
    private String numero;

    public Domicilio(int id_domicilio, String calle, String colonia, String numero) {
        this.id_domicilio = id_domicilio;
        this.calle = calle;
        this.colonia = colonia;
        this.numero = numero;
    }

    public Domicilio(String calle, String colonia, String numero) {
        this.calle = calle;
        this.colonia = colonia;
        this.numero = numero;
    }

    public int getId_domicilio() {
        return id_domicilio;
    }

    public void setId_domicilio(int id_domicilio) {
        this.id_domicilio = id_domicilio;
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
        return "Domicilio{" + "id_domicilio=" + id_domicilio + ", calle=" + calle + ", colonia=" + colonia + ", numero=" + numero + '}';
    }
    
    
    
}
