package com.tomtech.colegio2.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tomtech.colegio2.adapter.LocalDateAdapter;

import java.time.LocalDate;

import retrofit2.Retrofit;
import retrofit2.Converter.*;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit;




    public static Retrofit getClient(){

        //Conexion local


        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

      //del servidor tomcat en local

        retrofit= new Retrofit.Builder()
                .baseUrl("http://192.168.0.17:9090")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit;


//de la ejecucion en el ide
/*
        retrofit= new Retrofit.Builder()
                .baseUrl("http://192.168.0.17:8080")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit;


*/

//IP DEL SERVIDOR DE FRANCIS
/*

        retrofit= new Retrofit.Builder()
                .baseUrl("http://192.168.0.30:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;

*/

    }


}
