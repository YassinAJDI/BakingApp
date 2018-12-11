package com.ajdi.yassin.bakingapp.ui.list;

import com.ajdi.yassin.bakingapp.data.RecipeRepository;
import com.ajdi.yassin.bakingapp.data.remote.model.Recipe;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author Yassin Ajdi
 * @since 12/11/2018.
 */
public class RecipeListViewModel extends ViewModel {

    private LiveData<List<Recipe>> listLiveData;

    public RecipeListViewModel(RecipeRepository repository) {
        listLiveData = repository.loadAllRecipes();
    }

    public LiveData<List<Recipe>> getListLiveData() {
        return listLiveData;
    }
}
