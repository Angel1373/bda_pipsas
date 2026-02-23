/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.DAOs;

import java.util.List;
import persistencia.Dominio.Pizza;
import persistencia.Excepciones.persistenciaException;

/**
 *
 * @author Usuario
 */
public interface IPizzaDAO {
    
    Pizza agregarPizza(Pizza pizza) throws persistenciaException;
    Pizza actualizarPizza(Pizza pizza) throws persistenciaException;
    List<Pizza> obtenerPizzasDisponibles() throws persistenciaException;
    public List<Pizza> obtenerTodasPizzas() throws persistenciaException;
}
