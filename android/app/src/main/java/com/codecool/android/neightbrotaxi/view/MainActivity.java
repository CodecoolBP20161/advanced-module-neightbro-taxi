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

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName()+">>>>";

    private Toolbar toolbar;
    private EditText inputName, inputEmail, inputPassword1, inputPassword2;
    private TextInputLayout inputLayoutName, inputLayoutEmail,
            inputLayoutPassword1, inputLayoutPassword2;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getColor(R.color.colorPrimary));
        setSupportActionBar(toolbar);

        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword1 = (TextInputLayout) findViewById(R.id.input_layout_password1);
        inputLayoutPassword2 = (TextInputLayout) findViewById(R.id.input_layout_password2);
        inputName = (EditText) findViewById(R.id.input_name);
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword1 = (EditText) findViewById(R.id.input_password1);
        inputPassword2 = (EditText) findViewById(R.id.input_password2);
        btnSignUp = (Button) findViewById(R.id.btn_signup);

        inputName.addTextChangedListener(new MyTextWatcher(inputName));
        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputPassword1.addTextChangedListener(new MyTextWatcher(inputPassword1));
        inputPassword2.addTextChangedListener(new MyTextWatcher(inputPassword2));

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });
    }

    private void submitForm() {
        if (!validateName()) {
            return;
        }

        if (!validateEmail()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }
        if (!validatePasswordSame()) {
            return;
        }
        Toast.makeText(getApplicationContext(), "Thanks for registration!", Toast.LENGTH_SHORT).show();
    }

    private boolean validateName() {
        if (inputName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(getString(R.string.err_name));
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_email));
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        String password = inputPassword1.getText().toString().trim();

        if (password.isEmpty()) {
            inputLayoutPassword1.setError(getString(R.string.err_pw_empty));
            return false;
        }
        if (password.length()<8) {
            inputLayoutPassword1.setError(getString(R.string.err_pw_short));
            return false;
        }
        else if (password.length()>16) {
            inputLayoutPassword1.setError(getString(R.string.err_pw_long));
            return false;
        }
        else {
            inputLayoutPassword1.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePasswordSame() {
        String pw1 = inputPassword1.getText().toString().trim();
        String pw2 = inputPassword2.getText().toString().trim();
        if(!pw2.equals(pw1)) {
            inputLayoutPassword2.setError(getString(R.string.err_pw_same));
            return false;
        }
        else {
            inputLayoutPassword2.setErrorEnabled(false);
        }
        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_name:
                    validateName();
                    break;
                case R.id.input_email:
                    validateEmail();
                    break;
                case R.id.input_password1:
                    validatePassword();
                    break;
                case R.id.input_password2:
                    validatePasswordSame();
                    break;
            }
        }
    }
}