package com.example.mobiletest;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class my_network extends AsyncTask<Void, Void, Void> {

    public static final String Root_url = "http://x2021emergencity2490271133000.northeurope.cloudapp.azure.com:9000/";

    String login, password;

    my_network(String log, String pass) {
        login = log;
        password = pass;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        Retrofit adapter = new Retrofit.Builder()
                .baseUrl(Root_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitRest api = adapter.create(RetrofitRest.class);

        Call<ResponseBody> call = api.loginUser(login, password);

        try {
            Log.i("avant", "coucou");
            ResponseBody res = call.execute().body();
            Log.i("apres", "fjnfjjfdekfjjfdghfjdghjfd");
            Log.i("test", res.string());
        } catch (IOException e) {
            Log.i("erreur", "erreur erreur");
            e.printStackTrace();
        }
        return null;
    }
}
