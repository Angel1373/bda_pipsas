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
    
    }
    
    

