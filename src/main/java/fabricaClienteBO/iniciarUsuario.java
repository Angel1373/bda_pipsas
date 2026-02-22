/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fabricaClienteBO;

import Negocio.BOs.UsuarioBO;
import persistencia.DAOs.IUsuarioDAO;
import persistencia.DAOs.UsuarioDAO;
import persistencia.conexion.ConexionBD;
import persistencia.conexion.IConexionBD;

/**
 *
 * @author Usuario
 */
public class iniciarUsuario {
    
    // Metodo para llamar al DAO y no mostrarlo en el GUI, me regresa el usuario BO para usarlo 
    // esto existe por que el BO necesita al DAO
    // por ejemplo el de consultar usuario, llamamos a este metodo del BO y directamente llama al DAO dentro de ese metodo
    public static UsuarioBO iniciarUsuario(){
        
    IConexionBD conexion = new ConexionBD();
    IUsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
    return new UsuarioBO(usuarioDAO);
    }
}
