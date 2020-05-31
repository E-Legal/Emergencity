package com.example.mobiletest;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded // annotation used in POST type requests
    @POST("/retrofit/register.php")     // API's endpoints
    Call<User> registration(@Field("name") String name,
                            @Field("email") String email);//user_email and user_pass are the post parameters and SignUpResponse is a POJO class which receives the response of this API


}
