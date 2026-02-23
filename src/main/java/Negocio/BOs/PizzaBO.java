/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.BOs;

import Negocio.DTOs.PizzaDTO;
import Negocio.Excepciones.negocioException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.DAOs.IPizzaDAO;
import persistencia.Dominio.Pizza;
import persistencia.Excepciones.persistenciaException;

/**
 *
 * @author Gael Galaviz
 */
public class PizzaBO implements IPizzaBO {

    private IPizzaDAO pizzaDAO;
    private final Logger Log = Logger.getLogger(PizzaBO.class.getName());

    public PizzaBO(IPizzaDAO pizzaDAO) {
        this.pizzaDAO = pizzaDAO;
    }
    
     private static final Logger LOG = Logger.getLogger(PizzaBO.class.getName());

    @Override
    public Pizza insertarPizza(PizzaDTO pizza) throws negocioException {
        // 1. Validar que la pizza no sea nula
        if (pizza == null) {
            Log.warning("Pizza nula");
            throw new negocioException("La pizza no puede ser nula");
        }
        // 2. Validar que el nombre exista
        if (pizza.getNombre() == null || pizza.getNombre().trim().isEmpty()) {
            throw new negocioException("El nombre es obligatorio");
        }
        // 3. Validar que el tamaño exista
        if (pizza.getTamano() == null || pizza.getTamano().trim().isEmpty()) {
            throw new negocioException("el tamano es obligatorio");
        }
        // 4. Validar que el precio sea mayor a 0
        if (pizza.getPrecio() <= 0) {
            throw new negocioException("El precio debe de ser mayor a 0");

        }
        // 5. Validar que la descripcion exista
        if (pizza.getDescripcion() == null || pizza.getDescripcion().trim().isEmpty()) {
            throw new negocioException("La descripcion es obligatorio");
        }
        // Crear el objeto dominio Pizza
        Pizza nuevaPizza = new Pizza();
        // Asignamos los valores 
        nuevaPizza.setNombre(pizza.getNombre());
        nuevaPizza.setTamano(pizza.getTamano());
        nuevaPizza.setPrecio(pizza.getPrecio());
        nuevaPizza.setDescripcion(pizza.getDescripcion());
        nuevaPizza.setDisponible(pizza.isDisponible());
        try {
            return pizzaDAO.agregarPizza(nuevaPizza);
        } catch (persistenciaException e) {
            Log.log(Level.SEVERE, "Error al insertar la pizza", e);

            throw new negocioException("Error al guardar la pizza", e);
        }
    }

    @Override
    public Pizza actualizarPizza(PizzaDTO pizza) throws negocioException {
    // 1. Validar que la pizza no sea nula
    if (pizza == null) {
            Log.warning("Pizza nula");
            throw new negocioException("La pizza no puede ser nula");
        }
        // 2. Validar que exista un ID valido para actualizar
         if (pizza.getId_pizza() <= 0 ) {
             throw new negocioException("El ID de la pizza es obligatorio para actualizar");

        }
        // Validar nombre
        if (pizza.getNombre() == null || pizza.getNombre().trim().isEmpty()) {
            throw new negocioException("El nombre es obligatorio");
        }
        // Validar tamano
        if (pizza.getTamano() == null || pizza.getTamano().trim().isEmpty()) {
            throw new negocioException("el tamano es obligatorio");
        }
        // Validar precio
        if (pizza.getPrecio() <= 0) {
            throw new negocioException("El precio debe de ser mayor a 0");

        }
        //validar descripcion
        if (pizza.getDescripcion() == null || pizza.getDescripcion().trim().isEmpty()) {
            throw new negocioException("La descripcion es obligatorio");
        }
        // Crear el objeto dominio 
        Pizza pizzaActualizar = new Pizza();
        // Asignamos los valores
        pizzaActualizar.setIdPizza(pizza.getId_pizza());
        pizzaActualizar.setNombre(pizza.getNombre());
        pizzaActualizar.setTamano(pizza.getTamano());
        pizzaActualizar.setPrecio(pizza.getPrecio());
        pizzaActualizar.setDescripcion(pizza.getDescripcion());
        pizzaActualizar.setDisponible(pizza.isDisponible());
        
        try {
            return pizzaDAO.actualizarPizza(pizzaActualizar);
        } catch (persistenciaException e) {
            Log.log(Level.SEVERE, "Error al actualizar la pizza", e);

            throw new negocioException("No se pudo actualizar la pizza", e);
        }
    }
    
    @Override
    public List<PizzaDTO> obtenerPizzasDisponibles() throws negocioException {
        try {
            List<Pizza> pizzas = pizzaDAO.obtenerPizzasDisponibles();
            List<PizzaDTO> listaDTO = new ArrayList<>();
            for (Pizza p : pizzas) {
                PizzaDTO dto = new PizzaDTO();
                dto.setId_pizza(p.getIdPizza());
                dto.setNombre(p.getNombre());
                dto.setTamano(p.getTamano());
                dto.setDescripcion(p.getDescripcion());
                dto.setPrecio(p.getPrecio());
                dto.setDisponible(p.isDisponible());
                listaDTO.add(dto);
            }
            return listaDTO;
        } catch (persistenciaException e) {
            throw new negocioException("Error al obtener pizzas", e);
        }
    }
    
    //lo unico que cabia con el de arriba es que el dao de este obtiene todas pas pizzas, sin el where disponible = 1
    @Override
    public List<PizzaDTO> obtenerTodasPizzas() throws negocioException {
        try {
            List<Pizza> pizzas = pizzaDAO.obtenerTodasPizzas();
            List<PizzaDTO> listaDTO = new ArrayList<>();

            for (Pizza p : pizzas) {
                PizzaDTO dto = new PizzaDTO();
                dto.setId_pizza(p.getIdPizza());
                dto.setNombre(p.getNombre());
                dto.setTamano(p.getTamano());
                dto.setDescripcion(p.getDescripcion());
                dto.setPrecio(p.getPrecio());
                dto.setDisponible(p.isDisponible());
                listaDTO.add(dto);
            }
            return listaDTO;
        } catch (persistenciaException e) {
            throw new negocioException("Error al obtener todas las pizzas", e);
        }
    }

    @Override
    public Pizza actualizarDisponibleYPrecio(PizzaDTO pizza) throws negocioException {
        
        if(pizza == null){
            LOG.warning("La pizza no puede ser null");
            throw new negocioException("Error al actualizar la pizza");
        }
        if(pizza.getPrecio() <= 0){
            LOG.warning("El precio tiene que ser mayor a 0");
            throw new negocioException("Error al intentar modificar el precio");
        }
        try{
       Pizza pizzaCambiar = new Pizza();
       pizzaCambiar.setIdPizza(pizza.getId_pizza());
       pizzaCambiar.setPrecio(pizza.getPrecio());
       pizzaCambiar.setDisponible(pizza.isDisponible());
       pizzaDAO.actualizarDisponibleYPrecio(pizzaCambiar);
       return pizzaCambiar;
    }catch(persistenciaException ex){
    throw new negocioException("Error al actualizar la disponibilidad y el precio de la pizza", ex);
 }
        
}
}


