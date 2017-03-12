package com.codecool.android.neightbrotaxi.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.codecool.android.neightbrotaxi.R;
import com.codecool.android.neightbrotaxi.controller.StorageController;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName() + "<>";
    private StorageController storageController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TAG = TAG + getResources().getString(R.string.tag);
        storageController = new StorageController(getApplicationContext());

        Log.i(TAG, "ACTIVITY CREATED!");
/*
        // Set to the right color for the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getColor(R.color.colorPrimary));
        setSupportActionBar(toolbar);*/
    }

    public void showPopup(View v) {
        storageController = new StorageController(getApplicationContext());
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.main, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.profile:
                        getProfile();
                        break;
                    case R.id.logout:
                        logout();
                        break;
                }
                return false;
            }
        });
        popup.show();
    }

    private void logout() {
        Toast.makeText(getApplicationContext(), "logout clicked", Toast.LENGTH_SHORT).show();
        storageController.removeUser();
        finish();
    }

    private void getProfile() {
        Toast.makeText(getApplicationContext(), storageController.getStoredUser().toString(),
                Toast.LENGTH_SHORT).show();
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

        try {
            Log.i(TAG, "IN SESSION: " + storageController.getStoredUser().toString());
            storageController.getStoredUser();
        }
        catch (NullPointerException e ) {
            Log.e(TAG, e.getMessage());
            Intent intent = new Intent(MainActivity.this, AuthenticatorActivity.class);
            startActivity(intent);
        }
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
