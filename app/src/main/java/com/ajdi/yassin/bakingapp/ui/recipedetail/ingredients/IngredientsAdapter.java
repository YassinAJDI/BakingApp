package com.ajdi.yassin.bakingapp.ui.recipedetail.ingredients;

import android.view.ViewGroup;

import com.ajdi.yassin.bakingapp.data.model.Ingredient;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Yassin Ajdi
 * @since 12/12/2018.
 */
public class IngredientsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Ingredient> mIngredientList;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return IngredientViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Ingredient ingredient = mIngredientList.get(position);
        ((IngredientViewHolder) holder).bindTo(ingredient);
    }

    @Override
    public int getItemCount() {
        return mIngredientList != null ? mIngredientList.size() : 0;
    }

    public void submitList(List<Ingredient> ingredients) {
        mIngredientList = ingredients;
        notifyDataSetChanged();
    }
}
