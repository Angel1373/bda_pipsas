/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.DAOs;

import Negocio.DTOs.PedidoExpressDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import persistencia.Excepciones.persistenciaException;
import persistencia.conexion.IConexionBD;

/**
 *
 * @author luiscarlosbeltran
 */
public class PedidoExpressDAO implements IPedidoExpressDAO {

    private IConexionBD conexionBD;

    public PedidoExpressDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public void insertarPedidoExpress(PedidoExpressDTO pedidoExpress) throws persistenciaException {

        String sql = "insert into pedidosExpress (id_pedido, nip, folio) values (?, ?, ?)";

        try (Connection conn = conexionBD.CrearConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, pedidoExpress.getIdPedido());
            ps.setInt(2, pedidoExpress.getNip());
            ps.setString(3, pedidoExpress.getFolio());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new persistenciaException("Error al insertar pedido express", e);
        }
    }
}