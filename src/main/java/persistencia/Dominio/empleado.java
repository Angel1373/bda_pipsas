/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.Dominio;

/**
 *
 * @author luiscarlosbeltran
 */

//explciacion: clase que guarda un usuario y contraseña en este proyecto, no en la base de datos
//solo usada para validar de manera sencilla un inicio de sesion de 
//los objetos tipo empelado los pondre arriba del iniciarSesionEmpelado
public class empleado {

    private String usuarioE;
    private String contraseniaE;

    public empleado(String usuarioE, String contraseniaE) {
        this.usuarioE = usuarioE;
        this.contraseniaE = contraseniaE;
    }

    public String getUsuarioE() {
        return usuarioE;
    }

    public String getContraseniaE() {
        return contraseniaE;
    }
}