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
    dto.setNombres("Juan");
    dto.setApellidoPaterno("Perez");
    dto.setApellidoMaterno("Lopez");
    dto.setEstado(Cliente.EstadoCliente.ACTIVO);
    dto.setFechaNacimiento(LocalDate.of(2000, 5, 10));

    dto.setUsuario("juan123");
    dto.setContrasena("1234");

    dto.setCalle("Av Siempre Viva");
    dto.setColonia("Centro");
    dto.setNumeroCasa("123");

    dto.setNumeroTelefono("6671234567");
    dto.setEtiqueta("CASA");

    try {
        Cliente clienteGuardado = clienteBO.insertarCliente(dto);
        System.out.println("Cliente guardado con ID: " + clienteGuardado.getIdCliente());
    } catch (negocioException e) {
        System.out.println("Error: " + e.getMessage());
    }
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
    }
}
