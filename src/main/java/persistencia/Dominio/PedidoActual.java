/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.Dominio;

import Negocio.DTOs.DetallePedidoDTO;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author luiscarlosbeltran
 */

//esta clase es como el carrito que almacena los DetallePedidoDTO (instancias del producto que se mete en el carrito)
public class PedidoActual {
    private static List<DetallePedidoDTO> detalles = new ArrayList<>();
    
    //mete una pizza al pedido, como cuando se agrega un producto al carrito
    public static void agregarDetalle(DetallePedidoDTO detalle) { 
        detalles.add(detalle);
    }
    
    //devuelve todas las pizzas agregadas al pedido para que la interfaz MiPedido las use para hacer las tarjetitas de presentacion
    public static List<DetallePedidoDTO> getDetalles() {
        return detalles;
    }
    
    //agarra los subtotales de cada pizza en el pedido y devuelve una suma, que es el total
    public static double calcularTotal() {
        return detalles.stream().mapToDouble(DetallePedidoDTO::getSubtotal).sum();
    }
    
    //quita todos los objetos del carrito, para cuando se confirme el pedido quitara todo
    public static void limpiar() {
        detalles.clear();
    }
}
