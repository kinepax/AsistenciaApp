package com.tomtech.colegio2.model;

import java.util.Date;
import java.util.Objects;

public class Registro_Asistencia {

    private int id;
    private Alumno alumno;
    private String asistencia;

    private Matricula matricula;
    private String fecha;
    private String hora;
    private String estado;


    public Registro_Asistencia(int id, Alumno alumno, String asistencia, Matricula matricula, String fecha, String hora, String estado) {
        this.id = id;
        this.alumno = alumno;
        this.asistencia = asistencia;
        this.matricula = matricula;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public String getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(String asistencia) {
        this.asistencia = asistencia;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Registro_Asistencia that = (Registro_Asistencia) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
