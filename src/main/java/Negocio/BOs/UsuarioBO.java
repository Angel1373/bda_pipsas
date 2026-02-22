/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.BOs;

import Negocio.DTOs.UsuarioDTO;
import Negocio.Excepciones.negocioException;
import java.util.logging.Logger;
import persistencia.DAOs.IUsuarioDAO;
import persistencia.Dominio.Usuario;
import persistencia.Excepciones.persistenciaException;

/**
 *
 * @author Usuario
 */
public class UsuarioBO implements IUsuarioBO{

    private IUsuarioDAO usuarioDAO;
    
    // para llamar al dao
    public UsuarioBO(IUsuarioDAO usuario){
        this.usuarioDAO = usuario;
    }
    
    private static final Logger LOG = Logger.getLogger(UsuarioBO.class.getName());

    // metodo que recibe un usuario y lo consulta con el DAO usuario que solo recibe el usuario
    @Override
    public Usuario consultarUsuario(UsuarioDTO usuario) throws negocioException {
        
        if(usuario.getUsuario() == null || usuario.getUsuario().isEmpty()){
            LOG.warning("El usuario no puede ser null o estar vacio");
            throw new negocioException("Error con el usuario ingresado");
        }
        
        if(usuario.getContrasena() == null || usuario.getContrasena().isEmpty()){
            LOG.warning("La contraseña no puede ser null o estar vacia");
            throw new negocioException("Error con la contraseña ingresada");
        }
        try{   
       Usuario usuarioConsultado = new Usuario();
       usuarioConsultado.setUsuario(usuario.getUsuario());
       Usuario usuarioConfirmar = this.usuarioDAO.consultarUsuarioPorUsuario(usuarioConsultado);
       
        // Comparamos la contraseña escrita con la guardada en la base de datos
        if (!usuarioConfirmar.getContrasena().equals(usuario.getContrasena())) {
            throw new negocioException("Contraseña incorrecta");
        }
        return usuarioConfirmar;
        
         }catch(persistenciaException ex){
             LOG.warning("Algo ocurrio al intentar consultar al usuario");
             throw new negocioException("Error al consultar al usuario");
         }
    }
    
}
