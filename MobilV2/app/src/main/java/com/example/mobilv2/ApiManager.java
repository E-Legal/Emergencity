package com.example.mobilv2;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiManager extends OkHttpClient {
    private String _url = "http://192.168.1.16:9000";
    private String _token = "";


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

    public void removeToken() {
        _token = "";
    }

}
