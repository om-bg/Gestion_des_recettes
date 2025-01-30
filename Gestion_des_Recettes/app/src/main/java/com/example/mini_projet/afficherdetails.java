package com.example.mini_projet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class afficherdetails extends AppCompatActivity {
    private TextView recette_nom, recette_details, recette_details2,cat;
    private Button edit_button, delete_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_afficher_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recette_nom = findViewById(R.id.recette_nom);
        recette_details = findViewById(R.id.recette_details);
        recette_details2 = findViewById(R.id.recette_details2);
        edit_button = findViewById(R.id.edit_button);
        delete_button = findViewById(R.id.delete_button);
        cat=findViewById(R.id.cate);
        cat.setText("");

        String recipeName = getIntent().getStringExtra("RECIPE_NAME");
        String recipeDetails = getIntent().getStringExtra("RECIPE_DETAILS");
        TextView x=findViewById(R.id.title);

        recette_nom.setText(recipeName);
        recette_details.setText(recipeDetails.split("#")[0]);  // Ingredients
        recette_details2.setText(recipeDetails.split("#")[1]);
        cat.setText(recipeDetails.split("#")[2]);

        edit_button.setOnClickListener(v -> {
            Intent editIntent = new Intent(afficherdetails.this, modifier_activity.class);
            editIntent.putExtra("RECIPE_NAME", recipeName);
            editIntent.putExtra("RECIPE_DETAILS", recipeDetails);
            startActivity(editIntent);
        });


        delete_button.setOnClickListener(v -> {
            showDeleteConfirmationDialog(recipeName);




        });
    }
    private void showDeleteConfirmationDialog(String Recipe) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Vous voulez supprimer cette recette ?")
                .setCancelable(false)
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SharedPreferences prefs = getSharedPreferences("Recipes", MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.remove(Recipe);
                        editor.apply();
                        Toast.makeText(afficherdetails.this, "Recette supprimer", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(afficherdetails.this,MainActivity.class);
                        startActivity(i);
                    }
                })
                .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.dismiss();
                    }
                });


        AlertDialog alert = builder.create();
        alert.show();
    }
}