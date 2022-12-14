package com.example.apitesta;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryItem {

    @SerializedName("country")
    @Expose
    private List<Country> country = null;
    @SerializedName("name")
    @Expose
    private String name;

    public List<Country> getCountry() {
        return country;
    }

    public void setCountry(List<Country> country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}