package com.tomtech.colegio2.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Registro_Asistencia {

    private int id;
    private Alumno alumno;
    private String asistencia;

    private Matricula matricula;
    private LocalDate fecha;
    private String strfecha;

    private String hora;
    private String estado;



    public Registro_Asistencia(int id, Alumno alumno, Matricula matricula, String asistencia, String strfecha, String hora, String estado) {
        this.id = id;
        this.alumno = alumno;
        this.matricula = matricula;
        this.asistencia = asistencia;
        this.strfecha = strfecha;
        this.hora = hora;
        this.estado = estado;
    }



    public Registro_Asistencia(int id,Alumno alumno, String asistencia, Matricula matricula, String strfecha,String estado) {
        this.id = id;
        this.alumno = alumno;
        this.asistencia = asistencia;
        this.matricula = matricula;
        this.strfecha = strfecha;
        this.estado = estado;

    }


    public Registro_Asistencia(Alumno alumno, String asistencia, Matricula matricula, LocalDate fecha,String estado) {
        this.alumno = alumno;
        this.asistencia = asistencia;
        this.matricula = matricula;
        this.fecha = fecha;
        this.estado = estado;

    }

    public Registro_Asistencia(Alumno alumno, String asistencia, Matricula matricula, String strfecha,String estado) {

        this.alumno = alumno;
        this.asistencia = asistencia;
        this.matricula = matricula;
        this.strfecha = strfecha;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getStrfecha() {
        return strfecha;
    }

    public void setStrfecha(String strfecha) {
        this.strfecha = strfecha;
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
