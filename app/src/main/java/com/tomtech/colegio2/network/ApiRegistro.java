package com.tomtech.colegio2.network;

import com.tomtech.colegio2.model.Registro_Asistencia;
import com.tomtech.colegio2.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiRegistro {



    @POST("api2/registro?")
    Call<List<Registro_Asistencia>> getAsistencia(
            @Query("fecha") String fecha,
            @Query("grado") String grado
    );

    @POST("api2/registro")
    Call<List<Registro_Asistencia>> getAsistencia();

}
