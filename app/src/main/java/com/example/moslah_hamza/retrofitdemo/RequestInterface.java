package com.example.moslah_hamza.retrofitdemo;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Moslah_Hamza on 02/05/2017.
 */


public interface RequestInterface {

    @GET("dom-parsing-court.php")
    Call<JSONResponse> getJSON();
}

