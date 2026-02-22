/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class ConexionBD implements IConexionBD {
    
     private final String CADENA_CONEXION = "jdbc:mysql://localhost/bda_pipsas";

    /**
     * Usuario de la base de datos.
     */
    private final String USUARIO = "root";

    /**
     * Contraseña asociada al usuario de la base de datos.
     */
    private final String CONTRASENIA = "Relacional@";

    /**
     * Crea y retorna una conexión activa con la base de datos.
     */
   
    @Override
    public Connection CrearConexion() throws SQLException {
    return DriverManager.getConnection(CADENA_CONEXION, USUARIO, CONTRASENIA);    }

}

    

