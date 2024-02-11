package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

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
            editor.putString(PASSWORD, studentPassword);
            editor.putString(DATEOFBIRTH, studentDateOfBirth);
            editor.apply();

            addStudentToDb(studentId, studentName, studentPassword, studentDateOfBirth);

            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show();
        }
    }

    private void addStudentToDb(String studentId, String studentName, String studentPassword, String studentDateOfBirth) {
        String url = "http://10.0.2.2:5000/create/student";
        RequestQueue queue = Volley.newRequestQueue(this);

        JSONObject registrationData = new JSONObject();
        try {
            registrationData.put("studentId", studentId);
            registrationData.put("name", studentName);
            registrationData.put("password", studentPassword);
            registrationData.put("date_of_birth", studentDateOfBirth);
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, registrationData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error during registration: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(request);
    }

}