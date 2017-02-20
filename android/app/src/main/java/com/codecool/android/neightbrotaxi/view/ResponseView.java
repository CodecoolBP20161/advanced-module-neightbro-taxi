package com.codecool.android.neightbrotaxi.view;

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
public class ResponseView {
    private static final String TAG = ResponseView.class.getSimpleName()+"<>";

    /**
     * @param activity from the current context
     * @param response from the server
     * @throws JSONException when converting went wrong
     */
    public ResponseView(Activity activity, String response) throws JSONException {
        Log.d(TAG, "ResponseView response: "+response);
        try {
            JSONArray responseJson = new JSONArray(response);
            JSONObject content = (JSONObject) responseJson.get(0);

            if (content.get("codes").toString().contains("Duplicate")) {
                Log.i(TAG, "DUPLICATE!");
                new AlertUser(activity).duplicateError();
            }
        }
        catch (JSONException e) {
//            Intent intent = new Intent(activity, UserActivity.class);
            if (response.equals("")) {
                Log.i(TAG, "USER LOGGED IN!");
                Toast.makeText(activity, "AUTHENTICATION DONE!", Toast.LENGTH_SHORT).show();
//                startActivity(intent);
                return;
            }
            if (new JSONObject(response).has("id")) {
                Log.i(TAG, "USER REGISTERED!");
                Toast.makeText(activity, "REGISTRATION DONE!", Toast.LENGTH_SHORT).show();
//                startActivity(intent);
            }
        }
    }
}
