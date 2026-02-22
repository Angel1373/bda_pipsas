/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import persistencia.Excepciones.persistenciaException;
import persistencia.conexion.IConexionBD;

/**
 *
 * @author luiscarlosbeltran
 */
public class PizzaEnPedidoDAO implements IPizzaEnPedidoDAO {

    private final IConexionBD conexionBD;

    public PizzaEnPedidoDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public void insertarPizzaEnPedido(int idPedido, int idPizza, int cantidad, String notas)
            throws persistenciaException {

        String sql = "insert into pizzasEnPedidos (id_pedido, id_pizza, cantidad_pizza, notas) values (?,?,?,?)";

        try (Connection conn = conexionBD.CrearConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPedido);
            ps.setInt(2, idPizza);
            ps.setInt(3, cantidad);
            ps.setString(4, notas);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new persistenciaException("Error al insertar pizzas en pedido", e);
        }
    }
}