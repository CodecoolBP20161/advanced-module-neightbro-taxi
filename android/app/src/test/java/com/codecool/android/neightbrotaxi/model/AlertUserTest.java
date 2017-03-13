package com.codecool.android.neightbrotaxi.model;

import com.codecool.android.neightbrotaxi.BuildConfig;
import com.codecool.android.neightbrotaxi.view.AuthenticatorActivity;

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
        testDialog = new AlertUser(Robolectric.setupActivity(AuthenticatorActivity.class));
        ShadowLog.stream = System.out;
    }

    @Test
    public void connectionError() throws Exception {
        assertNotNull(testDialog.connectionError());
    }

    @Test
    public void serverError() throws Exception {
        assertNotNull(testDialog.serverError());
    }

    @Test
    public void duplicateError() throws Exception {
        assertNotNull(testDialog.duplicateError());
    }

    @Test
    public void invalidAuthenticationError() throws Exception {
        assertNotNull(testDialog.invalidAuthenticationError());
    }

}