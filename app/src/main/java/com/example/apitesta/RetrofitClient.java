package com.example.apitesta;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit=null;
    public static final String BASEURL = "https://api.nationalize.io/";

    public static CountryInterface getRetrofitClient(){
        if(retrofit==null){
            retrofit = new  Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(CountryInterface.class);

    }
}
