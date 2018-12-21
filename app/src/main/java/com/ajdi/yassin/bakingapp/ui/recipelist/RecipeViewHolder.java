package com.ajdi.yassin.bakingapp.ui.recipelist;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajdi.yassin.bakingapp.R;
import com.ajdi.yassin.bakingapp.data.local.model.Recipe;
import com.ajdi.yassin.bakingapp.databinding.ItemRecipeBinding;
import com.ajdi.yassin.bakingapp.ui.recipedetail.RecipeDetailsActivity;
import com.ajdi.yassin.bakingapp.utils.GlideApp;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Yassin Ajdi
 * @since 12/11/2018.
 */
class RecipeViewHolder extends RecyclerView.ViewHolder {

    private final ItemRecipeBinding binding;

    private final static List<String> images = Arrays.asList(
            "https://cdn.pixabay.com/photo/2018/08/30/10/30/plum-cake-3641849_1280.jpg",
            "https://cdn.pixabay.com/photo/2015/03/17/14/01/chocolate-677762_1280.jpg",
            "https://cdn.pixabay.com/photo/2016/11/29/11/38/blur-1869227_1280.jpg",
            "https://cdn.pixabay.com/photo/2016/08/08/16/20/cheesecake-1578691_1280.jpg");

    public RecipeViewHolder(@NonNull ItemRecipeBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bindTo(final Recipe recipe, int position) {
        binding.setRecipe(recipe);
        GlideApp.with(binding.getRoot().getContext())
                .load(images.get(position))
                .placeholder(R.color.primary_100)
                .into(binding.imageRecipeThumb);

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
