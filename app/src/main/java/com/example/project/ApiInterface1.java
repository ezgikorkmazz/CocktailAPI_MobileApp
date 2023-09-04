package com.example.project;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiInterface1 {
    @GET("search.php")
    Call<RCoctailsObject> getCocktails(@Query("s")String cocktailName);

    @GET("random.php")
    Call<RCoctailsObject> getRandom();
}
