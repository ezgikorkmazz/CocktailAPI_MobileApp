package com.example.project;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class RCocktailsAdapter extends RecyclerView.Adapter<RCocktailsAdapter.ViewHolder> {


    private Context context;

    public Context getContext() {
        return context;
    }

    private List<RCocktails> cocktailsList;

    public RCocktailsAdapter(List<RCocktails> cocktailsList) {
        this.cocktailsList = cocktailsList;
    }

    public void setCocktailsList(List<RCocktails> cocktailsList) {
        this.cocktailsList=cocktailsList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitle.setText(cocktailsList.get(position).getStrDrink());

        if(cocktailsList.get(position).getStrDrinkThumb()!=null)
        {
            Picasso.get().load(cocktailsList.get(position).getStrDrinkThumb()).into(holder.ivImage);
        }

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String bodyStr = "Drink Type : " + ((cocktailsList.get(holder.getAdapterPosition()).getStrAlcoholic() == null) ? "" : (cocktailsList.get(holder.getAdapterPosition()).getStrAlcoholic() + "\n\n")) +
                        "Ingredients\n" + ((cocktailsList.get(holder.getAdapterPosition()).getStrMeasure1() == null) ? "" : cocktailsList.get(holder.getAdapterPosition()).getStrMeasure1()) + ((cocktailsList.get(holder.getAdapterPosition()).getStrIngredient1() == null) ? "" : (cocktailsList.get(holder.getAdapterPosition()).getStrIngredient1() + "\n")) +
                        ((cocktailsList.get(holder.getAdapterPosition()).getStrMeasure2() == null) ? "" : cocktailsList.get(holder.getAdapterPosition()).getStrMeasure2()) + ((cocktailsList.get(holder.getAdapterPosition()).getStrIngredient2() == null) ? "" : (cocktailsList.get(holder.getAdapterPosition()).getStrIngredient2() + "\n")) +
                        ((cocktailsList.get(holder.getAdapterPosition()).getStrMeasure3() == null) ? "" : cocktailsList.get(holder.getAdapterPosition()).getStrMeasure3()) + ((cocktailsList.get(holder.getAdapterPosition()).getStrIngredient3() == null) ? "" : (cocktailsList.get(holder.getAdapterPosition()).getStrIngredient3() + "\n")) +
                        ((cocktailsList.get(holder.getAdapterPosition()).getStrMeasure4() == null) ? "" : cocktailsList.get(holder.getAdapterPosition()).getStrMeasure4()) + ((cocktailsList.get(holder.getAdapterPosition()).getStrIngredient4() == null) ? "" : (cocktailsList.get(holder.getAdapterPosition()).getStrIngredient4() + "\n")) +
                        ((cocktailsList.get(holder.getAdapterPosition()).getStrMeasure5() == null) ? "" : cocktailsList.get(holder.getAdapterPosition()).getStrMeasure5()) + ((cocktailsList.get(holder.getAdapterPosition()).getStrIngredient5() == null) ? "" : (cocktailsList.get(holder.getAdapterPosition()).getStrIngredient5() + "\n")) +
                        ((cocktailsList.get(holder.getAdapterPosition()).getStrMeasure6() == null) ? "" : cocktailsList.get(holder.getAdapterPosition()).getStrMeasure6()) + ((cocktailsList.get(holder.getAdapterPosition()).getStrIngredient6() == null) ? "" : (cocktailsList.get(holder.getAdapterPosition()).getStrIngredient6() + "\n")) +
                        ((cocktailsList.get(holder.getAdapterPosition()).getStrMeasure7() == null) ? "" : cocktailsList.get(holder.getAdapterPosition()).getStrMeasure7()) + ((cocktailsList.get(holder.getAdapterPosition()).getStrIngredient7() == null) ? "" : (cocktailsList.get(holder.getAdapterPosition()).getStrIngredient7() + "\n")) +
                        ((cocktailsList.get(holder.getAdapterPosition()).getStrMeasure8() == null) ? "" : cocktailsList.get(holder.getAdapterPosition()).getStrMeasure8()) + ((cocktailsList.get(holder.getAdapterPosition()).getStrIngredient8() == null) ? "" : (cocktailsList.get(holder.getAdapterPosition()).getStrIngredient8() + "\n")) +
                        ((cocktailsList.get(holder.getAdapterPosition()).getStrMeasure9() == null) ? "" : cocktailsList.get(holder.getAdapterPosition()).getStrMeasure9()) + ((cocktailsList.get(holder.getAdapterPosition()).getStrIngredient9() == null) ? "" : (cocktailsList.get(holder.getAdapterPosition()).getStrIngredient9() + "\n")) +
                        ((cocktailsList.get(holder.getAdapterPosition()).getStrMeasure10() == null) ? "" : cocktailsList.get(holder.getAdapterPosition()).getStrMeasure10()) + ((cocktailsList.get(holder.getAdapterPosition()).getStrIngredient10() == null) ? "" : (cocktailsList.get(holder.getAdapterPosition()).getStrIngredient10() + "\n")) +
                        ((cocktailsList.get(holder.getAdapterPosition()).getStrMeasure11() == null) ? "" : cocktailsList.get(holder.getAdapterPosition()).getStrMeasure11()) + ((cocktailsList.get(holder.getAdapterPosition()).getStrIngredient11() == null) ? "" : (cocktailsList.get(holder.getAdapterPosition()).getStrIngredient11() + "\n")) +
                        ((cocktailsList.get(holder.getAdapterPosition()).getStrMeasure12() == null) ? "" : cocktailsList.get(holder.getAdapterPosition()).getStrMeasure12()) + ((cocktailsList.get(holder.getAdapterPosition()).getStrIngredient12() == null) ? "" : (cocktailsList.get(holder.getAdapterPosition()).getStrIngredient12() + "\n")) +
                        ((cocktailsList.get(holder.getAdapterPosition()).getStrMeasure13() == null) ? "" : cocktailsList.get(holder.getAdapterPosition()).getStrMeasure13()) + ((cocktailsList.get(holder.getAdapterPosition()).getStrIngredient13() == null) ? "" : (cocktailsList.get(holder.getAdapterPosition()).getStrIngredient13() + "\n")) +
                        ((cocktailsList.get(holder.getAdapterPosition()).getStrMeasure14() == null) ? "" : cocktailsList.get(holder.getAdapterPosition()).getStrMeasure14()) + ((cocktailsList.get(holder.getAdapterPosition()).getStrIngredient14() == null) ? "" : (cocktailsList.get(holder.getAdapterPosition()).getStrIngredient14() + "\n")) +
                        ((cocktailsList.get(holder.getAdapterPosition()).getStrMeasure15() == null) ? "" : cocktailsList.get(holder.getAdapterPosition()).getStrMeasure15()) + ((cocktailsList.get(holder.getAdapterPosition()).getStrIngredient15() == null) ? "" : cocktailsList.get(holder.getAdapterPosition()).getStrIngredient15()) ;

                String bodyStr2= cocktailsList.get(holder.getAdapterPosition()).getStrInstructions();
//                String[] arr = bodyStr.split("null");
//                String temp =  "";
//
//                for (String s :
//                        arr) {
//                    temp = temp+ s;
//                }
//                bodyStr = temp;
                bodyStr = bodyStr + "\n\n" + bodyStr2;
                Intent intent = new Intent(holder.itemView.getContext(), InfoActivity.class);
                intent.putExtra("title", holder.tvTitle.getText().toString());
                intent.putExtra("body", bodyStr);
//                intent.putExtra("body2", bodyStr2);
                intent.putExtra("img", cocktailsList.get(position).getStrDrinkThumb());
                holder.itemView.getContext().startActivity(intent);



            }
        });

    }

    @Override
    public int getItemCount() {
        return cocktailsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

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

}
