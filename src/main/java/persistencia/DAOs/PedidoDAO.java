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
import persistencia.Dominio.Pedido;
import persistencia.Excepciones.persistenciaException;
import persistencia.conexion.IConexionBD;

/**
 *
 * @author Gael Galaviz
 */
public class PedidoDAO implements IPedidoDAO {

    private final IConexionBD conexionBD;

    private static final Logger LOG = Logger.getLogger(PedidoDAO.class.getName());

    public PedidoDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public Pedido agregarPedido(Pedido pedido) throws persistenciaException {

        String comandoSQL = """
                INSERT INTO pedidos (estado, notas, costo)
                VALUES (?,?,?)
                """;

        try (Connection conn = this.conexionBD.CrearConexion(); PreparedStatement ps = 
                conn.prepareStatement(comandoSQL, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, pedido.getEstado().name().toLowerCase());
            ps.setString(2, pedido.getNotas());
            ps.setDouble(3, pedido.getCosto());
            //ejecutamos el comando usando el preparedStatement
            int filasInsertadas = ps.executeUpdate();
            // == 0 si no se registro nada y == 1 si se registro 
            if (filasInsertadas == 0) {
                LOG.log(Level.WARNING,"No se pudo insertar el pedido: {0}", pedido);
                throw new persistenciaException("No se pudo insertar el pedido.");
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    //obtenemos la primera columna que es el id generado y se lo ponemos al pedido
                    // se obtiene el id generado por el insert del metodo por la base de datos lo creo
                    pedido.setIdPedido(rs.getInt(1));
                } else {
                    throw new persistenciaException("Error al obtener el ID generado del pedido.");
                }
            }

            LOG.log(Level.INFO, "Pedido insertado con exito. ID: {0}", pedido.getIdPedido());
            return pedido;

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE,"Error de SQL al insertar el pedido", ex);
            throw new persistenciaException("No se pudo agregar el pedido", ex);
        }
    }
}
