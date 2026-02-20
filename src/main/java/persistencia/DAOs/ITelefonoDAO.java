/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.DAOs;

import persistencia.Dominio.Telefono;
import persistencia.Excepciones.persistenciaException;

/**
 *
 * @author Usuario
 */
public interface ITelefonoDAO {
    
    Telefono agregarTelefono(Telefono telefono) throws persistenciaException;
    
}
