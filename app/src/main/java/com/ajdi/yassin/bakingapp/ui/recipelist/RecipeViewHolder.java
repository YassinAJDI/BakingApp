package com.ajdi.yassin.bakingapp.ui.recipelist;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajdi.yassin.bakingapp.data.local.model.Recipe;
import com.ajdi.yassin.bakingapp.databinding.ItemRecipeBinding;
import com.ajdi.yassin.bakingapp.ui.recipedetail.RecipeDetailsActivity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Yassin Ajdi
 * @since 12/11/2018.
 */
class RecipeViewHolder extends RecyclerView.ViewHolder {

    private final ItemRecipeBinding binding;

    public RecipeViewHolder(@NonNull ItemRecipeBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bindTo(final Recipe recipe) {
        binding.setRecipe(recipe);

        // recipe click event
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RecipeDetailsActivity.class);
                intent.putExtra(RecipeDetailsActivity.EXTRA_RECIPE_DATA, recipe);
                view.getContext().startActivity(intent);
            }
        });

        binding.executePendingBindings();
    }

    public static RecipeViewHolder create(ViewGroup parent) {
        // Inflate
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        // Create the binding
        ItemRecipeBinding binding =
                ItemRecipeBinding.inflate(layoutInflater, parent, false);
        return new RecipeViewHolder(binding);
    }
}
