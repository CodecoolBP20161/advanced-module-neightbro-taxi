package com.codecool.android.neightbrotaxi.controller;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class APIController extends AsyncTask{
    private static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private static final String API_URL = "http://localhost:9000/registration";

    private OkHttpClient client = new OkHttpClient();

    public String post(String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String getUserJson(String ... s) {
        return "{" +
                "'name':'" + s[0] + "'," +
                "'email':'" + s[1] + "'," +
                "'password':'" + s[2] + "'," +
                "'passwordConfirm':'" + s[3] + "'," +
                "}";
    }

    public boolean isNetworkAvailable(Activity activity) {
        ConnectivityManager manager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        return null;
    }
}
