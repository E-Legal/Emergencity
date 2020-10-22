package com.example.mobilv2;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiManager extends OkHttpClient {
    private final String _url = "https://emergencity.herokuapp.com";
    private String _token = "";
    private static boolean connected = false;

    public String getToken() {
        return (_token);
    }

    private static final ApiManager ourInstance = new ApiManager();

    public static ApiManager getInstance() {
        return ourInstance;
    }

    private ApiManager() {

    }

    public void getProfile(Callback callback) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(_url + "/profiles/me").newBuilder();
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + getToken())
                .build();
        newCall(request).enqueue(callback);
    }

    public void login(String content, final Callback callback) {
        MediaType JSON = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(JSON, content);
        final Request request = new Request.Builder()
                .url(_url + "/users/login")
                .post(body)
                .build();
        newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                callback.onFailure(call, e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        JSONObject responseBody = new JSONObject(response.body().string());
                        _token = responseBody.getString("token");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                callback.onResponse(call, response);
            }
        });
    }

    public void addCourse(String add, double latitude, double longitude, final Callback callback) {
        MediaType JSON = MediaType.parse("application/json");
        HttpUrl.Builder urlBuilder = HttpUrl.parse(_url + "/courses").newBuilder();
        String url = urlBuilder.build().toString();

        Log.i("to", add);

        RequestBody body = new FormBody.Builder()
                .add("address", add)
                .add("lat_pos", String.valueOf(latitude))
                .add("long_pos", String.valueOf(longitude))
                .build();
        //RequestBody body = RequestBody.create(JSON, content);

        Log.i("lat", String.valueOf(latitude));
        Log.i("lng", String.valueOf(longitude));

        Log.i("coucoucontent", getToken());
        Log.i("url", url);
        final Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + getToken())
                .post(body)
                .build();
        newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("erreur co", "fdfjkjfgdbh dfg  ikre rrg");
                callback.onFailure(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //Log.i("alors on danse", response.body().string());

                if (response.isSuccessful()) {
                    connected = true;
                    /*try {
                        //JSONObject responseBody = new JSONObject(response.body().string());
                        //Log.i("api test", responseBody.toString());
                    } catch (JSONException err) {
                        Log.e("oskour", err.getMessage());
                    }*/
                }
                callback.onResponse(call, response);
            }

        });
    }

    public void getCourse(String id, final Callback callback) {
        MediaType JSON = MediaType.parse("application/json");
        HttpUrl.Builder urlBuilder = HttpUrl.parse(_url + "/courses/").newBuilder();
        String url = urlBuilder.build().toString();

        final Request request = new Request.Builder()
//                .header("Authorization", token)
                .url(url + id)
                .addHeader("Authorization", "Bearer " + getToken())
                .build();
        newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("erreur co", "fdfjkjfgdbh dfg  ikre rrg");
                callback.onFailure(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Log.i("alors on danse", response.body().string());

                if (response.isSuccessful()) {
                    connected = true;
                    /*try {
                        //JSONObject responseBody = new JSONObject(response.body().string());
                        //Log.i("api test", responseBody.toString());
                    } catch (JSONException err) {
                        Log.e("oskour", err.getMessage());
                    }*/
                }
                callback.onResponse(call, response);
            }

        });
    }

    public void removeToken() {
        _token = "";
    }
}
