/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.conexion;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public interface IConexionBD {
    
    // crea y regresa una conexion de la base de datos
    Connection CrearConexion() throws SQLException;
      
    
}
