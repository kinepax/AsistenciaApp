package com.tomtech.colegio2.model;

import java.util.Date;
import java.util.Objects;

public class Alumno {

    private int id;
    private String nombres;
    private String apellido_paterno;
    private String apellido_materno;
    private String nacimiento;
    private String nombre_apoderado;
    private String celular;
    private String estado;
    private String fecha_registro;


    public Alumno(){


    }

    public Alumno(int id, String nombres, String apellido_paterno, String apellido_materno, String nacimiento, String nombre_apoderado, String celular, String estado, String fecha_registro) {
        this.id = id;
        this.nombres = nombres;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.nacimiento = nacimiento;
        this.nombre_apoderado = nombre_apoderado;
        this.celular = celular;
        this.estado = estado;
        this.fecha_registro = fecha_registro;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getNombre_apoderado() {
        return nombre_apoderado;
    }

    public void setNombre_apoderado(String nombre_apoderado) {
        this.nombre_apoderado = nombre_apoderado;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void String(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alumno alumno = (Alumno) o;
        return id == alumno.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
