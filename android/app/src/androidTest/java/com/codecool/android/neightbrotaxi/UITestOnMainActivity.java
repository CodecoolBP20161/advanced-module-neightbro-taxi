package com.codecool.android.neightbrotaxi;

import android.support.design.widget.TextInputLayout;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.codecool.android.neightbrotaxi.controller.APIController;
import com.codecool.android.neightbrotaxi.view.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class UITestOnMainActivity {

    @Rule
    public  ActivityTestRule<MainActivity> ACTIVITY_TEST_RULE =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void validateEmptyName() {
        checkNullInputError(R.id.input_name, R.id.input_layout_name, R.string.err_name);
    }

    @Test
    public void validateEmptyEmail() {
        fillCorrectInputs(4);
        checkNullInputError(R.id.input_email, R.id.input_layout_email, R.string.err_email);
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

    @Test
    public void correctInputsValues() {
        if (APIController.isNetworkAvailable(ACTIVITY_TEST_RULE.getActivity())) {
            fillCorrectInputs(1);
            onView(withText("Waiting for authentication!"))
                    .inRoot(withDecorView(not(ACTIVITY_TEST_RULE.getActivity().getWindow().getDecorView())))
                    .check(matches(isDisplayed()));
        }
        else {
            fillCorrectInputs(1);
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
                onView(withId(R.id.input_email)).perform(typeText("test@email.com"), closeSoftKeyboard());
            case 4:
                onView(withId(R.id.input_name)).perform(typeText("Test User"), closeSoftKeyboard());
                break;
        }
        if (option == 1) {
            onView(withId(R.id.btn_signup)).perform(click());
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
        onView(withId(R.id.btn_signup)).perform(click());
        onView(withId(inputLayoutName))
                .check(matches(simulateEmptyInputError(
                        ACTIVITY_TEST_RULE.getActivity().getString(errName))));
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