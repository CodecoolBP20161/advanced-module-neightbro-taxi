package com.codecool.android.neightbrotaxi.view;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codecool.android.neightbrotaxi.R;
import com.codecool.android.neightbrotaxi.controller.APIController;
import com.codecool.android.neightbrotaxi.model.AlertUser;


/**
 * This responsible for the first screen when the user launch the app.
 * Manage the activity, what ensure a registration interface.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Create TAG for logging and views for the inputs and their layouts.
     */
    private static final String TAG = MainActivity.class.getSimpleName() + "<>";
    private EditText inputName, inputEmail, inputPassword1, inputPassword2;
    private TextInputLayout inputLayoutName, inputLayoutEmail,
            inputLayoutPassword1, inputLayoutPassword2;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    /**
     * Set the layout for this activity, what appear on the screen.
     *
     * @param savedInstanceState get from System
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "ACTIVITY CREATED!");

        // Set to the right color for the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getColor(R.color.colorPrimary));
        setSupportActionBar(toolbar);

        // Find the views in the layout.
        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword1 = (TextInputLayout) findViewById(R.id.input_layout_password1);
        inputLayoutPassword2 = (TextInputLayout) findViewById(R.id.input_layout_password2);
        inputName = (EditText) findViewById(R.id.input_name);
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword1 = (EditText) findViewById(R.id.input_password1);
        inputPassword2 = (EditText) findViewById(R.id.input_password2);
        Button btnSignUp = (Button) findViewById(R.id.btn_signup);

        // Every input view get a TextWatcher.
        inputName.addTextChangedListener(new MyTextWatcher(inputName));
        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputPassword1.addTextChangedListener(new MyTextWatcher(inputPassword1));
        inputPassword2.addTextChangedListener(new MyTextWatcher(inputPassword2));

        // When user touch on the button run the submitForm.
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

    /**
     * Check the validator boolean result.
     * If the network connectionError is OK,
     * Send user inputs to the correct URL.
     * Finally notify user, what happens.
     */
    private void submitForm() {
        if (!validateName()) {
            return;
        }

        if (!validateEmail()) {
            return;
        }

        if (!validateFirstPassword()) {
            return;
        }
        if (!validatePasswordSame()) {
            return;
        }

        if (APIController.isNetworkAvailable(MainActivity.this)) {
            new APIController.PostTask(
                    MainActivity.this,
                    "registration",
                    inputName.getText().toString(),
                    inputEmail.getText().toString(),
                    inputPassword1.getText().toString(),
                    inputPassword2.getText().toString()
            ).execute();
            Toast.makeText(getApplicationContext(), "Waiting for authentication!", Toast.LENGTH_SHORT).show();
        } else {
           new AlertUser(MainActivity.this).connectionError();
        }

    }

    /**
     * Check is empty.
     *
     * @return the result as boolean
     */
    private boolean validateName() {
        if (inputName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(getString(R.string.err_name_empty));
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }

    /**
     * Check empty and validity.
     *
     * @return the result as boolean
     */
    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();
        Boolean isValidEmail =
                !TextUtils.isEmpty(email) &&
                        android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();


        if (email.isEmpty()) {
            inputLayoutEmail.setError(getString(R.string.err_email_empty));
        }
        else {
            inputLayoutEmail.setErrorEnabled(false);
            if (!isValidEmail) {
                inputLayoutEmail.setError(getString(R.string.err_email));
                return false;
            }
            else {
                inputLayoutEmail.setErrorEnabled(false);
            }
        }
        return true;
    }

    /**
     * Check empty and length.
     *
     * @return the result as boolean
     */
    private boolean validateFirstPassword() {
        String pw1 = inputPassword1.getText().toString().trim();

        if (pw1.isEmpty()) {
            inputLayoutPassword1.setError(getString(R.string.err_pw_empty));
            return false;
        }
        if (pw1.length() < 8) {
            inputLayoutPassword1.setError(getString(R.string.err_pw_short));
            return false;
        }
        if (pw1.length() > 16) {
            inputLayoutPassword1.setError(getString(R.string.err_pw_long));
            return false;
        }
        else {
            inputLayoutPassword1.setErrorEnabled(false);
        }
        return true;
    }

    /**
     * Check equals.
     *
     * @return the result as boolean
     */
    private boolean validatePasswordSame() {
        String pw1 = inputPassword1.getText().toString().trim();
        String pw2 = inputPassword2.getText().toString().trim();
        if (!pw2.equals(pw1)) {
            inputLayoutPassword2.setError(getString(R.string.err_pw_same));
            return false;
        } else {
            inputLayoutPassword2.setErrorEnabled(false);
        }
        return true;
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        /**
         * TextWatcher class used by system,
         * MyTextWatcher extended it.
         *
         * @param view from the system
         */
        private MyTextWatcher(View view) {
            this.view = view;
        }

        // Not used by me, just must to be implemented.
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        // Not used by me, just must to be implemented.
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        /**
         * Based on view id define order for TextWatcher.
         *
         * @param editable get by system
         */
        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_name:
                    validateName();
                    break;
                case R.id.input_email:
                    validateEmail();
                    break;
                case R.id.input_password1:
                    validateFirstPassword();
                    break;
                case R.id.input_password2:
                    validatePasswordSame();
                    break;
            }
        }
    }
}