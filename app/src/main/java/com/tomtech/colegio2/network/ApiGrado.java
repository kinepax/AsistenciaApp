package com.tomtech.colegio2.network;

import com.tomtech.colegio2.model.Grado;
import com.tomtech.colegio2.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiGrado {


    @GET("api2/grado/?")
    Call<List<Grado>> getGrado(
            @Query("grado") int id
    );

    @GET("api2/grado")
    Call<List<Grado>> getGrados();






/*

    @GET("grado/?")
    Call<List<Grado>> getGrado(
            @Query("grado") int id
    );

    @GET("grado")
    Call<List<Grado>> getGrados();

*/

}
