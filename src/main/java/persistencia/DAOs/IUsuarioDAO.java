/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia.DAOs;

import persistencia.Dominio.Usuario;
import persistencia.Excepciones.persistenciaException;

/**
 *
 * @author Gael Galaviz
 */
public interface IUsuarioDAO {
    Usuario agregarUsuario(Usuario usuario) throws persistenciaException;
    Usuario actualizarUsuario(Usuario usuario) throws persistenciaException;
    Usuario consultarUsuario(Usuario usuario) throws persistenciaException;
    Usuario consultarUsuarioPorUsuario(Usuario usuario) throws persistenciaException;
}
