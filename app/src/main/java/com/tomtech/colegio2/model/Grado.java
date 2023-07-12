package com.tomtech.colegio2.model;

import java.util.Objects;

public class Grado {

    private int id;
    private String grado;


    public Grado(int id, String grado) {
        this.id = id;
        this.grado = grado;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grado grado = (Grado) o;
        return id == grado.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return grado;
    }
}
