package com.ajdi.yassin.bakingapp.ui.details;

import android.os.Bundle;
import android.widget.TextView;

import com.ajdi.yassin.bakingapp.R;
import com.ajdi.yassin.bakingapp.data.model.Recipe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Yassin Ajdi
 * @since 12/11/2018.
 */
public class RecipeDetailsActivity extends AppCompatActivity {
    public static final String EXTRA_RECIPE_DATA = "extra_recipe";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        Recipe recipe = getIntent().<Recipe>getParcelableExtra(EXTRA_RECIPE_DATA);
        TextView textView = findViewById(R.id.name);
        textView.setText(recipe.getName());
    }
}
