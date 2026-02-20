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
}
