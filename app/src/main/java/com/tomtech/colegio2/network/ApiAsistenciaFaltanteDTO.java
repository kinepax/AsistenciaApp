package com.tomtech.colegio2.network;

import com.tomtech.colegio2.dto.AsistenciaFaltanteDTO;
import com.tomtech.colegio2.model.AsistenciaFaltante;

import java.time.LocalDate;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiAsistenciaFaltanteDTO {

//Carga la asistencia que falta




    @GET("api2/asistencia_faltante/{fecha}/{grado}")
    Call<List<AsistenciaFaltanteDTO>> getAsistenciaFaltante(
            @Path("fecha") LocalDate fecha,
            @Path("grado") String grado
    );


/*
    @GET("asistencia_faltante/{fecha}/{grado}")
    Call<List<AsistenciaFaltante>> getAsistenciaFaltante(
            @Path("fecha") LocalDate fecha,
            @Path("grado") String grado
    );

*/

}
