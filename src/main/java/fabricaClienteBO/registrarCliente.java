/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fabricaClienteBO;

import Negocio.BOs.ClienteBO;
import persistencia.DAOs.ClienteDAO;
import persistencia.DAOs.DomicilioDAO;
import persistencia.DAOs.IClienteDAO;
import persistencia.DAOs.IDomicilioDAO;
import persistencia.DAOs.ITelefonoDAO;
import persistencia.DAOs.IUsuarioDAO;
import persistencia.DAOs.TelefonoDAO;
import persistencia.DAOs.UsuarioDAO;
import persistencia.conexion.ConexionBD;
import persistencia.conexion.IConexionBD;

/**
 *
 * @author Usuario
 * Clase para que el dao no se muestre en el gui y es para crear BOs
 *
 */
public class registrarCliente {
    
    public static ClienteBO registrarCliente(){
         IConexionBD conexion = new ConexionBD();

        IClienteDAO clienteDAO = new ClienteDAO(conexion);
        IUsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
        IDomicilioDAO domicilioDAO = new DomicilioDAO(conexion);
        ITelefonoDAO telefonoDAO = new TelefonoDAO(conexion);

        return new ClienteBO(clienteDAO, usuarioDAO, domicilioDAO, telefonoDAO);

    }
    
}
