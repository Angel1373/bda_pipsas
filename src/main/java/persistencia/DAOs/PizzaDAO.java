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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.Dominio.Pizza;
import persistencia.Excepciones.persistenciaException;
import persistencia.conexion.IConexionBD;

/**
 *
 * @author Usuario
 */
public class PizzaDAO implements IPizzaDAO {

    private final IConexionBD conexionBD;

    private static final Logger LOG = Logger.getLogger(PizzaDAO.class.getName());

    public PizzaDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public Pizza agregarPizza(Pizza pizza) throws persistenciaException {

        String comandoSQL = """
                            INSERT INTO pizzas (nombre, tamano, descripcion, precio,disponible)
                            VALUES (?,?,?,?,?)
                            """;

        try (Connection conn = this.conexionBD.CrearConexion(); PreparedStatement ps = conn.prepareStatement(comandoSQL, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, pizza.getNombre());
            ps.setString(2, pizza.getTamano());
            ps.setString(3, pizza.getDescripcion());
            ps.setDouble(4, pizza.getPrecio());
            ps.setBoolean(5, pizza.isDisponible());

            //ejecutamos el comando usando el preparedStatement
            int filasInsertadas = ps.executeUpdate();

            // == 0 si no se registro nada y == 1 si se registro 
            if (filasInsertadas == 0) {
                LOG.log(Level.WARNING, "No se pudo insertar al Técnico: {0}", pizza);
                throw new persistenciaException("No se pudo insertar la pizza.");
            }
            //obtenemos el id generado automaticamente y lo leemos con el rs
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    //obtenemos la primera columna que es el id generado y se lo ponemos a la pizza
                    // se obtiene el id generado por el insert del metodo por la base de datos lo creo
                    pizza.setIdPizza(rs.getInt(1));
                } else {
                    throw new persistenciaException("Error al obtener el ID generado de la nueva pizza.");
                }
            }

            LOG.log(Level.INFO, "Pizza insertada con éxito. ID: {0}", pizza.getIdPizza());
            return pizza;

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error de SQL al insertar a la pizza", ex);
            throw new persistenciaException("No se pudo agregar la pizza");
        }

    }

    @Override
    public Pizza actualizarPizza(Pizza pizza) throws persistenciaException {
        String comandoSQL = """
                            UPDATE pizzas
                            SET nombre = ?, tamano = ?, descripcion = ?, precio = ?, disponible = ?
                            WHERE id_pizza = ?
                            """;
        try (Connection conn = this.conexionBD.CrearConexion(); PreparedStatement ps = conn.prepareStatement(comandoSQL)) {
            ps.setString(1, pizza.getNombre());
            ps.setString(2, pizza.getTamano());
            ps.setString(3, pizza.getDescripcion());
            ps.setDouble(4, pizza.getPrecio());
            ps.setBoolean(5, pizza.isDisponible());
            ps.setInt(6, pizza.getIdPizza());
            
            if(ps.executeUpdate() == 0){
                throw new persistenciaException("No se pudo actualizar: el ID de pizza no existe.");
                
            }
            return pizza;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error de SQL al actualizar pizza ", ex);
            throw new persistenciaException("Error al actualizar pizza", ex);
        }
                    
    }
    
    @Override
    public List<Pizza> obtenerPizzasDisponibles() throws persistenciaException {
        List<Pizza> lista = new ArrayList<>();

        String sql = "SELECT * FROM pizzas WHERE disponible = 1";

        try (Connection conn = conexionBD.CrearConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            

            while (rs.next()) {
                Pizza pizza = new Pizza();
                pizza.setIdPizza(rs.getInt("id_pizza"));
                pizza.setNombre(rs.getString("nombre"));
                pizza.setTamano(rs.getString("tamano"));
                pizza.setDescripcion(rs.getString("descripcion"));
                pizza.setPrecio(rs.getDouble("precio"));
                pizza.setDisponible(rs.getBoolean("disponible"));

                lista.add(pizza);
            }

            return lista;

        } catch (SQLException e) {
            throw new persistenciaException("Error al obtener pizzas disponibles", e);
        }
    }
    
    
    @Override
    public List<Pizza> obtenerTodasPizzas() throws persistenciaException {
        List<Pizza> lista = new ArrayList<>();
        String sql = "SELECT * FROM pizzas";

        try (Connection conn = conexionBD.CrearConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pizza pizza = new Pizza();
                pizza.setIdPizza(rs.getInt("id_pizza"));
                pizza.setNombre(rs.getString("nombre"));
                pizza.setTamano(rs.getString("tamano"));
                pizza.setDescripcion(rs.getString("descripcion"));
                pizza.setPrecio(rs.getDouble("precio"));
                pizza.setDisponible(rs.getBoolean("disponible"));
                lista.add(pizza);
            }

            return lista;
        } catch (SQLException e) {
            throw new persistenciaException("Error al obtener todas las pizzas", e);
        }
    }

}
