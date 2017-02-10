package com.codecool.android.neightbrotaxi;

import android.support.design.widget.TextInputLayout;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import com.codecool.android.neightbrotaxi.view.MainActivity;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static org.hamcrest.Matchers.is;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityUITest {

    @Rule
    public ActivityTestRule<MainActivity> mRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void validateEmptyName() {
        onView(withId(R.id.input_name)).perform(clearText());
        onView(withId(R.id.btn_signup)).perform(click());
        onView(withId(R.id.input_layout_name))
                .check(matches(simulateEmptyInputError(mRule.getActivity().getString(R.string.err_name))));
    }

    @Test
    public void validateEmptyEmail() {
        onView(withId(R.id.input_name)).perform(typeText("Test User"), closeSoftKeyboard());
        onView(withId(R.id.input_email)).perform(clearText());
        onView(withId(R.id.btn_signup)).perform(click());
        onView(withId(R.id.input_layout_email))
                .check(matches(simulateEmptyInputError(mRule.getActivity().getString(R.string.err_email))));
    }

    @Test
    public void validateEmptyPassword() {
        onView(withId(R.id.input_name)).perform(typeText("Test User"), closeSoftKeyboard());
        onView(withId(R.id.input_email)).perform(typeText("test@email.com"), closeSoftKeyboard());
        onView(withId(R.id.input_password1)).perform(clearText());
        onView(withId(R.id.btn_signup)).perform(click());
        onView(withId(R.id.input_layout_password1))
                .check(matches(simulateEmptyInputError(mRule.getActivity().getString(R.string.err_pw_empty))));
    }

    @Test
    public void validateEmptyPasswordConfirm() {
        onView(withId(R.id.input_name)).perform(typeText("Test User"), closeSoftKeyboard());
        onView(withId(R.id.input_email)).perform(typeText("test@email.com"), closeSoftKeyboard());
        onView(withId(R.id.input_password1)).perform(typeText("password"), closeSoftKeyboard());
        onView(withId(R.id.input_password2)).perform(clearText());
        onView(withId(R.id.btn_signup)).perform(click());
        onView(withId(R.id.input_layout_password2))
                .check(matches(simulateEmptyInputError(mRule.getActivity().getString(R.string.err_pw_same))));
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