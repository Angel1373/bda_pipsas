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
import persistencia.Dominio.Usuario;
import persistencia.Excepciones.persistenciaException;
import persistencia.conexion.IConexionBD;

/**
 *
 * @author Gael Galaviz
 */
public class UsuarioDAO implements IUsuarioDAO{
    private final IConexionBD conexionBD;
    
    private static final Logger LOG = Logger.getLogger(UsuarioDAO.class.getName());


    public UsuarioDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
    @Override
    public Usuario agregarUsuario(Usuario usuario) throws persistenciaException {
        String sql = "INSERT INTO usuarios (usuario, contrasena) VALUES (?, ?)";

        try (Connection conn = conexionBD.CrearConexion();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getContrasena());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    usuario.setId_cliente(rs.getInt(1)); // Guardamos el ID generado
                } else {
                    throw new persistenciaException("No se obtuvo el ID del usuario");
                }
            }

            return usuario;

        } catch (SQLException e) {
            throw new persistenciaException("Error al insertar usuario", e);
        }
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) throws persistenciaException {
          String sql = """
                 UPDATE usuarios
                 SET usuario = ?, contrasena = ?
                 WHERE id_cliente = ?
                 """;

        try (Connection conn = conexionBD.CrearConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

               ps.setString(1, usuario.getUsuario());
               ps.setString(2, usuario.getContrasena());
               ps.setInt(3, usuario.getId_cliente());

               int filas = ps.executeUpdate();

               if (filas == 0) {
                   throw new persistenciaException("No se pudo actualizar el usuario, ID inexistente");
               }

               return usuario;

           } catch (SQLException e) {
               throw new persistenciaException("Error al actualizar usuario", e);
           }
      }

    @Override
    public Usuario consultarUsuario(Usuario usuario) throws persistenciaException {
         String sql = """
                 SELECT id_cliente, usuario, contrasena
                 FROM usuarios
                 WHERE id_cliente = ?
                 """;

        try (Connection conn = conexionBD.CrearConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, usuario.getId_cliente());

            try (ResultSet rs = ps.executeQuery()) {

                if (!rs.next()) {
                    throw new persistenciaException("No existe el usuario con ese ID");
                }

            return extraerUsuario(rs);
        }

    } catch (SQLException e) {
        throw new persistenciaException("Error al consultar usuario", e);
    }
    }
    // convierte fila de resultset en un objeto usuario
        private Usuario extraerUsuario(ResultSet rs) throws SQLException {

        Usuario u = new Usuario();

        u.setId_cliente(rs.getInt("id_cliente"));
        u.setUsuario(rs.getString("usuario"));
        u.setContrasena(rs.getString("contrasena"));

        return u;
    }

        // metodo que regresa un usuario a partir del usuario
    @Override
    public Usuario consultarUsuarioPorUsuario(Usuario usuario) throws persistenciaException {
      String sql = """
                 SELECT id_cliente, usuario, contrasena
                 FROM usuarios
                 WHERE usuario = ?
                 """;

        try (Connection conn = conexionBD.CrearConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getUsuario());

            try (ResultSet rs = ps.executeQuery()) {

                if (!rs.next()) {
                    throw new persistenciaException("No existe ese usuario");
                }

            return extraerUsuario(rs);
        }

    } catch (SQLException e) {
        throw new persistenciaException("Error al consultar usuario", e);
    }   
    }
    
}
