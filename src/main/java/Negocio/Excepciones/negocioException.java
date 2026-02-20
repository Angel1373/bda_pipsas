/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.Excepciones;

/**
 *
 * @author Usuario
 */
public class negocioException extends Exception{
    
     public negocioException() {
    }

    public negocioException(String message) {
        super(message);
    }

    public negocioException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
