package com.example.mini_projet;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class afficher_recette extends AppCompatActivity {

    private RecetteAdapter adapter;
    private List<String> recipeNames = new ArrayList<>();
    private Map<String, ?> allRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_recette);


        RecyclerView recyclerView = findViewById(R.id.recette_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        SharedPreferences prefs = getSharedPreferences("Recipes", MODE_PRIVATE);
        allRecipes = (Map<String, ?>) prefs.getAll();


        recipeNames = new ArrayList<>(allRecipes.keySet());


        adapter = new RecetteAdapter((ArrayList<String>) recipeNames, allRecipes);
        recyclerView.setAdapter(adapter);

    }
}
