/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio.BOs;

import Negocio.DTOs.PizzaDTO;
import Negocio.Excepciones.negocioException;
import persistencia.Dominio.Pizza;

/**
 *
 * @author Gael Galaviz
 */
public interface IPizzaBO {
    public Pizza insertarPizza(PizzaDTO pizza) throws negocioException;
    public Pizza actualizarPizza(PizzaDTO pizza) throws negocioException;

}
