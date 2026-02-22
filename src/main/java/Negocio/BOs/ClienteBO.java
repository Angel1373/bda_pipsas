/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.BOs;

import Negocio.DTOs.ClienteCompletoDTO;
import Negocio.DTOs.DomicilioDTO;
import Negocio.DTOs.UsuarioDTO;
import Negocio.Excepciones.negocioException;
import java.time.LocalDate;
import java.time.Period;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import persistencia.DAOs.IClienteDAO;
import persistencia.DAOs.IDomicilioDAO;
import persistencia.DAOs.ITelefonoDAO;
import persistencia.DAOs.IUsuarioDAO;
import persistencia.Dominio.Cliente;
import persistencia.Dominio.Domicilio;
import persistencia.Dominio.Telefono;
import persistencia.Dominio.Usuario;
import persistencia.Excepciones.persistenciaException;

/**
 *
 * @author Usuario
 */
public class ClienteBO implements IClienteBO {
    
    private IClienteDAO clienteDAO;
    private IUsuarioDAO usuarioDAO;
    private IDomicilioDAO domicilioDAO;
    private ITelefonoDAO telefonoDAO;
    // constructor para mandar a llamar al DAO
    public ClienteBO(IClienteDAO cliente){
       this.clienteDAO = cliente; 
    }

    public ClienteBO(IClienteDAO clienteDAO, IUsuarioDAO usuarioDAO, IDomicilioDAO domicilioDAO, ITelefonoDAO telefonoDAO) {
        this.clienteDAO = clienteDAO;
        this.usuarioDAO = usuarioDAO;
        this.domicilioDAO = domicilioDAO;
        this.telefonoDAO = telefonoDAO;
    }
    
    
    
    private static final Logger LOG = Logger.getLogger(ClienteBO.class.getName());

    

    @Override
    public  Cliente insertarCliente(ClienteCompletoDTO cliente) throws negocioException {
         // aplicar todas las reglas de negocio para consultar un negocio
        // 1. validar que el objeto NO sea nulo
        if(cliente == null){
            LOG.warning("El tecnico era un objeto Nulo");
            throw new negocioException("El tecnico no puede ser un objeto Nulo");
        }
        // 2. Validamos que el tecnico no tenga un id ya asignado
        if(cliente.getIdCliente()==null){
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
         if(cliente.getEstado()== null){
            LOG.warning("El cliente no puede no tener un estado");
            throw new negocioException("El estado es invalido");
        }
        //7. Validamos fecha de nacimiento 
        if(!validarFechaNacimiento(cliente.getFechaNacimiento())){
            LOG.warning("No se pudo registrar la fecha");
            throw new negocioException("Se ingreso una fecha futura o menor a 2020");
        }
        //8. validar la calle
            if(cliente.getCalle()== null){
            LOG.warning("El cliente no puede no tener una calle asiganda");
            throw new negocioException("La calle es invalida");
        }
        //9. validar la colonia
            if(cliente.getColonia()== null){
            LOG.warning("El cliente no puede no tener una colonia asignada");
            throw new negocioException("La colonia es invalida");
        }
        //10. validar el numero de casa
            if(cliente.getNumeroCasa()== null){
            LOG.warning("El cliente no puede no tener un numero de casa asignado");
            throw new negocioException("El numero de casa es invalido");
        }
        //11. validar el usuario
            if(cliente.getUsuario()== null){
            LOG.warning("El cliente no puede no tener un usuario");
            throw new negocioException("El usuarrio es invalido");
        }
        //12. validar la contraseña del cliente
            if(cliente.getContrasena()== null){
            LOG.warning("El cliente no puede no tener una contraseña");
            throw new negocioException("La contraseña es invalida");
        }    
        //13. validar la etiqueta del numero de telefono
            if(cliente.getEtiqueta()== null){
            LOG.warning("El cliente no puede no tener una etiqueta");
            throw new negocioException("La etiquetda es invalida");
        }   
        //14. validar la edad
            if(cliente.getEdad()== null){
            LOG.warning("El cliente no puede no tener una edad");
            throw new negocioException("La edad es invalida");
        }
        //15. validar el numero de telefono
            if(cliente.getNumeroTelefono()== null){
            LOG.warning("El cliente no puede no tener un numero de telefono");
            throw new negocioException("El numero de telefono es invalido");
        }   
            
        /*
        En esta parte primero creamos el usuario para poder usar el id cliente en todas las tablas que se ocupen unir
        creamos 2 variables
        la de cliente guardado es para hacer el return y la de id cliente es para dar el cliente en el set
        */
        Cliente clienteGuardado = null;
        Integer idCliente = null;

        try {
            // Insertamos el usuario
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setUsuario(cliente.getUsuario());
            nuevoUsuario.setContrasena(cliente.getContrasena());

            Usuario usuarioGuardado = usuarioDAO.agregarUsuario(nuevoUsuario);
            if (usuarioGuardado == null || usuarioGuardado.getId_cliente() == null) {
                throw new negocioException("No se pudo registrar el usuario");
            }
            idCliente = usuarioGuardado.getId_cliente();

            // Insertamos el cliente
            Cliente clienteNuevo = new Cliente();
            clienteNuevo.setIdCliente(idCliente); //vincula con el id de cliente de usuario que es con el que uniermos todas las tablas
            clienteNuevo.setNombres(cliente.getNombres());
            clienteNuevo.setApellidoPaterno(cliente.getApellidoPaterno());
            clienteNuevo.setApellidoMaterno(cliente.getApellidoMaterno());
            clienteNuevo.setEstado(cliente.getEstado());
            clienteNuevo.setFechaNacimiento(cliente.getFechaNacimiento());
            clienteNuevo.setEdad(calcularEdad(cliente.getFechaNacimiento()));

            clienteGuardado = clienteDAO.agregarCliente(clienteNuevo);

            // insertamos el domicilio
            Domicilio nuevoDomicilio = new Domicilio();
            nuevoDomicilio.setId_cliente(idCliente); // se vincula con el id cliente
            nuevoDomicilio.setCalle(cliente.getCalle());
            nuevoDomicilio.setColonia(cliente.getColonia());
            nuevoDomicilio.setNumeroCasa(cliente.getNumeroCasa());

            domicilioDAO.agregarDomicilio(nuevoDomicilio);

            // insertamos el telefono
            Telefono nuevoTelefono = new Telefono();
            nuevoTelefono.setId_cliente(idCliente); // se vincula con el id cliente
            nuevoTelefono.setNumeroTelefono(cliente.getNumeroTelefono());
            nuevoTelefono.setEtiqueta(cliente.getEtiqueta());

            telefonoDAO.agregarTelefono(nuevoTelefono);

        } catch (persistenciaException e) {
            LOG.log(Level.SEVERE, "Error al insertar cliente completo", e);
            throw new negocioException("Problemas al agregar el cliente completo", e);
        }
        
        // Regresamos el cliente guardado con la variable de arriba 
        // solo regresamos este por las demas tablas se crean y este es donde vienen los datos "importantes"
        return clienteGuardado;
    }
    
    // Metodo para calcular la edad
    private int calcularEdad(LocalDate fechaNacimiento){
    return Period.between(fechaNacimiento, LocalDate.now()).getYears();
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

      //metodo para actualizar un cliente
      // con el dto que tambien utilize para el insertar, tomo los valores y los voy actualizando 1 por 1 con el mismo id del cliente
    @Override
    public Cliente actualizarCliente(ClienteCompletoDTO cliente) throws negocioException {

       
      // aplicar todas las reglas de negocio para consultar un negocio
        // 1. validar que el objeto NO sea nulo
        if(cliente == null){
            LOG.warning("El tecnico era un objeto Nulo");
            throw new negocioException("El tecnico no puede ser un objeto Nulo");
        }
        // 2. Validamos que el tecnico no tenga un id ya asignado
        if(cliente.getIdCliente()==null){
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
         if(cliente.getEstado()== null){
            LOG.warning("El cliente no puede no tener un estado");
            throw new negocioException("El estado es invalido");
        }
        //7. Validamos fecha de nacimiento 
        if(!validarFechaNacimiento(cliente.getFechaNacimiento())){
            LOG.warning("No se pudo registrar la fecha");
            throw new negocioException("Se ingreso una fecha futura o menor a 2020");
        }
        //8. validamos la edad 
        if(cliente.getEdad()== null){
            LOG.warning("El cliente no puede no tener una edad");
            throw new negocioException("La edad es invalida");
        }
         
        Cliente clienteActualizar = new Cliente();
        clienteActualizar.setIdCliente(cliente.getIdCliente());
        clienteActualizar.setNombres(cliente.getNombres());
        clienteActualizar.setApellidoPaterno(cliente.getApellidoPaterno());
        clienteActualizar.setApellidoMaterno(cliente.getApellidoMaterno());
        clienteActualizar.setFechaNacimiento(cliente.getFechaNacimiento());
        clienteActualizar.setEdad(cliente.getEdad());
        clienteActualizar.setEstado(cliente.getEstado());
        try{
       Cliente clienteConsultado = this.clienteDAO.consultarCliente(clienteActualizar);
          if(clienteConsultado == null){
              LOG.warning("No se pudo obtener ese id del cliente");
              throw new negocioException("El id del cliente no existe");
          }else{
             clienteDAO.actualizarCliente(clienteActualizar);
          }        
   
        Telefono telefonoActualizar = new Telefono();
        telefonoActualizar.setId_cliente(cliente.getIdCliente());
        telefonoActualizar.setEtiqueta(cliente.getEtiqueta());
        telefonoActualizar.setNumeroTelefono(cliente.getNumeroTelefono());
        Telefono telefonoConsultado = this.telefonoDAO.consultarTelefono(telefonoActualizar);
        
        if(telefonoConsultado == null){
            LOG.warning("No se pudo obtener ese id de cliente");
            throw new negocioException("Error al actualizar el telefono");
        }else{
            telefonoDAO.actualizarTelefono(telefonoActualizar);
        }
        
        Domicilio domicilioActu = new Domicilio();
        domicilioActu.setId_cliente(cliente.getIdCliente());
        domicilioActu.setCalle(cliente.getCalle());
        domicilioActu.setColonia(cliente.getColonia());
        domicilioActu.setNumeroCasa(cliente.getNumeroCasa());
        Domicilio domicilioConsultado = this.domicilioDAO.consultarDomicilio(domicilioActu);
        if(domicilioConsultado == null){
            LOG.warning("No se pudo obtener ese id de cliente");
            throw new negocioException("Error al actualizar el telefono");
        }else{
            domicilioDAO.actualizarDomicilio(domicilioActu);
        }
        
        Usuario usuarioActu = new Usuario();
        usuarioActu.setId_cliente(cliente.getIdCliente());
        usuarioActu.setContrasena(cliente.getContrasena());
        usuarioActu.setUsuario(cliente.getUsuario());
        Usuario usuarioConsultado = this.usuarioDAO.consultarUsuario(usuarioActu);
         if(usuarioConsultado == null){
            LOG.warning("No se pudo obtener ese id de cliente");
            throw new negocioException("Error al actualizar el usuario");
        }else{
            usuarioDAO.actualizarUsuario(usuarioActu);
        }
    }catch (persistenciaException ex) {
        LOG.warning("No se pudo actualizar el cliente");
        throw new negocioException("Ocurrio un error al actualizar el cliente",ex);
        }
        
        return clienteActualizar;       
        }

    }
    

