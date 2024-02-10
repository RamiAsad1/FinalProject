package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;

public class addToCart extends AppCompatActivity {
    private TextView cartFoodTextView;
    private TextView cartFoodPriceTextView;
    private ImageView cartFoodImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        setViews();
        loadCartItemData();
    }

    private void setViews(){
        cartFoodTextView = findViewById(R.id.cart_food);
        cartFoodPriceTextView = findViewById(R.id.cart_food_price);
        cartFoodImageView = findViewById(R.id.cart_food_image);
    }

    private void loadCartItemData() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        String itemName = preferences.getString("item_name", "");
        String itemImage = preferences.getString("item_image", "");
        String itemPrice = preferences.getString("item_price", "");

        cartFoodTextView.setText(itemName);
        cartFoodPriceTextView.setText(itemPrice);
        Glide.with(this).load(itemImage).into(cartFoodImageView);
    }

    private void updateViews(String name, String price, String picture) {
        TextView cartFood = findViewById(R.id.cart_food);
        TextView cartFoodPrice = findViewById(R.id.cart_food_price);
        ImageView cartFoodImage = findViewById(R.id.cart_food_image);

        cartFood.setText(name);
        cartFoodPrice.setText(price);

        Glide.with(this)
                .load(picture)
                .into(cartFoodImage);
    }


    public void btnHomeOnClick(View view){
        Intent intent3 = new Intent(addToCart.this,MainActivity.class);
        startActivity(intent3);
    }
}
