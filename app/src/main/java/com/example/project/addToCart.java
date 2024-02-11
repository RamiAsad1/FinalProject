package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class addToCart extends AppCompatActivity {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private TextView cartFoodTextView;
    private TextView cartFoodPriceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        setupPreferences();
        setViews();
        loadCartItemData();
    }

    private void setupPreferences() {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
    }

    private void setViews(){
        cartFoodTextView = findViewById(R.id.cart_food_name);
        cartFoodPriceTextView = findViewById(R.id.cart_food_price);
    }

    private void loadCartItemData() {
        String itemName = preferences.getString("item_name", "");
        String itemPrice = preferences.getString("item_price", "");

        cartFoodTextView.setText(itemName);
        cartFoodPriceTextView.setText(itemPrice);
    }

    private void createOrder() {
        String url = "http://10.0.2.2:5000/create/order";
        RequestQueue queue = Volley.newRequestQueue(this);

        String studentId = preferences.getString("USERNAME","");
        String productId = preferences.getString("item_id","");
        Log.d("pref",studentId);
        Log.d("pref",productId);
        JSONObject orderData = new JSONObject();
        try {
            orderData.put("student_id", studentId);
            orderData.put("product_id", productId);
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, orderData,
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

    public void btnPlaceOrderOnClick(View view){
        createOrder();
    }

    public void btnHomeOnClick(View view){
        Intent intent3 = new Intent(addToCart.this,MainActivity.class);
        startActivity(intent3);
    }
}
