package com.codecool.android.neightbrotaxi.controller;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.codecool.android.neightbrotaxi.model.AlertUser;
import com.codecool.android.neightbrotaxi.model.User;
import com.codecool.android.neightbrotaxi.view.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Responsible for managing Activity's screen, based on the server json responses.
 * @see AlertUser
 */
class ResponseController {
    private static final String TAG = ResponseController.class.getSimpleName()+"<>";

    /**
     * Responsible for convert string to json, then parse it.
     * @param mActivity from the current context
     * @param response from the server
     */
    ResponseController(Activity mActivity, String response) {
        Log.d(TAG, "Response from server: "+response);

        Intent intent = new Intent(mActivity, MainActivity.class);

        // When the user successfully registered
        try {
            if (new JSONObject(response).has("id")) {
                Log.i(TAG, "USER REGISTERED!");
                Toast.makeText(mActivity, "REGISTRATION DONE!", Toast.LENGTH_SHORT).show();
                mActivity.startActivity(intent);
                JSONObject json = new JSONObject(response);
                User user = new User(
                        json.getInt("id"),
                        json.getString("name"),
                        json.getString("email"),
                        json.getString("username"),
                        json.getString("password"),
                        json.getString("passwordConfirm"),
                        null
//                        json.getJSONArray("roles")
                );
                StorageController session = new StorageController(mActivity);
                session.setStoredUser(user);
                Log.i(TAG, "USER OBJECT SAVED: " + user);
                return;
            }
            // When the user successfully logged in
            if (new JSONObject(response).getString("infoMessages")
                    .equals("[\"Successfully logged in!\"]")) {
                Log.i(TAG, "USER LOGGED IN!");
                Toast.makeText(mActivity, "AUTHENTICATION DONE!", Toast.LENGTH_SHORT).show();
                mActivity.startActivity(intent);
                return;
            }
            // Invalid authentication at login
            if (new JSONObject(response).getString("errorMessages")
                    .equals("[\"Invalid username or password!\"]")) {
                Log.i(TAG, "INVALID AUTHENTICATION DATA!");
                new AlertUser(mActivity).invalidAuthenticationError();
            }
        }
        catch (JSONException error) {
            // If user give an exist email address when register
            try {
                JSONArray responseJson = new JSONArray(response);
                JSONObject content = (JSONObject) responseJson.get(0);
                if (content.get("codes").toString().contains("Duplicate")) {
                    Log.i(TAG, "DUPLICATE!");
                    new AlertUser(mActivity).duplicateError();
                }
            }
            // Catch everything else
            catch (JSONException e) {
                Log.e(TAG, String.valueOf(e));
            }
            Log.e(TAG, String.valueOf(error));
        }
    }
}
