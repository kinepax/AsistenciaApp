package com.tomtech.colegio2.model;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Objects;

public class AsistenciaFaltante {

    Alumno alumno;
    Matricula matricula;
    String nombres;
    String apellidoPaterno;

    String apellidoMaterno;



    Spinner spAsistencias;

    String estado;



    public AsistenciaFaltante(){

    }


    public AsistenciaFaltante(Alumno alumno, Matricula matricula, String nombres, String apellidoPaterno, String apellidoMaterno,String estado) {
        this.alumno = alumno;
        this.matricula = matricula;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.estado=estado;


    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
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


    public Spinner getSpAsistencias() {
        return spAsistencias;
    }

    public void setSpAsistencias(Spinner spAsistencias) {
        this.spAsistencias = spAsistencias;
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

        AsistenciaFaltante that = (AsistenciaFaltante) o;

        return Objects.equals(alumno, that.alumno);
    }

    @Override
    public int hashCode() {
        return alumno != null ? alumno.hashCode() : 0;
    }
}
