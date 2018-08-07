package com.azens.countrylist.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Azens Eklak on 8/7/18.
 */
public class Country {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("capital")
    @Expose
    private String capital;

    @SerializedName("subregion")
    @Expose
    private String region;

    @SerializedName("area")
    @Expose
    private String area;

    @SerializedName("population")
    @Expose
    private String population;

    @SerializedName("nativeName")
    @Expose
    private String nativeName;

    public Country(String name, String capital, String region,
                   String area, String population, String nativeName) {
        this.name = name;
        this.capital = capital;
        this.region = region;
        this.area = area;
        this.population = population;
        this.nativeName = nativeName;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

}
