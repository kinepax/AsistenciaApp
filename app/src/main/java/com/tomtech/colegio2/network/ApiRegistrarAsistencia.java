package com.tomtech.colegio2.network;
import com.tomtech.colegio2.model.Registro_Asistencia;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiRegistrarAsistencia {

    @POST("api2/registrar_asistencia?")
    Call<List<String>> setAsistencia(
            @Query("alumno") int usuario
    );

}
