/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.Dominio;

/**
 *
 * @author Gael Galaviz
 */
public class Usuario {
    private Integer id_cliente;
    private String usuario;
    private String contrasena;

    public Usuario(Integer id_cliente, String usuario, String contrasena) {
        this.id_cliente = id_cliente;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public Usuario(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public Usuario() {
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return "usuario{" + "id_cliente=" + id_cliente + ", usuario=" + usuario + ", contrasena=" + contrasena + '}';
    }
    
    
}
