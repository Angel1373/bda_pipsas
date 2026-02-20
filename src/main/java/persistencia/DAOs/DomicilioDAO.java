/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.Dominio.Domicilio;
import persistencia.Excepciones.persistenciaException;
import persistencia.conexion.IConexionBD;

/**
 *
 * @author Usuario
 */
public class DomicilioDAO implements IDomicilioDAO {

    
    private final IConexionBD conexionBD;
    
    private static final Logger LOG = Logger.getLogger(DomicilioDAO.class.getName());
       
   
    public DomicilioDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
  
    @Override
    public Domicilio agregarDomicilio(Domicilio domicilio) throws persistenciaException {
        String comandoSQL = """
                            INSERT INTO domicilios (calle, colonia, numero)
                            VALUES (?,?,?)
                            """;

        try (Connection conn = this.conexionBD.CrearConexion(); PreparedStatement ps = conn.prepareStatement(comandoSQL, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, domicilio.getCalle());
            ps.setString(2, domicilio.getColonia());
            ps.setString(3, domicilio.getNumero());
                
                //ejecutamos el comando usando el preparedStatement
            int filasInsertadas = ps.executeUpdate();
            
            // == 0 si no se registro nada y == 1 si se registro 
            if (filasInsertadas == 0) {
                LOG.log(Level.WARNING, "No se pudo insertar el domicilio: {0}", domicilio);
                throw new persistenciaException("No se pudo insertar el domicilio.");
            }
            //obtenemos el id generado automaticamente y lo leemos con el rs
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    //obtenemos la primera columna que es el id generado y se lo ponemos al domicilio
                    // se obtiene el id generado por el insert del metodo por la base de datos lo creo
                    domicilio.setId_domicilio(rs.getInt(1));
                } else {
                    throw new persistenciaException("Error al obtener el ID generado del nuevo domicilio.");
                }
            }

            LOG.log(Level.INFO, "Domicilio insertado con éxito. ID: {0}", domicilio.getId_domicilio());
            return domicilio;

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error de SQL al insertar el domicilio", ex);
            throw new persistenciaException("No se pudo agregar el domicilio");
        }
        
    }
    
    }
    
    

