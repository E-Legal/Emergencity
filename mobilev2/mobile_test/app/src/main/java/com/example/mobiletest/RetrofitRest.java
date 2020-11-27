package com.example.mobiletest;

import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitRest {

    String url = "x2021emergencity2490271133000.northeurope.cloudapp.azure.com:9000/";

    @FormUrlEncoded
    @POST("/login")
    Call<ResponseBody> loginUser(@Field("name") String name, @Field("password") String password);

}
