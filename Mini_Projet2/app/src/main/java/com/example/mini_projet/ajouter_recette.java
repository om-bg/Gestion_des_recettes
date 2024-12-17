package com.example.mini_projet;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ajouter_recette extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ajouter_recette);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText nameInput = findViewById(R.id.recette_nom);
        EditText ingredientsInput = findViewById(R.id.recette_ingredients);
        EditText stepsInput = findViewById(R.id.recette_etape);
        Button addButton = findViewById(R.id.ajouter);
        RadioGroup radioGroupCategorie = findViewById(R.id.radioGroupCategorie);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameInput.getText().toString();
                String ingredients = ingredientsInput.getText().toString();
                String steps = stepsInput.getText().toString();
                String category = "";

                int selectedId = radioGroupCategorie.getCheckedRadioButtonId();
                if (selectedId == R.id.radioEntree) {
                    category = "Entrée";
                } else if (selectedId == R.id.radioPlatPrincipal) {
                    category = "Plat Principal";
                } else if (selectedId == R.id.radioDessert) {
                    category = "Dessert";
                }

                if (name.isEmpty() || ingredients.isEmpty() || steps.isEmpty() || category.isEmpty()) {
                    Toast.makeText(ajouter_recette.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences prefs = getSharedPreferences("Recipes", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString(name, ingredients + "#" + steps+"#"+category);
                    editor.apply();

                    Toast.makeText(ajouter_recette.this, "Recette ajoutée avec succès!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

}}
