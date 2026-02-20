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
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.Dominio.Telefono;
import persistencia.Excepciones.persistenciaException;
import persistencia.conexion.IConexionBD;

/**
 *
 * @author Usuario
 */
public class TelefonoDAO implements ITelefonoDAO{

    
     private final IConexionBD conexionBD;
    private static final Logger LOG = Logger.getLogger(TelefonoDAO.class.getName());
   
       
   
    public TelefonoDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
    @Override
    public Telefono agregarTelefono(Telefono telefono) throws persistenciaException {
        String comandoSQL = """
                            INSERT INTO telefonos (numero, etiqueta, id_cliente)
                            VALUES (?,?,?)
                            """;

        try (Connection conn = this.conexionBD.CrearConexion(); PreparedStatement ps = conn.prepareStatement(comandoSQL, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, telefono.getNumero());
            ps.setString(2, telefono.getEtiqueta());
            ps.setInt(3, telefono.getId_cliente());
                
                //ejecutamos el comando usando el preparedStatement
            int filasInsertadas = ps.executeUpdate();
            
            // == 0 si no se registro nada y == 1 si se registro 
            if (filasInsertadas == 0) {
                LOG.log(Level.WARNING, "No se pudo insertar el telefono: {0}", telefono);
                throw new persistenciaException("No se pudo insertar el telefono.");
            }
            //obtenemos el id generado automaticamente y lo leemos con el rs
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    //obtenemos la primera columna que es el id generado y se lo ponemos al telefono
                    // se obtiene el id generado por el insert del metodo por la base de datos lo creo
                    telefono.setId_telefono(rs.getInt(1));
                } else {
                    throw new persistenciaException("Error al obtener el ID generado del nuevo telefono.");
                }
            }

            LOG.log(Level.INFO, "Telefono insertado con éxito. ID: {0}", telefono.getId_telefono());
            return telefono;

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error de SQL al insertar el telefono", ex);
            throw new persistenciaException("No se pudo agregar el telefono");
        }
        
    }
}
    

