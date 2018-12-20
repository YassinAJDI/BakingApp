package com.ajdi.yassin.bakingapp.ui.recipedetail;

import com.ajdi.yassin.bakingapp.data.local.model.Ingredient;
import com.ajdi.yassin.bakingapp.data.local.model.Recipe;
import com.ajdi.yassin.bakingapp.data.local.model.Step;
import com.ajdi.yassin.bakingapp.utils.SingleLiveEvent;

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

    private MutableLiveData<List<Step>> stepsList = new MutableLiveData<>();

    private MutableLiveData<Step> currentStep = new MutableLiveData<>();

    private final SingleLiveEvent<Integer> openStepDetailEvent = new SingleLiveEvent<>();

    public void init(Recipe recipe) {
        Timber.d("Initializing viewModel");
//        setRecipeLiveData(recipe);
        setIngredients(recipe.getIngredients());
        setSteps(recipe.getSteps());
//        setCurrentStep(0);
    }

    public void setCurrentStep(int position) {
        currentStep.setValue(stepsList.getValue().get(position));
        openStepDetailEvent.setValue(position);
    }

    public LiveData<Step> getCurrentStep() {
        return currentStep;
    }

    private void setSteps(List<Step> steps) {
        stepsList.setValue(steps);
    }

    private void setIngredients(List<Ingredient> ingredients) {
        ingredientsLiveData.setValue(ingredients);
    }

    public LiveData<List<Step>> getStepsList() {
        return stepsList;
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

    public SingleLiveEvent<Integer> getOpenStepDetailEvent() {
        return openStepDetailEvent;
    }
}
