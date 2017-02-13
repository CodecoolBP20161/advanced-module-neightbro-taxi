package com.codecool.android.neightbrotaxi.controller;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class APIController {
    private static final String TAG = APIController.class.getSimpleName()+"<>";

    private static OkHttpClient client = new OkHttpClient();
    private static String API_URL = "http://192.168.161.172:9000/";
    private static MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static class PostTask extends AsyncTask <String, Void, String> {

        String json;
        String method;

        public PostTask(String ... strings) {
            this.method = strings[0];
            strings = Arrays.copyOfRange(strings, 1, strings.length);
            this.json = UserDataJson(strings);
        }

        @Override
        protected String doInBackground(String... urls) {
            try {
                Log.d(TAG, "URL WITH METHOD: "+API_URL+method);
                return post(API_URL+method, json);
            } catch (Exception e) {
                Log.e(TAG, "PostTask caught exception: "+e);
                return null;
            }
        }

        String post(String url, String json) throws IOException {
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }

        protected void onPostExecute(String getResponse) {
            Log.i(TAG, "PostTask onPostExecute message: "+getResponse);
        }
    }

    static class GetTask extends AsyncTask <String, Void, String> {

        String method;

        GetTask(String... strings) {
            this.method = strings[0];
        }

        @Override
        protected String doInBackground(String... urls) {
            try {
                Log.d(TAG, "URL WITH METHOD: "+API_URL+method);
                return get(API_URL+method);
            } catch (Exception e) {
                Log.e(TAG, "GetTask caught exception: "+e);
                return null;
            }
        }

        private String get(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        }

        protected void onPostExecute(String getResponse) {
            Log.i(TAG, "GetTask onPostExecute message: "+getResponse);
        }
    }

    static String UserDataJson(String... strings) {
        JSONObject json = new JSONObject();
        try {
            json.put("name", strings[0]);
            json.put("email", strings[1]);
            json.put("password", strings[2]);
            json.put("passwordConfirm", strings[3]);
            Log.i(TAG, "JSON CREATED: "+json);
        }
        catch (JSONException | ArrayIndexOutOfBoundsException e) {
            Log.e(TAG, "UserDataJson caught exception: "+e);
        }
        return json.toString();
    }

    public static boolean isNetworkAvailable(Activity activity) {
        ConnectivityManager manager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
