package com.codecool.android.neightbrotaxi.view;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;

import com.codecool.android.neightbrotaxi.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName() + "<>";

    private DrawerLayout drawerLayout;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        // Set to the right color for the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getColor(R.color.colorPrimary));
        setSupportActionBar(toolbar);*/

//        imageView = (ImageView) findViewById(R.id.icon_menu);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                LayoutInflater layoutInflater = (LayoutInflater) getBaseContext()
//                        .getSystemService(LAYOUT_INFLATER_SERVICE);
//
//                View popupView = layoutInflater.inflate(R.layout.popup, null);
//
//                final PopupWindow popupWindow = new PopupWindow(popupView,
//                        RelativeLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//
////                popupWindow.showAsDropDown(mButton, 0, v.getHeight());
//
//                ListView list = (ListView) findViewById(R.id.list);
//
//                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//                    @Override
//                    public void onItemClick(AdapterView<?> arg0, View arg1,
//                                            int arg2, long arg3) {
//                        // SOME YOUR CODE
////                        Toast.makeText(getApplicationContext(), "button pressed", Toast.LENGTH_SHORT);
//
//                        ArrayList<String> myStringArray1 = new ArrayList<String>();
//                        myStringArray1.add("Andrea");
//                        adapter = new RecyclerView.Adapter(getActivity(), R.layout.row, myStringArray1);
//                        myListView.setAdapter(adapter);
//                        adapter.notifyDataSetChanged();
//                        popupWindow.dismiss();
//                    }
//                });
//            }
//        });


    }


    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.main, popup.getMenu());
        //        popup.setOnMenuItemClickListener(this);
        popup.show();
    }

//    @Override
//    public boolean onMenuItemClick(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_settings:
//                Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_SHORT).show();
//            default:
//            return false;
//        }
//    }

    public void logout(View v){

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

    /**
     * This method will be invoked when a menu item is clicked if the item
     * itself did not already handle the event.
     *
     * @param item the menu item that was clicked
     * @return {@code true} if the event was handled, {@code false}
     * otherwise
     */

}
