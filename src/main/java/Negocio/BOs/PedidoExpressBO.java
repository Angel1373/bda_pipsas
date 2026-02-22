/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.BOs;

import Negocio.DTOs.DetallePedidoDTO;
import Negocio.DTOs.PedidoExpressDTO;
import Negocio.Excepciones.negocioException;
import java.util.List;
import java.util.Random;
import persistencia.DAOs.IPedidoDAO;
import persistencia.DAOs.IPedidoExpressDAO;
import persistencia.Dominio.Pedido;
import persistencia.Excepciones.persistenciaException;

/**
 *
 * @author luiscarlosbeltran
 */
public class PedidoExpressBO {
    private IPedidoDAO pedidoDAO;
    private IPedidoExpressDAO pedidoExpressDAO;

    public PedidoExpressBO(IPedidoDAO pedidoDAO, IPedidoExpressDAO pedidoExpressDAO) {
        this.pedidoDAO = pedidoDAO;
        this.pedidoExpressDAO = pedidoExpressDAO;
    }

    public PedidoExpressDTO confirmarPedidoExpress(String notas, List<DetallePedidoDTO> detalles) throws negocioException {
        try {
            Pedido pedido = new Pedido();
            pedido.setEstado(Pedido.estadoPedido.PENDIENTE);
            pedido.setNotas(notas);
            pedido.setCosto(detalles.stream().mapToDouble(DetallePedidoDTO::getSubtotal).sum());

            Pedido pedidoGuardado = pedidoDAO.agregarPedidoCompleto(pedido, detalles);

            Random random = new Random();

            //el nip de 8 digitos aleatorio
            int nip = 10000000 + random.nextInt(90000000);

            //el folio con exp + id del pedido + numeros aletorio
            String folio = "EXP-" + pedidoGuardado.getIdPedido() + "-" + random.nextInt(1000);
            
            PedidoExpressDTO expressDTO = new PedidoExpressDTO();
            expressDTO.setIdPedido(pedidoGuardado.getIdPedido());
            expressDTO.setNip(nip);
            expressDTO.setFolio(folio);

            pedidoExpressDAO.insertarPedidoExpress(expressDTO);

            return expressDTO;

            } catch (persistenciaException e) {
                throw new negocioException("Error al guardar pedido express", e);
        }
    }
}