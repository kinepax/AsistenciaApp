package com.tomtech.colegio2.model;

import java.util.Objects;

public class Usuario {

    private int id;
    private String usuario;
    private String password;
    private String privilegios;


    public Usuario(int id, String usuario, String password, String privilegios) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
        this.privilegios = privilegios;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrivilegios() {
        return privilegios;
    }

    public void setPrivilegios(String privilegios) {
        this.privilegios = privilegios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
