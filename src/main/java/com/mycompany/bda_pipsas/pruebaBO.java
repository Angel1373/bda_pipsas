/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.bda_pipsas;

import Negocio.BOs.ClienteBO;
import Negocio.BOs.IClienteBO;
import Negocio.BOs.PedidoBO;
import Negocio.DTOs.ClienteCompletoDTO;
import Negocio.DTOs.PedidoDTO;
import Negocio.Excepciones.negocioException;
import java.time.LocalDate;
import persistencia.DAOs.ClienteDAO;
import persistencia.DAOs.DomicilioDAO;
import persistencia.DAOs.IClienteDAO;
import persistencia.DAOs.IDomicilioDAO;
import persistencia.DAOs.IPedidoDAO;
import persistencia.DAOs.ITelefonoDAO;
import persistencia.DAOs.IUsuarioDAO;
import persistencia.DAOs.PedidoDAO;
import persistencia.DAOs.TelefonoDAO;
import persistencia.DAOs.UsuarioDAO;
import persistencia.Dominio.Cliente;
import persistencia.Dominio.Pedido;
import persistencia.conexion.ConexionBD;
import persistencia.conexion.IConexionBD;

/**
 *
 * @author Usuario
 */
public class pruebaBO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        

    IConexionBD conexion = new ConexionBD();

    IClienteDAO clienteDAO = new ClienteDAO(conexion);
    IUsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
    IDomicilioDAO domicilioDAO = new DomicilioDAO(conexion);
    ITelefonoDAO telefonoDAO = new TelefonoDAO(conexion);
    IPedidoDAO pedidoDAO = new PedidoDAO(conexion);

    ClienteBO clienteBO = new ClienteBO(clienteDAO, usuarioDAO, domicilioDAO, telefonoDAO);
    PedidoBO pedidoBO = new PedidoBO(pedidoDAO);
    
    //Cliente
    ClienteCompletoDTO dto = new ClienteCompletoDTO();
    dto.setNombres("sss");
    dto.setApellidoPaterno("sss");
    dto.setApellidoMaterno("sss");
    dto.setEstado(Cliente.EstadoCliente.ACTIVO);
    dto.setFechaNacimiento(LocalDate.of(2004, 2, 11));

    dto.setUsuario("sss");
    dto.setContrasena("sss");

    dto.setCalle("rrr");
    dto.setColonia("sss");
    dto.setNumeroCasa("ss");

    dto.setNumeroTelefono("6671234567");
    dto.setEtiqueta("CASA");

    try {
        Cliente clienteGuardado = clienteBO.insertarCliente(dto);
        System.out.println("Cliente guardado con ID: " + clienteGuardado.getIdCliente());
    } catch (negocioException e) {
        System.out.println("Error: " + e.getMessage());
    }
    /*
    
    
    // Pedido
    PedidoDTO pedidoDTO = new PedidoDTO();
    pedidoDTO.setEstado("pendiente");
    pedidoDTO.setNotas("Pizza pepperoni con extra queso");
    pedidoDTO.setCosto(199.99);
    
        try {Pedido pedidoGuardado = pedidoBO.insertarPedido(pedidoDTO);
        System.out.println( "Pedido guardado con ID: " + pedidoGuardado.getIdPedido());
        } catch (negocioException e) {
            System.out.println("Error pedido: " + e.getMessage());
        }
 */
       ClienteCompletoDTO dto2 = new ClienteCompletoDTO();

        dto2.setIdCliente(1);

        dto2.setNombres("Prueba");
        dto2.setApellidoPaterno("Actualizar");
        dto2.setApellidoMaterno("Pelon");
        dto2.setEstado(Cliente.EstadoCliente.INACTIVO);
        dto2.setFechaNacimiento(LocalDate.of(2012, 3, 14));
        dto2.setEdad(66);

        // teléfono
        dto2.setNumeroTelefono("1234567890");
        dto2.setEtiqueta("pelon");

        // domicilio
        dto2.setCalle("caso");
        dto2.setColonia("caso");
        dto2.setNumeroCasa("111");

        // usuario
        dto2.setUsuario("Prueba");
        dto2.setContrasena("4321");
        try{
            clienteBO.actualizarCliente(dto2);
        }catch (negocioException e) {
            System.out.println("Error pedido: " + e.getMessage());
        }
        
        
      /*     
     try{
        
        Cliente cliente = new Cliente();
        cliente.setIdCliente(1); 
        cliente.setNombres("Angel");
        cliente.setApellidoPaterno("Perez");
        cliente.setApellidoMaterno("Gaxiola");
        cliente.setEstado(Cliente.EstadoCliente.ACTIVO);
        cliente.setFechaNacimiento(LocalDate.of(2000, 5, 10));
        cliente.setEdad(25);

     
        Cliente actualizado = clienteBO.actualizarCliente(cliente);

        System.out.println("Cliente actualizado: " + actualizado.getNombres());

    } catch (negocioException e) {
            System.out.println("error al actualizar el cliente");;
    }
    */
}
}

    


