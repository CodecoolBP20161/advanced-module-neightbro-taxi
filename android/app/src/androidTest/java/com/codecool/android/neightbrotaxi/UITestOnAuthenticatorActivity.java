package com.codecool.android.neightbrotaxi;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.design.widget.TextInputLayout;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.codecool.android.neightbrotaxi.controller.APIController;
import com.codecool.android.neightbrotaxi.view.AuthenticatorActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertFalse;

@RunWith(AndroidJUnit4.class)
public class UITestOnAuthenticatorActivity {

    @Rule
    public  ActivityTestRule<AuthenticatorActivity> ACTIVITY_TEST_RULE =
            new ActivityTestRule<>(AuthenticatorActivity.class);
    private AuthenticatorActivity TEST_ACTIVITY;
    private WifiManager DeviceWifiManager;

    @Before
    public void setUp() throws Exception {
        TEST_ACTIVITY = ACTIVITY_TEST_RULE.getActivity();
        DeviceWifiManager = (WifiManager)
                TEST_ACTIVITY.getSystemService(Context.WIFI_SERVICE);
        toggleScreen();
    }
    @Test
    public void toggleScreen() {
        onView(withId(R.id.btn_option)).perform(click());
    }

    @Test
    public void validateEmptyName() {
        checkNullInputError(R.id.input_name, R.id.input_layout_name, R.string.err_name_empty);
    }

    @Test
    public void validateEmptyEmail() {
        fillCorrectInputs(4);
        checkNullInputError(R.id.input_email, R.id.input_layout_email, R.string.err_email_empty);
    }

    @Test
    public void validateInvalidEmail() {
        fillCorrectInputs(4);
        checkStringInputError(
                "INVALID", R.id.input_email, R.id.input_layout_email, R.string.err_email);
    }

    @Test
    public void validateEmptyPassword() {
        fillCorrectInputs(3);
        checkNullInputError(R.id.input_password1, R.id.input_layout_password1, R.string.err_pw_empty);
    }

    @Test
    public void validateShortPassword(){
        fillCorrectInputs(3);
        checkStringInputError(
                "PASS", R.id.input_password1, R.id.input_layout_password1, R.string.err_pw_short);
    }

    @Test
    public void validateLongPassword(){
        fillCorrectInputs(3);
        checkStringInputError(
                "THIS_STRING_TOO_LONG",
                R.id.input_password1, R.id.input_layout_password1, R.string.err_pw_long);
    }

    @Test
    public void validateEmptyPasswordConfirm() {
        fillCorrectInputs(2);
        checkNullInputError(R.id.input_password2, R.id.input_layout_password2, R.string.err_pw_same);
    }

    // D/APIController<>: PostTask onPostExecute message: could not execute query
    //{"timestamp":1487181234442,"status":500,"error":"Internal Server Error"
    // TODO: Test separately the common cases!
    @Ignore
    @Test
    public void CorrectInputsNoWifi() {
        DeviceWifiManager.setWifiEnabled(false);

        Boolean signal = APIController.isNetworkAvailable(TEST_ACTIVITY);
        assertFalse(signal);

        DeviceWifiManager.setWifiEnabled(true);
    }

    @Test
    public void correctInputsValues() {
        fillCorrectInputs(1);
        if (APIController.isNetworkAvailable(TEST_ACTIVITY)) {
            try {
                onView(withText("Waiting for authentication!"))
                        .inRoot(withDecorView(not(TEST_ACTIVITY.getWindow().getDecorView())))
                        .check(matches(isDisplayed()));
            }
            catch (NoMatchingViewException e) {
                try {
                    onView(withText("Server Error!")).check(matches(isDisplayed()));
                }
                catch (NoMatchingViewException err) {
                    onView(withText("Already Registered!")).check(matches(isDisplayed()));
                }
            }
        }
        else {
            onView(withText("Connection Error!")).check(matches(isDisplayed()));
        }
    }

    private void fillCorrectInputs(int option) {
        switch (option) {
            case 1:
                onView(withId(R.id.input_password2)).perform(typeText("password"), closeSoftKeyboard());
            case 2:
                onView(withId(R.id.input_password1)).perform(typeText("password"), closeSoftKeyboard());
            case 3:
                onView(withId(R.id.input_email)).perform(typeText("add@re.ss"), closeSoftKeyboard());
            case 4:
                onView(withId(R.id.input_name)).perform(typeText("Tester"), closeSoftKeyboard());
                break;
        }
        if (option == 1) {
            onView(withId(R.id.btn_submit)).perform(click());
        }
    }

    private void checkNullInputError(int inputName, int inputLayoutName, int errName) {
        onView(withId(inputName)).perform(clearText());
        checkInputError(inputLayoutName, errName);
    }

    private void checkStringInputError(String input, int inputName, int inputLayoutName, int errName) {
        onView(withId(inputName)).perform(typeText(input), closeSoftKeyboard());
        checkInputError(inputLayoutName, errName);

    }

    private void checkInputError(int inputLayoutName, int errName) {
        onView(withId(R.id.btn_submit)).perform(click());
        onView(withId(inputLayoutName))
                .check(matches(simulateEmptyInputError(
                        TEST_ACTIVITY.getString(errName))));
    }

    public static Matcher<View> simulateEmptyInputError(final String string) {
        return simulateEmptyInputError(is(string));
    }

    public static Matcher<View> simulateEmptyInputError(final Matcher<String> stringMatcher) {
        checkNotNull(stringMatcher);

        return new BoundedMatcher<View, TextInputLayout>(TextInputLayout.class) {

            String errMsg = null;

            @Override
            public void describeTo(Description description) {
                stringMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(TextInputLayout textInputLayout) {
                CharSequence error = textInputLayout.getError();
                if (error != null) {
                    errMsg = error.toString();
                    return stringMatcher.matches(errMsg);
                }
                return false;
            }
        };
    }
}