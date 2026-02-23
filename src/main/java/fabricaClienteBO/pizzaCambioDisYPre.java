/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fabricaClienteBO;

import Negocio.BOs.PizzaBO;
import persistencia.DAOs.IPizzaDAO;
import persistencia.DAOs.PizzaDAO;
import persistencia.conexion.ConexionBD;
import persistencia.conexion.IConexionBD;

/**
 *
 * @author Usuario
 */
public class pizzaCambioDisYPre {
    
    
    public static PizzaBO regresarBO(){
        IConexionBD conexion = new ConexionBD();
        IPizzaDAO pizzaDAO = new PizzaDAO(conexion);

       //metodo para regresar el BO para no mostrar el DAO
        return new PizzaBO(pizzaDAO);
    }
          }


    


    

