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
}
