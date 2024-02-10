package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {
    public static final String USERNAME = "USERNAME";
    public static final String FULLNAME = "FULLNAME";
    public static final String DATEOFBIRTH = "DATEOFBIRTH";
    public static final String PASSWORD = "PASSWORD";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private EditText edtStudentID;
    private EditText edtStudentName;
    private EditText edtRegistrationPassword;
    private EditText edtDateOfBirth;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        setViews();
        setupPreferences();
    }

    private void setViews() {
        edtStudentID = findViewById(R.id.edtStudentID);
        edtStudentName = findViewById(R.id.edtStudentName);
        edtRegistrationPassword = findViewById(R.id.edtRegistrationPassword);
        edtDateOfBirth = findViewById(R.id.edtDateOfBirth);
        btnSignUp = findViewById(R.id.btnSignUp);
    }

    private void setupPreferences() {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
    }

    public void btnSignUpOnClick(View view) {
        String studentId = edtStudentID.getText().toString();
        String studentName = edtStudentName.getText().toString();
        String studentPassword = edtRegistrationPassword.getText().toString();
        String studentDateOfBirth = edtDateOfBirth.getText().toString();
        if (!studentId.isEmpty() && !studentName.isEmpty() && !studentPassword.isEmpty() && !studentDateOfBirth.isEmpty()) {
            editor.putString(USERNAME, studentId);
            editor.putString(FULLNAME, studentName);
            editor.putString(PASSWORD,studentPassword);
            editor.putString(DATEOFBIRTH, studentDateOfBirth);
            editor.apply();

            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show();
        }

    }
}