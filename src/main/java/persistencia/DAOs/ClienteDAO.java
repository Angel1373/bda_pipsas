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
import java.time.LocalDate;
import java.time.Period;
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
        (id_cliente, nombres, apellido_paterno, apellido_materno, estado, fecha_nacimiento, edad)
        VALUES (?, ?, ?, ?, ?, ?, ?)
    """;

    try (Connection conn = conexionBD.CrearConexion();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, cliente.getIdCliente()); // viene de Usuario
        ps.setString(2, cliente.getNombres());
        ps.setString(3, cliente.getApellidoPaterno());
        ps.setString(4, cliente.getApellidoMaterno());
        ps.setString(5, cliente.getEstado().getValor().toLowerCase()); // "activo" o "inactivo"
        ps.setDate(6, Date.valueOf(cliente.getFechaNacimiento()));
        ps.setInt(7, Period.between(cliente.getFechaNacimiento(), LocalDate.now()).getYears());

        int filas = ps.executeUpdate();
        if (filas == 0) {
            throw new persistenciaException("No se insertó el cliente");
        }

        return cliente;

    } catch (SQLException e) {
        throw new persistenciaException("Error al insertar cliente", e);
    }
    
}

    @Override
    public Cliente actualizarCliente(Cliente cliente) throws persistenciaException {
        
        String comandoSQL = """
                            UPDATE clientes
                            SET nombres = ?, apellido_paterno = ?, apellido_materno = ?, estado = ?, fecha_nacimiento = ?, edad = ?
                            WHERE id_cliente = ?
                            """;

        try (Connection conn = this.conexionBD.CrearConexion(); PreparedStatement ps = conn.prepareStatement(comandoSQL)) {

            ps.setString(1, cliente.getNombres());
            ps.setString(2, cliente.getApellidoPaterno());
            // pendiente agregar la parte de gestionar nulos seguros
            ps.setString(3, cliente.getApellidoMaterno());
            ps.setString(4, cliente.getEstado().getValor());
            // pendiente agregar la parte de gestionar nulos seguros
            ps.setDate(5, java.sql.Date.valueOf(cliente.getFechaNacimiento()));
            ps.setInt(6, cliente.getEdad());
            ps.setInt(7, cliente.getIdCliente());

            if (ps.executeUpdate() == 0) {
                throw new persistenciaException("No se pudo actualizar: el ID proporcionado no existe.");
            }

            return cliente;

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error de SQL al actualizar el ", ex);
            throw new persistenciaException("Error al actualizar cliente", ex);        }
    }

    @Override
    public Cliente consultarCliente(Cliente cliente) throws persistenciaException {
        String comandoSQL = """
                            SELECT id_cliente, nombres, apellido_paterno, apellido_materno, estado, fecha_nacimiento,edad
                            FROM clientes
                            WHERE id_cliente = ?
                            """;

        try (Connection conn = this.conexionBD.CrearConexion(); PreparedStatement ps = conn.prepareStatement(comandoSQL)) {

            ps.setInt(1, cliente.getIdCliente());

            try (ResultSet rs = ps.executeQuery()) {

                if (!rs.next()) {
                    LOG.log(Level.WARNING, "No se encontró el cliente con id {0}", cliente.getIdCliente());
                    throw new persistenciaException("No existe el cliente con el ID proporcionado.");
                }

                return extraerCliente(rs);
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error de SQL al consultar el cliente", ex);
            throw new persistenciaException("No se pudo consultar ese cliente");
        }    
    }
    
    private Cliente extraerCliente(ResultSet rs) throws SQLException{
        
    Cliente c = new Cliente();
    c.setIdCliente(rs.getInt("id_cliente"));
    c.setNombres(rs.getString("nombres"));
    c.setApellidoPaterno(rs.getString("apellido_paterno"));
    c.setApellidoMaterno(rs.getString("apellido_materno"));

    // ENUM
    c.setEstado(Cliente.EstadoCliente.valueOf(rs.getString("estado").toUpperCase()));

    c.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
    c.setEdad(rs.getInt("edad"));

    return c;
        
    }
}
