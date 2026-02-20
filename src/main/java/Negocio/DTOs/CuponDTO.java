/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.DTOs;

import java.time.LocalDate;

/**
 *
 * @author Gael Galaviz
 */
public class CuponDTO {
    private LocalDate fechaHoraInicioVigencia;
    private LocalDate fechaHoraFinVigencia;
    private int usos_total;
    private int usos_max;
    private String codigo;

    public CuponDTO(LocalDate fechaHoraInicioVigencia, LocalDate fechaHoraFinVigencia, int usos_total, int usos_max, String codigo) {
        this.fechaHoraInicioVigencia = fechaHoraInicioVigencia;
        this.fechaHoraFinVigencia = fechaHoraFinVigencia;
        this.usos_total = usos_total;
        this.usos_max = usos_max;
        this.codigo = codigo;
    }

    public CuponDTO() {
    }

    public LocalDate getFechaHoraInicioVigencia() {
        return fechaHoraInicioVigencia;
    }

    public void setFechaHoraInicioVigencia(LocalDate fechaHoraInicioVigencia) {
        this.fechaHoraInicioVigencia = fechaHoraInicioVigencia;
    }

    public LocalDate getFechaHoraFinVigencia() {
        return fechaHoraFinVigencia;
    }

    public void setFechaHoraFinVigencia(LocalDate fechaHoraFinVigencia) {
        this.fechaHoraFinVigencia = fechaHoraFinVigencia;
    }

    public int getUsos_total() {
        return usos_total;
    }

    public void setUsos_total(int usos_total) {
        this.usos_total = usos_total;
    }

    public int getUsos_max() {
        return usos_max;
    }

    public void setUsos_max(int usos_max) {
        this.usos_max = usos_max;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "CuponDTO{" + "fechaHoraInicioVigencia=" + fechaHoraInicioVigencia + ", fechaHoraFinVigencia=" + fechaHoraFinVigencia + ", usos_total=" + usos_total + ", usos_max=" + usos_max + ", codigo=" + codigo + '}';
    }
    
    
    
}
