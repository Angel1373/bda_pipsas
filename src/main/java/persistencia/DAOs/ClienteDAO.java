/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.Dominio.Cliente;
import persistencia.Excepciones.persistenciaException;
import persistencia.conexion.IConexionBD;

/**
 *
 * @author Usuario
 */
public class ClienteDAO implements IClienteDAO{

    private final IConexionBD conexionBD;
    
    private static final Logger LOG = Logger.getLogger(ClienteDAO.class.getName());   
   
    public ClienteDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
 
    // Metodo para agregar un cliente con un insert
    @Override
    public Cliente agregarCliente(Cliente cliente) throws persistenciaException {
        
        
        String comandoSQL = """
                            INSERT INTO clientes (nombres, apellido_paterno, apellido_materno, fecha_nacimiento,contrasena,id_domicilio)
                            VALUES (?,?,?,?,?,?)
                            """;

        try (Connection conn = this.conexionBD.CrearConexion(); PreparedStatement ps = conn.prepareStatement(comandoSQL, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, cliente.getNombres());
            ps.setString(2, cliente.getApellidoPaterno());

            if (cliente.getApellidoMaterno() != null) {
                ps.setString(3, cliente.getApellidoMaterno());
            } else {
                ps.setNull(3, Types.VARCHAR);
            }  
                ps.setDate(4, java.sql.Date.valueOf(cliente.getFechaNacimiento()));
           
                ps.setString(5, cliente.getContrasena());
                ps.setInt(6, cliente.getIdDomicilio());
                
                //ejecutamos el comando usando el preparedStatement
            int filasInsertadas = ps.executeUpdate();
            
            // == 0 si no se registro nada y == 1 si se registro 
            if (filasInsertadas == 0) {
                LOG.log(Level.WARNING, "No se pudo insertar al cliente: {0}", cliente);
                throw new persistenciaException("No se pudo insertar al cliente.");
            }
            //obtenemos el id generado automaticamente y lo leemos con el rs
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    //obtenemos la primera columna que es el id generado y se lo ponemos al cliente
                    // se obtiene el id generado por el insert del metodo por la base de datos lo creo
                    cliente.setIdCliente(rs.getInt(1));
                } else {
                    throw new persistenciaException("Error al obtener el ID generado del nuevo cliente.");
                }
            }

            LOG.log(Level.INFO, "Cliente insertado con éxito. ID: {0}", cliente.getIdCliente());
            return cliente;

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error de SQL al insertar al cliente", ex);
            throw new persistenciaException("No se pudo agregar al cliente");
        }
        
    }
    
}
