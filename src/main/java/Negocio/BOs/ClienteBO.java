/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.BOs;

import Negocio.Excepciones.negocioException;
import java.time.LocalDate;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import persistencia.DAOs.IClienteDAO;
import persistencia.Dominio.Cliente;

/**
 *
 * @author Usuario
 */
public class ClienteBO implements IClienteBO {
    
    private IClienteDAO clienteDAO;
    
    // constructor para mandar a llamar al DAO
    public ClienteBO(IClienteDAO cliente){
       this.clienteDAO = cliente; 
    }
    private static final Logger LOG = Logger.getLogger(ClienteBO.class.getName());

    

    @Override
    public Cliente insertarCliente(Cliente cliente) throws negocioException {
         // aplicar todas las reglas de negocio para consultar un negocio
        // 1. validar que el objeto NO sea nulo
        if(cliente == null){
            LOG.warning("El tecnico era un objeto Nulo");
            throw new negocioException("El tecnico no puede ser un objeto Nulo");
        }
        // 2. Validamos que el tecnico no tenga un id ya asignado
        if(cliente.getIdCliente()!=null){
            LOG.warning("El cliente no puede tener un id ya asignado");
            throw new negocioException("El cliente no puede tener un id ya asignado");
        }
        // 3. validar que los nombres no sean null, esten vacios o solo espacios
        if(!validarNombres(cliente.getNombres())){
            LOG.warning("Se ingreso el nombre con formato invalido.");
            throw new negocioException("El nombre ingresado es invalido por que tiene espacios o esta vacio");
        }// 4. validar que el apellido paterno no sea null, vacio o solo espacios
        if(!validarNombres(cliente.getApellidoPaterno())){
            LOG.warning("Se ingreso el apellido con un formato invalido ");
            throw new negocioException("El apellido ingresado es invalido por que tiene espacios o esta vacio");
        }
        //5. validamos que el apellido materno no sea null, vacio o solo espacios
       if (!validarNombres(cliente.getApellidoMaterno())) {
           LOG.warning("Se ingreso un apellido materno invalido");
             throw new negocioException("El apellido materno es inválido");
        }
        //6. validamos el estado 
         if(cliente.getEstado()!=null){
            LOG.warning("El cliente no puede no tener un estado");
            throw new negocioException("El estado es invalido");
        }
         //6. validamos el usuario
          if(cliente.getUsuario()!=null){
            LOG.warning("El cliente no puede no tener un usuario");
            throw new negocioException("El cliente no puede tener un usuario");
        }
 
        //7. Validamos fecha de nacimiento 
        if(!validarFechaNacimiento(cliente.getFechaNacimiento())){
            LOG.warning("No se pudo registrar la fecha");
            throw new negocioException("Se ingreso una fecha futura o menor a 2020");
        }
        //9. validamos el domicilio
         if(cliente.getIdDomicilio()!=null){
            LOG.warning("El tecnico no puede tener un id ya asignado");
            throw new negocioException("El tecnico no puede tener un id ya asignado");
        }
     
        
    }
    
       
    private boolean validarNombres(String nombres) throws negocioException{
        //verificamos que no sea null
        if(nombres == null){
            return false;
        }             
        String regex = "^(?!\\s*$).+";
        //Usamos trim en nombres y apellidos
        nombres = nombres.trim();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nombres);
        return matcher.matches();      
    }
    // Metodo para validar la fecha de nacimiento que no sea futura
      public boolean validarFechaNacimiento(LocalDate fecha) {
        return fecha.isBefore(LocalDate.now()); 
    }
    
    }
    

