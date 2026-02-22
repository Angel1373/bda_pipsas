/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio.BOs;

import Negocio.DTOs.DetallePedidoDTO;
import Negocio.DTOs.PedidoDTO;
import Negocio.Excepciones.negocioException;
import java.util.List;
import persistencia.Dominio.Pedido;

/**
 *
 * @author Gael Galaviz
 */
public interface IPedidoBO {
    public Pedido insertarPedido(PedidoDTO pedido) throws negocioException;
    
    public void confirmarPedidoCompleto(String notas, List<DetallePedidoDTO> detalles) throws negocioException;
}
