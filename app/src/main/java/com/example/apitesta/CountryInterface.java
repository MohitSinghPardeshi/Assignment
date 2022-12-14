package com.example.apitesta;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CountryInterface {
    String BASEURL = "https://api.nationalize.io/";


    @GET(BASEURL)
    Call<CountryItem> getDataUsingName(@Query("name")String name);

}
