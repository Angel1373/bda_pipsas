/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.DTOs;

import java.time.LocalDate;
import persistencia.Dominio.Cliente;

/**
 *
 * @author Usuario
 */
public class ClienteCompletoDTO {
    
       
    private Integer idCliente;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Cliente.EstadoCliente estado;
    private LocalDate fechaNacimiento;
    private Integer edad;
    private String numeroTelefono;
    private String etiqueta;
    private String calle;
    private String colonia;
    private String numeroCasa;
    private String usuario;
    private String contrasena;

    public ClienteCompletoDTO(Integer idCliente, String nombres, String apellidoPaterno, String apellidoMaterno, Cliente.EstadoCliente estado, LocalDate fechaNacimiento, Integer edad, String numeroTelefono, String etiqueta, String calle, String colonia, String numeroCasa, String usuario, String contrasena) {
        this.idCliente = idCliente;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.estado = estado;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.numeroTelefono = numeroTelefono;
        this.etiqueta = etiqueta;
        this.calle = calle;
        this.colonia = colonia;
        this.numeroCasa = numeroCasa;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public ClienteCompletoDTO(String nombres, String apellidoPaterno, String apellidoMaterno, Cliente.EstadoCliente estado, LocalDate fechaNacimiento, Integer edad, String numeroTelefono, String etiqueta, String calle, String colonia, String numeroCasa, String usuario, String contrasena) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.estado = estado;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.numeroTelefono = numeroTelefono;
        this.etiqueta = etiqueta;
        this.calle = calle;
        this.colonia = colonia;
        this.numeroCasa = numeroCasa;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public ClienteCompletoDTO() {
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

    public Cliente.EstadoCliente getEstado() {
        return estado;
    }

    public void setEstado(Cliente.EstadoCliente estado) {
        this.estado = estado;
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

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    
    
    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(String numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    
    
    
}
