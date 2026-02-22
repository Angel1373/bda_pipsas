/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.DAOs;

import persistencia.Excepciones.persistenciaException;

/**
 *
 * @author luiscarlosbeltran
 */
public interface IPizzaEnPedidoDAO {
    void insertarPizzaEnPedido(int idPedido, int idPizza, int cantidad, String notas) throws persistenciaException;
}