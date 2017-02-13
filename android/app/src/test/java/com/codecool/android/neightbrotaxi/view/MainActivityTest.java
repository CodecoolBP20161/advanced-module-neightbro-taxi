package com.codecool.android.neightbrotaxi.view;

import android.app.Activity;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import com.codecool.android.neightbrotaxi.BuildConfig;
import com.codecool.android.neightbrotaxi.R;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 25)
public class MainActivityTest {

    private Activity mActivity;

    @Before
    public void setUp() throws Exception {
        mActivity = Robolectric.setupActivity(MainActivity.class);
        mActivity.setContentView(R.layout.activity_main);
    }

    @Ignore
    @Test
    public void validateName() throws Exception {
        String errorMsg = mActivity.getString(R.string.err_name_empty);

        EditText inputName = (EditText) mActivity.findViewById(R.id.input_name);

        TextInputLayout inputLayoutName =
                (TextInputLayout) mActivity.findViewById(R.id.input_layout_name);

        inputName.setText(null);

//        inputName.addTextChangedListener(new MainActivity.MyTextWatcher(inputName));

        assertEquals(errorMsg, inputLayoutName.getError());
    }
}
