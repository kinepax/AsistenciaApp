package com.tomtech.colegio2.service;

import com.tomtech.colegio2.model.Usuario;

import java.util.List;

public interface UsuarioService {


    public List<Usuario> list()throws Exception;;


    public List<Usuario> listUser(String user, String password) throws Exception;
/*
    public void save(Usuario usuario) throws Exception;
    public void update(Usuario usuario) throws Exception;
    public void delete(Usuario usuario) throws Exception;
*/
}
