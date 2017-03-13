package com.codecool.android.neightbrotaxi.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.codecool.android.neightbrotaxi.R;
import com.codecool.android.neightbrotaxi.controller.StorageController;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName() + "<>";
    private StorageController storageController;

    RelativeLayout mLayout;
    TextView mUserName, mUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TAG = TAG + getResources().getString(R.string.tag);
        storageController = new StorageController(getApplicationContext());
        mLayout = (RelativeLayout) findViewById(R.id.profile_layout);
        mUserName = (TextView) findViewById(R.id.userName);
        mUserEmail = (TextView) findViewById(R.id.userEmail);

        Toast.makeText(getApplicationContext(), "WELCOME ON THE MAIN PAGE!",
                Toast.LENGTH_SHORT).show();

        Log.i(TAG, "ACTIVITY CREATED!");
/*
        // Set to the right color for the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getColor(R.color.colorPrimary));
        setSupportActionBar(toolbar);*/
    }

    public void back(View view) {
        if(mLayout.getVisibility()==View.INVISIBLE) {
            finish();
        }
        mLayout.setVisibility(View.INVISIBLE);
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

    private void getProfile() {
        storageController = new StorageController(getApplicationContext());

        mUserName.setText(storageController.getStoredUser().getName());
        mUserEmail.setText(storageController.getStoredUser().getEmail());

        ListView listView = (ListView) findViewById(R.id.userOpinions);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.opinions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listView.setAdapter(adapter);

        mLayout.setVisibility(View.VISIBLE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Profile editing unavailable for now..", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void logout() {
        Toast.makeText(getApplicationContext(), "logout clicked", Toast.LENGTH_SHORT).show();
        storageController.removeUser();
        finish();
    }

    private void profileEditing() {
        Intent intent = new Intent(this, FormActivity.class);
        intent.putExtra("PROFILE_SETTING", true);
        startActivity(intent);
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
