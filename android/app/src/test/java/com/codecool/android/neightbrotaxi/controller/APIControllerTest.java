package com.codecool.android.neightbrotaxi.controller;

import com.codecool.android.neightbrotaxi.BuildConfig;
import com.codecool.android.neightbrotaxi.view.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 25)
public class APIControllerTest  {

    private MainActivity mActivity;

    @Before
    public void setUp() throws Exception {
        mActivity = Robolectric.setupActivity(MainActivity.class);
        ShadowLog.stream = System.out;
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
    @Test
    public void checkPostTaskResponse() throws Exception {
        String requestAdr = "registration";
        String[] rawData = new String[]{
                "Test User",
                "test",
                "password",
                "password"
        };

        assertNotNull(
        new APIController.PostTask(
                mActivity,
                requestAdr,
                rawData
        ).execute());
    }

    @Test
    public void checkGetTaskResponse() throws Exception {
        assertNotNull(new APIController.GetTask(mActivity, "user").execute());
    }
}