package com.example.mini_projet;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class modifier_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modifier_recette);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText nom = findViewById(R.id.recette_nom);
        EditText ingred = findViewById(R.id.recette_ingredients);
        EditText etape = findViewById(R.id.recette_etape);
        Button savebutton = findViewById(R.id.save_recipe_button);
        RadioGroup radioGroupCategorie = findViewById(R.id.radioGroupCategorieModifier);

        String recipeName = getIntent().getStringExtra("RECIPE_NAME");
        SharedPreferences prefs = getSharedPreferences("Recipes", MODE_PRIVATE);
        String details = prefs.getString(recipeName, "");
        String[] parts = details.split("#", 3); // Split ingredients and steps by "<"


        nom.setText(recipeName);
        ingred.setText(parts[0]);
        etape.setText(parts[1]);
        String category=details.split("#")[2];

        if (category.equals("Entrée")) {
            radioGroupCategorie.check(R.id.radioEntreeModifier);
        } else if (category.equals("Plat Principal")) {
            radioGroupCategorie.check(R.id.radioPlatPrincipalModifier);
        } else if (category.equals("Dessert")) {
            radioGroupCategorie.check(R.id.radioDessertModifier);
        }

        savebutton.setOnClickListener(v -> {
            String newNom = nom.getText().toString();
            String newIngre = ingred.getText().toString();
            String newEtape = etape.getText().toString();
            String newCategory = "";

            int selectedId = radioGroupCategorie.getCheckedRadioButtonId();
            if (selectedId == R.id.radioEntreeModifier) {
                newCategory = "Entrée";
            } else if (selectedId == R.id.radioPlatPrincipalModifier) {
                newCategory = "Plat Principal";
            } else if (selectedId == R.id.radioDessertModifier) {
                newCategory = "Dessert";
            }

            SharedPreferences preferences = getSharedPreferences("Recipes", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            if (!recipeName.equals(newNom)) {
                editor.remove(recipeName);
            }
            editor.putString(newNom, newIngre + "#" + newEtape+"#"+newCategory);
            editor.apply();

            Toast.makeText(this, "Recette mise à jour!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }


}