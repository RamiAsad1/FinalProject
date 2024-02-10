package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.project.Adapter.CategoryAdapter;
import com.example.project.Adapter.FastDeliveryAdapter;
import com.example.project.Domain.CategoryDomain;
import com.example.project.Domain.FastedFoodDomain;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private CategoryAdapter adapter;
    private FastDeliveryAdapter adapter2;
    private RecyclerView recyclerViewCategory, recyclerViewFastFood;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupPreferences();
        initializeRecyclerViews();
        recyclerViewFastList();
        loadCategoryRecyclerData();
    }

    private void setupPreferences() {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
    }

    private void initializeRecyclerViews() {
        recyclerViewCategory = findViewById(R.id.view1);
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        adapter = new CategoryAdapter(new ArrayList<>(), new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CategoryDomain category) {
                addToCart(category.getTitle(), category.getPrice(), category.getPic());
            }
        });
        recyclerViewCategory.setAdapter(adapter);

        recyclerViewFastFood = findViewById(R.id.view2);
        recyclerViewFastFood.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

    }

    private void addToCart(String itemName, String itemImage, String itemPrice) {
        if (itemName != null && itemImage != null && itemPrice != null) {
            editor.putString("item_name", itemName);
            editor.putString("item_image", itemImage);
            editor.putString("item_price", itemPrice);
            editor.apply();
            showToast("Item added to cart successfully");
        } else {
            showToast("Failed to add item to cart. Please try again.");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void loadCategoryRecyclerData() {
        String url = "http://10.0.2.2:5000/products";
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<CategoryDomain> categoryList = new ArrayList<>();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONArray item = response.getJSONArray(i);
                                String name = item.getString(1);
                                String picture = item.getString(3);
                                String price = item.getString(4);
                                categoryList.add(new CategoryDomain(name, picture, price));
                            }
                            adapter.updateData(categoryList);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley Error", "Error fetching categories", error);
            }
        });

        queue.add(jsonArrayRequest);
    }

    private void recyclerViewFastList() {
        ArrayList<FastedFoodDomain> fastlist = new ArrayList<>();
        fastlist.add(new FastedFoodDomain("Chesse Burger", "fast_1"));
        fastlist.add(new FastedFoodDomain("pperoni pizza", "fast_2"));
        fastlist.add(new FastedFoodDomain("vagetable pizza", "fast_3"));

        adapter2 = new FastDeliveryAdapter(fastlist);
        recyclerViewFastFood.setAdapter(adapter2);
    }

    public void btnCartOnclick(View view) {
        Intent intent2 = new Intent(MainActivity.this, addToCart.class);
        startActivity(intent2);
    }
}