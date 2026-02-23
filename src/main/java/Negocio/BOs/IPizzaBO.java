/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio.BOs;

import Negocio.DTOs.PizzaDTO;
import Negocio.Excepciones.negocioException;
import java.util.List;
import persistencia.Dominio.Pizza;

/**
 *
 * @author Gael Galaviz
 */
public interface IPizzaBO {
    public Pizza insertarPizza(PizzaDTO pizza) throws negocioException;
    public Pizza actualizarPizza(PizzaDTO pizza) throws negocioException;
    public List<PizzaDTO> obtenerPizzasDisponibles() throws negocioException;
    public List<PizzaDTO> obtenerTodasPizzas() throws negocioException;
    public Pizza actualizarDisponibleYPrecio(PizzaDTO pizza) throws negocioException;
}
