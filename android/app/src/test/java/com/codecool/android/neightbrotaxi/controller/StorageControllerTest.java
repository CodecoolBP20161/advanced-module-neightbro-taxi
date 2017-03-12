package com.codecool.android.neightbrotaxi.controller;

import com.codecool.android.neightbrotaxi.BuildConfig;
import com.codecool.android.neightbrotaxi.model.User;
import com.codecool.android.neightbrotaxi.view.AuthenticatorActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 25)
public class StorageControllerTest {

    private AuthenticatorActivity mActivity;
    private StorageController controller;

    @Before
    public void setUp() throws Exception {
        mActivity = Robolectric.setupActivity(AuthenticatorActivity.class);
        ShadowLog.stream = System.out;
        controller = new StorageController(mActivity);
    }

    @Test
    public void controllerWorking() throws Exception {
        assertThat(controller, instanceOf(StorageController.class));
    }

    @Test
    public void setTestUser() throws Exception {
        User testUser = new User(0, null, null, null, null, null, null);
        controller.setStoredUser(testUser);
        assertNotNull(controller.getStoredUser());
    }

    @Test
    public void getNullStoredUser() throws Exception {
        assertNull(controller.getStoredUser());
    }

    @Test
    public void getNotNullStoredUser() throws Exception {
        setTestUser();
        assertNotNull(controller.getStoredUser());
    }

    @Test
    public void removeUser() throws Exception {
        controller.removeUser();
        assertNull(controller.getStoredUser());
    }

}