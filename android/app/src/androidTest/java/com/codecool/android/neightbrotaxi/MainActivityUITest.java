package com.codecool.android.neightbrotaxi;

import android.support.design.widget.TextInputLayout;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.codecool.android.neightbrotaxi.view.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class MainActivityUITest {
    public static final String TAG = MainActivityUITest.class.getSimpleName()+"<>";

    public static Matcher<View> withErrorInInputLayout(final Matcher<String> stringMatcher) {
        checkNotNull(stringMatcher);

        return new BoundedMatcher<View, TextInputLayout>(TextInputLayout.class) {
            String actualError = "";

            @Override
            public void describeTo(Description description) {
                description.appendText("description: "+description);
                stringMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(TextInputLayout textInputLayout) {
                CharSequence error = textInputLayout.getError();
                if (error != null) {
                    actualError = error.toString();
                    return stringMatcher.matches(actualError);
                }
                return false;
            }
        };
    }

    public static Matcher<View> withErrorInInputLayout(final String string) {
        return withErrorInInputLayout(is(string));
    }

    @Rule
    public ActivityTestRule<MainActivity> mRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void validateName() {
        String stringInput = "";
//        onView(withId(R.id.input_name)).perform(typeText(stringInput));
//
//        onView(withId(R.id.input_name)).perform(pressImeActionButton());

//        onView(withId(R.id.input_layout_name))
//                .check(matches(viewMatcher(String.valueOf(mRule.getActivity().getText(R.string.err_name)))));
    }
}