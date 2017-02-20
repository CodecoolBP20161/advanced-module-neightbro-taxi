package com.codecool.android.neightbrotaxi.controller;

import android.os.AsyncTask;

import com.codecool.android.neightbrotaxi.BuildConfig;
import com.codecool.android.neightbrotaxi.view.MainActivity;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 25)
public class APIControllerTest  {

    private MainActivity mActivity;

    @Before
    public void setUp() throws Exception {
        mActivity = Robolectric.setupActivity(MainActivity.class);

    }

    @Test
    public void checkNetworkConnection() throws Exception{
        assertTrue(APIController.isNetworkAvailable(mActivity));
    }

    @Test
    public void createUserJson() throws Exception{
        String finalData =
                APIController.UserDataJson("Test User", "test@email.com", "password", "password");

        String rawData =
                "{\"name\":\"Test User\",\"email\":\"test@email.com\"," +
                        "\"password\":\"password\",\"passwordConfirm\":\"password\"}";

        assertEquals(rawData, finalData);
    }


    @Ignore
    @Test
    public void makePostRequest() throws Exception{
        final String POST_URL = "http://www.roundsapp.com/post";
        String rawJson =
                "{'winCondition':'HIGH_SCORE',"
                + "'name':'Bowling',"
                + "'round':4,"
                + "'lastSaved':1367702411696,"
                + "'dateStarted':1367702378785,"
                + "'players':["
                + "{'name':'player1','history':[10,8,6,7,8],'color':-13388315,'total':39},"
                + "{'name':'player2','history':[6,10,5,10,10],'color':-48060,'total':41}"
                + "]}";

        AsyncTask task = new APIController.PostTask();
    }

    @Ignore
    @Test
    public void makeGetRequest() throws Exception{
        final String GET_URL = "https://publicobject.com/helloworld.txt";
        AsyncTask task = new APIController.GetTask();
    }
}