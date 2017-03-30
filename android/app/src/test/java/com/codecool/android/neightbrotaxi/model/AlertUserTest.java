package com.codecool.android.neightbrotaxi.model;

import com.codecool.android.neightbrotaxi.BuildConfig;
import com.codecool.android.neightbrotaxi.view.FormActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import static junit.framework.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 25)
public class AlertUserTest {

    private AlertUser testDialog;

    @Before
    public void setUp() throws Exception {
        testDialog = new AlertUser(Robolectric.setupActivity(FormActivity.class));
        ShadowLog.stream = System.out;
    }

    @Test
    public void connectionError() throws Exception {
        testDialog.connectionError();
    }

    @Test
    public void serverError() throws Exception {
       testDialog.serverError();
    }

    @Test
    public void duplicateError() throws Exception {
        testDialog.duplicateError();
    }

    @Test
    public void invalidAuthenticationError() throws Exception {
        testDialog.invalidAuthenticationError();
    }

}