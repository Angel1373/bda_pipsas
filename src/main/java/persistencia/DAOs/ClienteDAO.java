/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.DAOs;

import java.sql.Connection;
import java.sql.Date;
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
            String sql = """
             INSERT INTO clientes
             (nombres, apellido_paterno, apellido_materno, estado, usuario,
              fecha_nacimiento, edad, contrasena, id_domicilio)
             VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
             """;

         try (
             Connection conn = conexionBD.CrearConexion();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
         ) {

             ps.setString(1, cliente.getNombres());
             ps.setString(2, cliente.getApellidoPaterno());
             ps.setString(3, cliente.getApellidoMaterno());

             // tomamos el valor del enum con el get valor
             ps.setString(4, cliente.getEstado().getValor());

             ps.setString(5, cliente.getUsuario());
             ps.setDate(6, Date.valueOf(cliente.getFechaNacimiento()));
             ps.setInt(7, cliente.getEdad());
             ps.setString(8, cliente.getContrasena());
             ps.setInt(9, cliente.getIdDomicilio());

             int filas = ps.executeUpdate();

             if (filas == 0) {
                 throw new persistenciaException("No se insertó el cliente");
             }

             try (ResultSet rs = ps.getGeneratedKeys()) {
                 if (rs.next()) {
                     cliente.setIdCliente(rs.getInt(1));
                 }
             }

             return cliente;

         } catch (SQLException e) {
             throw new persistenciaException("Error al insertar cliente", e);
         }
     }
    
}
