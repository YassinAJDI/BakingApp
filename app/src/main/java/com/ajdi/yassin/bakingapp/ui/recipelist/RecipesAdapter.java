package com.ajdi.yassin.bakingapp.ui.recipelist;

import android.view.ViewGroup;

import com.ajdi.yassin.bakingapp.data.local.model.Recipe;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Yassin Ajdi
 * @since 12/11/2018.
 */
public class RecipesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Recipe> mRecipesList;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return RecipeViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Recipe recipe = mRecipesList.get(position);
        ((RecipeViewHolder) holder).bindTo(recipe);
    }

    @Override
    public int getItemCount() {
        return mRecipesList != null ? mRecipesList.size() : 0;
    }

    public void submitList(List<Recipe> recipes) {
        mRecipesList = recipes;
        notifyDataSetChanged();
    }

}
