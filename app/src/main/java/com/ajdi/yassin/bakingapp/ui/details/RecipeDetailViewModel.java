package com.ajdi.yassin.bakingapp.ui.details;

import com.ajdi.yassin.bakingapp.data.model.Ingredient;
import com.ajdi.yassin.bakingapp.data.model.Recipe;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import timber.log.Timber;

/**
 * @author Yassin Ajdi
 * @since 12/12/2018.
 */
public class RecipeDetailViewModel extends ViewModel {

    private MutableLiveData<Recipe> recipeLiveData = new MutableLiveData<>();

    private MutableLiveData<List<Ingredient>> ingredientsLiveData = new MutableLiveData<>();

    public void init(Recipe recipe) {
        Timber.d("Initializing viewModel");
//        setRecipeLiveData(recipe);
        setIngredients(recipe.getIngredients());
    }

    private void setIngredients(List<Ingredient> ingredients) {
        ingredientsLiveData.setValue(ingredients);
    }

    public LiveData<List<Ingredient>> getIngredientsLiveData() {
        return ingredientsLiveData;
    }

    private void setRecipeLiveData(Recipe recipe) {
        recipeLiveData.setValue(recipe);
    }

    public LiveData<Recipe> getRecipeLiveData() {
        return recipeLiveData;
    }
}
