/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia.DAOs;

import persistencia.Dominio.Pedido;
import persistencia.Excepciones.persistenciaException;


/**
 *
 * @author Gael Galaviz
 */
public interface IPedidoDAO {
    Pedido agregarPedido(Pedido pedido) throws persistenciaException;
    
}
