package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import androidx.recyclerview.widget.RecyclerView;
import androidx.cardview.widget.CardView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.project.Adapter.MenuItemAdapter;
import com.example.project.Domain.MenuItemDomain;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class MenuItemActivity extends AppCompatActivity {
    private MenuItemAdapter adapter;
    private RecyclerView menuRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        initializeRecyclerViews();
        loadCategoryDataIntoLayout();
    }

    private void initializeRecyclerViews() {
        menuRecyclerView = findViewById(R.id.menuRecyclerView);
        menuRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new MenuItemAdapter(new ArrayList<MenuItemDomain>());
        menuRecyclerView.setAdapter(adapter);
    }


    private void loadCategoryDataIntoLayout() {
        String url = "http://10.0.2.2:5000/products";
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<MenuItemDomain> MenuItemList = new ArrayList<>();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONArray item = response.getJSONArray(i);
                                String name = item.getString(1);
                                String price = item.getString(4);
                                String picture = item.getString(3);
                                MenuItemList.add(new MenuItemDomain(name,price,picture));
                            }

                            adapter.updateData(MenuItemList);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley Error", "Error fetching Items", error);
            }
        });

        queue.add(jsonArrayRequest);
    }
}