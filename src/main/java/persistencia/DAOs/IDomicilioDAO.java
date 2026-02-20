/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.DAOs;

import persistencia.Dominio.Domicilio;
import persistencia.Excepciones.persistenciaException;

/**
 *
 * @author Usuario
 */
public interface IDomicilioDAO {
    
    Domicilio agregarDomicilio(Domicilio domicilio) throws persistenciaException;
    
}
