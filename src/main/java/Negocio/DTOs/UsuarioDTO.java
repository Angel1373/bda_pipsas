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
    private String usuario;
    private String contrasena;

    public UsuarioDTO( String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public UsuarioDTO() {
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
        return "UsuarioDTO{" + "usuario=" + usuario + ", contrasena=" + contrasena + '}';
    }
    
    
    
}
