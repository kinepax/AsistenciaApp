package com.tomtech.colegio2.network;

import com.tomtech.colegio2.model.Registro_Asistencia;
import com.tomtech.colegio2.model.Usuario;

import java.time.LocalDate;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiRegistro {




    @GET("api2/registro/{fecha}/{grado}")
    Call<List<Registro_Asistencia>> getAsistencia(
            @Path("fecha") LocalDate fecha,
            @Path("grado") String grado
    );

    @GET("api2/registro")
    Call<List<Registro_Asistencia>> getAsistencia();



/*

    @GET("registro/{fecha}/{grado}")
    Call<List<Registro_Asistencia>> getAsistencia(
            @Path("fecha") LocalDate fecha,
            @Path("grado") String grado
    );

    @GET("registro")
    Call<List<Registro_Asistencia>> getAsistencia();

*/

}
