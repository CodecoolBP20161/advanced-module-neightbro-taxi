package com.codecool.android.neightbrotaxi.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.codecool.android.neightbrotaxi.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName() + "<>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     *
     * Activity lifecycle logging.
     *
     */

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "ACTIVITY STARTED!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "ACTIVITY RESUMED!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "ACTIVITY PAUSED!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "ACTIVITY STOPPED!");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "ACTIVITY DESTROYED!");
    }
}
