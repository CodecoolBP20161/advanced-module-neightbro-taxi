package com.codecool.android.neightbrotaxi.controller;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.codecool.android.neightbrotaxi.model.AlertUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Responsible for managing Activity's screen, based on the server json responses.
 * @see AlertUser
 */
public class ResponseController {
    private static final String TAG = ResponseController.class.getSimpleName()+"<>";

    /**
     * @param activity from the current context
     * @param response from the server
     */
    public ResponseController(Activity activity, String response) {
        Log.d(TAG, "Response from server: "+response);

//        Intent intent = new Intent(activity, UserActivity.class);

        try {
            if (new JSONObject(response).has("id")) {
                Log.i(TAG, "USER REGISTERED!");
                Toast.makeText(activity, "REGISTRATION DONE!", Toast.LENGTH_SHORT).show();
//                startActivity(intent);
                return;

                }
            if (new JSONObject(response).getString("infoMessages")
                    .equals("[\"Successfully logged in!\"]")) {
                Log.i(TAG, "USER LOGGED IN!");
                Toast.makeText(activity, "AUTHENTICATION DONE!", Toast.LENGTH_SHORT).show();
//                startActivity(intent);
                return;
            }
            if (new JSONObject(response).getString("errorMessages")
                    .equals("[\"Invalid username or password!\"]")) {
                Log.i(TAG, "INVALID AUTHENTICATION DATA!");
                new AlertUser(activity).invalidAuthenticationError();
            }
        }
        catch (JSONException error) {
            try {
                JSONArray responseJson = new JSONArray(response);
                JSONObject content = (JSONObject) responseJson.get(0);
                if (content.get("codes").toString().contains("Duplicate")) {
                    Log.i(TAG, "DUPLICATE!");
                    new AlertUser(activity).duplicateError();
                }
            }
            catch (JSONException e) {
                Log.e(TAG, String.valueOf(e));
            }
            Log.e(TAG, String.valueOf(error));
        }
    }
}
