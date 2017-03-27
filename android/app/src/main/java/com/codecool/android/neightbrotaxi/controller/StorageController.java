package com.codecool.android.neightbrotaxi.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.codecool.android.neightbrotaxi.R;
import com.codecool.android.neightbrotaxi.model.User;
import com.google.gson.Gson;

/**
 * Responsible for the storing on device like http session in client-server communication.
 * @see SharedPreferences
 */
public class StorageController {

    /**
     * Tag for logging.
     */
    private static String TAG = StorageController.class.getSimpleName() + " >>> ¤#¤ >>> ";
    /**
     * Create fields
     */
    private final static String PREFS_KEY = "com.myapp.app.passwordprotector.preferences";
    private static SharedPreferences mSharedPreferences;

    /**
     * Responsible for SharedPreferences initializing
     * @param context from the current activity
     */
    public StorageController(Context context) {
        mSharedPreferences = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
    }

    /**
   * Load the current user from device.
   * @return a user object
   */
    public User getStoredUser() {
        Gson gson = new Gson();
        String json = mSharedPreferences.getString("USER", null);
        Log.i(TAG, "User object data loaded as String.");
        return gson.fromJson(json, User.class);
    }

    /**
     * Save the current user in device.
     * @param user object
     */
    public void setStoredUser(User user) {
        Gson gson = new Gson();
        String stringJson = gson.toJson(user);
        mSharedPreferences.edit().putString("USER", stringJson).apply();
        Log.i(TAG, "User object data saved as String.");
    }

    /**
     * Remove the current user.
     */
    public void removeUser() {
        mSharedPreferences.edit().remove("USER").apply();
        Log.i(TAG, "User object data removed from storage!");
    }
}
