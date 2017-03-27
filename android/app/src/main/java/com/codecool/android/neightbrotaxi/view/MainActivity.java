package com.codecool.android.neightbrotaxi.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.text.Layout;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.codecool.android.neightbrotaxi.R;
import com.codecool.android.neightbrotaxi.controller.StorageController;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName() + " >>> ¤#¤ >>> ";
    private StorageController storageController;

    RelativeLayout mMainLayout;
    TextView mUserName, mUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storageController = new StorageController(getApplicationContext());
        mMainLayout = (RelativeLayout) findViewById(R.id.main_layout);
        mUserName = (TextView) findViewById(R.id.userName);
        mUserEmail = (TextView) findViewById(R.id.userEmail);

        Log.i(TAG, "ACTIVITY CREATED!");
/*
        // Set to the right color for the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getColor(R.color.colorPrimary));
        setSupportActionBar(toolbar);*/
    }

    public void back(View view) {
        if(mMainLayout.getVisibility()==View.INVISIBLE) {
            finish();
        }
        mMainLayout.setVisibility(View.INVISIBLE);
    }

    public void showPopup(View v) {
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
        mUserName.setText(storageController.getStoredUser().getName());
        mUserEmail.setText(storageController.getStoredUser().getEmail());

        ListView listView = (ListView) findViewById(R.id.userOpinions);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.opinions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listView.setAdapter(adapter);

        mMainLayout.setVisibility(View.VISIBLE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Profile editing unavailable for now..", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                profileEditing();
            }
        });
    }

    private void profileEditing() {
        // Sign an intent (profile editing) for FormActivity.
        Intent intent = new Intent(this, FormActivity.class);
        intent.putExtra("PROFILE_SETTING", "");
        startActivity(intent);
        recreate();
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
            Intent intent = new Intent(MainActivity.this, FormActivity.class);
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
