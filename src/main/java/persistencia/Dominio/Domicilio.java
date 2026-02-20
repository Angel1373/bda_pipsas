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
    
    private Integer id_domicilio ;
    private int id_cliente;
    private String calle;
    private String colonia;
    private String numeroCasa;

    public Domicilio(Integer id_domicilio, int id_cliente, String calle, String colonia, String numeroCasa) {
        this.id_domicilio = id_domicilio;
        this.id_cliente = id_cliente;
        this.calle = calle;
        this.colonia = colonia;
        this.numeroCasa = numeroCasa;
    }

    public Domicilio(int id_cliente, String calle, String colonia, String numeroCasa) {
        this.id_cliente = id_cliente;
        this.calle = calle;
        this.colonia = colonia;
        this.numeroCasa = numeroCasa;
    }

    public Domicilio(String calle, String colonia, String numeroCasa) {
        this.calle = calle;
        this.colonia = colonia;
        this.numeroCasa = numeroCasa;
    }

    public Domicilio() {
    }

    public Integer getId_domicilio() {
        return id_domicilio;
    }

    public void setId_domicilio(Integer id_domicilio) {
        this.id_domicilio = id_domicilio;
    }
    
    

  

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
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

    public String getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(String numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    @Override
    public String toString() {
        return "Domicilio{" + "id_domicilio=" + id_domicilio + ", id_cliente=" + id_cliente + ", calle=" + calle + ", colonia=" + colonia + ", numeroCasa=" + numeroCasa + '}';
    }

    
    
}
