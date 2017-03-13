package com.codecool.android.neightbrotaxi.controller;

import com.codecool.android.neightbrotaxi.BuildConfig;
import com.codecool.android.neightbrotaxi.view.FormActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 25)
public class ResponseControllerTest {

    private FormActivity mActivity;
    private String response;

    @Before
    public void setUp() throws Exception {
        mActivity = Robolectric.setupActivity(FormActivity.class);
        ShadowLog.stream = System.out;
    }

    @Test
    public void createInstance() throws Exception {
        ResponseController controller = new ResponseController(mActivity, "");
        assertThat(controller, instanceOf(ResponseController.class));
    }

    @Test
    public void validRegistration() throws Exception {
        response =
                "{\"id\":10,\"name\":\"Test User\",\"email\":\"test@a.c\",\"username\":\"test@a.c\"," +
                        "\"password\":\"$2a$10$VKSsTXcL5bzGVfrAf0HHHexSsV9LNC.bEVftsBCwiyyBiKSiGe1eq\"," +
                        "\"passwordConfirm\":null," +
                        "\"roles\":[{\"id\":5,\"name\":\"USER\"}]}";

        ResponseController controller = new ResponseController(mActivity, response);
        assertNotNull(controller);
    }

    @Test
    public void invalidRegistration() throws  Exception {
        response = "[{\"codes\":[\"Duplicate.user.email.user.email\",\"Duplicate.user.email.email\"," +
                "\"Duplicate.user.email.java.lang.String\",\"Duplicate.user.email\"]," +
                "\"arguments\":null,\"defaultMessage\":\"Email already in database\"," +
                "\"objectName\":\"user\",\"field\":\"email\",\"rejectedValue\":\"test@a.c\"," +
                "\"bindingFailure\":false,\"code\":\"Duplicate.user.email\"}]";

        ResponseController controller = new ResponseController(mActivity, response);
        assertNotNull(controller);
    }

    @Test
    public void validLogin() throws Exception {
        response =
                "{\"errorMessages\":[],\"infoMessages\":[\"Successfully logged in!\"]," +
                        "\"loggedInUser\":{\"id\":10,\"name\":\"Test User\",\"email\":\"test@a.c\"," +
                        "\"username\":\"test@a.c\",\"password\":\"$2a$10$VKSsTXcL5bzGVfrAf0HHHexSs" +
                        "V9LNC.bEVftsBCwiyyBiKSiGe1eq\",\"passwordConfirm\":null," +
                        "\"roles\":[{\"id\":5,\"name\":\"USER\"}]}}";

        ResponseController controller = new ResponseController(mActivity, response);
        assertNotNull(controller);
    }

    @Test
    public void invalidLogin() throws Exception {
        response = "{\"errorMessages\":[\"Invalid username or password!\"],\"infoMessages\":[]," +
                "\"loggedInUser\":{\"id\":10,\"name\":\"Test User\",\"email\":\"test@a.c\"," +
                "\"username\":\"test@a.c\",\"password\":\"$2a$10$VKSsTXcL5bzGVfrAf0HHHexSsV9LNC.bEVf" +
                "tsBCwiyyBiKSiGe1eq\",\"passwordConfirm\":null," +
                "\"roles\":[{\"id\":5,\"name\":\"USER\"}]}}";

        ResponseController controller = new ResponseController(mActivity, response);
        assertNotNull(controller);
    }

}