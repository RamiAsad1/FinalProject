package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnLogin;
    private TextView txtRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);

        setViews();
        setupPreferences();
    }

    @Override
    protected void onResume() {
        super.onResume();

        String username = preferences.getString(USERNAME, "");
        String password = preferences.getString(PASSWORD, "");
        edtUsername.setText(username);
        edtPassword.setText(password);
    }

    private void setViews() {
        edtUsername = findViewById(R.id.username);
        edtPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.loginButton);
        txtRegister = findViewById(R.id.signupText);


    }


    private void setupPreferences() {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        String username = preferences.getString(USERNAME, "");
        String password = preferences.getString(PASSWORD, "");
        edtUsername.setText(username);
        edtPassword.setText(password);
    }


    private boolean authenticateUser(String username, String password) {
        String storedUsername = preferences.getString(USERNAME, "");
        String storedPassword = preferences.getString(PASSWORD, "");

        if (username.equals("") || password.equals("") || storedUsername.equals("") || storedPassword.equals("")) {
            return false;
        }

        if (storedUsername.equals(username) && storedPassword.equals(password)) {
            return true;
        }
        return false;
    }

    public void btnSignInOnClick(View view) {
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();

        if (authenticateUser(username, password)) {
            Intent intent2 = new Intent(this, MainActivity.class);
            startActivity(intent2);
        } else {
            Toast.makeText(getApplicationContext(), "Please make sure your information is correct", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnRegisterOnClick(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

}
