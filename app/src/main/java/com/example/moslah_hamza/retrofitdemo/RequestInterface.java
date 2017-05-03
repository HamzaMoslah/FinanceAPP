package com.example.moslah_hamza.retrofitdemo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Moslah_Hamza on 02/05/2017.
 */


public interface RequestInterface {

    @GET("dom-parsing-court.php")
    Call<JSONResponse> getJSON();

//    @FormUrlEncoded
//    @POST("dom-currency-conversion.php")
//    Call<JSONConverter> convertcur(
//            @Field("from") String fromCur,
//            @Field("to") String toCur,
//            @Field("mount") String mount);
}

