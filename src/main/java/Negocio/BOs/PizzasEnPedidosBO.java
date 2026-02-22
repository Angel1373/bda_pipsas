/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.BOs;

import Negocio.DTOs.PizzasEnPedidosDTO;
import Negocio.Excepciones.negocioException;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.DAOs.PizzaEnPedidoDAO;
import persistencia.Excepciones.persistenciaException;

/**
 *
 * @author Gael Galaviz
 */
public class PizzasEnPedidosBO implements IPizzasEnPedidosBO {
    private PizzaEnPedidoDAO pizzaEnPedidoDAO;
    private final Logger Log = Logger.getLogger(PizzasEnPedidosBO.class.getName());

    public PizzasEnPedidosBO(PizzaEnPedidoDAO pizzaEnPedidoDAO) {
        this.pizzaEnPedidoDAO = pizzaEnPedidoDAO;
    }

    
    
    @Override
    public void insertarPizzaEnPedido(PizzasEnPedidosDTO pizza) throws negocioException {
        if (pizza == null) {
            Log.warning("Pizza en pedido nula");
            throw new negocioException("La pizza en pedido no puede ser nula");
        }
        if (pizza.getIdPedido() <= 0) {
            throw new negocioException("El id del pedido es invalido");
        }

        if (pizza.getIdPizza() <= 0) {
            throw new negocioException("El id de la pizza es invalido");
        }

        if (pizza.getCantidadPizza() <= 0) {
            throw new negocioException("La cantidad debe ser mayor a 0");
        }

        if (pizza.getNotas() != null && pizza.getNotas().trim().isEmpty()) {
            pizza.setNotas(null);
        }

        try {
            pizzaEnPedidoDAO.insertarPizzaEnPedido(pizza.getIdPedido(), pizza.getIdPizza(),
               pizza.getCantidadPizza(), pizza.getNotas());

        } catch (persistenciaException e) {
            Log.log(Level.SEVERE, "Error al insertar pizza en pedido", e);
            throw new negocioException("No se pudo agregar la pizza al pedido", e);
        }
    }
}
