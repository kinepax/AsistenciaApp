package com.tomtech.colegio2.network;

import com.tomtech.colegio2.model.Grado;
import com.tomtech.colegio2.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiGrado {


    @POST("api2/grado?")
    Call<List<Grado>> getGrado(
            @Query("grado") int id
    );

    @POST("api2/grado")
    Call<List<Grado>> getGrados();
}
