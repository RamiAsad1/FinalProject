package com.example.project.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.project.Domain.MenuItemDomain;
import com.example.project.R;

import java.util.ArrayList;

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.ViewHolder> {
    private ArrayList<MenuItemDomain> menuItemsDomains;

    public MenuItemAdapter(ArrayList<MenuItemDomain> menuItems) {
        this.menuItemsDomains = menuItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MenuItemDomain menuItem = menuItemsDomains.get(position);
        holder.titleTextView.setText(menuItem.getTitle());
        holder.priceTextView.setText(menuItem.getPrice());

        // Load image using Glide or your preferred image loading library
        Glide.with(holder.itemView.getContext())
                .load(menuItem.getPic())
                .into(holder.picImageView);
    }

    public void updateData(ArrayList<MenuItemDomain> newCategories) {
        menuItemsDomains.clear();
        menuItemsDomains.addAll(newCategories);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return menuItemsDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView priceTextView;
        private ImageView picImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.food_title1);
            priceTextView = itemView.findViewById(R.id.food_price1);
            picImageView = itemView.findViewById(R.id.food_image);
        }
    }
}
