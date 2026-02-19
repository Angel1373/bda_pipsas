/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.DAOs;

import persistencia.Dominio.Cliente;
import persistencia.Excepciones.persistenciaException;

/**
 *
 * @author Usuario
 */
public interface IClienteDAO {
    
    // Metodo para agregar un cliente
    Cliente agregarCliente(Cliente cliente)throws persistenciaException;
}
