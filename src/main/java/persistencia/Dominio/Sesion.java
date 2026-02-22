/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.Dominio;

/**
 *
 * Clase para guardar la sesion del usuario
 */
public class Sesion {
    // es de tipo usuario para poder usar sus atributos
    /*
    Es static para que todas las clases puedan acceder a su valor y por eso es importante para guardar la sesion
    */
    public static Usuario usuarioActual;
    
    private static Integer idCliente;

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static void setUsuarioActual(Usuario usuarioActual) {
        Sesion.usuarioActual = usuarioActual;
    }

    public static Integer getIdCliente() {
        return idCliente;
    }

    public static void setIdCliente(Integer idCliente) {
        Sesion.idCliente = idCliente;
    }
    
    
}
