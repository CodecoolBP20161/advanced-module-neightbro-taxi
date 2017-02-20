package com.codecool.android.neightbrotaxi.controller;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import com.codecool.android.neightbrotaxi.model.AlertUser;
import com.codecool.android.neightbrotaxi.view.ResponseView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Responsible for connectionError between app and the server, by OkHttpClient
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
    private static String API_URL = "http://192.168.0.106:9000/";


    /**
     * This is async.
     * (Run in the background as well.)
     */
    public static class PostTask extends AsyncTask <String, Void, String> {

        private final Activity mActivity;
        String serverRequest;
        String json;

        /** This one like to wish an Activity and a string list.
         * @param activity get from current context
         * @param strings First is every case will be a
         * @see #serverRequest
         * Second is json parts.
         */
        public PostTask(Activity activity, String request, String... strings) {
            mActivity = activity;
            serverRequest = request;
            json = UserDataJson(strings);
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
                Log.i(TAG, "URL WITH POST REQUEST: " + API_URL + serverRequest);
                return post(API_URL + serverRequest, json);
            }
            catch (Exception e) {
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
            okhttp3.Response response = client.newCall(request).execute();
            return response.body().string();
        }

        /**
         * Used by OS. Managing responses:
         * @see ResponseView
         * @param getResponse get from system
         */
        @Override
        protected void onPostExecute(String getResponse) {
            Log.d(TAG, "PostTask onPostExecute message: "+getResponse);
            if (getResponse != null) {
                try {
                    new ResponseView(mActivity, getResponse);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG, String.valueOf(e));
                }
            }
            else {
                new AlertUser(mActivity).serverError();
            }
        }
    }

    /**
     * This by used just for the test now!
     */
    static class GetTask extends AsyncTask <String, Void, String> {


        private final Activity mActivity;
        String serverRequest;

        GetTask(Activity activity, String request) {
            mActivity = activity;
            serverRequest = request;
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
                String url = API_URL + serverRequest;
                Log.i(TAG, "URL WITH GET REQUEST: " + url);
                return get(url);
            }
            catch (Exception e) {
                Log.e(TAG, "PostTask caught exception: "+e);
                return null;
            }
        }

        /**
         * Make a get request with:
         * @param url string web addresses
         * @return response from the serverError
         * @throws IOException, when something went wrong.
         */
        private String get(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();
            okhttp3.Response response = client.newCall(request).execute();
            return response.body().string();
        }

        /**
         * Used by OS.
         * @param getResponse and logged it
         */
        //TODO: Process the different response!
        @Override
        protected void onPostExecute(String getResponse) {
            Log.i(TAG, "GetTask onPostExecute message: "+getResponse);
            if (getResponse == null) {
                new AlertUser(mActivity).serverError();
            }
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
            if (strings.length == 4) {
                json.put("name", strings[0]);
                json.put("email", strings[1]);
                json.put("password", strings[2]);
                json.put("passwordConfirm", strings[3]);
                Log.d(TAG, "JSON CREATED: " + json);
            }
            if (strings.length == 2) {
                json.put("username", strings[0]);
                json.put("password", strings[1]);
            }
//            else {
//                throw new IllegalArgumentException("Can't create json with user data!");
//            }
        }
        catch (JSONException | ArrayIndexOutOfBoundsException e) {
            Log.e(TAG, "UserDataJson caught exception: "+e);
        }
        return json.toString();
    }

    /**
     * Checking network status and connectionError ability.
     * @param activity get from the current (alive) android Activity.
     * @return boolean result
     */
    public static boolean isNetworkAvailable(Activity activity) {
        ConnectivityManager manager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
