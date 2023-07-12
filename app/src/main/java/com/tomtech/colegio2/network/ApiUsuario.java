package com.tomtech.colegio2.network;

import com.tomtech.colegio2.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiUsuario {


    @POST("api2/usuario?")
    Call<List<Usuario>> getUsuario(
            @Query("usuario") String usuario,
            @Query("password") String password
    );

    @POST("api2/usuario")
    Call<List<Usuario>> getUsuarios();
}
