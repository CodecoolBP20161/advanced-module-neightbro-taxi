package com.codecool.android.neightbrotaxi.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.codecool.android.neightbrotaxi.model.User;
import com.google.gson.Gson;

/**
 * Responsible for the storing.
 * @see SharedPreferences
 */
class StorageController {

    /**
     * Tag for logging.
     */
    private final static String TAG = StorageController.class.getSimpleName() + "<>";
    /**
     * Initializing the storage.
     */
    private final static String PREFS_KEY = "com.myapp.app.passwordprotector.preferences";
    private static SharedPreferences mSharedPreferences;

    StorageController(Context context) {
        mSharedPreferences = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
    }

//    public  String getStoredString(String key, String defaultReturnValue) {
//        return mSharedPreferences.getString(key, defaultReturnValue);
//    }
//
//    public void setStoringString(String key, String data) {
//        mSharedPreferences.edit().putString(key, data).apply();
//        Log.i(TAG, "String saved successfully with this key: " + key);
//    }

    User getStoredUser() {
        Gson gson = new Gson();
        String json = mSharedPreferences.getString("USER", null);
        return gson.fromJson(json, User.class);
    }

    void setStoredUser(User user) {
        Gson gson = new Gson();
        String stringJson = gson.toJson(user);
        mSharedPreferences.edit().putString("USER", stringJson).apply();
        Log.i(TAG, "User data saved: " + stringJson);
    }

    void removeUser() {
        mSharedPreferences.edit().remove("USER").apply();
        Log.i(TAG, "User removed from storage!");
    }
}
