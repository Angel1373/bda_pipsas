/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio.BOs;

import Negocio.DTOs.PedidoDTO;
import Negocio.Excepciones.negocioException;
import persistencia.Dominio.Pedido;

/**
 *
 * @author Gael Galaviz
 */
public interface IPedidoBO {
    public Pedido insertarPedido(PedidoDTO pedido) throws negocioException;
    
}
