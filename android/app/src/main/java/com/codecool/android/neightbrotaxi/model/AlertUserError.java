package com.codecool.android.neightbrotaxi.model;

import android.app.Activity;
import android.app.AlertDialog;

public class AlertUserError {

    private static void buildDialog (Activity activity, String ... strings) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity)
                .setTitle(strings[0])
                .setMessage(strings[1])
                .setPositiveButton(strings[2], null);
        builder.create().show();
    }

    public static void connection (Activity activity)  {
        buildDialog(activity,
                "Connection Error!",
                "Please, make sure you have a right internet access!",
                "Okay");
    }

    public static void server (Activity activity)  {
        buildDialog(activity,
                "Server Error!",
                "The server doesn't available now! So, please try again a few minutes or make contact" +
                        "to the support team. Thank you! ",
                "Okay");
    }
}
