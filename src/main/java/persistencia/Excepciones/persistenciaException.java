/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.Excepciones;

/**
 *
 * @author Usuario
 */
public class persistenciaException extends Exception{
    
    public persistenciaException() {
    }

    public persistenciaException(String message) {
        super(message);
    }

    public persistenciaException(String message, Throwable cause) {
        super(message, cause);
    }
}
