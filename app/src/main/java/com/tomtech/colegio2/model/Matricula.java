package com.tomtech.colegio2.model;

import java.util.Objects;

public class Matricula {

    private int id ;
    private Alumno alumno;
    private Grado grado;
    private String año;


    public Matricula(int id, Alumno alumno, Grado grado, String año) {
        this.id = id;
        this.alumno = alumno;
        this.grado = grado;
        this.año = año;
    }
    public Matricula() {

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

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matricula matricula = (Matricula) o;
        return id == matricula.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
