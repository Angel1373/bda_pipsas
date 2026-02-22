/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.DTOs;

/**
 *
 * @author Gael Galaviz
 */
public class UsuarioDTO {
    private Integer idCliente;
    private String usuario;
    private String contrasena;

    public UsuarioDTO(Integer idCliente, String usuario, String contrasena) {
        this.idCliente = idCliente;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public UsuarioDTO(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public UsuarioDTO() {
    }

    
    
    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
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
        return "UsuarioDTO{" + "idCliente=" + idCliente + ", usuario=" + usuario + ", contrasena=" + contrasena + '}';
    }

    
    
    
    
}
