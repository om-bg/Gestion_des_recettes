package com.example.mini_projet;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Map;

public class RecetteAdapter extends RecyclerView.Adapter<RecetteAdapter.RecipeViewHolder> {

    private final ArrayList<String> recipeNames;
    private final Map<String, ?> allRecipes;

    public RecetteAdapter(ArrayList<String> recipeNames, Map<String, ?> allRecipes) {
        this.recipeNames = recipeNames;
        this.allRecipes = allRecipes;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        String recipeName = recipeNames.get(position);
        String recipeDetails = (String) allRecipes.get(recipeName);


        holder.recipeNameTextView.setText(recipeName);


        holder.detailsButton.setOnClickListener(v -> {
            Context context = v.getContext();
            Intent intent = new Intent(context, afficherdetails.class);
            intent.putExtra("RECIPE_NAME", recipeName);
            intent.putExtra("RECIPE_DETAILS", recipeDetails);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return recipeNames.size();
    }



    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        TextView recipeNameTextView;
        Button detailsButton;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeNameTextView = itemView.findViewById(R.id.recipe_nom);
            detailsButton = itemView.findViewById(R.id.details_button);

        }
    }
}
