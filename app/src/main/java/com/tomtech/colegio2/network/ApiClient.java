package com.tomtech.colegio2.network;

import retrofit2.Retrofit;
import retrofit2.Converter.*;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit;


    public static Retrofit getClient(){

        retrofit= new Retrofit.Builder()
                .baseUrl("http://192.168.0.16:9090")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;

  /*
        retrofit= new Retrofit.Builder()
                .baseUrl("http://192.168.0.22:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;

*/
    }


}
