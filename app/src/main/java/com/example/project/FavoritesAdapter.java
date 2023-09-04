package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {


    private Context context;

    private List<FavoriteModel> favoriteList;

    public Context getContext() {
        return context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView ivImage;
        ConstraintLayout card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            card = itemView.findViewById(R.id.card);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            ivImage = itemView.findViewById(R.id.ivImage);
        }
    }
    public void setfavoriteList(List<FavoriteModel> favoriteList) {
        this.favoriteList = favoriteList;
        notifyDataSetChanged();
    }

    public FavoritesAdapter(List<FavoriteModel> favoriteList) {
        this.favoriteList = favoriteList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvTitle.setText(favoriteList.get(position).getTitle());
        if(!favoriteList.get(position).getImg().equals(""))
        {
            Picasso.get().load(favoriteList.get(position).getImg()).into(holder.ivImage);
        }

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] temp = favoriteList.get(holder.getAdapterPosition()).getBody1().split("#");
                String bodyStr= "";
                for (String item:
                     temp) {
                    bodyStr = bodyStr + "\n" + item;
                }
                bodyStr = favoriteList.get(holder.getAdapterPosition()).getBody1();

                String bodyStr2= favoriteList.get(holder.getAdapterPosition()).getBody2();


                Intent intent = new Intent(holder.itemView.getContext(), InfoActivity.class);
                intent.putExtra("title", holder.tvTitle.getText().toString());
                intent.putExtra("body", bodyStr);
                intent.putExtra("body2", bodyStr2);
                intent.putExtra("img", favoriteList.get(position).getImg());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return favoriteList.size();
    }
}