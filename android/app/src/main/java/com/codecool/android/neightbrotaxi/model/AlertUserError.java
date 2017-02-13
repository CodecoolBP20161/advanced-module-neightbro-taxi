package com.codecool.android.neightbrotaxi.model;

import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;

import java.util.Arrays;

/**
 * Responsible for notify the user, when something went wrong in the app.
 */
public class AlertUserError {
    private final static String TAG = AlertUserError.class.getSimpleName()+"<>";

    /**
     * Build a Dialog and show it on the actual screen.
     * @param activity from the current context.
     * @param strings list with the Dialog details.
     */
    private static void buildDialog (Activity activity, String ... strings) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity)
                .setTitle(strings[0])
                .setMessage(strings[1])
                .setPositiveButton(strings[2], null);
        builder.create().show();
        Log.i(TAG, "Dialog created: "+ Arrays.toString(strings));
    }

    /**
     * If user send a request, but no internet connection.
     * @param activity from the current context.
     */
    public static void connection (Activity activity)  {
        buildDialog(activity,
                "Connection Error!",
                "Please, make sure you have a right internet access!",
                "Okay");
    }

    /**
     * If user send a request, but server doesn't response.
     * @param activity from the current context.
     */
    public static void server (Activity activity)  {
        buildDialog(activity,
                "Server Error!",
                "The server doesn't available now! So, please try again a few minutes or make contact" +
                        "to the support team. Thank you! ",
                "Okay");
    }
}
