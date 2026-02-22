/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.BOs;

import Negocio.DTOs.UsuarioDTO;
import Negocio.Excepciones.negocioException;
import persistencia.Dominio.Usuario;

/**
 *
 * @author Usuario
 */
public interface IUsuarioBO {
    
    public Usuario consultarUsuario(UsuarioDTO usuario) throws negocioException;
    
}
