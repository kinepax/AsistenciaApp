package com.tomtech.colegio2.network;
import com.tomtech.colegio2.dto.RegistroAsistenciaDTO;
import com.tomtech.colegio2.model.Registro_Asistencia;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiRegistrarAsistenciaDTO {
/*
    @POST("api2/registrar_asistencia?")
    Call<List<String>> setAsistencia(
            @Query("alumno") int usuario
    );


    @POST("api2/registrar_asistencia_faltante?")
    Call<List<String>> setAsistenciaFaltante(
            @Query("alumno") int alumno,
            @Query("asistencia") String asistencia,
            @Query("matricula") int matricula,
            @Query("fecha") LocalDate fecha,
            @Query("estado") String estado

            );


    @POST("api2/registrar_asistencia_faltante2")
    Call<Void> setAsistenciaFaltante2(@Body List<Registro_Asistencia> registro_asistencias);


*/




    @POST("api2/registro?")
    Call<List<String>> setAsistencia(
            @Query("alumno") int usuario
    );


/*

    @POST("asistencia_faltante")
    Call<Void> setAsistenciaFaltante2(@Body List<Registro_Asistencia> registro_asistencias);

*/
@POST("api2/registro/registros")
Call<Void> setAsistenciaFaltante2(@Body List<RegistroAsistenciaDTO> registro_asistencias);


}
