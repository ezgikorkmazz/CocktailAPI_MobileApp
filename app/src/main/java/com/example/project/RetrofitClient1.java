package com.example.project;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient1 {

    private static final String BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/";


    private static Retrofit retrofit = null;

    public static ApiInterface1 getRetrofitClient() {

        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiInterface1.class);
    }
}
