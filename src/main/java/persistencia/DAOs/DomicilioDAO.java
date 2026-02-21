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
import persistencia.Dominio.Domicilio;
import persistencia.Excepciones.persistenciaException;
import persistencia.conexion.IConexionBD;

/**
 *
 * @author Usuario
 */
public class DomicilioDAO implements IDomicilioDAO {

    
    private final IConexionBD conexionBD;
    
    private static final Logger LOG = Logger.getLogger(DomicilioDAO.class.getName());
       
   
    public DomicilioDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
  
    @Override
    public Domicilio agregarDomicilio(Domicilio domicilio) throws persistenciaException {
         String sql = "INSERT INTO domicilios (id_cliente, calle, colonia, numero) VALUES (?, ?, ?, ?)";

        try (Connection conn = conexionBD.CrearConexion();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, domicilio.getId_cliente()); // viene de Usuario
            ps.setString(2, domicilio.getCalle());
            ps.setString(3, domicilio.getColonia());
            ps.setString(4, domicilio.getNumeroCasa());

            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new persistenciaException("No se insertó el domicilio");
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    domicilio.setId_domicilio(rs.getInt(1));
                }
            }

            return domicilio;

        } catch (SQLException e) {
            throw new persistenciaException("Error al insertar domicilio", e);
        }
    }

    @Override
    public Domicilio actualizarDomicilio(Domicilio domicilio) throws persistenciaException {
        
    String sql = """
                 UPDATE domicilios
                 SET calle = ?, colonia = ?, numero = ?
                 WHERE id_domicilio = ?
                 """;

    try (Connection conn = conexionBD.CrearConexion();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, domicilio.getCalle());
        ps.setString(2, domicilio.getColonia());
        ps.setString(3, domicilio.getNumeroCasa());
        ps.setInt(4, domicilio.getId_domicilio());

        int filas = ps.executeUpdate();

        if (filas == 0) {
            throw new persistenciaException("No se pudo actualizar el domicilio, ID inexistente");
        }

        return domicilio;

    } catch (SQLException e) {
        throw new persistenciaException("Error al actualizar domicilio", e);
    }
    }

    @Override
    public Domicilio consultarDomicilio(Domicilio domicilio) throws persistenciaException {
          String sql = """
                 SELECT id_domicilio, id_cliente, calle, colonia, numero
                 FROM domicilios
                 WHERE id_domicilio = ?
                 """;

    try (Connection conn = conexionBD.CrearConexion();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, domicilio.getId_domicilio());

        try (ResultSet rs = ps.executeQuery()) {

            if (!rs.next()) {
                throw new persistenciaException("No existe el domicilio con ese ID");
            }

            return extraerDomicilio(rs);
        }

    } catch (SQLException e) {
        throw new persistenciaException("Error al consultar domicilio", e);
    }
    }
    // lo mismo que el de cliente, tomamos los valores y lo hacemos un objeto y lo regresamos
        private Domicilio extraerDomicilio(ResultSet rs) throws SQLException {

        Domicilio d = new Domicilio();

        d.setId_domicilio(rs.getInt("id_domicilio"));
        d.setId_cliente(rs.getInt("id_cliente"));
        d.setCalle(rs.getString("calle"));
        d.setColonia(rs.getString("colonia"));
        d.setNumeroCasa(rs.getString("numero"));

        return d;
        }   
    
    }
    
    

