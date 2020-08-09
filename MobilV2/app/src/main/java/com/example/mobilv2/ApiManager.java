package com.example.mobilv2;

import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class ApiManager extends OkHttpClient {
    private String _url = "http://192.168.1.16:9000";

    public String getToken() {
        return ("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI1ZjE1YjNlZTJmOWNhMjMyZDQ3Y2NkNzMiLCJpYXQiOjE1OTUyNjcwMjR9.WNvhOohwdZCSuLrYxoDU7iQqUYRIgYLgf1qm0ipcLZ4");
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

}
