
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.BOs;

import Negocio.DTOs.DetallePedidoDTO;
import Negocio.DTOs.PedidoDTO;
import Negocio.Excepciones.negocioException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.DAOs.IPedidoDAO;
import persistencia.DAOs.IPizzaEnPedidoDAO;
import persistencia.DAOs.PizzaEnPedidoDAO;
import persistencia.Dominio.Pedido;
import persistencia.Excepciones.persistenciaException;

/**
 *
 * @author Gael Galaviz
 */
public class PedidoBO implements IPedidoBO {

    private IPedidoDAO pedidoDAO;
    private final Logger Log = Logger.getLogger(PedidoBO.class.getName());

    public PedidoBO(IPedidoDAO pedidoDAO) {
        this.pedidoDAO = pedidoDAO;
    }

    @Override
    public Pedido insertarPedido(PedidoDTO pedido) throws negocioException {
        // 1. Validar que el pedido no sea nulo
        if (pedido == null) {
            Log.warning("Pedido nulo");
            throw new negocioException("El pedido no puede ser nulo");
        }
        // 2. Validar que el estado exista y no este vacio
        if (pedido.getEstado() == null || pedido.getEstado().trim().isEmpty()) {
            Log.warning("Estado vacio");
            throw new negocioException("El estado es obligatorio");
        }
        // 3. Validar que el costo sea mayor a 0
        if (pedido.getCosto() <= 0) {
            Log.warning("Costo invalido");
            throw new negocioException("El costo debe ser mayor a 0");
        }

        Pedido nuevoPedido = new Pedido();
        // Convertimos el estado recibido como String a el ENUM estadoPedido del dominio
        try {
            Pedido.estadoPedido estadoEnum = Pedido.estadoPedido.valueOf(pedido.getEstado().toUpperCase());
            nuevoPedido.setEstado(estadoEnum);
        } // Si el estado no coincide con el enum, se lanza una excepcion
        catch (IllegalArgumentException e) {
            throw new negocioException("Estado invalido");
        }
        // Asignamos los atributos de notas y el costo
        nuevoPedido.setNotas(pedido.getNotas());
        nuevoPedido.setCosto(pedido.getCosto());

        try {
            return pedidoDAO.agregarPedido(nuevoPedido);
        } catch (persistenciaException e) {
            Log.log(Level.SEVERE, "Error al insertar pedido", e);

            throw new negocioException("Error al guardar el pedido", e);
        }
    }
    
    @Override
    public void confirmarPedidoCompleto(String notas, List<DetallePedidoDTO> detalles) throws negocioException {
        if (detalles == null || detalles.isEmpty()) {
            throw new negocioException("El pedido no tiene productos");
        }
        double total = detalles.stream().mapToDouble(DetallePedidoDTO::getSubtotal).sum();
        Pedido pedido = new Pedido();
        pedido.setEstado(Pedido.estadoPedido.PENDIENTE);
        pedido.setNotas(notas);
        pedido.setCosto(total);
        try {
            pedidoDAO.agregarPedidoCompleto(pedido, detalles);
        } catch (persistenciaException e) {
            throw new negocioException("Error al guardar pedido completo", e);
        }
    }
    
    
    @Override
    public List<PedidoDTO> obtenerPedidos() throws negocioException {

        try {
            List<Pedido> pedidos = pedidoDAO.obtenerPedidos();
            List<PedidoDTO> listaDTO = new ArrayList<>();

            for (Pedido p : pedidos) {
                PedidoDTO dto = new PedidoDTO();
                dto.setIdPedido(p.getIdPedido());
                dto.setEstado(p.getEstado().getValor());
                dto.setNotas(p.getNotas());
                dto.setCosto(p.getCosto());
                listaDTO.add(dto);
            }
            return listaDTO;            
        } catch (persistenciaException e) {
                throw new negocioException("Error al obtener pedidos", e);
            }
    }

    @Override
    public void actualizarEstadoPedido(PedidoDTO pedido) throws negocioException {
        // 1. Validacion: el objeto no puede ser nulo
        if(pedido == null){
            Log.warning("Pedido nulo");
            throw new negocioException("El pedido no puede ser nulo");
            
        }
        // 2. Validacion: el ID debe ser valido
        if(pedido.getIdPedido() <= 0){
            throw new negocioException("ID de pedido invalido");
        }
        // 3. Validacion: el estado es obligatorio
        if(pedido.getEstado() == null || pedido.getEstado().trim().isEmpty()){
            throw new negocioException("El estado es obligatorio");
        }
        // Se crea la entidad de dominio
        Pedido pedidos = new Pedido();
        pedidos.setIdPedido(pedido.getIdPedido());
        // Convierte el String recibido del GUI a un ENUM del sistema
        try{Pedido.estadoPedido estadoEnum = Pedido.estadoPedido.fromValor(pedido.getEstado());
        // Se asigna el estado convertido
        pedidos.setEstado(estadoEnum);
        
        }catch(IllegalArgumentException e){
            // Si el estado no existe en el enum
            throw new negocioException("Estado invalido");
            
        }
        try{pedidoDAO.actualizarEstadoPedido(pedidos);
        } catch(persistenciaException e){
            // Registro del error en logs
            Log.log(Level.SEVERE, "Error al actualizar estado", e);
            // Se lanza excepcion de negocio hacia la GUI
            throw new negocioException("No se puede actualizar el estado pedido", e);
        }
        
    }
}
