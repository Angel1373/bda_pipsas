/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio.BOs;

import Negocio.DTOs.PizzasEnPedidosDTO;
import Negocio.Excepciones.negocioException;

/**
 *
 * @author Gael Galaviz
 */
public interface IPizzasEnPedidosBO {
    public void insertarPizzaEnPedido(PizzasEnPedidosDTO pizza) throws negocioException;
}
