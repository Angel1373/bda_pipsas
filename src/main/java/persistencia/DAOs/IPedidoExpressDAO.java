/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia.DAOs;

import Negocio.DTOs.PedidoExpressDTO;
import persistencia.Excepciones.persistenciaException;

/**
 *
 * @author luiscarlosbeltran
 */
public interface IPedidoExpressDAO {
    void insertarPedidoExpress(PedidoExpressDTO pedidoExpress) throws persistenciaException;
    
}