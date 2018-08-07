package com.azens.countrylist.api;

import com.azens.countrylist.model.Country;
import java.util.List;
import retrofit2.http.GET;

/**
 * Created by Azens Eklak on 8/7/18.
 */
public interface CountryClient {

    @GET("region/Asia")
    retrofit2.Call<List<Country>> getCountries();
}
