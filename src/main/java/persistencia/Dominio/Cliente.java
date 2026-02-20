/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.Dominio;

import java.time.LocalDate;

/**
 *
 * @author Usuario
 */
public class Cliente {
    
   
   
    private Integer idCliente;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private EstadoCliente estado;
    private String usuario;
    private LocalDate fechaNacimiento;
    private Integer edad;
    private String contrasena;
    private Integer idDomicilio;

    public Cliente() {
    }

    public Cliente(String nombres, String apellidoPaterno, String apellidoMaterno, EstadoCliente estado, String usuario, LocalDate fechaNacimiento, Integer edad, String contrasena, Integer idDomicilio) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.estado = estado;
        this.usuario = usuario;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.contrasena = contrasena;
        this.idDomicilio = idDomicilio;
    }
    
    
    
    public Cliente(Integer idCliente, String nombres, String apellidoPaterno, String apellidoMaterno, EstadoCliente estado, String usuario,LocalDate fechaNacimiento, Integer edad,String contrasena, Integer idDomicilio) {
        this.idCliente = idCliente;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.estado = estado;
        this.usuario = usuario;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.contrasena = contrasena;
        this.idDomicilio = idDomicilio;
    }

    
    public enum EstadoCliente {

    ACTIVO("activo"),
    INACTIVO("inactivo");

    private final String valor;

    EstadoCliente(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
    }
    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public EstadoCliente getEstado() {
        return estado;
    }

    public void setEstado(EstadoCliente estado) {
        this.estado = estado;
    }

    

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Integer getIdDomicilio() {
        return idDomicilio;
    }

    public void setIdDomicilio(Integer idDomicilio) {
        this.idDomicilio = idDomicilio;
    }
}