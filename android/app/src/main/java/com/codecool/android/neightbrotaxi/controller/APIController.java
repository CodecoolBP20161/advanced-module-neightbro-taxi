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

/**
 * Responsible for connection between app and the server, by OkHttpClient
 * @see APIController#client
 */
public class APIController {
    /**
     * Initialize TAG, client, JSON convert type and URL.
     */
    private static final String TAG = APIController.class.getSimpleName()+"<>";
    private static final OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    // Temporary address! (The final waiting for deploying.)
    private static String API_URL = "http://192.168.161.172:9000/";

    /**
     * This is async.
     * (Run in the background as well.)
     */
    public static class PostTask extends AsyncTask <String, Void, String> {

        String json;
        String serverRequest;

        /** This one like to wish  a string list.
         * @param strings First is every case will be a
         * @see #serverRequest
         * Second is json parts.
         */
        public PostTask(String ... strings) {
            // JUST: /registration
            this.serverRequest = strings[0];
            strings = Arrays.copyOfRange(strings, 1, strings.length);
            this.json = UserDataJson(strings);
        }

        /**
         * Basically this method used by the system,
         * I "just" logging.
         * @param urls if you want handle more url.
         * @return null, if catch exception or
         * @see #post(String, String), as result.
         */
        @Override
        protected String doInBackground(String... urls) {
            try {
                Log.d(TAG, "URL WITH METHOD: "+API_URL+ serverRequest);
                return post(API_URL+ serverRequest, json);
            } catch (Exception e) {
                Log.e(TAG, "PostTask caught exception: "+e);
                return null;
            }
        }

        /**
         * Make a post request with:
         * @param url string web addresses
         * @param json as converted sting
         * @return response from the server
         * @throws IOException, when something went wrong.
         */
        String post(String url, String json) throws IOException {
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }

        /**
         * Used by OS.
         * @param getResponse and logged it
         */
        //TODO: Process the different response!
        protected void onPostExecute(String getResponse) {
            Log.i(TAG, "PostTask onPostExecute message: "+getResponse);
        }
    }

    /**
     * This by used just for the test now!
     */
    static class GetTask extends AsyncTask <String, Void, String> {

        String method;

        GetTask(String... strings) {
            this.method = strings[0];
        }

        /**
         * Basically this method used by the system,
         * I "just" logging.
         * @param urls if you want handle more url.
         * @return null, if catch exception or
         * @see #get(String), as result.
         */
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

        /**
         * Make a get request with:
         * @param url string web addresses
         * @return response from the server
         * @throws IOException, when something went wrong.
         */
        private String get(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }

        /**
         * Used by OS.
         * @param getResponse and logged it
         */
        //TODO: Process the different response!
        protected void onPostExecute(String getResponse) {
            Log.i(TAG, "GetTask onPostExecute message: "+getResponse);
        }
    }

    /**
     * Try to create a json with an array:
     * @param strings based on the coded order
     * @return as string.
     * Catch JSONException and ArrayIndexOutOfBoundsException
     */
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

    /**
     * Checking network status and connection ability.
     * @param activity get from the current (alive) android Activity.
     * @return boolean result
     */
    public static boolean isNetworkAvailable(Activity activity) {
        ConnectivityManager manager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
