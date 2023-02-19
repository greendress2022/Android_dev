package com.ebookfrenzy.numad23sp_team16;

import android.database.Observable;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;


//create a Retrofit service interface to define the API endpoints
//retrieves information about a specific country by name
//sends an HTTP GET request to the specified endpoint
public interface RestCountriesService {
    @GET("name/{name}")
    Call<List<Country>> getCountries(@Path("name") String name);
//other end point example,get a list of all countries
//    @GET("all")  =>define endpoint here
//    Call<List<Country>> getAllCountries();


}
