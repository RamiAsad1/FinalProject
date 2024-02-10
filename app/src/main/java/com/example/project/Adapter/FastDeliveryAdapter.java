package com.example.project.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.project.Domain.FastedFoodDomain;
import com.example.project.R;
import java.util.ArrayList;

public class FastDeliveryAdapter extends RecyclerView.Adapter<FastDeliveryAdapter.ViewHolder> {
    ArrayList<FastedFoodDomain> fastedFoodDomains;

    public FastDeliveryAdapter(ArrayList<FastedFoodDomain> fastedFoodDomains) {
        this.fastedFoodDomains = fastedFoodDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_fast_food, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(fastedFoodDomains.get(position).getTitle());

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(fastedFoodDomains.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());
        if (drawableResourceId != 0) { // Check if the resource was found
            Glide.with(holder.itemView.getContext())
                    .load(drawableResourceId)
                    .into(holder.pic);
        }
    }

    @Override
    public int getItemCount() {
        return fastedFoodDomains.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView pic;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            pic = itemView.findViewById(R.id.pic);
        }
    }
}
