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
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.Dominio.Telefono;
import persistencia.Excepciones.persistenciaException;
import persistencia.conexion.IConexionBD;

/**
 *
 * @author Usuario
 */
public class TelefonoDAO implements ITelefonoDAO{

    
     private final IConexionBD conexionBD;
    private static final Logger LOG = Logger.getLogger(TelefonoDAO.class.getName());
   
       
   
    public TelefonoDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
    @Override
    public Telefono agregarTelefono(Telefono telefono) throws persistenciaException {
         String sql = "INSERT INTO telefonos (numero, etiqueta, id_cliente) VALUES (?, ?, ?)";

            try (Connection conn = conexionBD.CrearConexion();
                 PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                ps.setString(1, telefono.getNumeroTelefono());
                ps.setString(2, telefono.getEtiqueta());
                ps.setInt(3, telefono.getId_cliente()); // viene de Usuario

                int filas = ps.executeUpdate();
                if (filas == 0) {
                    throw new persistenciaException("No se insertó el telefono");
                }

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        telefono.setId_telefono(rs.getInt(1));
                    }
                }

                return telefono;

            } catch (SQLException e) {
                throw new persistenciaException("Error al insertar telefono", e);
            }
        }
}
    

