package com.codecool.android.neightbrotaxi.model;

import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.view.View;

import com.codecool.android.neightbrotaxi.R;

/**
 * Responsible for notify the user, when something went wrong in the app.
 */
public class AlertUser {
    private static String TAG = AlertUser.class.getSimpleName() + " >>> ¤#¤ >>> ";
    private Activity mActivity;

    /**
     * Work with current Activity.
     * @param activity from the current context.
     */
    public AlertUser(Activity activity) {
        mActivity = activity;
    }

    /**
     * If user send a request, but no internet connection.
     */
    public void connectionError()  {
        buildDialog(
                "Connection Error!",
                "Please, make sure you have a right internet access!",
                "All right!");
    }

    /**
     * If user send a request, but serverError doesn't response.
     */
    public void serverError() {
        buildDialog(
                "Server Error!",
                "The server doesn't available now! Please try again a few moments or make contact " +
                        "to the support team. Thank you! ",
                "Okay.");
    }

    /**
     * If user send an existing email in database.
     */
    public void duplicateError() {
        buildDialog(
                "Already Registered!",
                "The email address doesn't allow, because somebody use it.",
                "I fix it!"
        );
    }

    /**
     * If user want to login an invalid email address or password (based on server response).
     */
    public void invalidAuthenticationError() {
        buildDialog(
                "Invalid Authentication!",
                "Authentication data is doesn\'t exist. Please, fix it or register a new account!",
                "I get it!"
        );
    }

    /**
     * Build a Dialog and show it on the actual screen.
     */
    private void buildDialog(String... strings) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity, R.style.MyDialogTheme)
                .setTitle(strings[0])
                .setMessage(strings[1])
                .setPositiveButton(strings[2], null);
        AlertDialog dialog = builder.show();

        int titleDividerId = mActivity.getResources().getIdentifier("titleDivider", "id", "android");
        View titleDivider = dialog.findViewById(titleDividerId);
        if (titleDivider != null)
            titleDivider.setBackgroundColor(mActivity.getApplicationContext()
                    .getColor(android.R.color.holo_orange_light));

        Log.i(TAG, strings[0]);
    }
}
