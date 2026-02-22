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

    @Override
    public Telefono actualizarTelefono(Telefono telefono) throws persistenciaException {
                String sql = "UPDATE telefonos SET numero = ?, etiqueta = ? WHERE id_cliente = ?";

           try (Connection conn = conexionBD.CrearConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

               ps.setString(1, telefono.getNumeroTelefono());
               ps.setString(2, telefono.getEtiqueta());
               ps.setInt(3, telefono.getId_cliente());

               int filas = ps.executeUpdate();
               if (filas == 0) {
                   throw new persistenciaException("No se pudo actualizar el telefono");
               }

               return telefono;

           } catch (SQLException e) {
               throw new persistenciaException("Error al actualizar telefono", e);
           }
    }

    @Override
    public Telefono consultarTelefono(Telefono telefono) throws persistenciaException {
                String sql = """
                         SELECT  numero, etiqueta, id_cliente
                         FROM telefonos
                         WHERE id_cliente = ?
                         """;

            try (Connection conn = conexionBD.CrearConexion();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, telefono.getId_cliente());

                try (ResultSet rs = ps.executeQuery()) {

                    if (!rs.next()) {
                        throw new persistenciaException("No existe el telefono con ese ID");
                    }

                    return extraerTelefono(rs);
                }

            } catch (SQLException e) {
                throw new persistenciaException("Error al consultar telefono", e);
            }
    }
        private Telefono extraerTelefono(ResultSet rs) throws SQLException {

            Telefono t = new Telefono();

            t.setNumeroTelefono(rs.getString("numero"));
            t.setEtiqueta(rs.getString("etiqueta"));
            t.setId_cliente(rs.getInt("id_cliente"));

             return t;
    }
}
    

