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

    private static final Logger LOG = Logger.getLogger(PedidoDAO.class.getName());

    public UsuarioDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
    @Override
    public Usuario agregarUsuario(Usuario usuario) throws persistenciaException {
        String comandoSQL = "INSERT INTO usuarios (id_cliente, usuario, contrasena) VALUES (?, ?, ?)";
        
        try (Connection conn = this.conexionBD.CrearConexion(); PreparedStatement ps
                = conn.prepareStatement(comandoSQL, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setInt(1, usuario.getId_cliente());
            ps.setString(2, usuario.getUsuario());
            ps.setString(3, usuario.getContrasena());
            //ejecutamos el comando usando el preparedStatement
            int filasInsertadas = ps.executeUpdate();
            // == 0 si no se registro nada y == 1 si se registro
            if (filasInsertadas == 0){
                    LOG.log(Level.WARNING, "No se pudo insertar el usuario: {0}", usuario);
                    throw new persistenciaException("No se pudo insertar el usuario.");
                }
            // obtenemos el id generado automaticamente y lo leemos con el rs
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    usuario.setId_cliente(rs.getInt(1));
                }
            }

            LOG.log(Level.INFO, "Usuario insertado con exito para cliente ID: {0}",usuario.getId_cliente());

            return usuario;

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error de SQL al insertar el usuario", ex);
            throw new persistenciaException("No se pudo agregar el usuario", ex);
        }
    }
}
