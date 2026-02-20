/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.BOs;

import Negocio.DTOs.ClienteCompletoDTO;
import Negocio.Excepciones.negocioException;
import persistencia.Dominio.Cliente;

/**
 *
 * @author Usuario
 */
public interface IClienteBO {
    
    public Cliente insertarCliente(ClienteCompletoDTO cliente) throws negocioException;
    
}
